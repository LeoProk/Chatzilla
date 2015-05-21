package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by Leo on 5/21/2015.
 */
final class SaveFile implements FactoryInterface {

    private Context mContext;
    private String mFilename;
    private String mMessage;

    public SaveFile(Context context,String filename,String message){
        mContext = context;
        mFilename = filename;
        mMessage = message;

    }

    @Override
    public Object doTask() {
        try {
            FileOutputStream outputStream = mContext.openFileOutput(mFilename, Context.MODE_APPEND);
            outputStream.write(mMessage.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
