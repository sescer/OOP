package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for additional points of student
 * Teacher can specify student to add points, group, task to add points and amount of points to add
 */
class AdditionalPointsDSL {

    static ArrayList<AdditionalPoint> points = new ArrayList<>()

    static void add(@DelegatesTo(value = AdditionalPoint, strategy = DELEGATE_ONLY) final Closure closure) {

        AdditionalPoint additionalPoint = new AdditionalPoint()

        points << additionalPoint

        closure.delegate = additionalPoint
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}

class AdditionalPoint {

    String studentName
    String studentGroup
    Integer points
    String task

    void points(Integer points) {
        this.points = points
    }

    void student(String username) {
        this.studentName = username
    }

    void group(String groupId) {
        this.studentGroup = groupId
    }

    void task(String name) {
        this.task = name
    }
}