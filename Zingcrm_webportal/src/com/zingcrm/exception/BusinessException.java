package com.zingcrm.exception;


@SuppressWarnings("serial")
public class BusinessException extends Exception{
	 
		@SuppressWarnings("unused")
		private String customMsg;
	 
		//getter and setter methods
	 
		public BusinessException(String customMsg) {
			this.customMsg = customMsg;
		}

}