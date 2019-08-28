import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static Task createTaskFromInput(String[] inputArray) throws InvalidTaskDukeException, EmptyTaskDukeException {
        Task createdTask = null;
        switch(inputArray[0]) {
            case "T":
                createdTask = new ToDo(inputArray[2]);
                break;
            case "D":
                createdTask = new Deadline(inputArray[2], inputArray[3]);
                break;
            case "E":
                createdTask = new Event(inputArray[2], inputArray[3]);
                break;
        }
        if (createdTask != null) {
            if (inputArray[1].equals("1")) {
                createdTask.setDone(true);
            } else {
                createdTask.setDone(false);
            }
        }
        return createdTask;
    }

    private static String generateLineFromTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();

        // Determine type of Task and add to line
        if (task instanceof ToDo) {
            stringBuilder.append("T");
        } else if (task instanceof Deadline) {
            stringBuilder.append("D");
        } else if (task instanceof Event) {
            stringBuilder.append("E");
        }

        stringBuilder.append(" | ");

        // Determine if done and add to line
        if (task.isDone()) {
            stringBuilder.append("1");
        } else {
            stringBuilder.append("0");
        }

        stringBuilder.append(" | ");

        // Add name to line
        if (task instanceof ToDo) {
            stringBuilder.append(task.getName());
        } else if (task instanceof Event) {
            stringBuilder.append(task.getName());
            stringBuilder.append(" | ");
            Event event = (Event) task;
            stringBuilder.append(event.getAtTime());
        } else if (task instanceof Deadline) {
            stringBuilder.append(task.getName());
            stringBuilder.append(" | ");
            Deadline deadline = (Deadline) task;
            stringBuilder.append(deadline.getByWhen());
        }

        String lineToAdd = stringBuilder.toString();
        return lineToAdd;
    }

    public List<Task> loadList() throws LoadingErrorDukeException{
        List<Task> loadedList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readerLine = bufferedReader.readLine(); //initialise first line

            while (readerLine != null) {
                String[] readArray = readerLine.split("\\s\\|\\s");
                Task task = createTaskFromInput(readArray);
                loadedList.add(task);
                readerLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new LoadingErrorDukeException();
        } catch (IOException | EmptyTaskDukeException | InvalidTaskDukeException e) {
            e.printStackTrace();
        }
        return loadedList;
    }

    public void saveList(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (!tasks.isEmpty()) {
                Task task = tasks.remove(1);
                String line = generateLineFromTask(task);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
