package com.dkv.bubblealertlib;


import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DKV.
 */
public class ScreenUtils {
    private final static String TAG = ScreenUtils.class.getSimpleName();


    public static int unitToPixels(Context context, int unit, int value) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(unit, value,
                r.getDisplayMetrics());
        return (int) px;
    }


    public static View getMeasuredViewForAlertDialog(LayoutInflater inflater, int itemId, int btnCount, String content, int width, boolean hasEditText) {

        View view = inflater.inflate(itemId, null);
        if (btnCount == 1) {
            view.findViewById(R.id.btnCancel).setVisibility(View.GONE);
        } else if (btnCount == 3) {
            view.findViewById(R.id.btnCancel).setVisibility(View.VISIBLE);
            view.findViewById(R.id.btnExit).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.btnCancel).setVisibility(View.VISIBLE);
        }
        ((TextView) view.findViewById(R.id.txtContent)).setText(content);

        int visibility = hasEditText ? View.VISIBLE : View.GONE;
        view.findViewById(R.id.edtLibName).setVisibility(visibility);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.UNSPECIFIED));

        return view;
    }


}
