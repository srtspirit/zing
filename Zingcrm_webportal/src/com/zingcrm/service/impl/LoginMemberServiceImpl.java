package com.zingcrm.service.impl;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.LoginMemberDAO;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LoginMember;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.service.ActivityService;
import com.zingcrm.service.FeatureService;
import com.zingcrm.service.LoginMemberService;
import com.zingcrm.utility.PropertiesHolder;


@Service
public class LoginMemberServiceImpl implements LoginMemberService{

	private static Logger LOG=Logger.getLogger(LoginMemberServiceImpl.class.getName());
	
	@Autowired
	LoginMemberDAO loginMemberDAO;
	
	@Autowired
	PropertiesHolder properties;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	ActivityService activityService;
	
	private static JAXBContext JAXBCONTEXT;
	private static Marshaller MARSHALLER;

	static {
		try {
			JAXBCONTEXT = JAXBContext.newInstance(GridRows.class);
			MARSHALLER = JAXBCONTEXT.createMarshaller();
			MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	@Transactional
	public LoginMember getLogin() throws BusinessException
	{
		LoginMember member=new LoginMember();
		try
		{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String strAccountId=authentication.getName();
		
			List<?> list = loginMemberDAO.readLoginMember(strAccountId);
			if(list.size()!=0)
			{
				Iterator<?> iterator=list.iterator();
				Object[] array=(Object[]) iterator.next();
				member.setUserId( ""+array[0]);
				member.setRoleId((Integer) array[2]);
				member.setFirstName((String) array[3]);
				member.setLastName((String) array[4]);
				member.setLandingPage((String) array[5]);
				member.setOrgId(array[6].toString());
			}
				
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			LOG.fatal( "LOGIN_SER_001",e);
			throw new BusinessException(e.getMessage());
		}
		return member;
	}

	@Override
	@Transactional
	public String loginMember() throws BusinessException
	{
		String strData="";
		try
		{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String strAccountId=authentication.getName();
			
			List<?> list = loginMemberDAO.readLoginMember(strAccountId);
			if(list.size()!=0)
			{
				strData="<rows>";
				Iterator<?> iterator=list.iterator();
				Object[] array=(Object[]) iterator.next();
				LoginMember member=new LoginMember();
				member.setUserId(""+array[0]);
				member.setRoleId((Integer) array[2]);
				member.setFirstName((String) array[3]);
				member.setLastName((String) array[4]);
				member.setPage((String) array[5]);
				strData+="<row><cell id=\"userid\">"+member.getUserId()+"</cell><cell id=\"firstlast\">"+member.getFirstName() + " " + member.getLastName()+"</cell><cell id=\"email\">"+strAccountId+"</cell><cell id=\"roleid\">"+member.getRoleId()+"</cell><cell id=\"merchantid\">"+member.getOrgId()+"</cell><row>";
				strData+="</rows>";
			}
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			LOG.fatal( "LOGIN_SER_001",e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	
	}

	@Override
	@Transactional
	public String getMenu(int roleId,String orgId) throws BusinessException {
		try{
			return featureService.getFeatureList(""+roleId, orgId);
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			LOG.fatal( "LOGIN_SER_002",e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String verifyUser(String strEmail, String strPassword)
			throws BusinessException {
		String strResult="Not Exist";
		JSONObject jsonResult= new JSONObject();
		try{
			List<User> value = loginMemberDAO.verifyUser(strEmail, strPassword);
			if(value!=null)
			{
				Iterator<User> i = value.iterator();
				if(i.hasNext()) {
					User user=i.next();
					strResult=user.getUserid();
					
					try {
						jsonResult.put("userId", strResult);
						jsonResult.put("orgId",user.getOrgId());
						jsonResult.put("roleId",user.getRoleId());
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			} 
			if(strResult!=null && strResult.equalsIgnoreCase("Not Exist")){
				try {
					jsonResult.put("message", "failed");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					jsonResult.put("message", "success");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (BusinessException e) {
			LOG.error( "INDEX_SER_001",e);
			throw new BusinessException(e.getMessage());
		}
		return jsonResult.toString();
	}

	@Override
	@Transactional
	public String getChartData(String roleId,String userId,String orgId) throws BusinessException {
		try{
			return activityService.getChartData(roleId,userId,orgId);
		}
		catch (BusinessException e) {
			LOG.error( "INDEX_SER_002",e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getAllTasks(String sidx, String sord, String strRoleId,
			String orgId, int intPage, int intLimit, String status,String userId)
			throws BusinessException {
		String xml = "";

		try {
			
			List<?> mList = activityService.getAllTasks(strRoleId,userId,orgId,status,sidx,sord);

			OutputStream os = new ByteArrayOutputStream();

			int intStart = 0, intCount = 0, totalPages = 0, k = 0;

			List<GridRow> recordList = new ArrayList<GridRow>();
			if (mList != null) {
				intCount = mList.size();
				if (intCount > 0) {
					totalPages = (int) Math.ceil(Double.parseDouble(""
							+ intCount)
							/ Double.parseDouble("" + intLimit));
				} else if (intCount == 0) {
					totalPages = 0;
					intLimit = 0;
				}

				if (intPage > totalPages)
					intPage = totalPages;

				if (intPage == 1) {
					k = intPage;
				} else {
					intStart = intLimit * intPage - intLimit;
					k = intStart + 1;
				}

				int kk = 1;

				for (int i = intStart; i < mList.size()
						&& k <= intStart + intLimit; i++, k++) {
					Object[] array = (Object[]) mList.get(i);
					List<Object> columnList = new ArrayList<Object>();
					columnList.add(StringEscapeUtils.escapeHtml(array[6].toString())); // business Parnter
					columnList.add(StringEscapeUtils.escapeHtml(array[5].toString())); // opportunity
					columnList.add(StringEscapeUtils.escapeHtml(array[0].toString())); // Activity
					columnList.add(array[1]); // Type
					columnList.add(array[3]); // Due date
					columnList.add(array[2]); // Assigned To
					columnList.add(array[7]); // Id
					columnList.add(array[8]); // activityStatus
					
	                    
					GridRow row = new GridRow();
					row.setId(kk++);
					row.setGridCell(columnList);
					recordList.add(row);
				}

				GridRows gridRows = new GridRows();
				gridRows.setPage(intPage);
				gridRows.setRecords(intCount);
				gridRows.setTotal(totalPages);
				gridRows.setGridRow(recordList);

				MARSHALLER = JAXBCONTEXT.createMarshaller();
				MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				MARSHALLER.marshal(gridRows, os);
				xml = os.toString();
				os.close();
			}
		} catch (JAXBException e) {
			LOG.fatal("INDEX_SER_003", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

}
