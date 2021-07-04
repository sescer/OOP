package nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools;

import org.gradle.tooling.BuildException;

import java.io.FileNotFoundException;

/**
 * Gradle build task based on {@link BaseTask} specification
 * Build provided gradle project without tests
 */
public class BuildTask extends BaseTask {

    /**
     * Inherited from {@link BaseTask}
     */
    public BuildTask(String projectPath) throws FileNotFoundException {
        super(projectPath);
        buildLauncher.addArguments("-x=test");
    }


    /**
     * Override method toString
     * @return string of building task
     */
    @Override
    public String toString() {
        return "Build task: ";
    }

    /**
     * Implementation of execute method
     */
    @Override
    public boolean execute() {

        buildLauncher.forTasks("build");

        try {
            buildLauncher.run();
        } catch (BuildException e) {
            System.out.println(e.getMessage());
            projectConnection.close();
            return false;
        }

        projectConnection.close();
        return true;
    }

}
