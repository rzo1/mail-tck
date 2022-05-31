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

package javasoft.sqe.tests.jakarta.mail.internet.ParameterList;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>ParameterList.combineSegments()</strong> API. <p>
 *
 * api2test: public void combineSegments()  <p>
 *
 * how2test: Create a ParameterList with a parameter split into several
 *           segments and then call combineSegments() and verify that the
 *           parameter is returned as a single correct value.
 *	     If is so then this testcase passes, otherwise it fails.
 */

public class combineSegments_Test extends MailTest {

    public static void main( String argv[] )
    {
        combineSegments_Test test = new combineSegments_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	pattern = ";i18set=ISO-8859-1;charset=us-acii;abc=xyz";

	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting method ParameterList.combineSegments()\n");

        try {	     
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1:  ParameterList.combineSegments()");
	     System.setProperty("mail.mime.decodeparameters", "true");

	     ParameterList parmlist1 = new ParameterList();    // API TEST

	     parmlist1.set("p*0", "abc");
	     parmlist1.set("p*1", "def");
	     parmlist1.combineSegments();
	     if (parmlist1.get("p").equals("abcdef") &&
		    parmlist1.get("p*0") == null &&
		    parmlist1.get("p*1") == null)
		 out.println("UNIT TEST 1: passed");
	     else {
		 out.println("ParameterList: " + parmlist1);
		 out.println("UNIT TEST 1: FAILED");
		 errors++;
	     }
	  // END UNIT TEST 1:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
