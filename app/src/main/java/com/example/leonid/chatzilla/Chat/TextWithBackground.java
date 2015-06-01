package com.example.leonid.chatzilla.Chat;

import com.example.leonid.chatzilla.AppController;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  Add text views with background to the scroll view.
 */
final class TextWithBackground implements FactoryInterface {

    private Context mContext;

    private String mText;

    public TextWithBackground(Context context, String text) {

        mContext = context;
        mText = text;
    }

    @Override
    public Object doTask() {
        int numForHeight = 0;
        RelativeLayout viewForText = (RelativeLayout) ((Activity) mContext)
                .findViewById(R.id.chat_window);
        if (mText.replace(" ", "").length() != 0) {
            final TextView textView = new TextView(mContext);
            if (mText.length() > 0 && mText.length() < 51) {
                textView.setText(mText);
                numForHeight = getDp(50);
            }
            if (mText.length() > 60 && mText.length() < 151) {
                textView.setText(mText);
                numForHeight = getDp(75);
            }
            if (mText.length() > 150 && mText.length() < 201) {
                textView.setText(mText);
                numForHeight = getDp(90);
            }
            if (mText.length() > 200 && mText.length() < 251) {
                textView.setText(mText);
                numForHeight = getDp(115);
            }

            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.rounded_backround);
            int childCount = viewForText.getChildCount();
            if (childCount == 0) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, numForHeight);
                if (mText.contains("Me:")) {
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    textView.setGravity(Gravity.RIGHT);

                }
                layoutParams.setMargins(0, AppController.params, 0, 0);
                viewForText.addView(textView, layoutParams);
                AppController.textHeight = mText.length();
            } else {
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, numForHeight);
                if (mText.contains("Me:")) {
                    params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    viewForText.setGravity(Gravity.RIGHT);

                }

                if (AppController.textHeight > 0 && AppController.textHeight < 51) {
                    AppController.params = AppController.params + getDp(55);
                }
                if (AppController.textHeight > 60 && AppController.textHeight < 151) {

                    AppController.params = AppController.params + getDp(77);
                }
                if (AppController.textHeight > 150 && AppController.textHeight < 201) {

                    AppController.params = AppController.params + getDp(97);
                }
                if (AppController.textHeight > 200 && AppController.textHeight < 251) {

                    AppController.params = AppController.params + getDp(117);
                }
                params2.setMargins(0, AppController.params, 0, 0);
                viewForText.addView(textView, params2);
            }
            if (childCount != 0) {

                AppController.textHeight = mText.length();
            }
        }
        return null;
    }

    private int getDp(int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        int px = (int) Math.ceil(dp * logicalDensity);
        return px;
    }
}
