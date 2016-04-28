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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.juliazozulia.taskcollector.Model.States;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        //wonder if I can get somehow number of stringDef constants
        return (3); //[Comment]  Why in braces?
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecyclerFragment.newInstance(States.IN_PROGRESS);
            case 1:
                return RecyclerFragment.newInstance(States.COMPLETED);
            case 2:
                return ListTaskFragment.newInstance(States.WAITING);
            default:
                return RecyclerFragment.newInstance(States.IN_PROGRESS);
        }
    }
}
