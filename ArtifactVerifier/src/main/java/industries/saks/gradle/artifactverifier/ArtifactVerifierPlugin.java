package industries.saks.gradle.artifactverifier;

import java.util.function.Consumer;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.internal.tasks.TaskMutator;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;

public class ArtifactVerifierPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
        ArtifactVerifierPluginExtension extension = project.getExtensions().create("ArtifactVerifier", ArtifactVerifierPluginExtension.class);
        TaskContainer tasks = project.getTasks();
        ArtifactVerifierTask verifyTask = tasks.create("verifyArtifact", ArtifactVerifierTask.class);
		Action<Task> taskAdditionHandler = new Action<Task>() {
			@Override
			public void execute(Task task) {
				archiveTaskShouldFinnalizedByVerifierTask(task, verifyTask);
			}
		};
		tasks.whenTaskAdded(taskAdditionHandler);

		tasks.forEach(task->archiveTaskShouldFinnalizedByVerifierTask(task, verifyTask));

	}

	private void archiveTaskShouldFinnalizedByVerifierTask(Task task, ArtifactVerifierTask verifyTask) {
    	if (task instanceof AbstractArchiveTask) {
    		task.finalizedBy(verifyTask);
    	}
	}

}
