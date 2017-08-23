package com.dkv.bubblealertlib;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author DKV.
 */
public abstract class BblDialogFragmentBase extends DialogFragment implements DialogInterface.OnDismissListener {

    protected FrameLayout.LayoutParams containerLayoutParams;
    protected String drawText = "i";
    private AlertDrawable alertDrawable = null;
    private FrameLayout container = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int outerCircleRadius = getResources().getInteger(R.integer.outerCircleRadius);
        int arc = getResources().getInteger(R.integer.dialogArc);
        alertDrawable = new AlertDrawable(outerCircleRadius, 30, arc, drawText, getContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(this);


        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_alert_dialog_main, container, false);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialogDismissed();
    }

    protected void dialogDismissed() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = (FrameLayout) view;
        containerLayoutParams = new FrameLayout.LayoutParams(getWidth(), getHeight());
        container.setBackgroundDrawable(alertDrawable);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View contentView = onCreateView(getActivity().getLayoutInflater());
        if (contentView == null) {

            dismiss();
            return;
        }

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));

        linearLayout.setLayoutParams(containerLayoutParams);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1));

        linearLayout.addView(contentView);
        container.addView(linearLayout);
    }

    protected abstract int getHeight();

    protected abstract int getWidth();

    protected abstract View onCreateView(LayoutInflater layoutInflater);

}
