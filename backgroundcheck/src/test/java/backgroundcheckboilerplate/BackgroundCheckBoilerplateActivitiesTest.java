/* dacx */
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

  // Use JUnit Extensions to simplify the creation of the test environment.
  // This creates an environment and registers Activities to a Worker for testing.
  // If you would rather set this up yourself, look into TestActivityEnvironment
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

/*
Temporal provides the `TestWorkflowExtension` class to simplify the creation of 
the test environment. Using this extension you provide your Activity 
to register with a Worker created by the testing framework to be used during testing. 
The extension provides a `TestWorkflowEnvironment`, `Worker`, and a stubbed Activity 
object to each test as well as manage the lifecycle of the test environment.
If you require more granular control of the test environments, you can manually create
and destroy all these parts in methods annotated with `@BeforeEach` and `@AfterEach`
as defined by JUnit.

You annotate the method with @Test and test the results of the Activity via assertions.
*/

/* @dacx
id: backgroundcheck-boilerplate-add-activity-tests
title: Testing Activities
description: How to add a testing framework to your Temporal Application.
label: Test framework
lines: 4-42
tags:
- testing
- developer guide
- test framework
- java sdk
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-add-activity-tests-details
title: Add Activity method tests
description: How to test Activity code
label: Test Activity code
lines: 44-55
tags:
- testing
- developer guide
- java sdk
@dacx */