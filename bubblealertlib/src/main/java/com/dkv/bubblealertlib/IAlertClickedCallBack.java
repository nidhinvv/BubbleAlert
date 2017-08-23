package com.dkv.bubblealertlib;

/**
 * @author DKV.
 */
public interface IAlertClickedCallBack {

    void onOkClicked(String tag);

    void onCancelClicked(String tag);

    void onExitClicked(String tag);
}
