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

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import ua.juliazozulia.taskcollector.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTab;
    private Drawer mDrawerResult;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.all_issues));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bindViews();
        setupViews();
    }

    private void setupViews() {

        View footer = getLayoutInflater().inflate(R.layout.drawer_footer, null);
        TextView textView = (TextView) footer.findViewById(R.id.footer);
        RelativeLayout footerLayout = (RelativeLayout) footer.findViewById(R.id.footer_layout);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        setupTab();
        setupToolbar();
        mDrawerResult = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.drawer_header)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.all_issues).withIcon(R.drawable.ic_issues),
                        new PrimaryDrawerItem().withName(R.string.issues_on_map).withIcon(R.drawable.ic_map),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.login)
                )
                .withStickyFooterShadow(false)
                .withStickyFooter(footerLayout)
                .build();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        syncDrawerIcon();
    }

    private void setupTab() {
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mTab.setupWithViewPager(mPager);
        initTab();
    }

    private void bindViews() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mTab = (TabLayout) findViewById(R.id.tab);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerResult.isDrawerOpen()) {
            mDrawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initTab() {
        mTab.getTabAt(0).setText(R.string.in_progress);
        mTab.getTabAt(1).setText(R.string.completed);
        mTab.getTabAt(2).setText(R.string.waiting);
    }

    public void syncDrawerIcon() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mToolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu, null));
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_filter: {
                Toast.makeText(this, item.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
