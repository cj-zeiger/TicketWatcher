/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Mozilla Communicator client code, released March 31, 1998.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by Netscape are Copyright (C) 1998-1999
 * Netscape Communications Corporation.  All Rights Reserved.
 *
 * Contributor(s):
 *
 * IBM
 * -  Binding to permit interfacing between Mozilla and SWT
 * -  Copyright (C) 2003, 2012 IBM Corp.  All Rights Reserved.
 *
 * ***** END LICENSE BLOCK ***** */
package org.eclipse.swt.internal.mozilla;

public class nsIAuthInformation extends nsISupports {

	static final int LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 9;

	public static final String NS_IAUTHINFORMATION_IID_STR =
		"0d73639c-2a92-4518-9f92-28f71fea5f20";

	public static final nsID NS_IAUTHINFORMATION_IID =
		new nsID(NS_IAUTHINFORMATION_IID_STR);

	public nsIAuthInformation(long /*int*/ address) {
		super(address);
	}

	public static final int AUTH_HOST = 1;
	public static final int AUTH_PROXY = 2;
	public static final int NEED_DOMAIN = 4;
	public static final int ONLY_PASSWORD = 8;

	public int GetRealm(long /*int*/ aRealm) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aRealm);
	}

	public int GetUsername(long /*int*/ aUsername) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aUsername);
	}

	public int SetUsername(long /*int*/ aUsername) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aUsername);
	}

	public int GetPassword(long /*int*/ aPassword) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, getAddress(), aPassword);
	}

	public int SetPassword(long /*int*/ aPassword) {
		return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aPassword);
	}
}
