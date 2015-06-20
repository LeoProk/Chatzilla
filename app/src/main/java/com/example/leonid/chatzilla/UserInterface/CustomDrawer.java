/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.leonid.chatzilla.UserInterface;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.R;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This class contain logic of drawer.
 */

final class CustomDrawer implements FactoryInterface {

    Context mContext;

    DrawerLayout mDrawerLayout;

    ListView mDrawerList;

    protected CustomDrawer(Context context, DrawerLayout drawerLayout, ListView drawerList) {
        mContext = context;
        this.mDrawerLayout = drawerLayout;
        this.mDrawerList = drawerList;
    }

    //class of creating and populating navigation drawer
    @Override
    public ActionBarDrawerToggle doTask() {

        final ActionBarActivity activity = (ActionBarActivity) mContext;
        //get the arrays from strings
        /*final String[] menutitles = context.getResources().getStringArray(R.array.titles);
        final TypedArray menuIcons = context.getResources().obtainTypedArray(R.array.icons);*/
        ArrayList<RowItem> rowItems = new ArrayList<>();
        //create the drawer
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            //* Called when a drawer has settled in a completely closed state.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //* Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };/*
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        for (int i = 0; i < menutitles.length; i++) {
            RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(i, -1));
            rowItems.add(items);

        }
        menuIcons.recycle();*/
        CustomAdapter adapter = new CustomAdapter(mContext, rowItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new CustomDrawer.SlideitemListener());
        return mDrawerToggle;
    }

    //create fragment based on clicked position
    private void updateDisplay(int position) {
        //check for active fragment and delete them
        final ActionBarActivity activity = (ActionBarActivity) mContext;
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        if (activity.getFragmentManager().findFragmentByTag("twitter") != null) {
            ft.remove(activity.getFragmentManager().findFragmentByTag("twitter")).commit();
        }
        if (activity.getFragmentManager().findFragmentByTag("twitter") != null) {
            ft.remove(activity.getFragmentManager().findFragmentByTag("twitter"));
        }
        //create the fragment
       /* Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SearchTweetsFragment();
                break;
            case 1:
                fragment = new LoginFragment();
                break;
            default:
                break;
        }
        if (fragment != null&& new NetworkCheck(context).isNetworkConnected()) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "twitter").addToBackStack("twitter").commit();
            //closes drawer
            mDrawerLayout.closeDrawer(mDrawerList);

        }*/
    }

    // called when one of the items in drawer is clicked
    class SlideitemListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            updateDisplay(position);
        }
    }

}
