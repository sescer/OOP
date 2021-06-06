package nsu.fit.oop.boryapatrushev.task_2_4_1.GitTools;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class for counting commits every week from initial date
 */
public class CommitsCounter {
    private static final int WEEK = 7;
    private final RunCMD commandRunner;
    private final String date;
    private final String initialDate;
    private final ArrayList<Pair<String, String>> datesToCheck = new ArrayList<>();

    /**
     * Constructor of class
     * @param initialDate date from start with YYYY-MM-DD
     */
    public CommitsCounter(String initialDate) {

        this.commandRunner = new RunCMD();
        this.initialDate = initialDate;

        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        date = dtf.format(LocalDate.now().minusDays(WEEK));

        LocalDate init = LocalDate.parse(initialDate);
        LocalDate cur = init;

        while (cur.isBefore(LocalDate.now())) {

            cur = cur.plusDays(WEEK);
            if (cur.isAfter(LocalDate.now())) {
                cur = LocalDate.now();
            }

            datesToCheck.add(new Pair<>(dtf.format(init), dtf.format(cur)));

            init = cur;
        }
    }

    /**
     * Method for counting commits in git repository of student placed in repoDirectory
     * every week since initialDate
     * @param repoDirectory path to repository
     * @return date, commits
     */
    public ArrayList<Pair<String, String>> countCommits(String repoDirectory) {

        ArrayList<Pair<String, String>> commits = new ArrayList<>();

        commandRunner.directory(repoDirectory);
        String line;

        for (Pair<String, String> dates : datesToCheck) {

            commandRunner.command("git rev-list master --after=" + dates.getKey() + " --before="
                    + dates.getValue() + " --count");

            try {
                commandRunner.start();

                BufferedReader inputReader = commandRunner.inputReader();
                line = inputReader.readLine();

                commandRunner.waitForExec();

                commits.add(new Pair<>(dates.getValue(), line));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return commits;
    }
}
