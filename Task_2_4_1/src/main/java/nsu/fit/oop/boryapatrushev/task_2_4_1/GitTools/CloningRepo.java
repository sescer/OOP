package nsu.fit.oop.boryapatrushev.task_2_4_1.GitTools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Class for cloning git repositories of students
 */
public class CloningRepo {

    private final RunCMD commandRunner;

    /**
     * Constructor
     */
    public CloningRepo() {
        this.commandRunner = new RunCMD();
    }

    /**
     * Clone git repository from repoURL to cloneDirectoryPath
     * @param repoURL remote git repository URL
     * @param cloneDirectoryPath path to save files when cloning
     * @return true if clone succeeded, otherwise - not
     */
    public boolean cloningRepo(String repoURL, String cloneDirectoryPath) {

        try {
            FileUtils.deleteDirectory(new File(cloneDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        commandRunner.command("git clone " + repoURL + " " + cloneDirectoryPath);

        try {
            commandRunner.start();
            commandRunner.waitForExec();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }
}
