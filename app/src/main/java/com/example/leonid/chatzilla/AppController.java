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

import com.parse.Parse;

import android.app.Application;

import java.util.List;

/**
 * Application class, initialize parse with app info.
 */
public class AppController extends Application {

    public static String mPhoneNum;

    public static String mFriendName;

    private List<User> mFriends;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "JH4PvyQBIfygc3ZpVPutfrrzXhZzBpg5aHyOXmUQ",
                "0WVeOSktZSQLf1deZQjH4bE7Z7Z8FVCYmSVQZZSs");
    }
}
