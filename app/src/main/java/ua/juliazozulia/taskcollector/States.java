package ua.juliazozulia.taskcollector;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Julia on 18.04.2016.
 */
public class States {
    public static final String IN_PROGRESS = "in progress";
    public static final String DELAYED = "delayed";
    public static final String COMPLETED = "completed";

    public static String getStateName(Context context, @TaskState String state) {
        switch (state) {
            case IN_PROGRESS: {
                return context.getResources().getString(R.string.in_progress);
            }
            case DELAYED: {
                return context.getResources().getString(R.string.delayed);
            }
            case COMPLETED: {
                return context.getResources().getString(R.string.completed);
            }
            default: {
                return context.getResources().getString(R.string.unknown);
            }
        }
    }

    @StringDef({IN_PROGRESS, DELAYED, COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskState {
    }
}
