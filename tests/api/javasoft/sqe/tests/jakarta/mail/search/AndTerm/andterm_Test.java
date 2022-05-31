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

package javasoft.sqe.tests.jakarta.mail.search.AndTerm;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>AndTerm()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		A contructor that takes two SearchTerm arguments. <p>
 * api2test: public AndTerm(SearchTerm, SearchTerm)  <p>
 *              get search terms  <p>
 * api2test: public SearchTerm[] getTerms()  <p>
 *		The match method.   <p>
 * api2test: boolean match(Message)  <p>
 *
 * how2test: Call these APIs with pattern/string/message arguments and if
 *	     'match' returns boolean value, then this testcase passes.
 */

public class andterm_Test extends MailTest {

    public static void main( String argv[] )
    {
        andterm_Test test = new andterm_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class AndTerm: AndTerm(SearchTerm | SearchTerm[])\n");

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
	     
	     // Create a FROM term object
             FromTerm fromTerm = new FromTerm(new InternetAddress(from));

	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: AndTerm(SearchTerm, SearchTerm)");
	     
	     // Create a AND term object
             AndTerm at = new AndTerm(new SubjectTerm(subject), fromTerm);	// API TEST

	     if( at != null )
	     	 out.println("UNIT TEST 1: passed.");
	     else {
		   out.println("UNIT TEST 1: FAILED.");
		   errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: getTerms()");

	     // get the search terms
             SearchTerm[] searchTerm = at.getTerms();        // API TEST

	     if( searchTerm != null ) {
		for( int k = 0; k < searchTerm.length; k++ )
		     out.println(searchTerm[k]);

		out.println("UNIT TEST 2: passed.");
	     }
	  // END UNIT TEST 2:
	  
	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     boolean foundit = false;

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg =  (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ (i+2) +":  match(Message)");

		// find the pattern in message body
		foundit = at.match(msg);	// API TEST

	        if( foundit ) {
	            out.println("All search terms found in message header.");
                    out.println("UNIT TEST "+ (i+2) +":  passed\n");
	        } else {
		        out.println("Niether search terms found in message header!");
			out.println("UNIT TEST "+ (i+2) +":  passed\n");
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
