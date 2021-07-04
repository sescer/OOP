package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.GradleTools;


import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.BaseTask;
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.BuildTask;
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.JavadocTask;
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.TestsCheckingTask;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class GradleToolsTests {

    @Test
    public void test1() throws FileNotFoundException {

        BaseTask buildTask = new BuildTask(
                        "src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/GradleTools/Simple");
        boolean result = buildTask.execute();

        Assert.assertTrue(result);
    }

    @Test
    public void test2() throws FileNotFoundException {
        JavadocTask javadocTask = new JavadocTask(
                        "src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/GradleTools/Simple");

        boolean result = javadocTask.execute();
        Assert.assertTrue(result);
    }

    @Test
    public void test3() throws FileNotFoundException {
        TestsCheckingTask testTask = new TestsCheckingTask(
                        "src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/GradleTools/Simple");

        boolean result = testTask.execute();
        Assert.assertTrue(result);
    }
}
