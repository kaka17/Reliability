package com.kaiser.reliability.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kaiser.reliability.R;

/**
 * Created by ex-huangkeze001 on 2018/4/24.
 */

public class NoDataPop extends PopupWindow {
    View mView;
    TextView tvChangInfo;
    View.OnClickListener mClickListener;
    private int popupHeight;
    private int popupWidth;
    public NoDataPop(Context context, View.OnClickListener onClickListener) {
        super(context);
        mClickListener=onClickListener;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.nodata_pop, null);
        tvChangInfo= (TextView) mView.findViewById(R.id.tvChangInfo);

        tvChangInfo.setOnClickListener(onClickListener);

        setContentView(mView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.AnimationBottomFade);
//        ColorDrawable dw = new ColorDrawable(0x90000000);
//        setBackgroundDrawable(dw);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33F000FF")
        ));
        mView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = mView.getMeasuredHeight();
        popupWidth = mView.getMeasuredWidth();
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int left = mView.findViewById(R.id.llPopMenu).getLeft();
                int bottom=mView.findViewById(R.id.llPopMenu).getBottom();
                int y = (int) event.getY();
                int x= (int) event.getX();
                Log.e("paramss left:","--->"+left + " x:" + x + " bottom:" + bottom + " y" + y);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > bottom) {
                        dismiss();
                    }
                    if(x>left){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public int getPopupHeight() {
        return popupHeight;
    }

    public int getPopupWidth() {
        return popupWidth;
    }
}
