package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.PrintListCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.InvalidTaskDukeException;

import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a Parser used to read user input in Duke.
 */
public class Parser {
	/**
	 * Parses input and returns a Command based on input.
	 *
	 * @param fullCommand String of user input.
	 * @return Command based on user input.
	 * @throws InvalidInputDukeException If parser does not understand user input.
	 * @throws EmptyTaskDukeException    If user did not input task name.
	 * @throws InvalidTaskDukeException  If user did not input appropriate DateTime for Event and Deadline.
	 */
	public static Command parse(String fullCommand) throws InvalidInputDukeException, EmptyTaskDukeException, InvalidTaskDukeException {
		Scanner scanner = new Scanner(fullCommand);
		if (scanner.hasNext()) {
			String toProcess = scanner.next();
			switch (toProcess) {
			case "list":
				return new PrintListCommand();
			case "bye":
				return new ExitCommand();
			case "done":
				return new DoneCommand(scanner.nextInt());
			case "delete":
				return new DeleteCommand(scanner.nextInt());
			case "find":
				return new FindCommand(scanner.next());
			case "todo":
			case "deadline":
			case "event":
			case "doafter":
				return createAddCommand(fullCommand);
			default:
				throw new InvalidInputDukeException();
			}
		} else {
			throw new InvalidInputDukeException();
		}
	}

	/**
	 * Abstraction used to create AddCommand from given String.
	 *
	 * @param fullCommand String of user input.
	 * @return AddCommand based on user input.
	 * @throws EmptyTaskDukeException   If user did not input task name.
	 * @throws InvalidTaskDukeException If user did not input appropriate DateTime for Event and Deadline.
	 */
	private static Command createAddCommand(String fullCommand) throws EmptyTaskDukeException, InvalidTaskDukeException {
		Task taskToAdd = null;
		String checkType[] = Arrays.copyOf(fullCommand.split(" ", 2), 2);
		String typeOfTask = checkType[0];
		String theTask = checkType[1];
		// switch statement for todo, deadline, task
		switch (typeOfTask) {
		case "todo":
			taskToAdd = new ToDo(theTask);
			break;
		case "deadline":
			String taskByWhen[];
			if (theTask == null) {
				taskByWhen = new String[]{null, null};
			} else {
				taskByWhen = Arrays.copyOf(theTask.split(" /by ", 2), 2);
			}
			String deadlineTask = taskByWhen[0];
			String byWhen = taskByWhen[1];
			taskToAdd = new Deadline(deadlineTask, byWhen);
			break;
		case "event":
			String taskAtTime[];
			if (theTask == null) {
				taskAtTime = new String[]{null, null};
			} else {
				taskAtTime = Arrays.copyOf(theTask.split(" /at ", 2), 2);
			}
			String eventTask = taskAtTime[0];
			String atTime = taskAtTime[1];
			taskToAdd = new Event(eventTask, atTime);
			break;
		case "doafter":
			String taskAfter[];
			if (theTask == null) {
				taskAfter = new String[]{null, null};
			} else {
				taskAfter = Arrays.copyOf(theTask.split(" /after ", 2), 2);
			}
			String doAfterTask = taskAfter[0];
			String after = taskAfter[1];
			taskToAdd = new DoAfter(doAfterTask, after);
		default:
			break;
		}
		assert (taskToAdd != null);
		return new AddCommand(taskToAdd);
	}

}
