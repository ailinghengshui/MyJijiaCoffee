package com.hzjytech.operation.module.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.hzjytech.operation.R;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.widgets.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hehongcan on 2017/9/26.
 */

public class SelectTimeActivity extends BaseActivity {


    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_startTime)
    TextView mTvStartTime;
    @BindView(R.id.tv_endTime)
    TextView mTvEndTime;
    private String mStartTime;
    private String mEndTime;

    @Override
    protected int getResId() {
        return R.layout.activity_select_time;
    }

    @Override
    protected void initView() {
        initTitle();
        initTimeView();
    }

    private void initTimeView() {
        Intent intent = getIntent();
        String openingTime = intent.getStringExtra("openingTime");
        String closingTime = intent.getStringExtra("closingTime");
        if (openingTime != null && !openingTime.equals("")) {
            mStartTime = openingTime;
            mTvStartTime.setText(openingTime);
        }
        if (closingTime != null && !closingTime.equals("")) {
            mEndTime = closingTime;
            mTvEndTime.setText(closingTime);
        }
        mTvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });
        mTvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEndTime();
            }
        });
    }

    private void selectEndTime() {
        TimePickerView.Builder builder = new TimePickerView.Builder(this,
                new TimePickerView.OnTimeSelectListener() {


                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mEndTime = new SimpleDateFormat("HH:mm").format(date);
                        mTvEndTime.setText(mEndTime);
                    }
                });
        builder.setLabel("", "", "", "时", "分", "");
        builder.setType(TimePickerView.Type.HOURS_MINS);
        TimePickerView timePickerView = builder.build();
        timePickerView.show();
    }

    private void selectStartTime() {
        TimePickerView.Builder builder = new TimePickerView.Builder(this,
                new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mStartTime = new SimpleDateFormat("HH:mm").format(date);
                        mTvStartTime.setText(mStartTime);
                    }
                });
        builder.setLabel("", "", "", "时", "分", "");
        builder.setType(TimePickerView.Type.HOURS_MINS);
        TimePickerView timePickerView = builder.build();
        timePickerView.show();
    }

    private void initTitle() {
        final Intent intent = new Intent();
        mTitleBar.setTitle(R.string.set_operation_time);
        mTitleBar.setLeftText(R.string.cancel);
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.addAction(new TitleBar.TextAction("保存") {
            @Override
            public void performAction(View view) {
                intent.putExtra("startTime", mStartTime);
                intent.putExtra("endTime", mEndTime);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
