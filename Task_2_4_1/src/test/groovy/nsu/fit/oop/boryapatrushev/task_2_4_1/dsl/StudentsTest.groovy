package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import org.junit.Assert
import org.junit.Test

class StudentsTest {

    @Test
    void test1() {

        GroupsDSL.groups.clear()
        ShellConfigurator.configureShell().evaluate(new File("src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/dsl/students.dsl"))

        Assert.assertEquals(GroupsDSL.groups.get(0).getStudentsDSL().getSt().get(0).id, "1")
        Assert.assertEquals(GroupsDSL.groups.get(0).getStudentsDSL().getSt().get(0).username, "Vasya Pupkin")
        Assert.assertEquals(GroupsDSL.groups.get(0).getStudentsDSL().getSt().get(0).repoUrl, "Task.git")
        Assert.assertEquals(GroupsDSL.groups.get(0).getStudentsDSL().getSt().get(0).tasks.get(0), "TestTask")
    }
}
