package com.payfever.presentation.fragment.balance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.payfever.R;
import com.payfever.data.model.balance.BalanceModel;
import com.payfever.presentation.basics.BaseFABFragment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by richi on 2015.10.27..
 */
public class BalanceFragment extends BaseFABFragment implements BalanceView {

    private TextView tvBalance, tvAverage;
    private LineChart lcBalance;

    private BalancePresenter mPresenter;

    private int mPrimaryColor, mSecondaryColor;

    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected int[] mValues = new int[] {
            0,      10,     30,    50,    60,    65,   70,    78,    80,    83,    85,    86
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_balance);
        mPresenter = new BalancePresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initColors();
        initPresenter();
        findUI();
        initListeners();

        mPresenter.initialize(savedInstanceState);
    }

    private void initColors() {
        mPrimaryColor = mActivity.getResources().getColor(R.color.colorPrimary);
        mSecondaryColor = mActivity.getResources().getColor(R.color.diagram_fill_color);
    }

    private void initPresenter() {
        mPresenter.setView(this);
    }

    private void findUI() {
        tvAverage   = $(R.id.tvAverage_FB);
        tvBalance   = $(R.id.tvBalance_FB);

        lcBalance   = $(R.id.lcBalance_FB);
    }

    private void initListeners() {

    }

    @Override
    public void initChart() {
        lcBalance.setDrawGridBackground(false);
        lcBalance.setNoDataTextDescription("You do not have data!");
        lcBalance.setDragEnabled(true);
        lcBalance.setScaleEnabled(true);
        lcBalance.setPinchZoom(true);
        lcBalance.setLogEnabled(true);
        lcBalance.setDescription("");
        lcBalance.getAxisLeft().removeAllLimitLines();
        lcBalance.getAxisLeft().setAxisMaxValue(100f);
        lcBalance.getAxisLeft().setAxisMinValue(-10f);
        lcBalance.getAxisLeft().setStartAtZero(true);
        lcBalance.getAxisRight().setEnabled(false);
        lcBalance.getAxisLeft().setDrawGridLines(false);
        lcBalance.getAxisLeft().setAxisLineColor(mPrimaryColor);
        lcBalance.getAxisLeft().setTextColor(mPrimaryColor);
        lcBalance.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lcBalance.getXAxis().setDrawGridLines(false);
        lcBalance.getXAxis().setSpaceBetweenLabels(0);
        lcBalance.getXAxis().setAxisLineColor(mPrimaryColor);
        lcBalance.getXAxis().setTextColor(mPrimaryColor);
        lcBalance.getLegend().setForm(Legend.LegendForm.LINE);
        lcBalance.getLegend().setTextColor(mPrimaryColor);
//        lcBalance.setBackgroundColor(mSecondaryColor);
    }

    @Override
    public void showProgress() {
        getLoadingManager().showProgress();
    }

    @Override
    public void hideProgress() {
        getLoadingManager().hideProgress();
    }

    @Override
    public void setData(BalanceModel _data) {
        setDummyData();
        tvBalance.append(": " + _data.getmBalance());
        tvAverage.append(": " + _data.getmAverage());
    }

    @Override
    public void showServerError(String _error) {
        Toast.makeText(mActivity, _error, Toast.LENGTH_SHORT)
                .show();
    }


    private void setDummyData() {
        ArrayList<Entry> yVals = new ArrayList<>();
        LineDataSet set1 = new LineDataSet(yVals, "$");
        for (int i = 0; i < mValues.length; i++) {
            yVals.add(new Entry(mValues[i], i));
        }
        set1.setColor(mPrimaryColor);
        set1.setCircleColor(mPrimaryColor);
        set1.setValueTextColor(mPrimaryColor);
        set1.setFillColor(mPrimaryColor);
        set1.setDrawCubic(true);
        set1.setDrawValues(false);
        set1.setDrawFilled(true);
        LineData data = new LineData(mMonths, set1);
        data.setValueTextColor(mPrimaryColor);
        lcBalance.setData(data);
        lcBalance.notifyDataSetChanged();
    }

}
