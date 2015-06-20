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

import com.example.leonid.chatzilla.Fragments.FriendList;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class create parse user from the values entered in edit text (phone and name).
 * It also saves the values to storage for further use
 */
final class CreateNewUser implements FactoryInterface {

    private EditText mName;

    private Context mContext;

    private EditText mPhone;

    public CreateNewUser(Context context, EditText name, EditText phone) {
        mName = name;
        mPhone = phone;
        mContext = context;
    }

    @Override
    public Object doTask() {
        final ParseUser user = new ParseUser();
        final String cellNum = mPhone.getText().toString();
        user.setUsername(cellNum);
        user.setPassword(cellNum);
        ParseInstallation.getCurrentInstallation().put("phoneNum", cellNum);
        ParseInstallation.getCurrentInstallation().saveEventually();
        user.signUpInBackground(new SignUpCallback() {
            //start sing up session
            public void done(ParseException e) {
                if (e == null) {
                    try {
                        //creates the parse user
                        ParseObject userObj = new ParseObject("Dude");
                        userObj.put("name", mName.getText().toString());
                        userObj.put("phone", cellNum);
                        userObj.put("message", "");
                        userObj.save();
                        Toast.makeText(mContext,
                                "User have been successfully submitted !",
                                Toast.LENGTH_SHORT).show();
                        Fragment fragment = new FriendList();
                        FragmentManager fragmentManager = ((ActionBarActivity) mContext)
                                .getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,
                                "list")
                                .addToBackStack("list").commit();
                    } catch (ParseException e1) {
                        e1.printStackTrace();

                    }
                } else {
                    // User already registered so he will log in
                    ParseUser.logInInBackground(cellNum, cellNum, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                // Hooray! The user is logged in.
                                UtilitiesFactory
                                        .replaceFragment(mContext, new FriendList(), "list", true)
                                        .doTask();
                                Toast.makeText(mContext,
                                        "Welcome back " + mName.getText().toString() + " !",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Signup failed. Look at the ParseException to see what happened.
                            }
                        }
                    });

                }
            }
        });
        return null;
    }
}