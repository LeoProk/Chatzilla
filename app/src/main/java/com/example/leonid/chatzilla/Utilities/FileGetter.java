package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;

import java.io.FileInputStream;

/**
 * Retrieve file from internal storage by name;
 */
final class FileGetter implements FactoryInterface {
    private String mFileName;
    private Context mContext;

    public FileGetter(Context context,String fileName){

        mContext = context;
        mFileName = fileName;
    }
    @Override
    public Object doTask() {
        String temp = "";
        try {
            FileInputStream inputStream = mContext.openFileInput(mFileName);
            int c;

            while ((c = inputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            //string temp contains all the data of the file.
            inputStream.close();

        } catch (Exception e) {

        }
        return temp;
    }
}
