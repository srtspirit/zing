package com.zingcrm.service.impl;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.FeatureDAO;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.FeatureService;

/**
 * @author NeshTech
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    private static Logger log = Logger.getLogger(FeatureServiceImpl.class
            .getName());

    @Autowired
    private FeatureDAO featureDAO;


    @Override
    @Transactional
    public final String getFeatureList(final String strRoleId,
            final String strOrgId) throws BusinessException {
        StringBuffer strData = new StringBuffer();
        StringBuffer strData1 = new StringBuffer();
        StringBuffer strData2 = new StringBuffer();
        StringBuffer strData3 = new StringBuffer();
        int count=0,count1=0,count2=0;
        try {

            List<?> value= featureDAO.getNewFeatureList(strRoleId, strOrgId);
            if(value.size()!=0)
			{
				Iterator<?> iterator = value.iterator();
				strData.append("<div id="+"procontainer_blue"+"><div id="+"pronav_blue"+"><ul id='menu'>");
				while(iterator.hasNext())
				{
					Object[] array=(Object[]) iterator.next();
				
					if(array[5]!=null){ // Forms Addnew and View menu here
						if(array[8].equals("Create") && (Integer)array[15]==2) //Forming Addnew menu
						{
							if(count==0){ // Checks first time entry in addnew menu to start li 
							strData1.append("<li><a href='#'>Add New</a><ul class='sub-menu'>");
							count++;
							}
							if((Integer)array[14]==4){ // checks for last entry in addnew menu to end li
							strData1.append("<li><a href='"+(String) array[2]+"/create' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
							strData1.append("</ul></li>");	
							}
							else{
								strData1.append("<li><a href='"+(String) array[2]+"/create' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
								
							}
						}
						if(array[9]!=null && array[9].equals("View")){ // Forming View menu 
							if(count1==0){
							strData2.append("<li><a href='#'>View</a><ul class='sub-menu'>");
							count1++;
							}
							if((Integer)array[14]==4){ // Checks first time entry in addnew menu to start li 
								strData2.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
								strData2.append("</ul></li>");	
							}
							else{
								strData2.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
							}
						}
					}
					else{
						if((Integer)array[15]==3){// Forming Admin menu
							if(count2==0){// Checks and add first entry in Admin menu
								strData3.append("<li><a href='#'>Admin</a><ul class='sub-menu'>");
								count2++;
								}
							if((Integer)array[1]==2 &&(Integer)array[14]==4){ //Checks last entry in admin menu for superadmin role
								strData3.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
								strData3.append("</ul></li>");	
							}
							else if((Integer)array[1]==3 &&(Integer)array[14]==3){ //Checks last entry in admin menu for salesManager role
								strData3.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
								strData3.append("</ul></li>");	
							}else if((Integer)array[1]==5 &&(Integer)array[14]==1){ //Checks last entry in admin menu for support role
								strData3.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
								strData3.append("</ul></li>");	
							}
							else{
							strData3.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
							}
						}
						else{
						strData.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[4]+"' title='"+(String) array[3]+"'>"+(String) array[3]+"</a></li>");
						}
					}
				
			     }
				if(!strData1.equals("") && !strData2.equals("") && strData2.length()>0){
					strData1.append(strData2);
				}
				if(strData.length()>0){
					if(strData1.length()>0){
					   strData=strData.append(strData1);
					}
					   if(strData3.length()>0){
							strData.append(strData3);
						}
					   strData.append("<div id='hdworkorderid' class='right'></div>");
					   strData.append("</ul>");
				
				}
	          }
           
         
          /*  if(value.size()!=0)
			{
				Iterator<?> iterator = value.iterator();
				strData.append("<div id="+"procontainer_blue"+"><div id="+"pronav_blue"+"><ul id='menu'>");
				while(iterator.hasNext())
				{
					Object[] array=(Object[]) iterator.next();
					
					strData.append("<li><a href='"+(String) array[2]+"' id='"+(String) array[0]+"' title='"+(String) array[1]+"'>"+(String) array[1]+"</a></li>");
						
			     } 
				
	          }
			strData.append("<div id='hdworkorderid' class='right'></div>");
			strData.append("</ul>");*/
        } catch (BusinessException e) {
            log.fatal("FEA_SER_001", e);
            throw new BusinessException(e.getMessage());
        }
        return strData.toString();
    }

    
}
