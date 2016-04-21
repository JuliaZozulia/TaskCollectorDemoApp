package ua.juliazozulia.taskcollector.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import ua.juliazozulia.taskcollector.TaskApplication;

/**
 * Created by Julia on 21.04.2016.
 */
public class HelperDrawableUtils {

    public static Drawable getDrawable(String name) {
        Context context = TaskApplication.getInstance();
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return context.getResources().getDrawable(resourceId);
    }
}
