package com.sxind.gradle.artifactverifier;

import org.gradle.api.specs.Spec;

public class ArtifactSpec  {


	public ArtifactSpec shouldHave(String ... file) {
		return this;
	}

}
