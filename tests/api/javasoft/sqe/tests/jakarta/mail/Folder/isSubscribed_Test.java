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

package javasoft.sqe.tests.jakarta.mail.Folder;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>isSubscribed()</strong> API.
 * It does this by invoking the api under test and then checking
 * the value of the returned object.	<p>
 *
 *		Returns true if this Folder is subscribed <p>
 * api2test: public boolean isSubscribed()  <p>
 *
 * how2test: Call this API on a folder object. If it returns a boolean <p>
 *	     value then the testcase passes. This method can be invoked <p>
 *	     on a closed Folder. The default implementation returns true.
 */

public class isSubscribed_Test extends MailTest {

    public static void main( String argv[] )
    {
        isSubscribed_Test test = new isSubscribed_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: isSubscribed()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if (folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	  // BEGIN UNIT TEST 1:
	     // test with closed folder
	     
	     boolean subscribed;
             out.println("UNIT TEST 1: isSubscribed();");

             subscribed = folder.isSubscribed();	// API TEST

             if( subscribed ) {
		 out.println("This folder is subscribed.");
                 out.println("UNIT TEST 1: passed\n");
             }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // test with open folder

	     folder.open(Folder.READ_ONLY);
             out.println("UNIT TEST 2: isSubscribed();");

             subscribed = folder.isSubscribed();       // API TEST

             if( subscribed ) {
                 out.println("This folder is subscribed.");
                 out.println("UNIT TEST 2: passed\n");
             }
          // END UNIT TEST 2:
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
