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

package com.example.leonid.chatzilla.Fragments;

import com.example.leonid.chatzilla.Parse.ParseFactory;
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.Parse.User;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of friend list with list view that hold users that both in phone contact list and app
 * parse database.
 */
public class FriendList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        //sets adapter to list view with contacts that march the parse user datebase
        final ListView friendsList = (ListView) rootView.findViewById(R.id.friendsList);
        final List<User> users = (ArrayList<User>) ParseFactory.getContacts(getActivity()).doTask();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, users);
        friendsList.setAdapter(adapter);

        return rootView;
    }
}
