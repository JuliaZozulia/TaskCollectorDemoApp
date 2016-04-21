package ua.juliazozulia.taskcollector.Model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.TaskApplication;

/**
 * Created by Julia on 20.04.2016.
 */
public class TasksList {

    private static final String TAG = TasksList.class.getSimpleName();

    private TasksList() {
    }

    public static List<Task> getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static List<Task> createModel() {
        Type itemListType = new TypeToken<List<Task>>() {
        }.getType();

        List<Task> tasks;
        Gson gson = new Gson();
        tasks = gson.fromJson(loadJSONFromAsset(),
                itemListType);

        return tasks;
    }

    public static List<Task> getTasks(@States.TaskState String state) {
        List<Task> firstTask = new ArrayList<>();
        for (Task task : getInstance()) {
            if (task.getState().equals(state)) {
                firstTask.add(task);
            }
        }
        return firstTask;
    }

    public static String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = TaskApplication.getInstance().getAssets().open("tasks.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
            return null;
        }
        return json;
    }

    private static class LazyHolder {
        private static final List<Task> INSTANCE = createModel();
    }
}
