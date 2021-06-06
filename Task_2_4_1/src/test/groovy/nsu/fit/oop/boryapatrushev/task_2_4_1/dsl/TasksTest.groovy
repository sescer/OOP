package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl


import org.junit.Assert
import org.junit.Test

import java.text.SimpleDateFormat

class TasksTest {

    @Test
    void test1() {
        String pattern = "dd-MM-yyyy"
        ShellConfigurator.configureShell().evaluate(new File("src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/dsl/tasks.dsl"))
        Assert.assertEquals(TasksDSL.tasks.get(0).name, "Task")
        Assert.assertEquals(TasksDSL.tasks.get(0).points, 0)
        Assert.assertEquals(TasksDSL.tasks.get(0).deadLine, new SimpleDateFormat(pattern).parse("01-01-2021"))
    }
}
