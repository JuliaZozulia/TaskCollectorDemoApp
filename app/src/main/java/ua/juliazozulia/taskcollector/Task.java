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

package ua.juliazozulia.taskcollector;

import android.content.Context;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Model class for task instance
 */

public class Task {

    private String title;
    private Date createdDate;
    private Date registeredDate;
    private Date dueDate;
    private String responsible;
    private List<String> pictures;
    private String description;

    //TODO replace with enum maybe
    private String state;

    public Task() {
    }

    /**
     * setup demo data fot task. Takes values from R.string
     */

    public void setDemoData(Context context) {
        title = context.getResources().getString(R.string.title);

        Calendar calendar = Calendar.getInstance();
        createdDate = calendar.getTime();

        calendar.add(Calendar.DATE, 1);
        registeredDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        dueDate = calendar.getTime();

        responsible = context.getResources().getString(R.string.responsible_placeholder);
        pictures = Arrays.asList(context.getResources().getStringArray(R.array.picture_urls));
        state = context.getResources().getString(R.string.in_progress);
        description = context.getResources().getString(R.string.placeholder);
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }
}
