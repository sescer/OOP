package nsu.fit.oop.boryapatrushev.task_2_4_1.stats

import javafx.util.Pair
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AdditionalPoint
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.CheckPoint
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Student

/**
 * Class for storing information needed for further use
 */
class StatsGenerator {

    HashMap<String, ArrayList<HashMap<String, String>>> stats = new HashMap<>()
    ArrayList<Pair<String, ArrayList<Pair<String, String>>>> commits = new ArrayList<>()

    ArrayList<AdditionalPoint> additionalPoints
    ArrayList<CheckPoint> checkPointsInfo
    ArrayList<Student> students

    HashMap<String, HashMap<String, Integer>> checkPoints = new HashMap<>()

    /**
     * Constructor
     * @param students array of students
     * @param additionalPoints array of additionalPoints
     * @param checkPoints array of checkpoints information
     */
    StatsGenerator(ArrayList<Student> students, ArrayList<AdditionalPoint> additionalPoints,
                   ArrayList<CheckPoint> checkPoints) {

        this.additionalPoints = additionalPoints
        this.checkPointsInfo = checkPoints
        this.students = students
    }

    /**
     * Add credit points for task of student
     * @param taskName name of task
     * @param maxPoints max available points for tasks
     * @param points actual points for student's task
     * @param name student's name
     * @param groupId student's group
     */
    void addCredit(String taskName, Integer maxPoints, Integer points, String name, String groupId) {

        String res = maxPoints + "/" + points
        stats.get(taskName).last().put("credit", res)
        stats.get(taskName).last().put("add", "0")

        for (AdditionalPoint ap : additionalPoints) {
            if (ap.studentName == name && ap.studentGroup == groupId && ap.task == taskName)
                stats.get(taskName).last().put("add", ap.points.toString())
        }

        def total = points + Integer.parseInt(stats.get(taskName).last().get("add"))
        stats.get(taskName).last().put("total", total.toString())

        if (checkPoints.get(name + " " + groupId).containsKey("total"))
            checkPoints.get(name + " " + groupId).put("total", checkPoints.get(name + " " + groupId).get("total") + total)
        else
            checkPoints.get(name + " " + groupId).put("total", total)

    }

    /**
     * Add commit number for specified student
     * @param name student's name
     * @param groupId student's group
     * @param cnt commits <date, commit cnt>
     */
    void addCommit(String name, String groupId, ArrayList<Pair<String, String>> cnt) {
        commits.add(new Pair<String, ArrayList<Pair<String, String>>>(name + " " + groupId, cnt))
    }

    /**
     * Add task to stats
     * @param taskName name of the task
     */
    void addTask(String taskName) {
        stats.put(taskName, new ArrayList<HashMap<String, String>>())
    }

    /**
     * Add student to stats
     * @param taskName name of the task
     * @param name student's name
     * @param groupId student's group
     */
    void addStudent(String taskName, String name, String groupId) {
        stats.get(taskName).add(new HashMap<String, String>())
        stats.get(taskName).last().put("name", name + " " + groupId)

        if (!checkPoints.containsKey(name + " " + groupId))
            checkPoints.put(name + " " + groupId, new HashMap<String, Integer>())
    }

    /**
     * Add test task result to stats
     * @param taskName name of the task
     * @param result result
     */
    void addTestResult(String taskName, String result) {
        stats.get(taskName).last().put("tests", result)
    }

    /**
     * Add checkstyle task result to stats
     * @param taskName name of the task
     * @param result result of execution
     */
    void addCheckstyleResult(String taskName, boolean result) {
        if (result) {
            stats.get(taskName).last().put("style", "+")
        } else {
            stats.get(taskName).last().put("style", "-")
        }
    }

    /**
     * Add build result to stats
     * @param taskName name of the task
     * @param result result of execution
     */
    void addBuildResult(String taskName, boolean result) {
        if (result) {
            stats.get(taskName).last().put("build", "+")
        } else {
            stats.get(taskName).last().put("build", "-")
        }
    }

    /**
     * Add javadoc task result to stats
     * @param taskName name of the task
     * @param result result of execution
     */
    void addJavadocResult(String taskName, boolean result) {
        if (result) {
            stats.get(taskName).last().put("doc", "+")
        } else {
            stats.get(taskName).last().put("doc", "-")
        }
    }

    /**
     * Calculate checkpoints results according to total points that student get
     */
    void calculateCheckPoints() {

        for (Student st : students) {

            if (checkPoints.containsKey(st.username + " " + st.groupId)) {
                def total = checkPoints.get(st.username + " " + st.groupId).get("total")

                for (CheckPoint ch : checkPointsInfo) {

                    if (total < ch.satPoints)
                        checkPoints.get(st.username + " " + st.groupId).put(ch.name, 2)
                    else if (total < ch.goodPoints)
                        checkPoints.get(st.username + " " + st.groupId).put(ch.name, 3)
                    else if (total < ch.excPoints)
                        checkPoints.get(st.username + " " + st.groupId).put(ch.name, 4)
                    else
                        checkPoints.get(st.username + " " + st.groupId).put(ch.name, 5)
                }
            }
        }
    }

}