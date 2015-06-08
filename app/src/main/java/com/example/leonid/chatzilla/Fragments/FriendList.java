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

import com.example.leonid.chatzilla.Chat.ChatFactory;
import com.example.leonid.chatzilla.Parse.User;
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Fragment of friend list with list view that hold users that both in phone contact list and app
 * parse database.
 */
public class FriendList extends Fragment {

    private ListView friendsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        rootView.setBackground(getResources().getDrawable(R.drawable.godzilla_chat));
        //sets adapter to list view with contacts that march the parse user datebase
        friendsList = (ListView) rootView.findViewById(R.id.friendsList);
        ArrayAdapter<User> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                (List<User>) UtilitiesFactory.callSQL(getActivity(), null, "retrieve").doTask());
        friendsList.setAdapter(adapter);
        friendsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ChatFactory.onFriendSelected(position, friendsList).doTask();
                Fragment fragment = new ChatFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().add(R.id.frame_container, fragment, "chat")
                        .commit();

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ChatFactory.checkNewContacts(getActivity(), friendsList).doTask();
    }
}
