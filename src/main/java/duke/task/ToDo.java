package duke.task;

import duke.exception.EmptyTaskDukeException;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
	/**
	 * Constructor of ToDo object.
	 *
	 * @param name Name of ToDo.
	 * @throws EmptyTaskDukeException If name is empty.
	 */
	public ToDo(String name) throws EmptyTaskDukeException {
		super(name);
		if (name == null) {
			throw new EmptyTaskDukeException("todo");
		}
	}

	/**
	 * Gives appropriate representation of ToDo.
	 *
	 * @return String representation of ToDo. Includes type of Task, isDone and name of Task.
	 */
	@Override
	public String toString() {
		return "[ToDo]" + super.toString();
	}
}
