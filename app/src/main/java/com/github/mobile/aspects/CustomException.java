package com.github.mobile.aspects;

import java.util.ResourceBundle;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 3019146088268246881L;
	private final static String RESOURCE_LOCATION_TEMPLATE = "error_messages";
	private final static String DEFAULT_ERROR = "default_error_name";

	private String messageKey;

	public CustomException(String propertyName, String debugMessage) {
		super(debugMessage);
		this.messageKey = propertyName;
	}

	public CustomException(String propertyName, String debugMessage, Exception ex) {
		super(debugMessage, ex);
		this.messageKey = propertyName;
	}

	public String getFriendlyMessage() {
		ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_LOCATION_TEMPLATE);
		
		if (!resource.containsKey(this.messageKey)) {
			return (String) resource.getObject(DEFAULT_ERROR);
		}
		
		return (String) resource.getObject(this.messageKey);
	}
}
