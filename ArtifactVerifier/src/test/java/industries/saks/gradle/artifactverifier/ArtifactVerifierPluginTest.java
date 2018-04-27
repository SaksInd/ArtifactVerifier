package industries.saks.gradle.artifactverifier;

import static org.gradle.testkit.runner.TaskOutcome.NO_SOURCE;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.gradle.testkit.runner.BuildResult;
import org.junit.Test;

public class ArtifactVerifierPluginTest extends ArtifactVerifierTestBase {


	public static void main(String[] args) throws IOException {
		ArtifactVerifierPluginTest test = new ArtifactVerifierPluginTest();
		test.testProjectDir.create();
		test.simpleVerifying();
	}

    @Test
    public void simpleVerifying() {
    	createSource(DEFAULT_JAVA_SRC + "Main.java", "class Main{}");

    	BuildResult result = runTask("testHelloWorld", "build");
    	//assertThat(result.task(":build").getOutcome(), is(equalTo(NO_SOURCE)));

    }




}
