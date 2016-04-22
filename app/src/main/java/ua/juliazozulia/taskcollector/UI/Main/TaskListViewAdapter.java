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

import android.app.Activity;
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
import ua.juliazozulia.taskcollector.Utils.DateUtils;
import ua.juliazozulia.taskcollector.Utils.HelperDrawableUtils;

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

    class ViewHolder {
        TextView title;
        TextView address;
        TextView creteDate;
        TextView days;
        ImageView icon;
        TextView plusCount;

        ViewHolder(View row) {
            title = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.address);
            creteDate = (TextView) row.findViewById(R.id.create_date);
            days = (TextView) row.findViewById(R.id.days);
            icon = (ImageView) row.findViewById(R.id.picture);
            plusCount = (TextView) row.findViewById(R.id.plus);
        }
    }
}
