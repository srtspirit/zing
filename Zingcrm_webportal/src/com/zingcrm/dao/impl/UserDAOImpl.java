package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.UserDAO;
import com.zingcrm.entity.Authority;
import com.zingcrm.entity.ContactInfo;
import com.zingcrm.entity.Role;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.RegistrationForm;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.Encrypt;
import com.zingcrm.utility.PropertiesHolder;

@Repository
public class UserDAOImpl implements UserDAO{

	
    /**
    *
    */
   private static Logger log = Logger.getLogger(UserDAOImpl.class
           .getName());

   /**
    *
    */
   @Autowired
   private SessionFactory sessionFactory;
   
   @Autowired
   private PropertiesHolder properties;
   
   @Autowired
   private CalendarTimeZone calendar;
   
   @Autowired
   private Encrypt encrypt;
   
	@Override
	public List<?> getRep(String orgId,String repId,String activeflag,String roleId) throws BusinessException {
		  try {	       return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getManagerRep (:roleId,:orgId,:status)")
					.setParameter("roleId", repId)
					.setParameter("orgId", orgId)
					.setBoolean("status",calendar.getFlag(activeflag))
					.list());
	        } catch (HibernateException e) {
	            log.fatal("USER_DAO_001", e);
	            throw new BusinessException("UserDAOImpl :: getRep()");
	        }
	}
	
	@Override
	public List<?> getAllRepList(String orgId,String roleId) throws BusinessException {
		  try {	       return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getAllRep (:roleId,:orgId)")
					.setParameter("roleId", roleId)
					.setParameter("orgId", orgId)
					.list());
	        } catch (HibernateException e) {
	            log.fatal("USER_DAO_001", e);
	            throw new BusinessException("UserDAOImpl :: getRep()");
	        }
	}
	
	@Override
	public List<?> getActivityRep(String orgId, String roleId,
			String companyId) throws BusinessException {
		  try {	       return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getActivityManagerRep (:roleId,:orgId,:status,:companyId)")
					.setParameter("roleId", roleId)
					.setParameter("orgId", orgId)
					.setBoolean("status",calendar.getFlag(properties.getProperty("active")))
					.setParameter("companyId", companyId)
					.list());
	        } catch (HibernateException e) {
	            log.fatal("USER_DAO_002", e);
	            throw new BusinessException("UserDAOImpl :: getRep()");
	        }
	}


	@Override
	public List<?> gridView(String orgId, String userName, String sortId,
			String sortDir, String status) throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getUser (:orgId,:userName,:sortId,:sortDir,:status)")
					.setString("orgId", orgId)
					.setString("userName", userName)
					.setString("sortId", sortId)
					.setString("sortDir", sortDir)
					.setString("status", status)
					.list());
		} catch (HibernateException e) {

			log.fatal("USER_DAO_004", e);
			throw new BusinessException(
					"UserDAOImpl ::gridView()");
		}
	}

	@Override
	public List<?> getUserDetails(String userId) throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getUserInfo (:userId)")
					.setString("userId", userId)
					.list());
		} catch (HibernateException e) {

			log.fatal("USER_DAO_005", e);
			throw new BusinessException(
					"UserDAOImpl ::getUserDetails()");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRole(String roleId,String superRole) throws BusinessException {
		 try {
	            return (List<Role>) sessionFactory.getCurrentSession()
	                    .createQuery("from Role where roleId>=:role and roleId!=:superRole")
	                    .setParameter("role", Integer.parseInt(roleId))
	                    .setParameter("superRole", Integer.parseInt(superRole))
	                    .list();
	        } catch (HibernateException e) {
	            log.fatal("ROLE_DAO_001", e);
	            throw new BusinessException("UserDAOImpl :: getRole()");
	        }
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getEditRole(String roleId) throws BusinessException {
		 try {
	            return (List<Role>) sessionFactory.getCurrentSession()
	                    .createQuery("from Role where roleId>=:role")
	                    .setParameter("role", Integer.parseInt(roleId)).list();
	        } catch (HibernateException e) {
	            log.fatal("ROLE_DAO_001", e);
	            throw new BusinessException("UserDAOImpl :: getRole()");
	        }
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getEmailNameValidation(String email,
			 String status) throws BusinessException {
		try {
            return (List<User>) sessionFactory.getCurrentSession()
                    .createQuery("from User where email like :email")
                    .setParameter("email", email).list();
        } catch (HibernateException e) {
            log.fatal("USER_DAO_009", e);
            throw new BusinessException("UserDAOImpl :: getActivityNameValidation()");
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getEditEmailNameValidation(String email,
			String userId, String status)
			throws BusinessException {
		try {
            return (List<User>) sessionFactory.getCurrentSession()
                    .createQuery("from User where Email like :email and UserId!=:userid")
                    .setParameter("email", email).setParameter("userid", userId).list();
        } catch (HibernateException e) {
            log.fatal("USER_DAO_010", e);
            throw new BusinessException("UserDAOImpl :: getEditActivityNameValidation()");
        }
	}
	

	@SuppressWarnings("static-access")
	@Override
	public void saveUser(RegistrationForm regForm, String enabled)
			throws BusinessException {
	try{
	     User userinfo = new User();
         userinfo.setOrgId(""+regForm.getOrg());
         userinfo.setEmail(regForm.getEmail());
         userinfo.setRoleId(""+regForm.getRole());
         userinfo.setLanguageId(properties.getProperty("language") .trim());
         userinfo.setStatus(regForm.getStatus());
         userinfo.setTimeZoneId(regForm.getTimezone());
         userinfo.setCreatedDate(calendar.getCurrentDateTime());
         userinfo.setPassword(encrypt.encryptPwd(regForm.getPassword()));
         userinfo.setEnable(calendar.getFlag(enabled));
         sessionFactory.getCurrentSession().save(userinfo);
         
         ContactInfo usrcontinfo = new ContactInfo();
         usrcontinfo.setUserId(userinfo.getUserid());
         usrcontinfo.setUserFirstName(regForm.getFirstName());
         usrcontinfo.setUserLastName(regForm.getLastName());
         usrcontinfo.setUserPhoneCountryCode(regForm.getPhoneCode());
         usrcontinfo.setUserPhoneNumber(regForm.getPhone());
         sessionFactory.getCurrentSession().save(usrcontinfo);
         
         Authority authority = new Authority();
         authority.setUserId(userinfo.getUserid());
         authority.setAuthority(properties.getProperty("authoritymember")
         .trim()); sessionFactory.getCurrentSession().save(authority);
	 } catch (HibernateException e) {
        log.fatal("USER_DAO_011", e);
        throw new BusinessException("UserDAOImpl :: saveUser()");
    }
		
	}

	@Override
	public void deleteUser(String userId, String status, String disabled)
			throws BusinessException {
		try {
			User user=(User) sessionFactory.getCurrentSession().createQuery("from User where UserId = :id").setParameter("id", userId).list().get(0);
			user.setStatus(calendar.getFlag(status));
			user.setEnable(calendar.getFlag(disabled));
			sessionFactory.getCurrentSession().update(user);
			} catch (HibernateException e) {

				log.fatal("USER_DAO_012", e);
				throw new BusinessException("UserDAOImpl ::deleteUser()");
			}
	}

	@Override
	public void editUser(RegistrationForm regForm) throws BusinessException {
		try{
			User userinfo=(User) sessionFactory.getCurrentSession().createQuery("from User where UserId = :id").setParameter("id", regForm.getUserId()).list().get(0);
	         userinfo.setEmail(regForm.getEmail());
	         userinfo.setRoleId(""+regForm.getRole());
	         userinfo.setTimeZoneId(regForm.getTimezone());
	         sessionFactory.getCurrentSession().update(userinfo);
	         
	         ContactInfo usrcontinfo = new ContactInfo();
	         usrcontinfo.setId(regForm.getContactId());
	         usrcontinfo.setUserId(userinfo.getUserid());
	         usrcontinfo.setUserFirstName(regForm.getFirstName());
	         usrcontinfo.setUserLastName(regForm.getLastName());
	         usrcontinfo.setUserPhoneCountryCode(regForm.getPhoneCode());
	         usrcontinfo.setUserPhoneNumber(regForm.getPhone());
	         sessionFactory.getCurrentSession().update(usrcontinfo);
	         
		 } catch (HibernateException e) {
	        log.fatal("USER_DAO_013", e);
	        throw new BusinessException("UserDAOImpl :: editUser()");
	    }
		
	}

	@Override
	public List<?> getAssignedRepList(String orgId,String userid,String companyid) throws BusinessException {
		  try {	 
			  
			 /* try {	       return ((List<?>) sessionFactory.getCurrentSession()
						.createSQLQuery("call getBPRep (:roleId,:orgId,:status,:companyid)")
						.setParameter("roleId", repId)
						.setParameter("orgId", orgId)
						.setBoolean("status",calendar.getFlag(activeflag))
						.list());
		        } catch (HibernateException e) {
		            log.fatal("USER_DAO_001", e);
		            throw new BusinessException("UserDAOImpl :: getRep()");
		        }*/
			  
			 return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("Select cnf.UserID,CONCAT(cnf.FirstName,' ',cnf.LastName) 'LastName',role.RoleName from userinfo usr join contactinfo cnf on usr.UserID=cnf.UserID join roleinfo as role on usr.RoleID=role.RoleID where usr.UserID in (select AssignedRep from businesspartner where BPID=:companyid and DeleteFlag=:statusid and OrgID=:orgid)")
					.setParameter("companyid", companyid)
					.setParameter("statusid", calendar.getFlag(properties.getProperty("active")))
					.setParameter("orgid", orgId)
					.list());
	        } catch (HibernateException e) {
	            log.fatal("USER_DAO_014", e);
	            throw new BusinessException("UserDAOImpl :: getAssignedRepList()");
	        }
	}
	
	@Override
	public List<?> getnewActivityAssignRepList(String orgId,String userid,String companyid) throws BusinessException {
		  try {	      
			  return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("Select cnf.UserID,CONCAT(cnf.FirstName,' ',cnf.LastName) 'LastName',role.RoleName from userinfo usr join contactinfo cnf on usr.UserID=cnf.UserID join roleinfo as role on usr.RoleID=role.RoleID where usr.RoleID>4 and usr.OrgID=:orgid")
					.setParameter("orgid", orgId)
					.list());
	        } catch (HibernateException e) {
	            log.fatal("USER_DAO_015", e);
	            throw new BusinessException("UserDAOImpl :: getnewActivityAssignRepList()");
	        }
	}
	

}
