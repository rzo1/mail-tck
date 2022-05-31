/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javasoft.sqe.tests.jakarta.mail.internet.MimeUtility;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>decodeWord()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *	The string is parsed using the rules in RFC 2047 for parsing an "encoded-word". <p>
 * api2test: public static String decodeWord(String)  <p>
 *
 * how2test: Call API decoded strings, if outputs decoded messages
 *	     then testcase passes, otherwise it fails.
 */

public class decodeWord_Test extends MailTest {

public String[] datim = { "=?ISO-8859-1?Q?Keld_J=F8rn_Simonsen?=",
			  "=?ISO-8859-1?Q?Andr=E9?=",
			  "=?ISO-8859-1?B?SWYgeW91IGNhbiByZWFkIHRoaXMgeW8=?=",
			  "=?ISO-8859-2?B?dSB1bmRlcnN0YW5kIHRoZSBleGFtcGxlLg==?=",
			  "=?ISO-8859-1?Q?Olle_J=E4rnefors?=",
			  "=?ISO-8859-1?Q?Patrik_F=E4ltstr=F6m?="
			};

    public static void main( String argv[] )
    {
        decodeWord_Test test = new decodeWord_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	// parse command-line options
	parseArgs(argv);

        out.println("\nTesting class MimeUtility: decodeWord(String);");

        try {
	  // BEGIN UNIT TEST:

	     for( int k = 0; k < datim.length; k++ )
	     {
	         out.println("UNIT TEST "+ (k+1) +":  decodeWord("+datim[k]+")");

	         out.println("Decoded text = "+ MimeUtility.decodeWord(datim[k]));
		 out.println("\nUNIT TEST "+ (k+1) +": passed");
	         out.println("----------------------------------------");
	     }
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
