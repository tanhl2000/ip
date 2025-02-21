package command;

import exception.MeowerException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

public class MarkCommand extends Command {

    private int pos;
    
    public MarkCommand(String pos) {
        super();
        this.pos = Integer.parseInt(pos);
    }
    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case marks the task that the user specified as done
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException{
        tasks.mark(this.pos);
        return ui.mark(this.pos);
    }

    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    @Override
    public Task getTask() {
        return Task.empty();
    }
}
