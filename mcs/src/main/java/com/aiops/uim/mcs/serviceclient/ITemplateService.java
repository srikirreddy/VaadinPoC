package com.aiops.uim.mcs.serviceclient;

import java.util.List;

import com.aiops.uim.mcs.models.Template;

public interface ITemplateService {

	Template getTemplateById(long templateId);
	List<Template> getAllTemplatesByDevice(long deviceId);
}
