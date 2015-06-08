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

package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Retrieve file from internal storage by name;
 */
final class FileGetter implements FactoryInterface {

    private String mFileName;

    private Context mContext;

    public FileGetter(Context context, String fileName) {

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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
