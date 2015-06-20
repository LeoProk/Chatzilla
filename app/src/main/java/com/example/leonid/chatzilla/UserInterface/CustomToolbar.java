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

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * This class contain logic of toolbar
 */
final class CustomToolbar implements FactoryInterface {

    Context context;

    Toolbar toolbar;

    protected CustomToolbar(Context context, Toolbar toolbar) {
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
