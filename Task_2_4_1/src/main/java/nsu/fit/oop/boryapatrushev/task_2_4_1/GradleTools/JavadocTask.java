package nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools;

import org.gradle.tooling.BuildException;

import java.io.FileNotFoundException;

/**
 * Gradle javadoc task based on {@link BaseTask} specification
 * Generates javadoc of provided gradle project
 */
public class JavadocTask extends BaseTask {

    /**
     * Inherited from {@link BaseTask}
     */
    public JavadocTask(String projectPath) throws FileNotFoundException {
        super(projectPath);
    }

    /**
     * Override method toString
     * @return string of generating javadoc
     */
    @Override
    public String toString() {
        return "Generate javadoc: ";
    }

    /**
     * Implementation of execute method
     */
    @Override
    public boolean execute() {

        buildLauncher.forTasks("javadoc");

        try {
            buildLauncher.run();
        } catch (BuildException e) {
            projectConnection.close();
            return false;
        }

        projectConnection.close();
        return true;
    }
}
