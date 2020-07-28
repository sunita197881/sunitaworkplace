package com.adobe.training.sunitaaem65.core.models;

import org.osgi.annotation.versioning.ConsumerType;
import org.osgi.resource.Resource;

import com.adobe.cq.export.json.ComponentExporter;

@ConsumerType
public interface InstallationDocumentModel extends ComponentExporter {

	String getHeader();

	Resource getInstallationdocumentLink();

}
