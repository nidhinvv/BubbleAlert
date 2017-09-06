package com.dkv.bubblealertview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dkv.bubblealertlib.AppConstants;
import com.dkv.bubblealertlib.BblDialogManager;
import com.dkv.bubblealertlib.ConstantsIcons;
import com.dkv.bubblealertlib.IAlertClickedCallBack;
import com.dkv.bubblealertlib.IDialogListener;

/**
 * @author DKV.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    AlertClickHandler alertHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertHandler = new AlertClickHandler();


        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:

                BblDialogManager.showBblDialog(getSupportFragmentManager(),
                        LayoutInflater.from(this), "success", this.getString(R.string.ok),
                        null, ConstantsIcons.ALERT_ICON_SUCCESS,
                        alertHandler, this, AppConstants.TAG_FEEDBACK_SUCCESS);
                break;
            case R.id.button2:


                BblDialogManager.showEditTextBblDialog(getSupportFragmentManager(), LayoutInflater.from(this),
                        getString(R.string.text_enter_comments), getString(R.string.yes), getString(R.string.alert_cancel), ConstantsIcons.ALERT_ICON_INFO,
                        new IDialogListener() {
                            @Override
                            public void onOkClicked(String tag, String reason) {
                                if (!TextUtils.isEmpty(reason)) {
                                    Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, getString(R.string.msg_enter_your_comments), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelClicked(String tag) {
                                Toast.makeText(MainActivity.this, getString(R.string.alert_cancel), Toast.LENGTH_SHORT).show();
                            }
                        }, this, "", "", true, TAG);


                break;
            case R.id.button3:


                BblDialogManager.showBblDialog(getSupportFragmentManager(),
                        LayoutInflater.from(this), getString(R.string.msg_creating_msg),
                        getString(R.string.action_continue), getString(R.string.action_save_exit),
                        getString(R.string.action_exit), ConstantsIcons.ALERT_ICON_COMMON_INFO,
                        new IAlertClickedCallBack() {
                            @Override
                            public void onOkClicked(String tag) {
                                Toast.makeText(MainActivity.this, "Continue", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelClicked(String tag) {
                                Toast.makeText(MainActivity.this, "Save and exit", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onExitClicked(String tag) {
                                Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();

                            }

                        }, this, AppConstants.TAG_ERROR_DIALOG);


                break;

            case R.id.button4:


                BblDialogManager.showBblDialog(getSupportFragmentManager(),
                        LayoutInflater.from(this), getString(R.string.msg_logout_confirmation), getString(R.string.yes),
                        getString(R.string.no), ConstantsIcons.ALERT_ICON_INFO,
                        alertHandler, this, AppConstants.TAG_LOGOUT_DIALOG);


                break;
        }

    }


    private static class AlertClickHandler implements IAlertClickedCallBack {

        @Override
        public void onOkClicked(String tag) {

        }

        @Override
        public void onCancelClicked(String tag) {

        }

        @Override
        public void onExitClicked(String tag) {

        }
    }
}
