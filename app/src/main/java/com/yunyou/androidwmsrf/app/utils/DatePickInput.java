package com.yunyou.androidwmsrf.app.utils;

/**
 * Author:      yunyou
 * Date:        2018-07-23 20:33
 * Description: 数字工具类（http://javapub.iteye.com/blog/666544）
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

public class DatePickInput extends TextView {
    /**
     * 日期框
     */
    private NewDatePicker mDialog;
    private final Context context;
    /**
     * 月份的取值
     */
    private final String[] mDisplayMonths = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    public DatePickInput(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public DatePickInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public DatePickInput(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        if (isInEditMode()) {
            return;
        }
        setFocusable(false);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        final Calendar c = Calendar.getInstance(Locale.CHINA);

        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Date date = new Date();
                try {
                    date = dateFormat.parse(getText().toString());
                } catch (ParseException ignored) {
                }
                c.setTime(date);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                mDialog = new NewDatePicker(getContext(), AlertDialog.THEME_HOLO_LIGHT, null, year, month, day);
                mDialog.setTitle(year + "年 " + (month + 1) + "月 " + day + "日");
                mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = mDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();

                        c.set(year, month, day);
                        Date date = c.getTime();

                        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                        setText(dateFormat.format(c.getTime()));
                    }
                });
                mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                mDialog.show();
                /**
                 * 初始设置月份
                 */
                DatePicker dp = mDialog.getDatePicker();
                if (dp != null) {
                    ((NumberPicker) ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(1)).setDisplayedValues(mDisplayMonths);
                }
            }
        });
    }
}