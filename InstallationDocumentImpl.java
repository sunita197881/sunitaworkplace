package com.adobe.training.sunitaaem65.core.models.impl;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.training.sunitaaem65.core.models.InstallationDocumentModel;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { InstallationDocumentImpl.class,
		ComponentExporter.class }, resourceType = InstallationDocumentImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class InstallationDocumentImpl implements InstallationDocumentModel {

	static final String RESOURCE_TYPE = "sunitaTraining/components/content/installation";

	protected static final Logger LOGGER = LoggerFactory.getLogger(InstallationDocumentImpl.class);

	@ValueMapValue
	@Via("resource")
	private String header;
	

	@ChildResource
	private Resource installationdocmtlink;

	// Inject the installationdocmtlink node under the current node

	/*
	 * @Inject
	 * 
	 * @Optional public Resource installationdocmtlink;
	 */


	public static String getResourceType() {
		return RESOURCE_TYPE;
	}

	@Override
	public String getHeader() {
		LOGGER.info("Before return header *******" + header);
		return header;
	}
	
	@Override
	public Resource getInstallationdocumentLink() {
		LOGGER.info("Before return12345" + installationdocmtlink);
		return installationdocmtlink;
	}

	@Override
	public String getExportedType() {
		return RESOURCE_TYPE;
	}

}
