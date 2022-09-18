package command;

import exception.DukeException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Event;
import task.Task;

public class EventCommand extends Command{

    private String description;
    private String duration;

    public EventCommand(String description, String duration) {
        super();
        this.description = description;
        this.duration = duration;
    }

    
    /** 
     * Returns description inputted by the user
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    
    /** 
     * Checks if command will cause chatbot to end
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return false;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case adds the event task described by the user into the tasklist
     * @param tasks the tasklist of tasks from the chatbot instance
     * @param ui the ui from the chatbot instance
     * @param storage the storage from the chatbot instance
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Task newEvent = this.getTask();
            tasks.add(newEvent);
            ui.add(newEvent);
        } catch (DukeException e) {
            throw e;
        }
    }

    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     * @throws DukeException Main exception class that is extended by the various custom exceptions
     */
    @Override
    public Task getTask() throws DukeException{
        try {
            return new Event(description, duration);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }
}
