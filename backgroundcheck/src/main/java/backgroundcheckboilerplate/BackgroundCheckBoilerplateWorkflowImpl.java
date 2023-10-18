/* dacx */
package backgroundcheckboilerplate;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class BackgroundCheckBoilerplateWorkflowImpl implements BackgroundCheckBoilerplateWorkflow {

  // Define the Activity Execution options
  // StartToCloseTimeout or ScheduleToCloseTimeout must be set
  ActivityOptions options = ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofSeconds(5))
          .build();

  // Create an client stub to activities that implement the given interface
  private final BackgroundCheckBoilerplateActivities activities =
      Workflow.newActivityStub(BackgroundCheckBoilerplateActivities.class, options);

  @Override
  public String backgroundCheck(String name) {
    String ssnTraceResult = activities.ssnTraceActivity(name);
    return ssnTraceResult;
  }

}

/*
Workflow Definitions support the passing of parameters.
All Workflow Definition parameters must be serializable (using the Jackson JSON 
Payload Converter).

To request the execution of an Activity, also referred to as an [Activity Execution](/concepts/what-is-an-activity-execution), 
call the Activity Method from within the Workflow Method. Use the `activities`
object that was created in the Workflow Definition, along with the name of the 
method and any parameters that need to be passed. 


A Java-based Workflow Definition can return any serializable output, or raise an 
exception if one was encountered.
We get into the best practices around Workflow parameters, return values, and 
exceptions in the one of the next sections.

In regards to code organization, we recommend organizing Workflow code the same
way you'd organize your standard java code. 
*/

/* @dacx
id: backgroundcheck-boilerplate-workflow-implementation
title: BackgroundCheck Workflow Implementation
label: Workflow code
description: In the Temporal Java SDK, a Workflow Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- workflow
- code sample
lines: 4-27
@dacx */

/* @dacx
id: backgroundcheck-boilerplate-workflow-details
title: BackgroundCheck Workflow Implementation
label: Workflow code
description: In the Temporal Java SDK, a Workflow Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- workflow
lines: 29-47
@dacx */
