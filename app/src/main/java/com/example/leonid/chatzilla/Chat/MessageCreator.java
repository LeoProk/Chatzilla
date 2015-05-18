package com.example.leonid.chatzilla.Chat;

import com.example.leonid.chatzilla.AppController;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

/**
 * Created by Leo on 5/18/2015.
 */
public class MessageCreator implements FactoryInterface {

    private EditText mMessage;
    private Context mContext;
    private TextView mChatText;
    MessageCreator(Context context,EditText message,TextView textView){
       mContext = context;
       mMessage = message;
       mChatText = textView;
    }
    @Override
    public Object doTask() {
        mChatText.append("\n" + "Me:" + "\n" + mMessage.getText().toString().trim());
        try {
            JSONObject data = new JSONObject();
            data.put("action", "il.co.arielstudio.PUSH_NOTIFICATION");
            data.put("message", mMessage.getText().toString());
            data.put("phone", UtilitiesFactory.getFile(mContext,"phone").doTask());
            data.put("name",  UtilitiesFactory.getFile(mContext,"name").doTask());
            ParseQuery<ParseInstallation> query = ParseInstallation.getQuery();
            query.whereEqualTo("phoneNum", AppController.mPhoneNum);
            // Send push notification to query
            ParsePush push = new ParsePush();
            push.setData(data);
            push.setQuery(query); // Set our Installation query
            push.sendInBackground();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream = mContext.openFileOutput(AppController.mPhoneNum, Context.MODE_APPEND);
            outputStream.write(("\n" + "Me:" + "\n" + mMessage.getText().toString().trim()).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
