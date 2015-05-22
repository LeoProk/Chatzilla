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
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Log in fragment users name and phone number to create new user.
 */
public class LogIn extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        //set picture as background
        rootView.setBackground(getResources().getDrawable(R.drawable.login_zilla));
        final EditText name = (EditText) rootView.findViewById(R.id.name);
        final EditText phone = (EditText) rootView.findViewById(R.id.phone);
        final Button logIn = (Button) rootView.findViewById(R.id.log_in);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseFactory.createUser(getActivity(), name, phone).doTask();
                UtilitiesFactory.saveFile(getActivity(), "name", name.getText().toString())
                        .doTask();
                UtilitiesFactory.saveFile(getActivity(), "phone", phone.getText().toString())
                        .doTask();
            }
        });
        return rootView;
    }
}