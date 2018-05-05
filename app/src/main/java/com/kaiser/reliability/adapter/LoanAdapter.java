package com.kaiser.reliability.adapter;

import android.widget.TextView;

import com.kaiser.reliability.R;
import com.kaiser.reliability.base.baseadapter.BaseQuickAdapter;
import com.kaiser.reliability.base.baseadapter.BaseViewHolder;
import com.kaiser.reliability.bean.loanbean.LoanBean;

import java.util.List;

/**
 * Created by ex-huangkeze001 on 2018/4/19.
 */

public class LoanAdapter extends BaseQuickAdapter<LoanBean> {
    public LoanAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, LoanBean item, int position) {
       TextView tvMoney= helper.getView(R.id.tvMoney);
       TextView tvName= helper.getView(R.id.tvName);
       TextView tvCrete= helper.getView(R.id.tvCrete);

        tvMoney.setText(item.getMoney());
        tvName.setText(item.getName());
        tvCrete.setText(item.getCredit());

    }
}
