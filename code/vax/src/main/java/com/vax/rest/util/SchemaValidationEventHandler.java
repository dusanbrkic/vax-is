package com.vax.rest.util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

public class SchemaValidationEventHandler implements ValidationEventHandler {
	
	public boolean handleEvent(ValidationEvent event) {

		if (event.getSeverity() != ValidationEvent.WARNING) {
			ValidationEventLocator validationEventLocator = event.getLocator();
			System.out.println("ERROR: Line "
					+ validationEventLocator.getLineNumber() + ": Col"
					+ validationEventLocator.getColumnNumber() + ": "
					+ event.getMessage());

			return false;
		} else {
			ValidationEventLocator validationEventLocator = event.getLocator();
			System.out.println("WARNING: Line "
					+ validationEventLocator.getLineNumber() + ": Col"
					+ validationEventLocator.getColumnNumber() + ": "
					+ event.getMessage());

			return true;
		}
	}
}
