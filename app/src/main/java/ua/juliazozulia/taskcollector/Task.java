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

    @Category.TaskCategory
    private String mCategory;
    @States.TaskState
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
        @States.TaskState String s = context.getResources().getString(R.string.placeholder_state);
        setState(s);
        @Category.TaskCategory String c = context.getResources().getString(R.string.placeholder_category);
        setCategory(c);
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
        return States.getStateName(context, mState);
    }

    public void setState(@States.TaskState String currentState) {
        this.mState = currentState;
    }

    public String getCategoryName(Context context) {
        return Category.getCategoryName(context, mCategory);
    }

    public void setCategory(@Category.TaskCategory String category) {
        mCategory = category;
    }
}
