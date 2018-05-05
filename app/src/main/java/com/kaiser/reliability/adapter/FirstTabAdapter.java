package com.kaiser.reliability.adapter;



import android.widget.ImageView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.base.baseadapter.BaseQuickAdapter;
import com.kaiser.reliability.base.baseadapter.BaseViewHolder;
import com.kaiser.reliability.bean.FirstBean;
import com.kaiser.reliability.utils.ImageLoaderUtils;

import java.util.List;



/**
 * 描述：
 * Created by qyh on 2016/12/30.
 */
public class FirstTabAdapter extends BaseQuickAdapter {
    
    public FirstTabAdapter(int layoutResId, List<FirstBean> listData) {
        super(layoutResId, listData);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item, int position) {
        FirstBean data=(FirstBean)item;
        ImageLoaderUtils.display(mContext, (ImageView) helper.getView(R.id.iv_item_picture)
                , data.getUrl());
    }
}
