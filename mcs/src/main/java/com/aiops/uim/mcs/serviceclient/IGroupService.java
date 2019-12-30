package com.aiops.uim.mcs.serviceclient;

import java.util.List;

import com.aiops.uim.mcs.models.Group;

public interface IGroupService {

	Group getGroupById(long deviceId);
	List<Group> getAllGroups();
}