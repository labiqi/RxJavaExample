package com.example.lcq.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcq.myapp.R;
import com.example.lcq.myapp.mode.HighFinanceInfo;

import java.util.List;

public class HighFinanceAdapter extends BaseAdapter {
    private List<HighFinanceInfo> list;
    private LayoutInflater inflater;

    public HighFinanceAdapter(List<HighFinanceInfo> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.high_finance_item, null);
            holder.highFinanceIcon = convertView.findViewById(R.id.high_finance_icon);
            holder.highFinanceArrow = convertView.findViewById(R.id.high_finance_arrow);
            holder.highFinanceName = convertView.findViewById(R.id.high_finance_name);
            holder.highFinanceCompany = convertView.findViewById(R.id.high_finance_company);
            holder.highFinanceType = convertView.findViewById(R.id.high_finance_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HighFinanceInfo info = (HighFinanceInfo) getItem(position);
        holder.highFinanceName.setText(info.getProdNme());
        holder.highFinanceType.setText(info.getInvestType());
        if (info.getTypeCode() == 2) {
            holder.highFinanceIcon.setBackgroundResource(R.drawable.high_finance_background_item2);
        } else if (info.getTypeCode() == 3) {
            holder.highFinanceIcon.setBackgroundResource(R.drawable.high_finance_background_item3);
        } else {
            holder.highFinanceIcon.setBackgroundResource(R.drawable.high_finance_background_item1);
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView highFinanceIcon;
        TextView highFinanceName;
        TextView highFinanceCompany;
        TextView highFinanceType;
        ImageView highFinanceArrow;
    }
}
