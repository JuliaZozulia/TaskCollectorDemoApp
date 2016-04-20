package ua.juliazozulia.taskcollector;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Julia on 18.04.2016.
 */
public class Category {
    public static final String MUNICIPAL = "municipal";
    public static final String ECOLOGY = "ecology";
    public static final String HEALTH_SERVICE = "health_service";
    public static final String PROPOSALS = "proposals";


    public static String getCategoryName(Context context, @TaskCategory String state) {
        switch (state) {
            case MUNICIPAL: {
                return context.getResources().getString(R.string.municipal);
            }
            case ECOLOGY: {
                return context.getResources().getString(R.string.ecology);
            }
            case HEALTH_SERVICE: {
                return context.getResources().getString(R.string.health_service);
            }
            case PROPOSALS: {
                return context.getResources().getString(R.string.proposals);
            }
            default: {
                return context.getResources().getString(R.string.unknown);
            }
        }
    }

    @StringDef({MUNICIPAL, ECOLOGY, HEALTH_SERVICE, PROPOSALS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskCategory {
    }
}
