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
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Model class for task instance
 */

public class Task {

    public static final String IN_PROGRESS = "in progress";
    public static final String DELAYED = "delayed";
    public static final String COMPLETED = "completed";

    //Thank you for hint about  @StringDef. I didn't know about it.
    // Have I implemented it correctly?

    //about google code style... I used to use auto generated methods for getters and setters (Alt+Insert). It's convenient for model class.
    // I suggest that getters like getmPictures() is incorrect.
    // So decision is not to use auto generated methods?

    @TaskState
    private String mState;
    private String mTitle;
    private Date mCreatedDate;
    private Date mRegisteredDate;
    private Date mDueDate;
    private String mResponsible;
    private List<String> mPictures;
    private String mDescription;


    /**
     * setup demo data fot task. Takes values from R.string
     */

    public void setDemoData(Context context) {

        mTitle = context.getResources().getString(R.string.title);

        Calendar calendar = Calendar.getInstance();
        mCreatedDate = calendar.getTime();

        calendar.add(Calendar.DATE, 1);
        mRegisteredDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        mDueDate = calendar.getTime();

        mResponsible = context.getResources().getString(R.string.responsible_placeholder);
        mPictures = Arrays.asList(context.getResources().getStringArray(R.array.picture_urls));
        mDescription = context.getResources().getString(R.string.placeholder);
        @TaskState String s = context.getResources().getString(R.string.placeholder_state);
        setState(s);
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

    public Date getRegisteredDate() {
        return mRegisteredDate;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public List<String> getPictures() {
        return mPictures;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getStateName(Context context) {
        switch (mState) {
            case IN_PROGRESS: {
                return context.getResources().getString(R.string.in_progress);
            }
            case DELAYED: {
                return context.getResources().getString(R.string.delayed);
            }
            case COMPLETED: {
                return context.getResources().getString(R.string.completed);
            }
            default: {
                return context.getResources().getString(R.string.unknown);
            }
        }
    }

    @TaskState
    public String getState() {
        return mState;
    }

    public void setState(@TaskState String currentState) {
        this.mState = currentState;
    }

    @StringDef({IN_PROGRESS, DELAYED, COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskState {
    }
}
