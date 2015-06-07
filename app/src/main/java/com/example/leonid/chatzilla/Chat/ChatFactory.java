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

    public static FactoryInterface createMessage(Context context, EditText message
    ) {
        return new MessageCreator(context, message);
    }

    public static FactoryInterface onFriendSelected(int position, ListView listView) {
        return new OnFriendsSelected(position, listView);
    }

    public static FactoryInterface createNotification(Context context, String title, String message,
            String phone) {
        return new NotificationMaker(context, title, message, phone);
    }

    public static FactoryInterface addBackgroundText(Context context, String message) {
        return new TextWithBackground(context, message);
    }

    public static FactoryInterface checkNewContacts(Context context, ListView friendList) {
        return new ContactsSizeCheck(context,friendList);
    }

}
