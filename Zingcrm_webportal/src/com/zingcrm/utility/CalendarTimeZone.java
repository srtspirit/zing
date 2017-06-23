package com.zingcrm.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

/**
 * @author NeshTech
 * 
 */
@Component
public class CalendarTimeZone {

    /**
     * @param strUTCDate
     *            UTC Date Parameter
     * @param strTimeZoneCode
     *            User TimeZone Code
     * @param strTimezoneValue
     *            User TimeZone Value
     * @return UTC DateTime with Day light savings
     */
    public final String getDate(final String strUTCDate,
            final String strTimeZoneCode, final String strTimezoneValue) {

        String strUTCDateDST = "";
        if ((strUTCDate != null) && !strUTCDate.equals("")) {
            Calendar cal = Calendar.getInstance();
            Date dateUTC;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            DateFormat formatUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatUser.setTimeZone(TimeZone.getTimeZone("GMT"
                    + strTimeZoneCode));
            try {
                dateUTC = format.parse(strUTCDate);
                cal.setTime(dateUTC);
                int intDST = 0;
                for (String string : TimeZone.getAvailableIDs(TimeZone
                        .getTimeZone("GMT" + strTimeZoneCode).getRawOffset())) {
                    if (string.contains(strTimezoneValue)) {
                        if (TimeZone.getTimeZone(string)
                                .inDaylightTime(dateUTC)) {
                            intDST = TimeZone.getTimeZone(string)
                                    .getDSTSavings() / (60 * 60 * 1000);
                        }
                        break;
                    }
                }
                cal.add(Calendar.HOUR_OF_DAY, intDST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            strUTCDateDST = formatUser.format(cal.getTime());
        }
        return strUTCDateDST;
    }

    /**
     * @return Current UTC Date Time
     */
    public final Date getCurrentDateTime() {
        Date currentDateTime = null;
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDateTime = dateFormat.parse(dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    public final String getDateCurrentDateTime() {
        String currentDateTime = null;
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDateTime = dateFormat.format(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    /**
     * @param date 
     * @return Current UTC Date Time
     */
    public final Date getStringToDate(String date) {
        Date currentDateTime = null;
        try {
        	if(date!=null && !date.equals(""))
        	{
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	            currentDateTime = dateFormat.parse(date.toString());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    /**
     * @param date 
     * @return Current UTC Date Time
     */
    public final Date getStringToDates(String date) {
        Date currentDateTime = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDateTime = dateFormat.parse(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    
    public final Date getStringToDateTime(String date) {
        Date currentDateTime = null;
        try {
        	if(!date.equals(""))
        	{
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	            currentDateTime = dateFormat.parse(date.toString());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    
    /**
     * @param date 
     * @return Current UTC Date Time
     */
    public final String getDateToString(Date date) {
        String currentDateTime = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDateTime = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    /**
     * @param date 
     * @return Current UTC Date Time
     */
    public final String getDateTimeToString(Date date) {
        String currentDateTime = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDateTime = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }
    
    
  public boolean getFlag(String status) {
		
		if(status instanceof String)
		{
			if(status.length()>1)
			{
				return status=="true" ? true : false ;
			}
			else
			{
				return Integer.parseInt(status) > 0 ? true : false ;
			}
		}
		return false;
	}

    
}
