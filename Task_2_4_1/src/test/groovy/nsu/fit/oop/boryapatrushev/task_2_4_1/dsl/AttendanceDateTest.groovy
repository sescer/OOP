package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import org.junit.Assert
import org.junit.Test

class AttendanceDateTest {
    @Test
    void test1() {
        ShellConfigurator.configureShell().evaluate(new File("src/test/resources/nsu/fit/oop/boryapatrushev/task_2_4_1/dsl/attendance_date.dsl"))
        Assert.assertEquals(AttendanceDateDSL.attendanceDate.initialDate, "Bruh")
    }
}
