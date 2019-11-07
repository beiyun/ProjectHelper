package com.beiyun.library.util;

import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by beiyun on 2017/12/29.
 *
 */
public class Dialogs {
    private AlertDialog.Builder mBuilder;

    private Dialogs(AlertDialog.Builder builder){
        this.mBuilder = builder;
    }

    public static TextDialog getTextDialog(){
        return new TextDialog();
    }

    public static class TextDialog {
        private CharSequence title;
        private CharSequence message;
        private CharSequence positiveText;
        private CharSequence negativeText;
        private int titleColor;
        private int messageColor;
        private boolean cancelable;
        private DialogInterface.OnClickListener mPositiveListener,mNegativeListener;

        public Dialogs build(){
            AlertDialog.Builder builder = new AlertDialog.Builder(Apps.getCurrentActivity());
            builder.setTitle(getColorString(title,titleColor))
                    .setMessage(getColorString(message,messageColor))
                    .setCancelable(cancelable)
                    .setPositiveButton(positiveText, mPositiveListener)
                    .setNegativeButton(negativeText, mNegativeListener)
                    .create();



            return new Dialogs(builder);
        }


        public TextDialog setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public TextDialog setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public TextDialog setMessageColor(@ColorInt int messageColor){
            this.messageColor = messageColor;
            return this;
        }

        public TextDialog setTitleColor(@ColorInt int titleColor){
            this.titleColor = titleColor;
            return this;
        }

        public TextDialog setNegativeButton(CharSequence negativeText, DialogInterface.OnClickListener listener) {
            this.negativeText = negativeText;
            this.mNegativeListener = listener;
            return this;
        }


        public TextDialog setPositiveButton(CharSequence positiveText, DialogInterface.OnClickListener listener) {
            this.positiveText = positiveText;
            this.mPositiveListener = listener;
            return this;
        }

        public TextDialog setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        private CharSequence getColorString(CharSequence source,int color){
            if(Strings.isEmpty(source) || color == 0) return source;
            SpannableString str = new SpannableString(source);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
            str.setSpan(colorSpan,0,str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            return str;
        }

    }

    public void show(){
        mBuilder.show();
    }






}
