package com.dkv.bubblealertlib;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;


/**
 * @author DKV.
 */
public class BblDialogManager {

    public static void showBblDialog(FragmentManager fm, LayoutInflater inflater, String content,
                                     String ok, String cancel, String drawText,
                                     IAlertClickedCallBack alertClickedCallBack, Context context, String TAG,int dialogButtonColor) {
        try {
            BblContentFragment fragment = BblContentFragment.newInstance(TAG);
            if (TextUtils.isEmpty(content)) {
                content = context.getString(R.string.err_server_error);
            }
            fragment.setContent(content, ok, cancel, null, null);
            fragment.setClickedCallBack(alertClickedCallBack);
            fragment.setDialogButtonColor(dialogButtonColor);

            BblDialog sampleDialog = new BblDialog();
            sampleDialog.setContentFragment(fragment, R.layout.layout_bbl_content, inflater, content, drawText, context,Color.GRAY);
            sampleDialog.setDisMissCallBack(null);
            fm.beginTransaction().add(sampleDialog, "Test").commitAllowingStateLoss();
        } catch (Exception e) {
            AppLog.logException(TAG, e);
        }
    }

    public static boolean isDialogShown() {
        return BblDialog.shown;
    }


    public static void showEditTextBblDialog(FragmentManager fm, LayoutInflater inflater, String content,
                                             String ok, String cancel, String drawText,
                                             IDialogListener dialogListener, Context context, String textContent,
                                             String hintText, boolean isMultiline, String TAG,int dialogButtonColor) {
        BblContentFragment fragment = BblContentFragment.newInstance(TAG);

        if (TextUtils.isEmpty(content)) {

            content = context.getString(R.string.err_server_error);
        }
        fragment.setContent(content, ok, cancel, null, null)
                .setHasEditText(true)
                .setMultiLine(isMultiline)
                .setHintText(hintText)
                .setTextContent(textContent)
                .setDialogButtonColor(dialogButtonColor)
                .setDialogListener(dialogListener);
        BblDialog sampleDialog = new BblDialog();
        sampleDialog.setHasEditText(true)
                .setContentFragment(fragment, R.layout.layout_bbl_content, inflater, content, drawText, context,Color.CYAN)
                .setDisMissCallBack(null);
        fm.beginTransaction().add(sampleDialog, "Test").commit();
    }

    public static void showEditTextBblDialog(FragmentManager fm, LayoutInflater inflater, String dialogTitle, String content,
                                             String ok, String cancel, String drawText,
                                             IDialogListener dialogListener, Context context, String textContent,
                                             String hintText, boolean isMultiline, String TAG,int dialogButtonColorId) {

        BblContentFragment fragment = BblContentFragment.newInstance(TAG);

        if (TextUtils.isEmpty(content)) {

            content = context.getString(R.string.err_server_error);
        }
        fragment.setContent(content, ok, cancel, null, dialogTitle)
                .setHasEditText(true)
                .setMultiLine(isMultiline)
                .setHintText(hintText)
                .setTextContent(textContent)
                .setDialogButtonColor(dialogButtonColorId)
                .setDialogListener(dialogListener);


        BblDialog sampleDialog = new BblDialog();
        sampleDialog.setHasEditText(true)
                .setContentFragment(fragment, R.layout.layout_bbl_content, inflater, content, drawText, context,Color.BLACK)
                .setDisMissCallBack(null);
        fm.beginTransaction().add(sampleDialog, "Test").commit();
    }


    public static void showBblDialog(FragmentManager fm, LayoutInflater inflater, String content,
                                     String ok, String cancel, String exit, String drawText,
                                     IAlertClickedCallBack alertClickedCallBack, Context context, String TAG,int dialogButtonColor) {
        try {
            BblContentFragment fragment = BblContentFragment.newInstance(TAG);
            if (TextUtils.isEmpty(content)) {
                content = context.getString(R.string.err_server_error);
            }
            fragment.setContent(content, ok, cancel, exit, null);
            fragment.setClickedCallBack(alertClickedCallBack);
            fragment.setDialogButtonColor(dialogButtonColor);
            BblDialog sampleDialog = new BblDialog();
            sampleDialog.setContentFragment(fragment, R.layout.layout_bbl_content, inflater, content, drawText, context,Color.BLUE);
            sampleDialog.setDisMissCallBack(null);
            fm.beginTransaction().add(sampleDialog, "Test").commit();
        } catch (Exception e) {
            AppLog.logException(TAG, e);
        }
    }
}
