package com.example.lcq.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lcq.myapp.R;
import com.example.lcq.myapp.adapter.HighFinanceAdapter;
import com.example.lcq.myapp.mode.HighFinanceInfo;

import java.util.ArrayList;
import java.util.List;

public class HighEndFinanceActivity extends Activity {
    private HighFinanceAdapter adapter;
    private List<HighFinanceInfo> highFinanceInfoList = new ArrayList<>();
    private ListView highFinanceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_finance);
        highFinanceList = findViewById(R.id.high_finance_list);
        mockData();
        adapter = new HighFinanceAdapter(highFinanceInfoList,this);
        highFinanceList.setAdapter(adapter);
        highFinanceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HighEndFinanceActivity.this, "进入高端理财详情", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mockData() {
        HighFinanceInfo financeInfo = new HighFinanceInfo();
        financeInfo.setInvestType("1");
        financeInfo.setProdCode("1111");
        financeInfo.setProdNme("金太极");
        financeInfo.setTypeCode(1);
        highFinanceInfoList.add(financeInfo);
        HighFinanceInfo financeInfo1 = new HighFinanceInfo();
        financeInfo1.setInvestType("2");
        financeInfo1.setProdCode("1111");
        financeInfo1.setProdNme("金太极1");
        financeInfo1.setTypeCode(1);
        highFinanceInfoList.add(financeInfo1);
        HighFinanceInfo financeInfo2 = new HighFinanceInfo();
        financeInfo2.setInvestType("3");
        financeInfo2.setProdCode("1111");
        financeInfo2.setProdNme("金罗盘");
        financeInfo2.setTypeCode(2);
        highFinanceInfoList.add(financeInfo2);
        HighFinanceInfo financeInfo3 = new HighFinanceInfo();
        financeInfo3.setInvestType("1");
        financeInfo3.setProdCode("1111");
        financeInfo3.setProdNme("量化多策略");
        financeInfo3.setTypeCode(3);
        highFinanceInfoList.add(financeInfo3);
    }
}
