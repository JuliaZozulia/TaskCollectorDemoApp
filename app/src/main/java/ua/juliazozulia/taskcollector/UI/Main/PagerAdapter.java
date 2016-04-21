package ua.juliazozulia.taskcollector.UI.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.juliazozulia.taskcollector.Model.States;

/**
 * Created by Julia on 20.04.2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        //wonder if I can get somehow number of stringDef constants
        return (3);
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
