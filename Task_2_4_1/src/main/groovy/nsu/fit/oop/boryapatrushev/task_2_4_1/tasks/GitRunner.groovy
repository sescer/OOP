package nsu.fit.oop.boryapatrushev.task_2_4_1.tasks


import javafx.util.Pair
import nsu.fit.oop.boryapatrushev.task_2_4_1.GitTools.CloningRepo
import nsu.fit.oop.boryapatrushev.task_2_4_1.GitTools.CommitsCounter
import nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.Student
import nsu.fit.oop.boryapatrushev.task_2_4_1.stats.StatsGenerator

/**
 * Runs git tasks {@link CloningRepo} and {@code CommitsCounter}
 */
class GitRunner {

    ArrayList<Student> students
    StatsGenerator statsGenerator
    CloningRepo cloningRepo
    CommitsCounter commitsCounter
    String path

    /**
     * Constructor
     * @param students array of students to clone and check commits
     * @param statsGenerator to save stats
     * @param initialDate initialDate for commit counter yyyy-MM-dd
     * @param path path to student's repositories
     */
    GitRunner(ArrayList<Student> students, StatsGenerator statsGenerator, String initialDate, String path) {
        this.students = students
        this.statsGenerator = statsGenerator
        this.cloningRepo = new CloningRepo()
        this.commitsCounter = new CommitsCounter(initialDate)
        this.path = path
    }

    /**
     * Execute git clone and count commits of specified students repositories
     */
    void execute() {

        for (Student st: students) {

            cloningRepo.cloningRepo(st.repoUrl, path + "/" + st.username.replaceAll("\\s+",""))
            println(st.username + " " + st.groupId + " clone complete")

            ArrayList<Pair<String, String>> commits = commitsCounter.countCommits(path + "/"
                    + st.username.replaceAll("\\s+",""))

            statsGenerator.addCommit(st.username, st.groupId, commits)
            println(st.username + " " + st.groupId + " commits count complete\n")
        }
    }

}