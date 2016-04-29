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

import ua.juliazozulia.taskcollector.model.Task;
import ua.juliazozulia.taskcollector.model.TasksList;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.TaskApplication;
import ua.juliazozulia.taskcollector.ui.details.DetailActivity;
import ua.juliazozulia.taskcollector.utils.DateUtils;
import ua.juliazozulia.taskcollector.utils.HelperDrawableUtils;

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
        TextView title;
        TextView address;
        TextView creteDate;
        TextView days;
        ImageView icon;
        TextView plusCount;
        private Task mItem;

        public ViewHolder(View row) {
            super(row);
            context = row.getContext();
            title = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.address);
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

