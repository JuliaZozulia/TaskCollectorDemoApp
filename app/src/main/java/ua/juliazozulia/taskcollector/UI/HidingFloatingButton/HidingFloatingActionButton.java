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

package ua.juliazozulia.taskcollector.UI.HidingFloatingButton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;

import ua.juliazozulia.taskcollector.R;

// Thanks to https://github.com/makovkastar/FloatingActionButton
//and Kevin Cronly's answer here http://stackoverflow.com/questions/30937028/how-to-animate-floatingactionbutton-like-in-google-app-for-android

/**
 * HidingFloatingActionButton is hiding whet user scrolls down listview/recyclerview and showing when she scrolls it up
 * Can be used with both listview and recyclerview
 * You need to attache fab to view using attachToListView() or attachToRecyclerView()
 */

public class HidingFloatingActionButton extends FloatingActionButton {
    public HidingFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void show() {
        super.show();
    }

    public void hide() {
        super.hide();
    }

    public void attachToListView(@NonNull AbsListView listView) {

        AbsListViewScrollDetectorImpl scrollDetector = new AbsListViewScrollDetectorImpl();
        scrollDetector.setListView(listView);
        listView.setOnScrollListener(scrollDetector);
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerViewScrollDetector() {
            @Override
            void onScrollUp() {
                hide();
            }

            @Override
            void onScrollDown() {
                show();
            }

            @Override
            void setScrollThreshold() {
                setScrollThreshold(getResources().getDimensionPixelOffset(R.dimen.fab_scroll_threshold));
            }
        });
    }

    private class AbsListViewScrollDetectorImpl extends AbsListViewScrollDetector {

        @Override
        public void onScrollDown() {
            show();
        }

        @Override
        public void onScrollUp() {
            hide();
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                             int totalItemCount) {
            super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            super.onScrollStateChanged(view, scrollState);
        }
    }
}