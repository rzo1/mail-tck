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
 * This class tests the <strong>contains()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	     Check whether all the flags in the specified Flags object are present
 *	     in this Flags object.  <p>
 * api2test: public boolean contains(Flags fo)  <p>
 *
 * how2test: Call the contains() API with some specified Flags objects. The test is <p>
 *	     considered passing if the specified Flags object is present in this object
 *	     in which case this api return a TRUE value otherwise a FALSE. <p>
 *
 *
 *		Check whether the specified flag is present in this Flags object. <p>
 * api2test: public boolean contains(String flag)  <p>
 *
 * how2test: Call the contains() API with some specified Flags objects. The test is <p>
 *           considered passing if the specified Flags object is present in this object
 *           in which case this api return a TRUE value otherwise a FALSE.
 */

public class contains_Test extends MailTest {

    public static void main( String argv[] )
    {
        contains_Test test = new contains_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Flags: contains(Flags | String)\n");

        try {
	  // BEGIN UNIT TEST 1:

	     out.println("\nUNIT TEST 1:  contains(Flags)");

	     Flags flag1 = new Flags();

             if(( flag1 != null ) && ( flag1 instanceof Flags ))
	     {
		   flag1.add(Flags.Flag.DELETED);
		   flag1.add(Flags.Flag.SEEN);
		   flag1.add(Flags.Flag.RECENT);
		   flag1.add(Flags.Flag.ANSWERED);

		   if( flag1.contains(Flags.Flag.DELETED) )		// API TEST
			out.println("DELETED flag is contained in Flags object.");
		   else
			errors++;

                   if( flag1.contains(Flags.Flag.SEEN) )		// API TEST
                       out.println("SEEN flag is contained in Flags object.");
                   else
                       errors++;

                   if( flag1.contains(Flags.Flag.RECENT) )		// API TEST
                       out.println("RECENT flag is contained in Flags object.");
                   else
                       errors++;

                   if( flag1.contains(Flags.Flag.ANSWERED) )	// API TEST
                       out.println("ANSWERED flag is contained in Flags object.");
                   else
                       errors++;

		   if( errors == 0 )
                       out.println("UNIT TEST 1: passed\n");
		   else
		       out.println("UNIT TEST 1: FAILED\n");
	     }
	     else {
		    out.println("UNIT TEST 1: FAILED\n");
		    errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

             out.println("\nUNIT TEST 2: contains(String)");

             Flags flag2 = new Flags(Flags.Flag.ANSWERED);
	     flag1.add("TEST_USER");
	     flag2.add("USER_TEST");

             if (( flag2 != null ) && ( flag2 instanceof Flags ))
	     {
                   if( flag1.contains("TEST_USER") )		// API TEST
                       out.println("TEST_USER flag is contained in Flags object.");
                   else
                       errors++;

                   if( flag2.contains("USER_TEST") )		// API TEST
                       out.println("USER_TEST flag is contained in Flags object.");
                   else
                       errors++;

                   if( !(flag2.contains("NONEXIST")) )         // API TEST
                       out.println("NONEXIST flag is NOT contained in Flags object.");
                   else
                       errors++;

                   if( errors == 0 )
                       out.println("UNIT TEST 2: passed\n");
                   else
                       out.println("UNIT TEST 2: FAILED\n");
	     }
             else {
                    out.println("UNIT TEST 2: FAILED\n");
                    errors++;
             }
          // END UNIT TEST 2:

	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
