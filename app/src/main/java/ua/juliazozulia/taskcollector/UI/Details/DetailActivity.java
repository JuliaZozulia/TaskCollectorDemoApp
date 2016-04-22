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

package ua.juliazozulia.taskcollector.UI.Details;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ua.juliazozulia.taskcollector.Model.States;
import ua.juliazozulia.taskcollector.Model.Task;
import ua.juliazozulia.taskcollector.Model.TasksList;
import ua.juliazozulia.taskcollector.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TASK = "task";
    private TextView mCreateDate;
    private TextView mRegisteredDate;
    private TextView mDueToDate;
    private TextView mResponsible;
    private TextView mCategory;
    private TextView mState;
    private TextView mDescription;
    private RecyclerView mRecyclerView;
    private Task mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initActionbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int position = Integer.parseInt(getIntent().getStringExtra(EXTRA_TASK));
        mTask = TasksList.getInstance().get(position);
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
        mCategory = (TextView) findViewById(R.id.category);
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
        mState.setText(mTask.getStateName(this));
        mCategory.setText(mTask.getCategoryName(this));

        switch (mTask.getState()) {
            case States.IN_PROGRESS: {
                mState.setBackground(getResources().getDrawable(R.drawable.status_label_orange));
                break;
            }
            case States.COMPLETED: {
                mState.setBackground(getResources().getDrawable(R.drawable.status_label_green));
                break;
            }
            case States.WAITING: {
                mState.setBackground(getResources().getDrawable(R.drawable.status_label_gray));
                break;
            }
        }
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.base_padding), false));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
