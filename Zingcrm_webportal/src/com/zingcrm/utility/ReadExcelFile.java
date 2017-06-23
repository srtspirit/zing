package com.zingcrm.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.BusinessPartnerContact;
import com.zingcrm.forms.LeadForms;

   
    @Component
    public class ReadExcelFile {
        
        @Autowired
        CalendarTimeZone timezone;
        
        @SuppressWarnings("rawtypes")
		ArrayList array=new ArrayList();
        
        @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
        public ArrayList SetExcelData(Map<String,Integer> sourcemap,
				Map<String, String> repmap, String fileName, String strUserId, String orgId, Map<String, Integer> countrymap, Map<String, Integer> statemap, Map<String, Boolean> flagmap) throws Exception {
            array.clear();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList list=new ArrayList();
            List<BusinessPartner> leadList=new ArrayList<BusinessPartner>();
            List<BusinessPartnerContact> leadContactList=new ArrayList<BusinessPartnerContact>();
            List<LeadForms> leadbean=new ArrayList<LeadForms>();
            List<?> errMsg = new ArrayList<BusinessPartner>();
            StringBuffer sb=new StringBuffer();
            try {
            	BusinessPartner lead = null;
                BusinessPartnerContact leadContact=null;
                LeadForms leadform=null;
                FileInputStream file = new FileInputStream(new File(fileName));
                //Create Workbook instance holding reference to .xlsx file
                HSSFWorkbook workbook = new HSSFWorkbook(file);
                int count=0;
                //Get first/desired sheet from the workbook
                HSSFSheet sheet = workbook.getSheetAt(0);
                //Iterate through each rows one by one
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext())
                {
                    lead = new BusinessPartner();
                    leadContact = new BusinessPartnerContact();
                    leadform=new LeadForms();
                    Row row = rowIterator.next();
                    
                   if(row.getRowNum()!=0)
                   {
                        if(count==0){
                            //This past to skip the headers.Assuming the first row is always headings
                            count++;
                            continue;
                         }    
                        //For each row, iterate through all the columns
                        Iterator<Cell> cellIterator = row.cellIterator();
                          while (cellIterator.hasNext())
                        {
                                Cell cell = cellIterator.next();
                                switch(cell.getColumnIndex())
                                 {
                               		
                                     case 0:    //Date
                                     {
                                    	 if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setLeadDate(cell.getDateCellValue());
                                         }
                                         else if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setLeadDate(formatter.parse(cell.getStringCellValue()));
                                         }
                                         else
                                         {
                                        	 lead.setLeadDate(null);
                                         }
                                         break;
                                     }
                                     case 1: //Source
                                     {
                                         if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                             if(sourcemap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setSource(sourcemap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setSource(0);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setSource(0);
                                         }
                                         leadform.setSourceName(cell.getStringCellValue());
                                         break;
                                     }
                                     case 2: //Company Name
                                     {
                                         if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setName(cell.getStringCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setName(null);
                                         }
                                         break;
                                     }
                                     case 3: //Rep Name
                                     {

                                         if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                                             if(repmap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setAssignedRep(repmap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setAssignedRep(null);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setAssignedRep(null);
                                         }
                                         leadform.setRepName(cell.getStringCellValue());
                                         break;
                                     }
                                     case 4: //Address1
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setAddress(cell.getStringCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setAddress(null);
                                         }
                                         break;
                                     }
                                      
                                     case 5: //Address2
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setAddress2(cell.getStringCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setAddress2(null);
                                         }
                                         break;
                                     }
                                     case 6: //Country
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                                             if(countrymap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setCountry(countrymap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setCountry(0);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setCountry(0);
                                         }
                                         leadform.setCountryName(cell.getStringCellValue());
                                         break;
                                     }
                                     case 7: //State
                                     {
                                    	 
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                                             if(statemap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setState(statemap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setState(0);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setState(0);
                                         }
                                         leadform.setStateName(cell.getStringCellValue());
                                         break;
                                     }
                                     case 8: //City
                                     {
                                    	/* if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setCity(Integer.parseInt(cell.getStringCellValue()));
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setCity((int) cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setCity(0);
                                         }*/
                                    	  leadform.setCityName(cell.getStringCellValue());
                                         break;
                                     }
                                   
                                     case 9: //Zip Code
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setZipCode(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setZipCode(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setZipCode(null);
                                         }
                                         break;
                                     }
                                     
                                     case 10: //Fax
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setFax(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setFax(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setFax(null);
                                         }
                                         break;
                                     }
                                     case 11: //Website
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                        	 lead.setWebsite(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setWebsite(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setWebsite(null);
                                         }
                                         break;
                                     }
                                     case 12: //Primary Contact name
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryFirstName(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryFirstName(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryFirstName(null);
                                         }
                                         break;
                                     }
                                     case 13: //Primary Last name
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryLastName(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryLastName(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryLastName(null);
                                         }
                                         break;
                                     }
                                     case 14: //Primary PhoneNumber
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryPhone(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryPhone(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryPhone(null);
                                         }
                                         break;
                                     }
                                     case 15: //Primary MobileNumber
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryMobile(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryMobile(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryMobile(null);
                                         }
                                         break;
                                     }
                                     case 16: //Primary Email
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryEmail(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryEmail(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryEmail(null);
                                         }
                                         break;
                                     }
                                     case 17: //Primary Dept
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryDepartment(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setPrimaryDepartment(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setPrimaryDepartment(null);
                                         }
                                         break;
                                     }
                                     
                                     case 18: //Secondary Contact name
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryFirstName(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryFirstName(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryFirstName(null);
                                         }
                                         break;
                                     }
                                     
                                     case 19: //Secondary Contact Last name
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryLastName(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryLastName(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryLastName(null);
                                         }
                                         break;
                                     }
                                     case 20: //Secondary PhoneNumber
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryPhone(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryPhone(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryPhone(null);
                                         }
                                         break;
                                     }
                                     case 21: //Secondary MobileNumber
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryMobile(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryMobile(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryMobile(null);
                                         }
                                         break;
                                     }
                                     case 22: //Secondary Email
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryEmail(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryEmail(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryEmail(null);
                                         }
                                         break;
                                     }
                                     case 23: //Secondary Dept
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryDepartment(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 leadContact.setSecondaryDepartment(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 leadContact.setSecondaryDepartment(null);                                         }
                                         break;
                                     }
                                     case 24: //Customer Flag
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                                             if(flagmap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setCustomFlag(flagmap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setCustomFlag(false);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setCustomFlag(false);
                                         }
                                         leadform.setCustomFlagName(cell.getStringCellValue());
                                         break;
                                     }
                                     case 25: //Active Flag
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                                             if(flagmap.get(cell.getStringCellValue().toLowerCase())!=null)
                                             {
                                            	 lead.setPrivateFlag(flagmap.get(cell.getStringCellValue().trim().toLowerCase()));
                                             }
                                             else
                                             {
                                            	 lead.setPrivateFlag(false);
                                             }
                                         }
                                         else
                                         {
                                        	 lead.setPrivateFlag(false);
                                         }
                                         leadform.setPrivateFlag(cell.getStringCellValue());
                                         break;
                                         
                                     }
                                     case 26: //Coordinate lat
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 lead.setLatitude(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setLatitude(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setLatitude(null);                                         }
                                         break;
                                     }
                                     case 27: //Coordinate long
                                     {
                                    	 if(Cell.CELL_TYPE_STRING==cell.getCellType())
                                         {
                                    		 lead.setLongitude(cell.getStringCellValue());
                                         }
                                    	 else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType())
                                         {
                                    		 lead.setLongitude(""+cell.getNumericCellValue());
                                         }
                                         else
                                         {
                                        	 lead.setLongitude(null);                                         }
                                         break;
                                     }
                                     
                                 }
                            }
                          
                          if(lead.getLeadDate()!=null || lead.getSource()!=0 || lead.getName()!=null || lead.getAssignedRep() !=null
                                  || lead.getAddress()!=null || lead.getCountry()!=0 || lead.getState()!=0
                                     || lead.getZipCode()!=null || leadContact.getPrimaryFirstName()!=null ||leadContact.getPrimaryMobile()!=null
                                  ||leadContact.getPrimaryPhone()!=null || leadContact.getPrimaryEmail()!=null || leadContact.getPrimaryDepartment()!=null
                                  || leadContact.getSecondaryFirstName()!=null ||leadContact.getSecondaryMobile()!=null
                                  ||leadContact.getSecondaryPhone()!=null || leadContact.getSecondaryEmail()!=null || leadContact.getSecondaryDepartment()!=null){
                          
                          lead.setAccountId(Integer.parseInt(orgId));
                          lead.setCreatedBy(strUserId);
                          lead.setCreatedDate(timezone.getCurrentDateTime());
                          leadList.add(lead);
                          leadContactList.add(leadContact);
                          leadbean.add(leadform);
                          }
                   }
                   
                    count++;
                    file.close();
                    list.add(leadList);
                    list.add(leadContactList);
                    list.add(leadbean);
                }
            }
            catch (Exception e)
            {
              
            }
          //Create a blank sheet
            return list;
        }

		@SuppressWarnings("rawtypes")
		public ArrayList residentEntityValidation(BusinessPartner leads,
				BusinessPartnerContact leadContact, LeadForms bean, int i, String strData, String strCityData) {
			  ArrayList list = null;
	            //TODO 
	            String[] leadArray = null,emptyArray={"","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	            leadArray=new String[28];
	            leadArray[0]=(leads.getLeadDate()==null)?"Date ":"";
	            leadArray[1]=(leads.getSource()==0)?"Source ":"";
	            leadArray[2]=(leads.getName()==null)?"Name ":"";
	            if(strData!="")
	            {
	            	leadArray[2]+="Duplicate  Name";
	            }
	            
	            leadArray[3]=(leads.getAssignedRep()==null)?"Assigned Rep ":"";
	            leadArray[4]="";
	            //leadArray[4]=(leads.getAddress()==null)?"Address1 ":"";
	          //  leadArray[5]=(res.getSectionId()==0)?"Section ":"";
	            leadArray[5]=(leads.getAddress2()==null)?"":"";
	            leadArray[6]=(leads.getCountry()==0)?"Country":"";
	            leadArray[7]=(leads.getState()==0)?"Province ":"";
	            if(strCityData!="")
	            {
	            	leadArray[8]+="City Name is not avaliable";
	            }
	            leadArray[8]=(leads.getCity()==0)?"City ":"";
	           
	            leadArray[9]=(leads.getZipCode()==null)?"Zipcode ":"";
	            leadArray[10]="";
	            leadArray[11]="";
	            leadArray[12]="";
	            leadArray[13]="";
	            leadArray[14]="";
	            leadArray[15]="";
	            
	            leadArray[16]="";
	            leadArray[17]="";
	            leadArray[18]="";
	            leadArray[19]="";
	            leadArray[20]="";
	            leadArray[21]="";
	            leadArray[22]="";
	            leadArray[23]="";
	            leadArray[24]="";
	            leadArray[25]="";
	            leadArray[26]="";
	            leadArray[27]="";
	            /*leadArray[10]=(leadContact.getPrimaryName()==null)?"Primary Name ":"";
	            leadArray[11]=(leadContact.getPrimaryPhone()==null)?"Primary Phone ":"";
	            if(formatTeleNumber(leadContact.getPrimaryPhone()).equals("(000)000-0000"))
	            {
	            	 leadArray[11]+="Primary Phone is invalid";
	            }*/
	            /*leadArray[12]=(leadContact.getPrimaryMobile()==null)?"Primary Mobile ":"";
	            if(formatTeleNumber(leadContact.getPrimaryMobile()).equals("(000)000-0000"))
	            {
	            	 leadArray[12]+="Primary Mobile is invalid";
	            }*/
	           /* leadArray[13]=(leadContact.getPrimaryEmail()==null)?"Primary Email ":"";
	            if(!emailValidation(leadContact.getPrimaryEmail()))
        		{
	            	 leadArray[13]+="Primary Email is invalid";
        		}
	            */
	          //  leadArray[14]=(leadContact.getPrimaryDepartment()==null)?"Primary Dept ":"";
	          /*  leadArray[15]=(leadContact.getSecondaryName()==null)?"Secondary Name ":"";
	            leadArray[16]=(leadContact.getSecondaryPhone()==null)?"Secondary Phone ":"";
	            if(formatTeleNumber(leadContact.getSecondaryPhone()).equals("(000)000-0000"))
	            {
	            	 leadArray[16]+="Secondary Phone is invalid";
	            }
	            leadArray[17]=(leadContact.getSecondaryMobile()==null)?"Secondary Mobile ":"";
	            if(formatTeleNumber(leadContact.getSecondaryMobile()).equals("(000)000-0000"))
	            {
	            	 leadArray[17]+="Secondary Mobile is invalid";
	            }
	            
	            leadArray[18]=(leadContact.getSecondaryEmail()==null)?"Secondary Email ":"";
	            if(!emailValidation(leadContact.getSecondaryEmail()))
        		{
	            	 leadArray[18]+="SecondaryEmail is invalid";
        		}
	            leadArray[19]=(leadContact.getSecondaryDepartment()==null)?"Secondary Dept ":"";*/
	            
	            if(Arrays.deepEquals( leadArray, emptyArray))
	            {
	                //dont do anything
	            }
	            else
	            {
	                list=errorLeadLog(leadArray,leads,i,bean,leadContact);
	            }
	            return list;
		}

		@SuppressWarnings("unchecked")
		private ArrayList<?> errorLeadLog(String[] leadArray, BusinessPartner leads, int i,
				LeadForms bean,BusinessPartnerContact leadContact) {
			   CalendarTimeZone timezone=new  CalendarTimeZone();
	            String[] tempArrayString=new String[30];
	            String sb = "";
	            
	            if(leads.getLeadDate()!=null)
	            {
	            	tempArrayString[0]=timezone.getDateToString(leads.getLeadDate());
	            }
	            tempArrayString[1]=bean.getSourceName();
	            tempArrayString[2]=leads.getName();
	            tempArrayString[3]=bean.getRepName();
	            tempArrayString[4]=leads.getAddress();
	            tempArrayString[5]=leads.getAddress2();
	            tempArrayString[6]=bean.getCountryName();
	            tempArrayString[7]=bean.getStateName();
	            tempArrayString[8]=bean.getCityName();
	            tempArrayString[9]=leads.getZipCode();
	            tempArrayString[10]=leads.getFax();
	            tempArrayString[11]=leads.getWebsite();
	            tempArrayString[12]=leadContact.getPrimaryFirstName();
	            tempArrayString[13]=leadContact.getPrimaryLastName();
	            tempArrayString[14]=leadContact.getPrimaryPhone();
	            tempArrayString[15]=leadContact.getPrimaryMobile();
	            tempArrayString[16]=leadContact.getPrimaryEmail();
	            tempArrayString[17]=leadContact.getPrimaryDepartment();
	            tempArrayString[18]=leadContact.getSecondaryFirstName();
	            tempArrayString[19]=leadContact.getSecondaryLastName();
	            tempArrayString[20]=leadContact.getSecondaryPhone();
	            tempArrayString[21]=leadContact.getSecondaryMobile();
	            tempArrayString[22]=leadContact.getSecondaryEmail();
	            tempArrayString[23]=leadContact.getSecondaryDepartment();
	            tempArrayString[24]=bean.getCustomFlagName();
	            tempArrayString[25]=bean.getPrivateFlag();
	            tempArrayString[26]=leads.getLatitude();
	            tempArrayString[27]=leads.getLongitude();
	            
	            for(int k=0;k<leadArray.length;k++)
	            {
	                if(!leadArray[k].equals(""))
	                {
	                    sb+=leadArray[k]+",";
	                }
	            }
	            if(sb!="")
	            {
	                sb=sb.substring(0,sb.length()-1);
	                tempArrayString[28]=sb;
	                tempArrayString[29]="Not Imported";
	            }
	           
	             array.add(tempArrayString);
	             return array;
		}

		public Object appendErrorsToFile(ArrayList<?> errListLog, String strPath,
				int successfile) {
			try{
	              int count=4;
	              //Blank workbook
	              FileInputStream file = new FileInputStream(new File(strPath));
	              //Create Workbook instance holding reference to .xlsx file
	              HSSFWorkbook workbook = new HSSFWorkbook(file);
	              //Create a blank sheet
	              HSSFSheet sheet = workbook.createSheet("Imported file with errorlog");
	              
	              Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	              data.put(1,  new Object[] {"Summary"});
	              if(successfile>1)
	              {
	                  data.put(2, new Object[] {successfile+" Residents Imported successfully"});
	              }
	              else
	              {
	                  data.put(2, new Object[] {successfile+" Resident Imported successfully"});
	              }
	              
	              data.put(3, new Object[] {""});
	              
	            //Iterate over data and write to sheet
	              Set<Integer> keyset = data.keySet();
	              int rownum = 0;
	              for (Integer key : keyset)
	              {
	                  Row row = sheet.createRow(rownum++);
	                  Object [] objArr = data.get(key);
	                  int cellnum = 0;
	                  for (Object obj : objArr)
	                  {
	                     Cell cell = row.createCell(cellnum++);
	                     if(obj instanceof String)
	                          cell.setCellValue((String)obj);
	                      else if(obj instanceof Integer)
	                          cell.setCellValue((Integer)obj);
	                  }
	              }
	              
	              
	              data.put(++count,  new Object[] {"Date","Source", "Business Partner", "Assigned Rep", "Address 1","Address 2","Country"
		                    ,"Province","City","Zipcode","Fax","Website","Primary Contact name","Primary Last Name","Primary Phone","Primary Mobile","Primary Email","Primary Dept","Secondary Contact name","Secondary Last Name","Secondary Phone","Secondary Mobile","Secondary Email","Secondary Dept","Customer Flag","Private Flag","Latitude","Longitude","Error","Status"});
	               List<?> list =errListLog;
	                  if (list.size() != 0) 
	                  {
	                      Iterator<?> ir = list.iterator();
	                      while (ir.hasNext()) {
	                          String[] array = (String[]) ir.next();
	                          data.put(++count, array);
	                      }
	                    //Iterate over data and write to sheet
	                     keyset = data.keySet();
	                        rownum = 0;
	                      for (Integer key : keyset)
	                      {
	                          Row row = sheet.createRow(rownum++);
	                          Object [] objArr = data.get(key);
	                          int cellnum = 0;
	                          for (Object obj : objArr)
	                          {
	                             Cell cell = row.createCell(cellnum++);
	                             if(obj instanceof String)
	                                  cell.setCellValue((String)obj);
	                              else if(obj instanceof Integer)
	                                  cell.setCellValue((Integer)obj);
	                          }
	                      }
	                      
	                      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                      workbook.write(baos);
	                      return new ByteArrayInputStream(baos.toByteArray());

	                  }
	          }
	          catch(Exception e)
	          {
	              
	          }
	        return null;
		}
		
		@SuppressWarnings("unused")
		private Boolean emailValidation(String email) {
			 try {
		                String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		                Boolean b = email.matches(emailreg);
		                 return b;
		        } catch (Exception e) {

		            e.printStackTrace();
		            System.out.println(e.getMessage());
		        }
			return null;
			
		}
		
		
		String formatTeleNumber(String s) 
		{
		    StringBuffer number = new StringBuffer();

		    if (s.length() == 0) {

		        return "(000)000-0000";
		    }

		    // Strip all non-numbers
		    for (int i = 0; i < s.length(); i++) {

		        if (Character.isDigit(s.charAt(i))) {
		           number.append(s.charAt(i));
		        }
		    }

		    // Pad with 0s
		    for (int i = number.length(); i < 10; i++) {

		        number.insert(0, '0');
		    }

		    // Format telephone number to (###)###-####
		    number = new StringBuffer(number.substring(number.length() - 10));
		    number.insert(0, '(');
		    number.insert(4, ')');
		    number.insert(8, '-');

		    return number.toString();
		}
 }
