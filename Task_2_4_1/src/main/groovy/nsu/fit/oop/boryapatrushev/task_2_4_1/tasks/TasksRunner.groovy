package nsu.fit.oop.boryapatrushev.task_2_4_1.tasks

import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.BaseTask
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.BuildTask
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.CheckstyleTask
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.JavadocTask
import nsu.fit.oop.boryapatrushev.task_2_4_1.GradleTools.TestsCheckingTask
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Student
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Task
import nsu.fit.oop.boryapatrushev.task_2_4_1.stats.StatsGenerator

/**
 * Class that executes gradle tasks {@link BaseTask}
 * build, javadoc, checkstyle, tests
 */
class TasksRunner {

    ArrayList<Student> students
    ArrayList<Task> tasks
    StatsGenerator statsGenerator
    String path

    /**
     * Constructor
     * @param students Array of students to test
     * @param tasks Array of tasks to test students with
     * @param statsGenerator to save stats
     * @param path to student's repositories
     */
    TasksRunner(ArrayList<Student> students, ArrayList<Task> tasks, StatsGenerator statsGenerator, String path) {
        this.students = students
        this.tasks = tasks
        this.statsGenerator = statsGenerator
        this.path = path
    }

    /**
     * Execute all tasks(Build, Javadoc, CheckStyle and TestsChecking)
     */
    void execute() {

        for (Task t : tasks) {

            statsGenerator.addTask(t.name)

            for (Student st : students) {
                if (st.tasks.contains(t.name)) {

                    boolean buildResult
                    boolean docResult
                    boolean checkstyleResult
                    boolean testResult

                    statsGenerator.addStudent(t.name, st.username, st.groupId)
                    def targetPath = path + "/" + st.username.replaceAll("\\s+", "") + "/" + t.name

                    BaseTask buildTask = new BuildTask(targetPath)
                    BaseTask checkstyleTask = new CheckstyleTask(targetPath)
                    BaseTask javadocTask = new JavadocTask(targetPath)
                    BaseTask testsCheckingTask = new TestsCheckingTask(targetPath)

                    println(st.username + " " + st.groupId + " " + t.name)

                    buildResult = buildTask.execute()
                    statsGenerator.addBuildResult(t.name, buildResult)
                    println("\tBuild complete")


                    checkstyleResult = checkstyleTask.execute()
                    if (checkstyleResult) {
                        if (checkstyleTask.getWarningsCnt() == 0)
                            statsGenerator.addCheckstyleResult(t.name, true)
                        else
                            statsGenerator.addCheckstyleResult(t.name, false)
                    } else {
                        statsGenerator.addCheckstyleResult(t.name, false)
                    }
                    println("\tCheckStyle complete")


                    docResult = javadocTask.execute()
                    statsGenerator.addJavadocResult(t.name, docResult)
                    println("\tJavadoc complete")


                    testResult = testsCheckingTask.execute()
                    if (testResult) {
                        def res = testsCheckingTask.successfulTests + "/" + testsCheckingTask.ignoredTests + "/" + testsCheckingTask.failedTests
                        statsGenerator.addTestResult(t.name, res)
                    } else {
                        statsGenerator.addTestResult(t.name, "failed")
                    }
                    println("\tTest complete\n")


                    if (buildResult && checkstyleResult && docResult && testResult
                            && testsCheckingTask.failedTests == 0 && checkstyleTask.getWarningsCnt() == 0)
                        statsGenerator.addCredit(t.name, t.points, t.points, st.username, st.groupId)
                    else
                        statsGenerator.addCredit(t.name, t.points, 0, st.username, st.groupId)

                }
            }
        }
        statsGenerator.calculateCheckPoints()
    }
}
