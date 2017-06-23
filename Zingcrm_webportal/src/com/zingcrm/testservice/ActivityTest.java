//package com.zingcrm.testservice;
//import junit.framework.Assert;
//
//import org.json.JSONException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.zingcrm.service.ActivityService;
//import com.zingcrm.exception.BusinessException;
//import com.zingcrm.forms.ActivityForms;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//	    "file:WebContent/WEB-INF/hibernate-config.xml",
//	    "file:WebContent/WEB-INF/spring-servlet.xml",
//	    "file:WebContent/WEB-INF/mail.xml",
//	    "file:WebContent/WEB-INF/spring-security.xml"})
//public class ActivityTest {
//	
//	@Autowired
//	private ActivityService actService;
//	
//	@Test
//	public void test() throws JSONException {
//		try {
//			String response = actService.orgList("1","1");
//			Assert.assertNotNull(response);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void createActivity() throws JSONException {
//		try {
//			ActivityForms actForms=new ActivityForms();
//			actForms.setUserField1("1");
//			actForms.setUserField2("1");
//			actForms.setAssignedTo("f15a0100-4040-49e5-8570-b9c3aad13da7");
//			actForms.setCreatedBy("f15a0100-4040-49e5-8570-b9c3aad13da7");
//			actForms.setName("11111ss");
//			actForms.setOpportunity(1);
//			actForms.setType("1");
//			actForms.setDesc("1111qq");
//			actForms.setDueDate("2014-04-04");
//			String response = actService.saveActivity(actForms);
//			Assert.assertNotNull(response);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
