package com.kaiser.reliability.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.kaiser.reliability.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yehan099 on 2017/7/4.
 */

public class CustomerPickerDialog extends CustomerBaseDialog {
//    public static final String TAG = CustomerSourceDialog.class.getSimpleName();

    public static final int GRAVITY_LEFT = 0;
    public static final int GRAVITY_CENTER = 1;

    private LoopView contentView;
    private LoopView.OnItemSelectedListener onItemSelectedListener;
    private OnClickOkListener onClickOkListener;
    private int mTextGravity = GRAVITY_LEFT;
    private String index;
    private LinearLayout mLlTitle;

    public CustomerPickerDialog(Context context) {
        super(context);
        initView(context);
    }

    public CustomerPickerDialog(Context context, String[] menus) {
        this(context);
        setMenus(menus);
    }
    public CustomerPickerDialog(Context context, List<String> menus, LoopView.OnItemSelectedListener onItemSelectedListener) {
        this(context,menus,"");
        this.onItemSelectedListener = onItemSelectedListener;
        setMenus(menus);
    }

    public CustomerPickerDialog(Context context, String[] menus, int textGravity) {
        this(context);
        setMenus(menus, textGravity);
    }

    public CustomerPickerDialog(Context context, List<String> menus, int textGravity) {
        this(context);
        setMenus(menus, textGravity);
    }

    public CustomerPickerDialog(Context context, List<String> menus, int textGravity, LoopView.OnItemSelectedListener onItemSelectedListener) {
        this(context);
        setMenus(menus, textGravity);
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public CustomerPickerDialog(Context context, List<String> menus, String index) {
        this(context);
        setBtn2ClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentView.onItemSelected();
            }
        });
        this.index = index;
        Window window = getWindow();
        WindowManager.LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.4f;
        int width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.75f);
        int height = (int) (window.getWindowManager().getDefaultDisplay().getHeight() * 0.5f);

        window.setLayout(width, height);

        setMenus(menus, GRAVITY_LEFT, this.index);
    }



    private void initView(Context context) {
        mLlTitle = (LinearLayout) findViewById(R.id.ll_title);
        mLlTitle.setVisibility(View.GONE);
        contentView = (LoopView) LayoutInflater.from(context).inflate(R.layout.loop_dialog, null);
        contentView.setPadding((int)context.getResources().getDimension(R.dimen.dp_40px),0,
                (int)context.getResources().getDimension(R.dimen.dp_40px),0);
        contentView.setNotLoop();
        contentView.setTextSize(15f);

    }

    public void setMenus(String[] menus, int textGravity) {
        List<String> listMenus = Arrays.asList(menus);
        setMenus(listMenus, textGravity, null);
    }

    public void setMenus(List<String> menus) {
        setMenus(menus, GRAVITY_LEFT);
    }

    public void setMenus(String[] menus) {
        setMenus(menus, GRAVITY_LEFT);
    }

    public void setMenus(List<String> menus, int textGravity) {
        setMenus(menus, textGravity, null);
    }

    public void setMenus(List<String> menus, int textGravity, String index) {
        if (textGravity == GRAVITY_LEFT) {
            mTextGravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        } else {
            mTextGravity = Gravity.CENTER;
        }
        contentView.setItems(menus);
        contentView.setListener(onItemSelectedListener);

    }

    @Override
    public View createContentView() {

        return contentView;
    }

    public OnClickOkListener getOnClickOkListener() {
        return onClickOkListener;
    }

    public void setOnClickOkListener(OnClickOkListener onClickOkListener) {
        this.onClickOkListener = onClickOkListener;
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    public void setSelectedPosition(int position) {
       contentView.setInitPosition(position);
    }

    public interface OnClickOkListener {
        void onclick();
    }
}
