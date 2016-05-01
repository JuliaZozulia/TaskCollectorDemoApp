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

package ua.juliazozulia.taskcollector.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.juliazozulia.taskcollector.OnTaskItemClick;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.model.Category;
import ua.juliazozulia.taskcollector.model.States;
import ua.juliazozulia.taskcollector.model.TasksList;
import ua.juliazozulia.taskcollector.ui.details.DetailActivity;
import ua.juliazozulia.taskcollector.ui.details.SpacesItemDecoration;
import ua.juliazozulia.taskcollector.ui.hidingfloatingbutton.HidingFloatingActionButton;

public class RecyclerFragment extends Fragment implements OnTaskItemClick {
    private static final String ARG_STATUS = "status";
    @Category.TaskCategory
    private String mStatus;
    private RecyclerView mRecyclerView;
    private HidingFloatingActionButton mFab;

    public RecyclerFragment() {
    }

    public static RecyclerFragment newInstance(@States.TaskState String status) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @SuppressWarnings("ResourceType")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mStatus = getArguments().getString(ARG_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_recycler, container, false);
        bindViews(view);
        setupView();
        return view;
    }

    private void bindViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mFab = (HidingFloatingActionButton) view.findViewById(R.id.fab);
    }

    private void setupView() {
        mFab.attachToRecyclerView(mRecyclerView);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.base_padding), true));
        mRecyclerView.setAdapter(new TaskRecycleViewAdapter(TasksList.getTasks(mStatus), this));
    }

    @Override
    public void onTaskClick(int task) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TASK, Integer.toString(task));
        startActivity(intent);
    }
}
