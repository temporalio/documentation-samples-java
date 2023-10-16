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
**How to add a Testing Framework and Tests for Workflows.**

We can test Workflow code for the following conditions:
- Workflow status. For example, did the Workflow reach a completed status?
- Workflow threw an exception. Did the Workflow function return an error?
- Error when checking for a result of a Workflow. Is there an error in getting the result returned by the Workflow?
- Workflow return value. If the Workflow did return something other than an error, is it what you expected it to be?

We can also perform a Workflow Replay test, and we'll provide detailed coverage of this topic in another section.
*/

/* To test Workflow code in isolation, that is, without invoking the Activity
   code and only testing the Workflow code, use Mocking. 

   This example demonstrates how to use Mockito to mock an Activity and have
   your Workflow invoke the mocked Activity.
 */

public class BackgroundCheckBoilerplateWorkflowTest {

  /* Use JUnit Extensions to simplify the creation of the test environment.
     This creates an environment and registers the Workflow to a Worker for testing.
     If you would rather set this up yourself, look into TestWorkflowEnvironment
  */
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

    /* Specify what value should be returned when a specific Activity is invoked.
       Your Activity must have the same method name here as it would within your Workflow */
    when(mockedActivities.ssnTraceActivity("Angela")).thenReturn("pass");

    // Register the Workflow's Activities with the Worker provided by the Extension
    worker.registerActivitiesImplementations(mockedActivities);

    // Start the test environment
    testEnv.start();

    /* Request execution of the backgroundCheck Workflow
       This will execute your Workflow, calling the Mocked Activities in place
       of your actual implementation of the Activities.
    */

    String pass_output = workflow.backgroundCheck("Angela");
  
    assertEquals("pass", pass_output);
  
  }
}

/* @dacx
id: backgroundcheck-boilerplate-add-test-framework-workflow
title: Add a testing framework for Workflows
description: How to add a testing framework to your Temporal Application for your Workflows
label: Test framework details
lines: 3-23, 32-41
tags:
- testing
- developer guide
- test framework
- java sdk
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-add-workflow-test-mock
title: Add Workflow method test using Mocks
description: Details about how to test Workflow code in isolation using Mocks
label: Test Workflow code details
tags:
- testing
- developer guide
- java sdk
lines: 25-71
@dacx */