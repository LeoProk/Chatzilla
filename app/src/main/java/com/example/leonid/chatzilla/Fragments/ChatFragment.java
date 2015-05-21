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
import com.example.leonid.chatzilla.R;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class ChatFragment extends Fragment {

    public static ChatFragment mThis = null;
    private TextView mTextView;
    private ScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        mTextView = (TextView)  rootView.findViewById(R.id.chatHistory);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        final Button button = (Button)  rootView.findViewById(R.id.send);
        mScrollView = ((ScrollView)  rootView.findViewById(R.id.chat_ScrollView));
        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mTextView.setText((String)UtilitiesFactory.getFile(getActivity(), AppController.mPhoneNum).doTask());
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

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
