package ua.juliazozulia.taskcollector.UI.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.juliazozulia.taskcollector.Model.Category;
import ua.juliazozulia.taskcollector.Model.States;
import ua.juliazozulia.taskcollector.Model.TasksList;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.UI.AnimatedFloatingButton.AnimatedFloatingActionButton;
import ua.juliazozulia.taskcollector.UI.Details.SpacesItemDecoration;

public class RecyclerFragment extends Fragment {
    private static final String ARG_STATUS = "status";
    @Category.TaskCategory
    private String mStatus;
    private RecyclerView mRecyclerView;
    private AnimatedFloatingActionButton mFab;

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
        View view = inflater.inflate(R.layout.fragment_fragment_recycler, container, false);
        bindViews(view);
        setupView();
        return view;
    }

    private void bindViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mFab = (AnimatedFloatingActionButton) view.findViewById(R.id.fab);
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
        mRecyclerView.setAdapter(new TaskRecycleViewAdapter(TasksList.getTasks(mStatus)));
    }
}
