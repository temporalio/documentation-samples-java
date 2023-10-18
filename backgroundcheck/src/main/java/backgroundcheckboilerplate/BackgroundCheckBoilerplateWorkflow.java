/* dacx */
package backgroundcheckboilerplate;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/*
In the Temporal Java SDK programming model, a [Workflow Definition](/concepts/what-is-a-workflow-definition) is an interface and its implementation.
The `BackgroundCheckWorkflow` interface below is an example of a the first part of a Workflow Definition.
*/

// Workflow Interfaces must be annotated with @WorkflowInterface
@WorkflowInterface
public interface BackgroundCheckBoilerplateWorkflow {

  // The Workflow Method within the interface must be annotated with @WorkflowMethod
  @WorkflowMethod
  public String backgroundCheck(String name);

}

/* @dacx
id: backgroundcheck-boilerplate-workflow-interface
title: BackgroundCheck Workflow Interface
label: Workflow code
description: In the Temporal Java SDK, a Workflow Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- workflow
- code sample
lines: 4-20
@dacx */
