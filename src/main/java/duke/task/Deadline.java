package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidTaskDukeException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

	/**
	 * Represents due date (byWhen) of the Deadline.
	 */
	private String byWhen;

	/**
	 * Constructor of Deadline object.
	 *
	 * @param name   Name of Deadline.
	 * @param byWhen byWhen of Deadline.
	 * @throws EmptyTaskDukeException   If name is empty.
	 * @throws InvalidTaskDukeException If byWhen input does not follow DD/MM/YYYY HHMM.
	 */
	public Deadline(String name, String byWhen) throws EmptyTaskDukeException, InvalidTaskDukeException {
		super(name);
		if (name == null) {
			throw new EmptyTaskDukeException("deadline");
		}
		if (byWhen == null) {
			throw new InvalidTaskDukeException("deadline");
		}
		this.byWhen = byWhen;
	}

	/**
	 * Accessor to get due date (byWhen) of Deadline.
	 *
	 * @return String representation of due date.
	 */
	public String getByWhen() {
		return byWhen;
	}

	/**
	 * Gives appropriate representation of Deadline.
	 *
	 * @return String representation of Deadline. Includes type of Task, isDone, name of Task and byWhen.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[Deadline]");
		stringBuilder.append(super.toString());
		stringBuilder.append(" (");
		stringBuilder.append(DateTime.create(byWhen));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
