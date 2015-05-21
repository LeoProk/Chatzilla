package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import android.content.Context;

/**
 * Created by Leo on 5/18/2015.
 */
public class UtilitiesFactory {

    public static FactoryInterface checkNetwork(Context context) {
        return new NetworkCheck(context);
    }
    public static FactoryInterface getFile(Context context,String filename) {
        return new FileGetter(context,filename);
    }
    public static FactoryInterface saveFile(Context context,String filename,String message) {
        return new SaveFile(context,filename,message);
    }
}
