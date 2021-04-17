package ru.nsu.fit.oop.boryapatrushev.notebook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.cli.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;


public class Notebook {
    private ArrayList<Record> records;
    private final Gson gson;
    private String storagePath;

    public Notebook(String path) {
        gson = new Gson();
        try {
            File storage = new File(path);
            if (!storage.exists()) {
                storage.createNewFile();
            }
            storagePath = path;
            FileReader rd = new FileReader(storage);
            records = gson.fromJson(rd, new TypeToken<ArrayList<Record>>() {}.getType());
            if (records == null) {
                records = new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
            records = new ArrayList<>();
        }
    }

    public Notebook() {
        storagePath = "storage.json";
        records = new ArrayList<>();
        gson = new Gson();
    }

    /**
     * Get list of records
     *
     * @return list of records
     */
    public ArrayList<Record> getRecords() {
        return records;
    }

    /**
     * Add note to notebook
     *
     * @param title must be unique
     * @param text body of the note
     * @return true if title was unique and note was added
     */
    public boolean addNote(String title, String text) {
        if (checkUnique(title)) {
            records.add(new Record(title, text, new Date()));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove note with specified title.
     *
     * @param title of note to remove
     */
    public void removeNote(String title) {
        records.removeIf(r -> r.getTitle().equals(title));
    }

    /**
     * Check if title doesn't exist
     *
     * @param title to check
     * @return true if title is available
     */
    public boolean checkUnique(String title) {
        return records.stream().map(Record::getTitle).noneMatch(t -> t.equals(title));
    }

    /**
     * Write serialized notebook to storage file
     *
     * @throws IOException if exception occurred during writing file
     */
    public void saveNotebook() throws IOException {
        Writer wr = new FileWriter(new File(storagePath));
        wr.write(gson.toJson(records));
        wr.flush();
    }

    public Stream<Record> stream() {
        return records.stream();
    }

    private static Stream<Record> getBefore(Date date, Stream<Record> current) {
        return current.filter(r -> r.before(date));
    }

    private static Stream<Record> getAfter(Date date, Stream<Record> current) {
        return current.filter(r -> r.after(date));
    }

    private static Stream<Record> getInTitle(String substr, Stream<Record> current) {
        return current.filter(r -> r.inTitle(substr));
    }

    private static void printHelp(Options opts) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Notebook", opts);
        System.out.println("Only one action may be performed at a time.");
    }

    private static Options initOpts() {
        Options options = new Options();
        options.addOption(
                "s", "show", false, "Action. Show all notes. Can be used with -a, -b, -i options.");
        Option newOpt =
                new Option("n", "new", true, "Action. Add new note. Usage: -n \"Title\"  \"Text here\"");
        newOpt.setArgs(2);
        options.addOption(newOpt);
        options.addOption("r", "remove", true, "Action. Remove note. Usage: -r \"Title\"");
        options.addOption(
                "b",
                "before",
                true,
                "Filter notes before date to show. Usage -b \"Dec 25, 2020, 08:05:36 PM\"");
        options.addOption(
                "a",
                "after",
                true,
                "Filter notes after date to show. Usage -a \"Dec 25, 2020, 08:05:36 PM\"");
        Option incOpt =
                new Option(
                        "i",
                        "includes",
                        true,
                        "Filter notes including substrings in title to show. Usage -i \"Ti\" \"tle\"");
        incOpt.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(incOpt);
        options.addOption("h", "help", false, "Action. Display this message.");
        return options;
    }

    public static void main(String[] args) {
        Notebook nb = new Notebook("storage.json");
        CommandLineParser parser = new DefaultParser();
        Options options = initOpts();
        Stream<Record> selected;
        String[] actions = new String[3];
        actions[0] = "n";
        actions[1] = "s";
        actions[2] = "r";
        try {
            CommandLine line = parser.parse(options, args);
            int cnt = 0;
            for (String act : actions) {
                if (line.hasOption(act)) {
                    cnt++;
                }
            }
            if (cnt != 1) {
                printHelp(options);
                return;
            }
            if (line.hasOption("n")) {
                String[] nArgs = line.getOptionValues("n");
                if (nArgs.length != 2) {
                    printHelp(options);
                    return;
                }
                if (!nb.addNote(nArgs[0], nArgs[1])) {
                    System.out.println("Title already in use. Please choose another title.");
                    return;
                } else {
                    System.out.println("Added new note!");
                    nb.saveNotebook();
                    return;
                }
            }
            if (line.hasOption("h")) {
                printHelp(options);
                return;
            }
            if (line.hasOption("r")) {
                String rArg = line.getOptionValue("r");
                nb.removeNote(rArg);
                nb.saveNotebook();
                return;
            }

            if (line.hasOption("s")) {
                selected = nb.stream().sorted();
                if (line.hasOption("i")) {
                    for (String substr : line.getOptionValues("i")) {
                        selected = getInTitle(substr, selected);
                    }
                }
                DateFormat dateFmt = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss aa");
                if (line.hasOption("b")) {
                    String bfr = line.getOptionValue("b");
                    selected = getBefore(dateFmt.parse(bfr), selected);
                }
                if (line.hasOption("a")) {
                    String aft = line.getOptionValue("a");
                    selected = getAfter(dateFmt.parse(aft), selected);
                }
                selected.forEach(r -> System.out.println(r.getPrintable()));
            }
        } catch (Exception exp) {
            System.out.println("Error occurred: " + exp.getMessage());
        }
    }
}