package com.sxind.gradle.artifactverifier;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

abstract public class ArtifactVerifierTestBase {
    @Rule
    public TemporaryFolder testProjectDir = new TemporaryFolder();

    File buildFile;

    public void setup(String buildFileContent) {
    	try {
			buildFile = testProjectDir.newFile("build.gradle");
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

    }


}
