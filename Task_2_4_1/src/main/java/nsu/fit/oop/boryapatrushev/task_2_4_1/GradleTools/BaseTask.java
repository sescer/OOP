package nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Template class for custom gradle tasks
 * Provides all methods for interaction with other parts of project
 */
public abstract class BaseTask {

    BuildLauncher buildLauncher;
    ProjectConnection projectConnection;

    /**
     * Constructor
     * @param projectPath path to gradle project to execute task
     * @throws FileNotFoundException if projectPath not found
     */
    BaseTask(String projectPath) throws FileNotFoundException {

        if (Files.notExists(Paths.get(projectPath))) {
            throw new FileNotFoundException("Directory not found.");
        } else {

            this.projectConnection = GradleConnector.newConnector().
                    forProjectDirectory(new File(projectPath)).connect();
            this.buildLauncher = projectConnection.newBuild();
            this.buildLauncher.addArguments("--console=plain", "--warning-mode=none", "--rerun-tasks");
        }
    }

    /**
     * The method in which gradle task execution should happen.
     * @return true if execution succeeded, otherwise - failed
     */
    public abstract boolean execute();
}
