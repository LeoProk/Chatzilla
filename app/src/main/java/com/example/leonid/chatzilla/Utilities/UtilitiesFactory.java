package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Parse.User;

import android.content.Context;

import java.util.List;

/**
 * Factory for network statues , retrieving files , saving and appending new files.
 */
public class UtilitiesFactory {

    public static FactoryInterface checkNetwork(Context context) {
        return new NetworkCheck(context);
    }

    public static FactoryInterface getFile(Context context, String filename) {
        return new FileGetter(context, filename);
    }

    public static FactoryInterface saveFile(Context context, String filename, String message) {
        return new SaveFile(context, filename, message);
    }

    public static FactoryInterface appendFile(Context context, String filename, String message) {
        return new AppendFile(context, filename, message);
    }

    public static FactoryInterface saveSQL(Context context, List<User> users) {
        return new SaveToSQL(context, users);
    }
}
