/*****************************************************************************************
 * Copyright (c) 2004 IBM Corporation and others. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Common Public
 * License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ****************************************************************************************/

package org.eclipse.gef.examples.text.model;

import org.eclipse.jface.util.Assert;

/**
 * @since 3.1
 */
public class TextRun extends ModelElement {

private String text;

/**
 * @since 3.1
 */
public TextRun(String text) {
	this.text = text;
}

/**
 * Divide this Run into two runs at the given offset.  The second run is return.
 * @since 3.1
 * @param offset where to divide
 * @return the second half
 */
public TextRun subdivideRun(int offset) {
	String remainder = removeRange(offset, size() - offset);
	if (this instanceof ImportStatement)
		return new ImportStatement(remainder);
	return new TextRun(remainder);
}

public String getText() {
	return text;
}

public void insertText(String someText, int offset) {
	this.text = text.substring(0, offset) + someText
			+ text.substring(offset, text.length());
	firePropertyChange("text", null, text);
}

public String removeRange(int offset, int length) {
	Assert.isTrue(offset <= text.length());
	Assert.isTrue(offset + length <= text.length());
	String result = text.substring(offset, offset + length);
	text = text.substring(0, offset) + text.substring(offset + length);
	firePropertyChange("text", null, text);
	return result;
}

public void setText(String text) {
	this.text = text;
	firePropertyChange("text", null, text);
}

/**
 * @see org.eclipse.gef.examples.text.model.ModelElement#size()
 */
int size() {
	return getText().length();
}

}