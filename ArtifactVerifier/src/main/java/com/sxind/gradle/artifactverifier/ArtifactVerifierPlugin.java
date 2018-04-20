package com.sxind.gradle.artifactverifier;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.internal.plugins.DefaultArtifactPublicationSet;

public class ArtifactVerifierPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
        ArtifactVerifierPluginExtension extension = project.getExtensions().create("ArtifactVerifier", ArtifactVerifierPluginExtension.class);
        ArtifactVerifierTask task = project.getTasks().create("hellp", ArtifactVerifierTask.class);

	}

}
