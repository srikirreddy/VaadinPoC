package com.aiops.uim.mcs.serviceclient;

import com.aiops.uim.mcs.models.Profile;

public interface IProfileService {

	void saveProfile(Profile profile);

	Profile getProfileById(long id);
}
