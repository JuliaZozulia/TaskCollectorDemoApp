package ua.juliazozulia.taskcollector.model;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ua.juliazozulia.taskcollector.R;

public class States {
    public static final String IN_PROGRESS = "in progress";
    public static final String WAITING = "waiting";
    public static final String COMPLETED = "completed";

    public static String getStateName(Context context, @TaskState String state) {
        switch (state) {
            case IN_PROGRESS: {
                return context.getResources().getString(R.string.in_progress);
            }
            case WAITING: {
                return context.getResources().getString(R.string.waiting);
            }
            case COMPLETED: {
                return context.getResources().getString(R.string.completed);
            }
            default: {
                return context.getResources().getString(R.string.unknown);
            }
        }
    }

    @StringDef({IN_PROGRESS, WAITING, COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskState {
    }
}
