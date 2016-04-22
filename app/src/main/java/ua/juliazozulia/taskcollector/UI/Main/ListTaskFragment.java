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

package ua.juliazozulia.taskcollector.UI.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ua.juliazozulia.taskcollector.Model.Category;
import ua.juliazozulia.taskcollector.Model.States;
import ua.juliazozulia.taskcollector.Model.TasksList;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.UI.HidingFloatingButton.HidingFloatingActionButton;
import ua.juliazozulia.taskcollector.UI.Details.DetailActivity;

public class ListTaskFragment extends Fragment {
    private static final String ARG_STATUS = "status";
    @Category.TaskCategory
    private String mStatus;
    private ListView mListView;
    private HidingFloatingActionButton mFab;

    public ListTaskFragment() {
    }

    public static ListTaskFragment newInstance(@States.TaskState String status) {
        ListTaskFragment fragment = new ListTaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        bindViews(view);
        setupView();
        return view;

    }

    private void bindViews(View view) {
        mListView = (ListView) view.findViewById(R.id.task_listview);
        mFab = (HidingFloatingActionButton) view.findViewById(R.id.fab);
    }

    private void setupView() {
        mFab.attachToListView(mListView);
        setupListView();
    }

    private void setupListView() {
        mListView.setAdapter(new TaskListViewAdapter(getActivity(), TasksList.getTasks(mStatus)));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TASK, Integer.toString(TasksList.getInstance().indexOf(TasksList.getTasks(mStatus).get(position))));
                getActivity().startActivity(intent);
            }
        });
    }

}
