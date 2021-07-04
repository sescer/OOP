package nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools;

import org.gradle.tooling.BuildException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gradle checkStyle task based on {@link BaseTask} specification
 * Check code style of provided gradle project according to google java style
 */
public class CheckstyleTask extends BaseTask {

    private Integer warningsCnt;

    /**
     * Inherited from {@link BaseTask}
     */
    public CheckstyleTask(String projectPath) throws FileNotFoundException {
        super(projectPath);
        buildLauncher.addArguments("src\\main\\resources\\nsu\\fit\\oop\\boryapatrushev\\task_2_4_1\\init.gradle");
    }

    /**
     * Override method toString
     * @return string of checkstyle
     */
    @Override
    public String toString() {
        return "Checkstyle: ";
    }

    /**
     * Implementation of execute method
     */
    @Override
    public boolean execute() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        buildLauncher.forTasks("checkstyleMain");
        buildLauncher.setStandardOutput(out);

        try {
            buildLauncher.run();

        } catch (BuildException e) {
            projectConnection.close();
            return false;
        }

        Pattern pattern = Pattern.compile("Checkstyle violations by severity: \\[warning:\\d+]");
        Matcher matcher = pattern.matcher(out.toString());

        if (matcher.find()) {
            String match = matcher.group(0);

            pattern = Pattern.compile("\\d+");
            matcher = pattern.matcher(match);

            warningsCnt = Integer.parseInt(matcher.group(0));

        } else {
            warningsCnt = 0;
        }

        projectConnection.close();
        return true;
    }

    /**
     * Get number of code formatting mistakes
     * @return number of mistakes
     */
    public Integer getWarningsCnt() {
        return warningsCnt;
    }
}
