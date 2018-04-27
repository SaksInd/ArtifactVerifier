package industries.saks.gradle.artifactverifier;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.PublishArtifactSet;
import org.gradle.api.file.FileCollection;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.TaskAction;

import groovy.lang.Closure;

public class ArtifactVerifierTask extends DefaultTask {

    private final Logger logger = Logging.getLogger(getClass());

	@TaskAction
	void runTask(){
		Configuration archiveConfiguration = getProject().getConfigurations().getByName("archives");
		PublishArtifactSet allArtifacts = archiveConfiguration.getAllArtifacts();
		FileCollection artifactFiles = allArtifacts.getFiles();
	}

	public void setName(String name) {
		logger.error(name);

	}

	public ArtifactSpec has(Integer count) {
		logger.debug("should have {0}", count);
		ArtifactCountingSpec artifactCountingSpec = new ArtifactCountingSpec();
		artifactCountingSpec.setCount(count);
		return artifactCountingSpec;
	}

	public ArtifactSpec jarc(Closure<ArtifactSpec> closure){
		closure.setDelegate(this);
		return closure.call();
	}

	public ArtifactSpec isEmpty = new ArtifactSpec();
	public ArtifactSpec isEmpty() {
		return null;
	}
}
