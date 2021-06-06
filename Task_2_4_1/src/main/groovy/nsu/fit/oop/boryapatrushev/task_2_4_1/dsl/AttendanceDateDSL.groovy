package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for attendanceDate
 * Teacher can specify initial date to start count attendance from
 */
class AttendanceDateDSL {

    static AttendanceDate attendanceDate

    static void attendanceDate(@DelegatesTo(value = AttendanceDate, strategy = DELEGATE_ONLY) final Closure closure) {

        attendanceDate = new AttendanceDate()

        closure.delegate = attendanceDate
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}

class AttendanceDate {

    String initialDate

    void initialDate(String date) {
        this.initialDate = date
    }
}
