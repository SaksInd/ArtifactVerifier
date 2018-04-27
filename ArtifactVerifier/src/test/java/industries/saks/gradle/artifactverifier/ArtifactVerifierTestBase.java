package industries.saks.gradle.artifactverifier;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.gradle.testkit.runner.internal.DefaultBuildResult;
import org.gradle.testkit.runner.internal.DefaultBuildTask;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

abstract public class ArtifactVerifierTestBase  {

    File buildFile;
    static final String DEFAULT_JAVA_SRC = "src/java/main/";

    @Rule
    public TemporaryFolder testProjectDir = new TemporaryFolder();



    protected void createSource(String path, String content) {
    	try {
			File file = createFile(path);
			writeFile(file, content);

		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    }

    private File createFile(String path) throws IOException {
    	File rootFile = testProjectDir.getRoot();
    	Path parentPath = Paths.get(rootFile.getPath(), path).getParent();
    	if (!Files.exists(parentPath)) {
    		Files.createDirectories(parentPath);
    	}

    	return testProjectDir.newFile(path);


	}

	protected BuildResult runTask(String buildFileLocation, String taskName){

    	try {

    		prepareBuildFiles(testProjectDir, buildFileLocation);

    		ArrayList<File> classpath = new ArrayList<>();
	        String currentClassPaths = System.getProperty("java.class.path");

	        for (String currentClassPath : currentClassPaths.split(File.pathSeparator)) {
				classpath.add(new File(currentClassPath));
			}

			BuildResult result = GradleRunner.create()
	            .withProjectDir(testProjectDir.getRoot())
	            .withPluginClasspath(classpath)
	            .withArguments(taskName)
	            .withDebug(true)
	            .forwardOutput()
	            .build();

	        return result;

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    	return new FailedBuildResult(taskName);
   	}


    class FailedBuildResult extends DefaultBuildResult {

		public FailedBuildResult(List<BuildTask> tasks) {
			super("", tasks);
		}

		public FailedBuildResult(String taskName) {
			super("", FailedBuildTask.getTasks(taskName));
		}
    }

    static class FailedBuildTask extends DefaultBuildTask {

		public FailedBuildTask(String path) {
			super(path, TaskOutcome.FAILED);
		}

		public static List<BuildTask> getTasks(String taskName) {
			List<BuildTask> tasks = new ArrayList<>();
			tasks.add(new FailedBuildTask(taskName));
			return tasks;
		}


    }

    private void prepareBuildFiles(TemporaryFolder testProjectDir, String buildFileLocation) {

    	try {
    		URL buildFileDir = getClass().getClassLoader().getResource(buildFileLocation);

			Path basePath = Paths.get(buildFileDir.toURI());

			for (Iterator<File> filesAndDirs = FileUtils.iterateFilesAndDirs(new File(buildFileDir.getPath()), TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE)
					; filesAndDirs.hasNext();) {
				 File file = filesAndDirs.next();
				 Path childPath = Paths.get(file.getPath());

				 Path relativePath = basePath.relativize(childPath);

				 if (file.isFile()) {
					 File dest = testProjectDir.newFile(relativePath.toString());
					 FileUtils.copyURLToFile(file.toURI().toURL(), dest);
				 } else if (file.isDirectory()) {
					 File testIfExists = new File(testProjectDir.getRoot(), relativePath.toString());
					 if (!testIfExists.exists()) {
						 testProjectDir.newFolder(relativePath.toString());
					 }
				 } else {
					 throw new IllegalArgumentException("Unknown file type:" + file);
				 }
			}

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	protected void writeFile(File destination, String content) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(destination));) {
            output.write(content);
        } catch (IOException e) {
        	fail(e.getMessage());
		}
    }

	void prepareBuildFile(Class<? extends ArtifactVerifierPluginTest> class1, String string) {

	}
}
