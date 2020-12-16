package ru.nsu.fit.oop.boryapatrushev.recordbook;
import java.util.ArrayList;
import java.util.AbstractMap;

public class Record {
    private Credit finalProject;
    private final ArrayList<ArrayList<AbstractMap.SimpleEntry<String, Credit>>> records;
    private final int marksInDiploma;
    private int currentFinalMarks;

    /**
     * Record of credit constructor. Has constant number of semesters
     *
     * @param semesters number of semesters in programme.
     * @param _marksInDiploma number of marks that wil be in diploma. Can be changed later.
     * @throws IllegalArgumentException if any argument is negative.
     */
    public Record(int semesters, int _marksInDiploma) {
        if (semesters < 0) {
            throw new IllegalArgumentException("Negative  number of semesters doesn't exist");
        }
        if (_marksInDiploma < 0) {
            throw new IllegalArgumentException("Negative  number of marks in diploma doesn't exist");
        }
        marksInDiploma = _marksInDiploma;
        records = new ArrayList<>();
        for (int i = 0; i < semesters; i++) {
            records.add(new ArrayList<>());
        }
        currentFinalMarks = 0;
    }

    /**
     * Add a credit in semester. Can be final or intermediate
     *
     * @param title is title of discipline to be stored.
     * @param mark numerical evaluation of semester results.
     * @param isFinal if mark should be included in diploma.
     * @param semester number of semester of this mark, counting from 1.
     * @throws IllegalStateException if trying to add mark to already full diploma.
     */
    public void addCredit(String title, int mark, boolean isFinal, int semester) {
        if (isFinal) {
            if (currentFinalMarks == marksInDiploma) {
                throw new IllegalStateException("All marks are already in diploma");
            }
            currentFinalMarks++;
        }
        records.get(semester - 1).add(new AbstractMap.SimpleEntry<>(title, new Credit(mark, isFinal)));
    }

    /**
     * Add a credit in semester. Can be final or intermediate
     *
     * @param title is title of discipline to be stored.
     * @param passed if student has successfully passed course.
     * @param isFinal if mark should be included in diploma.
     * @param semester number of semester of this mark, counting from 1.
     */
    public void addCredit(String title, boolean passed, boolean isFinal, int semester) {
        records
                .get(semester - 1)
                .add(new AbstractMap.SimpleEntry<>(title, new Credit(passed, isFinal)));
    }

    /**
     * Set mark for final year project.
     *
     * @param mark to be set.
     */
    public void setFinalProject(int mark) {
        finalProject = new Credit(mark, true);
    }


    /**
     * Get average of all marks in record book
     *
     * @return average mark.
     */
    public double getAverage() {
        Long stats =
                records.stream()
                        .map(
                                sm ->
                                        sm.stream()
                                                .filter(p -> p.getValue().isMark())
                                                .map(p -> (long) p.getValue().getMark())
                                                .reduce(0L, Long::sum))
                        .reduce(0L, Long::sum);
        Long count =
                records.stream()
                        .map(sm -> sm.stream().filter(p -> p.getValue().isMark()).count())
                        .reduce(0L, Long::sum);
        return stats.doubleValue() / count;
    }

    /**
     * Determine if there will be an upgraded scholarship this semester
     *
     * @param semester - number of semester(counting from 1)
     * @return if student would have an upgraded scholarship this semester
     */
    public boolean hasAward(int semester) {
        return records.get(semester - 1).stream()
                .allMatch(
                        p ->
                                p.getValue().getMark() > 3
                                        || (p.getValue().getMark() == 1 && !p.getValue().isMark()));
    }

    /**
     * Determine if student still can get a special diploma.
     * Explanation: all marks should be good or excellent
     *
     * @return if special diploma is possible.
     */
    public boolean canHaveSpecialDiploma() {
        boolean goodMarks =
                records.stream()
                        .allMatch(
                                (sm) ->
                                        sm.stream()
                                                .allMatch(
                                                        p ->
                                                                p.getValue().getMark() > 3
                                                                        || (p.getValue().getMark() == 1 && !p.getValue().isMark())));
        if (!goodMarks) return false;
        Long stats =
                records.stream()
                        .map(
                                sm ->
                                        sm.stream()
                                                .filter(p -> p.getValue().isMark() && p.getValue().isFinal())
                                                .map(p -> (long) p.getValue().getMark())
                                                .reduce(0L, Long::sum))
                        .reduce(0L, Long::sum);
        double bestAvg =
                (stats.doubleValue() + 5 * (marksInDiploma - currentFinalMarks)) / marksInDiploma;
        if (finalProject != null && finalProject.getMark() != 5) {
            return false;
        }
        return bestAvg >= 4.75;
    }
}
