/* dacx */
package backgroundcheckboilerplate;

import io.temporal.activity.ActivityInterface;

/*
In the Temporal Java SDK programming model, a Activity is an interface and its implementation.
The `BackgroundCheckWorkflow` interface below is an example of a the first part defining an Activity
*/

// Activity Interfaces must be annotated with @ActivityInterface
@ActivityInterface
// BackgroundCheckActivities is the interface that contains your Activity Definitions
public interface BackgroundCheckBoilerplateActivities {

  // ssnTraceActivity is your custom Activity Definition
  public String ssnTraceActivity(String name);

}

/* @dacx
id: backgroundcheck-boilerplate-activity-interface
title: BackgroundCheck Workflow Interface
label: Activity code
description: In the Temporal Java SDK, an Activity Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- activity
- code sample
lines: 4-19
@dacx */
