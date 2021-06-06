package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import static groovy.lang.Closure.DELEGATE_ONLY

/**
 * DSL for students
 * Teacher can specify group, students with id, name, repoURL and tasks to test
 */
class StudentsDSL {

    ArrayList<Student> st = new ArrayList<>()

    void student(@DelegatesTo(value = Student, strategy = DELEGATE_ONLY) final Closure closure) {

        Student student = new Student(GroupsDSL.groups.last().groupId)

        st << student

        closure.delegate = student
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class Student {

    String groupId
    String id
    String username
    String repoUrl
    ArrayList<String> tasks = []

    Student(String groupId) {
        this.groupId = groupId
    }

    void id(String id) {
        this.id = id
    }

    void username(String username) {
        this.username = username
    }

    void repoUrl(String url) {
        this.repoUrl = url
    }

    void tasks(String... tasks) {
        this.tasks.addAll(tasks)
    }
}