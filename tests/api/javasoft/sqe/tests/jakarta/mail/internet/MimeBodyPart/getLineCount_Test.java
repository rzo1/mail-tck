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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getLineCount()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the number of lines for the content of BodyPart. <p>
 * api2test: public int getLineCount()  <p>
 *
 * how2test: Call API and then output the value returned, if this operation is
 *	     successfull then testcase passes, otherwise it fails.
 */

public class getLineCount_Test extends MailTest {

    public static void main( String argv[] )
    {
        getLineCount_Test test = new getLineCount_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeBodyPart: getLineCount()\n");

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
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ i +":  getLineCount()");

		// Get the "type" of content
	        Object content = msg.getContent();

		if ( content instanceof Multipart )
		{    // get body count
		     int bodycount = ((MimeMultipart)content).getCount();

		     for( int k = 0; k < bodycount; k++ )
		     {   // get bodypart
		     	  BodyPart bp = ((MimeMultipart)content).getBodyPart(k);

			 // get the number of lines for the content of BodyPart.
			  int lineCount = ((MimeBodyPart)bp).getLineCount();	// API TEST

			  if ( lineCount > 0 ) {
			       out.println("BodyPart line count is: "+ lineCount);
			       out.println("UNIT TEST "+ i +":  passed\n");
			  } else {
				 out.println("UNIT TEST "+ i +":  has zero lines!\n");
			  }
		     }
		}
	     // END UNIT TEST:
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
