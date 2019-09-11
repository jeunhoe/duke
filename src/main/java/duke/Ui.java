package duke;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.InvalidTaskDukeException;

/**
 * Represents the user interface of Duke.
 */
public class Ui {

	/**
	 * Logo of Duke.
	 */
	public static final String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	/**
	 * Constructor of user interface.
	 */
	public Ui() {
	}

	/**
	 * Prints error when exception caught.
	 *
	 * @param e Exception that was caught.
	 */
	public String showError(Exception e) {
		if (e instanceof InvalidInputDukeException) {
			return "OOPS!!! I'm sorry, but I don't know what that means :-(";
		} else if (e instanceof EmptyTaskDukeException) {
			return String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage());
		} else if (e instanceof InvalidTaskDukeException) {
			return String.format("OOPS!!! Invalid input! Make sure your %s has a description and /at, /by or /after.",
					e.getMessage());
		} else {
			return e.getMessage(); // for undeclared exceptions
		}
	}


}
