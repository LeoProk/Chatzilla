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

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Factory for creating contract message in text view, and on contact on select event
 */
public class ChatFactory {

    //Query the message we wrote to parse.
    public static FactoryInterface createMessage(Context context, EditText message
    ) {
        return new MessageCreator(context, message);
    }

    //Get the selected user phone and name.
    public static FactoryInterface onFriendSelected(int position, ListView listView) {
        return new OnFriendsSelected(position, listView);
    }

    //Create notification from CustomReciever text that the other user sent.
    public static FactoryInterface createNotification(Context context, String title, String message,
            String phone) {
        return new NotificationMaker(context, title, message, phone);
    }

    //Creates text view with background when we send or receive messages
    public static FactoryInterface addBackgroundText(Context context, String message) {
        return new TextWithBackground(context, message);
    }

    //Check the size of contract and  if it changes from last check, runs all contacts in parse database
    public static FactoryInterface checkNewContacts(Context context, ListView friendList) {
        return new ContactsSizeCheck(context, friendList);
    }

}
