/* @dacx */
package backgroundcheckboilerplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

/*
 **Workflow Integration Testing**

 This example tests a complete Workflow by invoking the Activities the Workflow
 calls. This is, in reality, an integration test.
 */


public class BackgroundCheckBoilerplateWorkflowIntegrationTest {

  /* Use JUnit Extensions to simplify the creation of the test environment.
     This creates an environment and registers the Workflow to a Worker for testing.
     If you would rather set this up yourself, look into TestWorkflowEnvironment
  */
  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = TestWorkflowExtension
      .newBuilder().setWorkflowTypes(BackgroundCheckBoilerplateWorkflowImpl.class).setDoNotStart(true).build();

  @Test
  public void testSuccessfulBackgroundCheckBoilerplate(TestWorkflowEnvironment testEnv, Worker worker,
      BackgroundCheckBoilerplateWorkflow workflow) {

    // Register the Workflow's Activities with the Worker provided by the Extension
    worker.registerActivitiesImplementations(new BackgroundCheckBoilerplateActivitiesImpl());

    // Start the test environment
    testEnv.start();

    /* Request execution of the backgroundCheck Workflow
       This will execute your entire Workflow, along with every Activity the
       Workflow calls
    */
    String output = workflow.backgroundCheck("Mason");

    // Check for the expected return value
    assertEquals("pass", output);
  }
}

/* @dacx
id: backgroundcheck-boilerplate-add-test-framework-workflow-integration
title: Workflow Integration Testing
description: How to test the integration between your Workflows and Activities
label: Test framework
lines: 11-16
tags:
- testing
- developer guide
- test framework
- java sdk
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-add-workflow-integration-tests
title: Add Workflow Integration tests
description: How to test the integration between Workflows and Activities
label: Test Workflow Integration
tags:
- testing
- developer guide
- java sdk
lines: 19-48
@dacx */