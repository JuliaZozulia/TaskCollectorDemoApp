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
import ua.juliazozulia.taskcollector.UI.AnimatedFloatingButton.AnimatedFloatingActionButton;
import ua.juliazozulia.taskcollector.UI.Details.DetailActivity;

public class ListTaskFragment extends Fragment {
    private static final String ARG_STATUS = "status";
    @Category.TaskCategory
    private String mStatus;
    private ListView mListView;
    private AnimatedFloatingActionButton mFab;

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
        mFab = (AnimatedFloatingActionButton) view.findViewById(R.id.fab);
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
