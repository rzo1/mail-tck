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

package javasoft.sqe.tests.jakarta.mail.URLName;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>toString()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Constructs a string representation of this URLName. <p>
 * api2test: public String toString()  <p>
 *
 * how2test: Call this API, check the value/type of returned object.
 *	     If its a string then this testcase passes.
 */

public class toString_Test extends MailTest {

    public static void main( String argv[] )
    {
        toString_Test test = new toString_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class URLName: toString()\n");

        try {
	   // Create a URLName object
              URLName urlname = new URLName(protocol, host, 0, mailbox, user, password);

              if( urlname == null )
                  return Status.failed("Failed to create a URLName object!");

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  toString()");
              String tostr = urlname.toString(); 	// API TEST

              if( tostr != null ) {
		  out.println(tostr);
                  out.println("UNIT TEST 1: passed");
	      } else {
		    out.println("UNIT TEST 1: FAILED.\n");
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
