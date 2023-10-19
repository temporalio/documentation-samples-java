/* dacx */
package backgroundcheckboilerplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

/*
Temporal provides the `TestWorkflowEnvironment` and `TestWorkflowExtension` classes
to allow for testing Workflows. There are two ways to test Workflows; the first
is to test the Workflow code without invoking the real Activities by mocking
the Workflow's Activities and the second is to test the Workflow and its Activities
in their entirety. This section will focus on the first scenario while a following.
section will cover the later. 

Testing Workflows is similar to testing non-Temporal java code.

Some examples of things an Workflow can be tested for are:
- Exceptions thrown when invoking the Workflow Execution.
- Exceptions thrown when checking for the result of the Workflow Execution.
- Workflow return values. Check to ensure the return value is expected.

We can also perform a Workflow Replay test, and we'll provide detailed coverage of this topic in another section.
*/

public class BackgroundCheckBoilerplateWorkflowTest {

  // Use JUnit Extensions to simplify the creation of the test environment.
  // This creates an environment and registers the Workflow to a Worker for testing.
  // If you would rather set this up yourself, look into TestWorkflowEnvironment
  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = TestWorkflowExtension
      .newBuilder().setWorkflowTypes(BackgroundCheckBoilerplateWorkflowImpl.class)
      .setDoNotStart(true).build();

  @Test
  public void testSuccessfulBackgroundCheckBoilerplateWithMocks(TestWorkflowEnvironment testEnv,
      Worker worker, BackgroundCheckBoilerplateWorkflow workflow) {
    
    // Create a mock object of your Activities
    BackgroundCheckBoilerplateActivities mockedActivities =
        mock(BackgroundCheckBoilerplateActivities.class, withSettings().withoutAnnotations());

    // Specify what value should be returned when a specific Activity is invoked.
    // Your Activity must have the same method name here as it would within your Workflow
    when(mockedActivities.ssnTraceActivity("Angela")).thenReturn("pass");

    // Register the Workflow's Activities with the Worker provided by the Extension
    worker.registerActivitiesImplementations(mockedActivities);

    // Start the test environment
    testEnv.start();

    // Request execution of the backgroundCheck Workflow
    // This will execute your Workflow, calling the Mocked Activities in place
    // of your actual implementation of the Activities.
    String pass_output = workflow.backgroundCheck("Angela");
  
    assertEquals("pass", pass_output);
  
  }
}

/* 
To test Workflow code in isolation, that is, without invoking the Activity
code and only testing the Workflow code, use Mocking. 

This example demonstrates how to use Mockito to mock an Activity and have
your Workflow invoke the mocked Activity.
*/

/* @dacx
id: backgroundcheck-boilerplate-add-workflow-tests
title: Testing Workflows
description: How to test your Temporal Application Workflows
label: Test framework details
lines: 4-68
tags:
- testing
- developer guide
- test framework
- java sdk
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-add-workflow-tests-details
title: Add Workflow method test using Mocks
description: Details about how to test Workflow code in isolation using Mocks
label: Test Workflow code details
tags:
- testing
- developer guide
- java sdk
lines: 70-78
@dacx */