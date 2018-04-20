package com.sxind.gradle.artifactverifier;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.PublishArtifactSet;
import org.gradle.api.internal.plugins.DefaultArtifactPublicationSet;
import org.gradle.api.tasks.TaskAction;

public class ArtifactVerifierTask extends DefaultTask {

	@TaskAction
	void runTask(){
		Configuration archiveConfiguration = getProject().getConfigurations().getByName("archives");
		PublishArtifactSet allArtifacts = archiveConfiguration.getAllArtifacts();
		allArtifacts.getFiles();

	}

}
