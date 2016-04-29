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

package ua.juliazozulia.taskcollector.model;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ua.juliazozulia.taskcollector.R;

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
