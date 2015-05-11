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

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.leonid.chatzilla.Fragments.ContactsList;
import com.example.leonid.chatzilla.Fragments.LogIn;
import com.example.leonid.chatzilla.Interfaces.UIInterface;
import com.example.leonid.chatzilla.UserInterface.UIFactory;
import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        UIInterface getToolbar = UIFactory.getUI(this,toolbar);
        getToolbar.doTask();
        //create the drawer
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.slider_list);
        UIInterface getDrawer = UIFactory.getUI(this, mDrawerLayout, mDrawerList);
        mDrawerToggle = (ActionBarDrawerToggle)getDrawer.doTask();
        if (ParseUser.getCurrentUser() == null) {
            Fragment fragment = new LogIn();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment, "twitter").addToBackStack("twitter").commit();
        } else {
            Fragment fragment = new ContactsList();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment, "twitter").addToBackStack("twitter").commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
