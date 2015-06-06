package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Parse.User;

import android.app.Fragment;
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

    public static FactoryInterface callSQL(Context context, List<User> users,
            String saveUpgradeRetrieve) {
        return new SQLDatabase(context, users, saveUpgradeRetrieve);
    }

    public static FactoryInterface addFragment(Context context, Fragment fragment, String tag,
            boolean addToBackStack) {
        return new AddFragment(context, fragment, tag, addToBackStack);
    }

    public static FactoryInterface replaceFragment(Context context, Fragment fragment, String tag,
            boolean addToBackStack) {
        return new ReplaceFragment(context, fragment, tag, addToBackStack);
    }
}
