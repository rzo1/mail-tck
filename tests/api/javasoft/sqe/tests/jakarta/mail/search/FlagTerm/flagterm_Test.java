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

package javasoft.sqe.tests.jakarta.mail.search.FlagTerm;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>FlagTerm()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		A contructor takes Flags object and boolean argument. <p>
 * api2test: public FlagTerm(Flags, boolean)  <p>
 *		Return the Flags to test. <p>
 * api2test: public Flags getFlags()  <p>
 *		Return true if testing whether the flags are set. <p>
 * api2test: public boolean getTestSet()  <p>
 *		The match method.   <p>
 * api2test: boolean match(Message)  <p>
 *
 * how2test: Call these APIs with pattern/string/message arguments and if
 *	     'match' returns boolean value, then this testcase passes.
 */

public class flagterm_Test extends MailTest {

    public static void main( String argv[] )
    {
        flagterm_Test test = new flagterm_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class FlagTerm: FlagTerm(Flags, boolean)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg =  (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		out.println("UNIT TEST "+ i +":  FlagTerm(Flags, true)");

		// get Flags object for this message
		Flags fo = msg.getFlags();

		// create a FlagTerm object
		FlagTerm ft = new FlagTerm(fo, true);	// API TEST

                if( ft == null ) {
                    log.println("Warning: FlagTerm contructor returned a Null object!");
                    continue;
                } else
                      out.println("UNIT TEST "+ i +": passed.");

		out.println("UNIT TEST "+ i +":  getFlags()");

		// get Flags to test
		Flags f2t = ft.getFlags();		// API TEST

                if( f2t == null ) {
                    log.println("Warning: getFlags() method returned a Null object!");
                    continue;
                } else 
                      out.println("UNIT TEST "+ i +": passed.");

		out.println("UNIT TEST "+ i +":  getTestSet()");

		// testing whether the flags are set
		boolean rc = ft.getTestSet();		// API TEST

                if( rc ) {
                    out.println("The flags are set to true!");
		    out.println("UNIT TEST "+ i +": passed.");
                } else {
			out.println("The flags are set to false!");
                        out.println("UNIT TEST "+ i +": passed.");
		}
		out.println("UNIT TEST "+ i +":  match(Message)");

		// call the comparison method
		boolean foundit = ft.match(msg);	// API TEST

	        if( foundit ) {
	            out.println("The flag comparison was successfull.");
                    out.println("UNIT TEST "+ (i+1) +":  passed\n");
	        } else {
		        out.println("The flag comparison was Not successfull!");
			out.println("UNIT TEST "+ (i+1) +":  passed\n");
	        }
	     // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
