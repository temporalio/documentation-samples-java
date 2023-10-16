/* @dacx */
package backgroundcheckboilerplate;

public class BackgroundCheckBoilerplateActivitiesImpl implements BackgroundCheckBoilerplateActivities{

  @Override
  public String ssnTraceActivity(String name){
    /* 
      This is where a call to another service would be made to perform the trace
      We are simulating that the service that does SSNTrace executed successfully
      with a passing value being returned
    */

    String result = "pass";
    return result;
  }

}

/*
Activity Definitions support the passing of parameters.
All Workflow Definition parameters must be serializable (using the Jackson JSON 
Payload Converter).
*/

/* @dacx
id: boilerplate-backgroundcheck-activity-implementation
title: BackgroundCheck Workflow Interface
label: Activity code
description: In the Temporal Java SDK, an Activity Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- activity
- code sample
lines: 4-18
@dacx */

/* @dacx
id: boilerplate-backgroundcheck-activity-details
title: BackgroundCheck Workflow Interface
label: Activity code
description: In the Temporal Java SDK, an Activity Definition is an interface and its implementation.
tags:
- java sdk
- developer guide
- activity
lines: 20-24
@dacx */


