package ua.juliazozulia.taskcollector.UI.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ua.juliazozulia.taskcollector.Model.Task;
import ua.juliazozulia.taskcollector.R;
import ua.juliazozulia.taskcollector.TaskApplication;
import ua.juliazozulia.taskcollector.UI.Details.DetailActivity;
import ua.juliazozulia.taskcollector.Utils.DateUtils;
import ua.juliazozulia.taskcollector.Utils.HelperDrawableUtils;

/**
 * Created by Julia on 21.04.2016.
 */
public class TaskListViewAdapter extends ArrayAdapter<Task> {

    TaskListViewAdapter(Activity activity, List<Task> items) {
        super(activity, R.layout.row_task, R.id.title, new ArrayList(items));
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View row = super.getView(position, convertView, parent);

        Task item = getItem(position);

        ViewHolder holder = (ViewHolder) row.getTag();

        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        holder.icon.setImageDrawable(HelperDrawableUtils.getDrawable(item.getCategory()));
        holder.title.setText(item.getCategoryName(getContext()));
        holder.address.setText(item.getAddress());
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        holder.creteDate.setText(formatter.format(item.getCreatedDate()));
        Date dueDate = item.getDueDate();
        Date currentDate = Calendar.getInstance().getTime();
        holder.days.setText(
                String.format(TaskApplication.getInstance().getResources().getString(R.string.days),
                        DateUtils.getDifferenceDays(currentDate, dueDate)));
        holder.plusCount.setText(Integer.toString(item.getPlusCount()));

        return (row);
    }

    class ViewHolder{
        TextView title;
        TextView address;
        TextView creteDate;
        TextView days;
        ImageView icon;
        TextView plusCount;

        ViewHolder(View row) {
            title = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.adress);
            creteDate = (TextView) row.findViewById(R.id.create_date);
            days = (TextView) row.findViewById(R.id.days);
            icon = (ImageView) row.findViewById(R.id.picture);
            plusCount = (TextView) row.findViewById(R.id.plus);
        }
    }
}
