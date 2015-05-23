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
import com.example.leonid.chatzilla.MainActivity;
import com.example.leonid.chatzilla.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Makes Notification when receiving JSONObject from CustomReceiver and displays it.
 */
final class NotificationMaker implements FactoryInterface {

    private Context mContext;

    private String mTitle;

    private String mMessage;

    private String mPhone;

    public NotificationMaker(Context context, String title, String message, String phone) {

        mContext = context;
        mTitle = title;
        mMessage = message;
        mPhone = phone;
    }

    @Override
    public Object doTask() {
        final Intent notificationIntent = new Intent(mContext, MainActivity.class);
        notificationIntent.putExtra("phoneNum", mPhone);
        notificationIntent.putExtra("friendName", mTitle);
        final int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        final PendingIntent contentIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        final NotificationManager notificationManager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.warrning)
                .setContentTitle(mTitle)
                .setContentText(mMessage)
                .setAutoCancel(true);

        notificationManager.notify(iUniqueId, builder.build());
        return null;
    }
}
