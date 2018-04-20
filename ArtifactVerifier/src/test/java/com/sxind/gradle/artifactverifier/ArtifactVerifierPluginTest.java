package com.sxind.gradle.artifactverifier;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.adrianwalker.multilinestring.Multiline;
import org.gradle.internal.impldep.org.apache.commons.io.IOUtils;
import org.gradle.internal.io.IoUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ArtifactVerifierPluginTest extends ArtifactVerifierTestBase {


	/**
	 */
	@Multiline String simpleBuildFile;

    @Test
    public void simpleVerifying() {

    }

}
