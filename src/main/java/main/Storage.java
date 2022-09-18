package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import exception.DukeException;
import exception.DukeFileNotFoundException;
import exception.DukeIOException;
import exception.InvalidCommandException;
import task.Task;

public class Storage {

    private static String logFileAddress = "./ip/dukeLog.txt";

    private ArrayList<String[]> loggedTasks = new ArrayList<String[]>();
    private TaskList existingTasks;
    private Ui ui;
    private Storage storage;

    public Storage(TaskList existingTasks, Ui ui, Storage storage) {
        this.existingTasks = existingTasks;
        this.ui = ui;
        this.storage = storage;
    }
    
    
    /** 
     * loadLog loads the tasks stored in the log file into the curren tasklist, 
     * throws DukeExceptions if theres issues with the format of the log file or file address is invalid
     * @return TaskList
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public TaskList loadLog() throws DukeException{
        try {
            Parser parser = new Parser();
            Scanner fileReader = new Scanner(new File(logFileAddress));
            TaskList newTaskList = new TaskList();
            while (fileReader.hasNextLine()) {
                String nextLogLine = fileReader.nextLine();
                String[] parsedLogLine = nextLogLine.split(",", 2);
                loggedTasks.add(parsedLogLine);
            }
            fileReader.close();
            for (String[] loggedTask : loggedTasks) {
                boolean isDone = Integer.parseInt(loggedTask[0]) == 1;
                Command parsedCommand = parser.parse(loggedTask[1]);
                Task newTask = parsedCommand.getTask();
                if (isDone) {
                    newTask.mark();
                }
                newTaskList.add(parsedCommand.getTask());
            }
            return newTaskList;
        } catch (InvalidCommandException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Saves the current tasklist configuration into the log file
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public void cleanUp() throws DukeException {
        if (existingTasks.getSize() == 0) {
            this.sendNoTasksMessage();
        }
        try {
            FileWriter fileWriter = new FileWriter(logFileAddress);
            int numOfTasks = 0;
            for (Task task : existingTasks.getTasks()) {
                fileWriter.write(task.log());
                numOfTasks += 1;
            }
            fileWriter.close();
            this.sendEndMessage(numOfTasks);
        }
        catch (IOException e) {
            throw new DukeIOException("Error in saving Tasks\n");
        }
    }

    
    /** 
     * Shows to the user that the tasks have been saved
     * @param numOfTasks integer value of the number of tasks in the tasklist
     */
    public void sendEndMessage(int numOfTasks) {
        this.ui.chat(String.format("Saved %d tasks to log file\n", numOfTasks));
    }

    /**
     * Shows to the user that nothing was saved to the log file
     */
    public void sendNoTasksMessage() {
        this.ui.chat("No tasks saved to log file \n");
    }

}
