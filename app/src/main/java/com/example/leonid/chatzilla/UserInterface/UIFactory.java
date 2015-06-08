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

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

/**
 * Factory for tollbar and drawer
 */
public class UIFactory {

    //Creates toolbar actionbar must be disabled in manifest
    public static FactoryInterface getToolbar(Context context, Toolbar toolbar) {
        return new CustomToolbar(context, toolbar);
    }

    //Creates navigation drawer
    public static FactoryInterface getDrawer(Context context, DrawerLayout mDrawerLayout,
            ListView mDrawerList) {
        return new CustomDrawer(context, mDrawerLayout, mDrawerList);
    }

}


