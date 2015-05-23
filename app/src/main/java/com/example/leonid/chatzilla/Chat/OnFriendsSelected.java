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

package com.example.leonid.chatzilla.Chat;

import com.example.leonid.chatzilla.AppController;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import android.widget.ListView;

/**
 *
 */
final class OnFriendsSelected implements FactoryInterface {

    private int mPosition;

    private ListView mListView;

    OnFriendsSelected(int position,ListView listView){
        mPosition = position;
        mListView = listView;
    }

    @Override
    public Object doTask() {
        String selectedFromList = (mListView.getItemAtPosition(mPosition).toString());
        String finalNum = selectedFromList.replaceAll("[^\\d.]", "");
        String unfilteredNum = selectedFromList.replaceAll(" ", "");
        String finalName = unfilteredNum.replaceAll("([0-9])", "");
        AppController.mFriendName = finalName;
        AppController.mPhoneNum = finalNum;
        return null;
    }
}
