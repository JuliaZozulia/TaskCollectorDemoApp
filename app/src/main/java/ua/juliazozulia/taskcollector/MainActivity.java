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

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCreateDate;
    private TextView mRegisteredDate;
    private TextView mDueToDate;
    private TextView mResponsible;
    private TextView mState;
    private TextView mDescription;
    private RecyclerView mRecyclerView;
    private Task mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mTask = new Task();
        mTask.setDemoData(this);
        bindViews();
        setupViews();
        bindListeners();
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.picture_recycler_view);
        mCreateDate = (TextView) findViewById(R.id.create_date);
        mRegisteredDate = (TextView) findViewById(R.id.register_date);
        mDueToDate = (TextView) findViewById(R.id.due_date);
        mResponsible = (TextView) findViewById(R.id.responsible);
        mState = (TextView) findViewById(R.id.state);
        mDescription = (TextView) findViewById(R.id.description);
    }

    private void setupViews() {
        setTitle(mTask.getTitle());
        mState.requestFocus();
        setupRecyclerView();

        DateFormat formatter = SimpleDateFormat.getDateInstance();
        mCreateDate.setText(formatter.format(mTask.getCreatedDate()));
        mRegisteredDate.setText(formatter.format(mTask.getRegisteredDate()));
        mDueToDate.setText(formatter.format(mTask.getDueDate()));

        mResponsible.setText(mTask.getResponsible());
        mDescription.setText(mTask.getDescription());
        mState.setText(mTask.getState());
    }

    private void setupRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalSpacesItemDecoration(8));
        mRecyclerView.setAdapter(new PictureAdapter(mTask.getPictures()));
    }

    private void bindListeners() {
        mCreateDate.setOnClickListener(this);
        mRegisteredDate.setOnClickListener(this);
        mDueToDate.setOnClickListener(this);
        mResponsible.setOnClickListener(this);
        mState.setOnClickListener(this);
        mDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }
}
