package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for groups
 * Teacher can specify group with students in it
 */
class GroupsDSL {

    static ArrayList<Group> groups = new ArrayList<>()

    static void group(String groupId, @DelegatesTo(value = StudentsDSL, strategy = DELEGATE_ONLY) final Closure closure) {

        Group group = new Group(groupId)

        groups << group

        closure.delegate = group.getStudentsDSL()
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}

class Group {

    StudentsDSL studentsDSL
    String groupId

    Group(String groupId) {
        this.groupId = groupId
        studentsDSL = new StudentsDSL()
    }

}
