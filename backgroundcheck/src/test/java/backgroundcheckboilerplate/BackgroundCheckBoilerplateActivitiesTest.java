/* @dacx */
package backgroundcheckboilerplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import io.temporal.testing.TestActivityExtension;

/*
 ** How to add a Testing Framework and Tests Testing Activities.**

We can test Activity code for the following conditions:
- Exceptions thrown when invoking the Activity Execution.
- Exceptions thrown when checking for the result of the Activity Execution.
- Activity return values. Check to ensure the return value is expected.

This example asserts that the expected value was returned by the invocation of the Activity.
*/


public class BackgroundCheckBoilerplateActivitiesTest {

  /* Use JUnit Extensions to simplify the creation of the test environment.
     This creates an environment and registers Activities to a Worker for testing.
     If you would rather set this up yourself, look into TestActivityEnvironment
  */
  @RegisterExtension
  public static final TestActivityExtension activityExtension = TestActivityExtension.newBuilder()
      .setActivityImplementations(new BackgroundCheckBoilerplateActivitiesImpl()).build();

  // Test the Activity in isolation from the Workflow
  @Test
  public void testSsnTraceActivity(BackgroundCheckBoilerplateActivities activities) {
    String name = "Patrick";

    // Run the Activity in the test environment
    String result = activities.ssnTraceActivity(name);

    // Check for the expected return value
    assertEquals("pass", result);
  }

}

/* @dacx
id: backgroundcheck-boilerplate-add-test-framework-activities
title: Add a testing framework for Testing Activities
description: How to add a testing framework to your Temporal Application.
label: Test framework
lines: 4-28
tags:
- testing
- developer guide
- test framework
- java sdk
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-add-activity-tests
title: Add Activity method tests
description: How to test Activity code
label: Test Activity code
lines: 21-42
tags:
- testing
- developer guide
- java sdk
@dacx */