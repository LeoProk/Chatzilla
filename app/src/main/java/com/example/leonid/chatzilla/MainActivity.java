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


package com.example.leonid.chatzilla;

import com.example.leonid.chatzilla.Fragments.ChatFragment;
import com.example.leonid.chatzilla.Fragments.FriendList;
import com.example.leonid.chatzilla.Fragments.LogIn;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.UserInterface.UIFactory;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.ParseUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FactoryInterface getToolbar = UIFactory.getToolbar(this, toolbar);
        getToolbar.doTask();
        //create the drawer
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.slider_list);
        FactoryInterface getDrawer = UIFactory.getDrawer(this, mDrawerLayout, mDrawerList);
        mDrawerToggle = (ActionBarDrawerToggle) getDrawer.doTask();
        Bundle bundle = getIntent().getExtras();
        if (ParseUser.getCurrentUser() == null) {
            UtilitiesFactory.addFragment(this, new LogIn(), "login", true).doTask();
        } else {
            if (getIntent().hasExtra("friendName")) {
                AppController.mFriendName = bundle.getString("friendName");
                AppController.mPhoneNum = bundle.getString("phoneNum");
                UtilitiesFactory.addFragment(this, new ChatFragment(), "chat", true).doTask();
            } else {
                UtilitiesFactory.addFragment(this, new FriendList(), "list", true).doTask();
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (getFragmentManager().findFragmentByTag("list") != null) {
            getFragmentManager().findFragmentByTag("list");
            ft.remove(getFragmentManager().findFragmentByTag("chat"));
            ft.show(getFragmentManager().findFragmentByTag("list")).commit();
        } else {
            Fragment fragment = new FriendList();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment, "list")
                    .addToBackStack("chatzilla").commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
