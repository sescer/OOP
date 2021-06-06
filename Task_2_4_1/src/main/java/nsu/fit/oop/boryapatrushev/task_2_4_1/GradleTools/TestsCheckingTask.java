package nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools;

import org.gradle.tooling.BuildException;
import org.gradle.tooling.events.OperationType;
import org.gradle.tooling.events.ProgressEvent;
import org.gradle.tooling.events.ProgressListener;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Gradle test task based on {@link BaseTask} specification
 * Tests provided gradle project
 */
public class TestsCheckingTask extends BaseTask {

    private int ignoredTests = 0;
    private int successfulTests = 0;
    private int failedTests = 0;
    private int totalTests = 0;
    private final ArrayList<String> failedNames = new ArrayList<>();

    /**
     * Inherited from {@link BaseTask}
     */
    public TestsCheckingTask(String projectPath) throws FileNotFoundException {
        super(projectPath);
        buildLauncher.addProgressListener(new CustomListener(), OperationType.TEST);
    }

    /**
     * Override method toString
     * @return string of tests
     */
    @Override
    public String toString() {
        return "Tests: ";
    }

    /**
     * Implementation of execute method
     */
    @Override
    public boolean execute() {

        buildLauncher.forTasks("test");

        try {
            buildLauncher.run();
        } catch (BuildException e) {
            projectConnection.close();
            return false;
        }

        projectConnection.close();
        return true;
    }

    /**
     * Get number of ignored tests
     * @return number of ignored tests
     */
    public int getIgnoredTests() {
        return ignoredTests;
    }

    /**
     * Get number of succeeded tests
     * @return number of succeeded tests
     */
    public int getSuccessfulTests() {
        return successfulTests;
    }

    /**
     * Get number of failed tests
     * @return number of failed tests
     */
    public int getFailedTests() {
        return failedTests;
    }

    /**
     * Get total number of tests
     * @return total number of tests
     */
    public int getTotalTests() {
        return totalTests;
    }

    /**
     * Get names of failed tests
     * @return collection of failed tests names
     */
    public ArrayList<String> getFailedNames() {
        return failedNames;
    }

    /**
     * Class that implements {@link ProgressListener} for tracking
     * and checking task progress
     */
    class CustomListener implements ProgressListener {

        String pattern = "^Test .* (.*)";

        @Override
        public void statusChanged(ProgressEvent progressEvent) {

            String event = progressEvent.getDisplayName();

            if (!event.contains("class")) {
                if (event.matches(pattern)) {

                    String[] elem = event.split(" ");

                    switch (elem[elem.length - 1]) {
                        case "succeeded":
                            successfulTests++;
                            break;
                        case "skipped":
                            ignoredTests++;
                            break;
                        case "failed":
                            failedTests++;
                            failedNames.add(elem[elem.length - 2]);
                            break;
                        case "started":
                            totalTests++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
