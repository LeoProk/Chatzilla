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

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.leonid.chatzilla.Interfaces.UIInterface;
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.UserInterface.Drawer.CustomAdapter;
import com.example.leonid.chatzilla.UserInterface.Drawer.RowItem;

import java.util.ArrayList;

/**
 * Factory for tollbar and drawer
 */
public class UIFactory {


    public static UIInterface getUI(Context context,Toolbar toolbar){
            return new CustomToolbar(context,toolbar);
    }
    public static UIInterface getUI(Context context, DrawerLayout mDrawerLayout, ListView mDrawerList){
            return new CustomDrawer(context,mDrawerLayout,mDrawerList);
    }

}

/**
 * This class contain logic of drawer and toolbar
 */
final class CustomToolbar implements UIInterface {
    Context context;
    Toolbar toolbar;
    public CustomToolbar(Context context,android.support.v7.widget.Toolbar toolbar){
        this.context = context;
        this.toolbar = toolbar;
    }

    //replace the default action bat with toolbar
    @Override
    public Object doTask() {
        final ActionBarActivity activity = (ActionBarActivity) context;
        activity.setSupportActionBar(toolbar);
        ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.drawable.index);
        ab.setDisplayUseLogoEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        return null;
    }



}


/**
 * This class contain logic of drawer and toolbar
 */

final class CustomDrawer implements UIInterface {
    Context context;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    public CustomDrawer(Context context, DrawerLayout mDrawerLayout, ListView mDrawerList){
        this.context = context;
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerList = mDrawerList;
    }

    //class of creating and populating navigation drawer
    @Override
    public ActionBarDrawerToggle doTask() {

        final ActionBarActivity activity = (ActionBarActivity) context;
        //get the arrays from strings
        /*final String[] menutitles = context.getResources().getStringArray(R.array.titles);
        final TypedArray menuIcons = context.getResources().obtainTypedArray(R.array.icons);*/
        ArrayList<RowItem> rowItems = new ArrayList<>();
        //create the drawer
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

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
        CustomAdapter adapter = new CustomAdapter(context, rowItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideitemListener());
        return  mDrawerToggle;
    }

    // called when one of the items in drawer is clicked
    class SlideitemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            updateDisplay(position);
        }
    }
    //create fragment based on clicked position
    private void updateDisplay(int position) {
        //check for active fragment and delete them
        final ActionBarActivity activity = (ActionBarActivity) context;
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        if(activity.getFragmentManager().findFragmentByTag("twitter") != null){
            ft.remove(activity.getFragmentManager().findFragmentByTag("twitter")).commit();
        }
        if(activity.getFragmentManager().findFragmentByTag("twitter") != null){
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

}
