package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Save new file to internal storage
 */
final class SaveFile implements FactoryInterface {

    private Context mContext;

    private String mFilename;

    private String mMessage;

    public SaveFile(Context context, String filename, String message) {
        mContext = context;
        mFilename = filename;
        mMessage = message;

    }

    @Override
    public Object doTask() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream outputStream = mContext
                            .openFileOutput(mFilename, Context.MODE_PRIVATE);
                    outputStream.write(mMessage.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        return null;
    }
}
