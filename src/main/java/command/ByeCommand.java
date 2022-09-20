package command;

import exception.MeowerException;
import exception.MeowerFileAddressInvalidException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

/**
 * Represents a "bye" command from the user that will end the chatbot.
 * @extends Command
 */

public class ByeCommand extends Command {

    private String logFileAddress = "";
    
    public ByeCommand() {
    }

    public ByeCommand(String newAddress) {
        this.logFileAddress = newAddress;
    }
 
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case saves the chatbot logs
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        try {
            if (this.logFileAddress.equals("")) {
                return ui.bye(storage.saveToFile());
            } else {
                return ui.bye(storage.saveToFile(this.logFileAddress));
            }
        } catch (MeowerException e) {
            throw e;
        }
    }
    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    public Task getTask() {
        return Task.empty();
    }

}
