package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidTaskDukeException;

public class DoAfter extends Task {

	private String after;

	/**
	 * Constructor of Task.
	 *
	 * @param name Sets name of Task to input.
	 */
	public DoAfter(String name, String after) throws EmptyTaskDukeException, InvalidTaskDukeException {
		super(name);
		if (name == null) {
			throw new EmptyTaskDukeException("doafter");
		}
		if (after == null) {
			throw new InvalidTaskDukeException("doafter");
		}
		this.after = after;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public boolean isDone() {
		return super.isDone();
	}

	@Override
	public void setDone(boolean done) {
		super.setDone(done);
	}

	@Override
	public void done() {
		super.done();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[DoAfter]");
		stringBuilder.append(super.toString());
		stringBuilder.append(" (");
		stringBuilder.append(DateTime.create(after));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
