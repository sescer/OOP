package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl;

import org.junit.Assert
import org.junit.Test

class CheckPointsTest {

    @Test
    void test1() {
        ShellConfigurator.configureShell().evaluate(new File("src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/dsl/checkpoints.dsl"))
        Assert.assertEquals(CheckPointsDSL.checkPoints.get(0).name, "Task")
        Assert.assertEquals(CheckPointsDSL.checkPoints.get(0).satPoints, 50)
        Assert.assertEquals(CheckPointsDSL.checkPoints.get(0).goodPoints, 75)
        Assert.assertEquals(CheckPointsDSL.checkPoints.get(0).excPoints, 90)
    }
}