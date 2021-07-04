package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import java.text.SimpleDateFormat

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for tasks
 * Teacher can specify maximum available points for task and deadline of it
 */
class TasksDSL {

    static ArrayList<Task> tasks = new ArrayList<>()

    static void task(String name, @DelegatesTo(value = Task, strategy = DELEGATE_ONLY) final Closure closure) {

        Task task = new Task(name)

        tasks << task

        closure.delegate = task
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}

class Task {

    String name
    Integer points
    Date deadLine
    String pattern = "dd-MM-yyyy"

    Task(String name) {
        this.name = name
    }

    void points(Integer points) {
        this.points = points
    }

    void deadLine(String date) {
        deadLine = new SimpleDateFormat(pattern).parse(date)
    }

}