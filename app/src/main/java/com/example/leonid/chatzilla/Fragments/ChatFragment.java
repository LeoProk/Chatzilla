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

import com.example.leonid.chatzilla.AppController;
import com.example.leonid.chatzilla.Chat.ChatFactory;
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

//Chat fragment send and receive message from the selected use in friends list

public class ChatFragment extends Fragment {

    public static ChatFragment mThis = null;

    public boolean mCheckNewText;

    private ScrollView mScrollView;

    private RelativeLayout mViewForText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        rootView.setBackground(getResources().getDrawable(R.drawable.godzilla_main));
        mScrollView = ((ScrollView) rootView.findViewById(R.id.chat_ScrollView));
        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
        AppController.textHeight = 0;
        AppController.numForStart = 1;
        AppController.params = 1;
        mCheckNewText = true;
        mViewForText =  (RelativeLayout) rootView.findViewById(R.id.chat_window);
        final EditText editText = (EditText) rootView.findViewById(R.id.input);
        final Button button = (Button) rootView.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilitiesFactory.appendFile(getActivity(), AppController.mPhoneNum,
                        ("\n" + "   " + "Me:" + " " + editText.getText().toString().trim() + "   "
                                + "/@newline@/")).doTask();
                ChatFactory.createMessage(getActivity(), editText).doTask();

            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mCheckNewText == true) {
            getTextFromStorage();
        }
    }

    private void getTextFromStorage() {
        mViewForText.removeAllViews();
        mCheckNewText = false;
        final String chatHistory[] = ((String) UtilitiesFactory
                .getFile(getActivity(), AppController.mPhoneNum).doTask()).split("/@newline@/");
        for (int i = 0; i < chatHistory.length; i++) {
            ChatFactory.addBackgroundText(getActivity(), chatHistory[i]).doTask();
        }

        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    public void messageFromReceiver(String message) {
        ChatFactory.addBackgroundText(getActivity(), message).doTask();

    }

    @Override
    public void onResume() {
        super.onResume();
        mThis = this;
    }

    @Override
    public void onPause() {
        super.onPause();

        mThis = null;
    }
}
