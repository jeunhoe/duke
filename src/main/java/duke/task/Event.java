package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidTaskDukeException;

/**
 * Represents an Event task.
 */

public class Event extends Task {
	/**
	 * Represents date and time of event.
	 */
	private String atTime;

	/**
	 * Constructor of Event object.
	 *
	 * @param name   Name of Event.
	 * @param atTime atTime of Event.
	 * @throws EmptyTaskDukeException   If name is empty.
	 * @throws InvalidTaskDukeException If atTime input does not follow DD/MM/YYYY HHMM.
	 */
	public Event(String name, String atTime) throws EmptyTaskDukeException, InvalidTaskDukeException {
		super(name);
		if (name == null) {
			throw new EmptyTaskDukeException("event");
		}
		if (atTime == null) {
			throw new InvalidTaskDukeException("event");
		}
		this.atTime = atTime;
	}

	/**
	 * Accessor to get date and time of Event.
	 *
	 * @return
	 */
	public String getAtTime() {
		return atTime;
	}

	/**
	 * Gives appropriate representation of Event.
	 *
	 * @return String representation of Event. Includes type of Task, isDone, name of Task and atTime.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[Event]");
		stringBuilder.append(super.toString());
		stringBuilder.append(" (");
		stringBuilder.append(DateTime.create(atTime));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
