/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Julia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.juliazozulia.taskcollector.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ua.juliazozulia.taskcollector.TaskApplication;

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
