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

package com.example.leonid.chatzilla.Parse;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Factory that get matching contacts or create new parse user
 */
public class ParseFactory {

    //Check contacts for matching users from parse database and if yes add them to SQL
    public static FactoryInterface getUserList(Context context, ListView userList) {
        return new ListViewCreator(context, userList);
    }

    //Creates new user or log in to existing one.
    public static FactoryInterface createUser(Context context, EditText name, EditText phone) {
        return new CreateNewUser(context, name, phone);
    }

}
