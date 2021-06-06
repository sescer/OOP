package nsu.fit.oop.boryapatrushev.task_2_4_1

import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AdditionalPointsDSL
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AttendanceDateDSL
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.CheckPointsDSL
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Group
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.GroupsDSL
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Student
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.TasksDSL
import nsu.fit.oop.boryapatrushev.task_2_4_1.html.HtmlGenerator
import nsu.fit.oop.boryapatrushev.task_2_4_1.stats.StatsGenerator
import nsu.fit.oop.boryapatrushev.task_2_4_1.tasks.GitRunner
import nsu.fit.oop.boryapatrushev.task_2_4_1.tasks.TasksRunner
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

/**
 * Point of entry
 */
class Main {

    static void main(String[] args) {

        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.GroupsDSL", "group"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.TasksDSL", "task"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AdditionalPointsDSL", "add"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.CheckPointsDSL", "checkpoint"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AttendanceDateDSL", "attendanceDate"

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(importCustomizer)

        GroovyShell groovyShell = new GroovyShell(configuration)

        groovyShell.evaluate new File("application/config/students.dsl")
        groovyShell.evaluate new File("application/config/tasks.dsl")
        groovyShell.evaluate new File("application/config/add.dsl")
        groovyShell.evaluate new File("application/config/checkpoints.dsl")
        groovyShell.evaluate new File("application/config/attendance_date.dsl")

        ArrayList<Student> students = []

        for (Group gr : GroupsDSL.getGroups()) {
            students.addAll(gr.getStudentsDSL().getSt())
        }

        StatsGenerator statsGenerator = new StatsGenerator(students, AdditionalPointsDSL.points, CheckPointsDSL.checkPoints)

        GitRunner gitRunner = new GitRunner(students, statsGenerator, AttendanceDateDSL.attendanceDate.initialDate, "application/repositories")
        gitRunner.execute()

        TasksRunner tasksRunner = new TasksRunner(students, TasksDSL.getTasks(), statsGenerator, "application/repositories")
        tasksRunner.execute()

        HtmlGenerator generator = new HtmlGenerator(statsGenerator, "application/results.html")
        File file = generator.generateHtml()

        println("Result html-file: " + file.getAbsolutePath())
    }
}