package com.jpinup.handler;

import java.util.HashMap;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class CustomTaskItemHandler implements WorkItemHandler {

	public CustomTaskItemHandler() {
	}

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("***** OK ****");
		manager.completeWorkItem(workItem.getId(), new HashMap <String, Object>());
	}


	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// Do nothing, notifications cannot be aborted
	}
}