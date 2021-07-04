package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for checkpoints
 * Teacher can specify checkpoint name, points for sat/good/excellent marks
 */
class CheckPointsDSL {

    static ArrayList<CheckPoint> checkPoints = new ArrayList<>()

    static void checkpoint(String name, @DelegatesTo(value = CheckPoint, strategy = DELEGATE_ONLY) final Closure closure) {

        CheckPoint checkPoint = new CheckPoint(name)

        checkPoints << checkPoint

        closure.delegate = checkPoint
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}

class CheckPoint {

    String name
    Integer satPoints
    Integer goodPoints
    Integer excPoints

    CheckPoint(String name) {
        this.name = name
    }

    void satPoints(Integer points) {
        this.satPoints = points
    }

    void goodPoints(Integer points) {
        this.goodPoints = points
    }

    void excPoints(Integer points) {
        this.excPoints = points
    }
}
