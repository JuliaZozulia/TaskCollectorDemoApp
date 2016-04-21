package ua.juliazozulia.taskcollector.UI.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ua.juliazozulia.taskcollector.Model.Task;
import ua.juliazozulia.taskcollector.Model.TasksList;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.TaskApplication;
import ua.juliazozulia.taskcollector.UI.Details.DetailActivity;
import ua.juliazozulia.taskcollector.Utils.DateUtils;
import ua.juliazozulia.taskcollector.Utils.HelperDrawableUtils;

/**
 * Created by Julia on 20.04.2016.
 */
public class TaskRecycleViewAdapter extends RecyclerView.Adapter<TaskRecycleViewAdapter.ViewHolder> {

    private List<Task> mDataset;

    public TaskRecycleViewAdapter(List<Task> dataset) {
        mDataset = dataset;
    }

    @Override
    public TaskRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon.setImageDrawable(HelperDrawableUtils.getDrawable(mDataset.get(position).getCategory()));
        holder.title.setText(mDataset.get(position).getCategoryName(TaskApplication.getInstance()));
        holder.address.setText(mDataset.get(position).getAddress());
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        holder.creteDate.setText(formatter.format(mDataset.get(position).getCreatedDate()));
        Date dueDate = mDataset.get(position).getDueDate();
        Date currentDate = Calendar.getInstance().getTime();
        holder.days.setText(
                String.format(TaskApplication.getInstance().getResources().getString(R.string.days),
                        DateUtils.getDifferenceDays(currentDate, dueDate)));
        holder.plusCount.setText(Integer.toString(mDataset.get(position).getPlusCount()));
        holder.setItem(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        private Task mItem;
        TextView title;
        TextView address;
        TextView creteDate;
        TextView days;
        ImageView icon;
        TextView plusCount;

        public ViewHolder(View row) {
            super(row);
            context = row.getContext();
            title = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.adress);
            creteDate = (TextView) row.findViewById(R.id.create_date);
            days = (TextView) row.findViewById(R.id.days);
            icon = (ImageView) row.findViewById(R.id.picture);
            plusCount = (TextView) row.findViewById(R.id.plus);
            row.setOnClickListener(this);
        }

        public void setItem(Task item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);

            intent.putExtra(DetailActivity.EXTRA_TASK, Integer.toString(TasksList.getInstance().indexOf(mItem)));
            context.startActivity(intent);
        }
    }
}

