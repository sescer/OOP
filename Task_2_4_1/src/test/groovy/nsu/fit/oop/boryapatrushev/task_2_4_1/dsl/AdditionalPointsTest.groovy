package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import org.junit.Assert
import org.junit.Test


class AdditionalPointsTest {

    @Test
    void test1() {
        ShellConfigurator.configureShell().evaluate(new File("src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/dsl/add.dsl"))
        Assert.assertEquals(AdditionalPointsDSL.getPoints().get(0).points, 100)
        Assert.assertEquals(AdditionalPointsDSL.getPoints().get(0).studentGroup, "11111")
        Assert.assertEquals(AdditionalPointsDSL.getPoints().get(0).task, "Task")
        Assert.assertEquals(AdditionalPointsDSL.getPoints().get(0).studentName, "Vasya Pupkin")
    }
}
