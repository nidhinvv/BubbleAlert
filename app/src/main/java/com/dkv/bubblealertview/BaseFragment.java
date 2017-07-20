package com.dkv.bubblealertview;

import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * @author DKV.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected boolean isActive = true;
    protected IAlertDismissCallBack dismissCallBack = null;
    protected IAlertClickedCallBack clickedCallBack = null;

    OnFragmentInteractionListener mOnFragmentInteractionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            AppLog.logException("BUBBLELINGO", e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnFragmentInteractionListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        isActive = false;
        AppLog.logString(TAG, "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();


        isActive = true;
        AppLog.logString(TAG, "onResume");

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            fragmentVisible();
        } else {
            fragmentHidden();
        }
    }


    protected void fragmentVisible() {
    }

    protected void fragmentHidden() {
    }


    public IAlertDismissCallBack getCallBack() {
        return dismissCallBack;
    }

    public void setCallBack(IAlertDismissCallBack callBack) {
        this.dismissCallBack = callBack;
    }


}
