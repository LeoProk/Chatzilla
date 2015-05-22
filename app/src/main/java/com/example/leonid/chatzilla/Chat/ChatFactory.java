package com.example.leonid.chatzilla.Chat;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Factory for creating contract message in text view, and on contact on select event
 */
public class ChatFactory {

    public static FactoryInterface createMessage(Context context, EditText message,
            TextView chatWindow) {
        return new MessageCreator(context, message, chatWindow);
    }

    public static FactoryInterface onFriendSelected(Context context) {
        return new SelectedFriend();
    }
}
