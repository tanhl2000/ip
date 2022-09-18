package main;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import task.Task;

public class Ui {

    TaskList tasks;

    private final String UI_LINE_SPACING = "----------------------------------------";
    private final String greeting = "Hello! I'm Duke  \n" + "What can I do for you?";
    private final String markMessage = "Task has been marked done:";
    private final String unmarkMessage = "Task has been marked not done:";
    private final String addMessage = "Task added: \n";
    private final String deleteMessage = "Task deleted: \n";
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void greeting() {
        this.chat(this.greeting);
    }

    
    /** 
     * Shows a list of the tasks the chatbot holds to the user in tasklist index order, 
     * indicates the task type, done or not done, task description and task date
     * @param tasks the tasklist
     */
    public void list(TaskList tasks) {
        this.chat(tasks.toString());
    }

    
    /** 
     * Shows the new task that was added to the user, indicates the task type, task description and task date
     * @param newTask task to be added to tasklist
     */
    public void add(Task newTask) {
        this.chat(String.format("%s%s", addMessage, newTask));
    }

    
    /** 
     * Shows the task that was deleted to the user, indicates the task type, task description and task date
     * @param pos index of task to be deleted
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public void delete(int pos) throws DukeException{
        try {
            this.chat(String.format("%s%s", deleteMessage, this.tasks.get(pos)));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked done to the user, indicates the task type, done or not done, task description and task date
     * @param pos index of task to be marked done
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public void mark(int pos) throws DukeException {
        try {
            this.chat(markMessage + this.tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked not done to the user, indicates the task type, done or not done, task description and task date
     * @param pos index of task to be marked not done
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public void unmark(int pos) throws DukeException {
        try {
            this.chat(unmarkMessage + tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the error messages from exceptions
     * @param e exception thrown
     */
    public void errorMessage(DukeException e) {
        this.chat(e.getLocalizedMessage());
    }

    public void errorEnd() {
        this.chat("Error in saving Tasks, progress from current session is not saved :(");
    }

    public void bye() {
        this.chat("Bye bye! :D");
    }

    
    /** 
     * Shows a message to the user
     * @param message String representation of the message to be shown
     */
    public void chat(String message) {
        System.out.println(UI_LINE_SPACING + "\n" + message + "\n" + UI_LINE_SPACING);
    }
}
