package com.dkv.bubblealertlib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author DKV.
 */
public class BblDialog extends BblDialogFragmentBase {

    public static boolean shown = false;
    IAlertDismissCallBack callBack = new IAlertDismissCallBack() {
        @Override
        public void dismiss() {
            shown = false;
            BblDialog.this.dismiss();
        }
    };
    private BaseFragment contentFragment = null;
    private Context context = null;
    private int width, height;
    private boolean hasEditText = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getChildFragmentManager().beginTransaction().add(R.id.fragment_container_1,
                contentFragment, "Test").addToBackStack("Test").commit();

    }

    @Override
    protected void dialogDismissed() {
        super.dialogDismissed();
        shown = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    protected View onCreateView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.layout_alert_dialog, null);
    }

    public BblDialog setContentFragment(BaseFragment contentFragment, int ID,
                                        LayoutInflater inflater, String content, String drawText, Context context) {
        this.contentFragment = contentFragment;
        this.context = context;
        this.drawText = drawText;
        int tempWidth = (62 * context.getResources().getDisplayMetrics().widthPixels) / 100;
        int tempHeight = 0;
        setWidth(tempWidth);
        int outerCircleRadius = context.getResources().getInteger(R.integer.outerCircleRadius);
        int dialogSpacing = context.getResources().getInteger(R.integer.dialogSpacing);
        View view = ScreenUtils.getMeasuredViewForAlertDialog(inflater, ID,
                ((BblContentFragment) contentFragment).buttonCount(), content, width, hasEditText);
        tempHeight = view.getMeasuredHeight() + ScreenUtils.unitToPixels(context, TypedValue.COMPLEX_UNIT_DIP,
                (2 * outerCircleRadius) + dialogSpacing);
        setHeight(tempHeight);
        shown = true;
        return this;
    }

    public BblDialog setDisMissCallBack(IAlertDismissCallBack iAlertDismissCallBack) {

        if (null != contentFragment) {
            contentFragment.setCallBack(callBack);
        }
        return this;
    }

    public BblDialog setHasEditText(boolean hasEditText) {
        this.hasEditText = hasEditText;
        return this;
    }
}
