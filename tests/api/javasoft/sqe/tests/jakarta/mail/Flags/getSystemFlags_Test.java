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

package javasoft.sqe.tests.jakarta.mail.Flags;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getSystemFlags()</strong> API.
 * It does this by invoking the test api and then checking
 * the type of the returned object.	<p>
 *
 *		Return all system flags in this Flags object. <p>
 * api2test: public Flags.Flag[] getSystemFlags()  <p>
 *
 * how2test: Get flag object of a message and then call 'get()' method <p>
 *	     to obtain all the flags in this Flags object. Write the  <p>
 *	     content of array returned by the 'get()' api. <p>
 *	     If these operations are successful then the test passes.
 */

public class getSystemFlags_Test extends MailTest {

    public static void main( String argv[] )
    {
        getSystemFlags_Test test = new getSystemFlags_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Flags: getSystemFlags()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);
	     msgcount = folder.getMessageCount();

	     if ( msgcount < 1 )
		  return Status.failed("Mail folder is empty!");

	     for( int i = 1; i <= msgcount; i++ )
	     {
	     // Get message object
		MimeMessage msg = (MimeMessage)folder.getMessage(i);

		if ( msg == null ) {
		     log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		     continue;
		}
	     // Get Flags object for this message
		Flags flag = msg.getFlags();

                if ( flag == null ) {
                     log.println("WARNING: FAILED TO GET FLAGS OBJECT FOR MESSAGE: "+ i);
                     continue;
                }
	     // BEGIN UNIT TEST:

                out.println("UNIT TEST "+ i +":  getSystemFlags()");

		Flags.Flag[] flaglist = flag.getSystemFlags();		// API TEST

		if( flaglist.length > 0 )
		{
		    for( int j = 0; j < flaglist.length; j++ )
		         out.println(flaglist[j]);

		    out.println("UNIT TEST " + i + ":  passed\n");
		}
		else if( flaglist.length == 0 ) {
			 out.println("No system flags set for this Flags object.");
			 out.println("UNIT TEST " + i + ":  passed\n");
		     } else {
		             out.println("UNIT TEST "+ i +":  FAILED\n");
			     errors++;
		}
	     // END OF UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
