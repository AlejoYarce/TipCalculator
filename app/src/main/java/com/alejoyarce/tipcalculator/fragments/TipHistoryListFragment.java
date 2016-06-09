package com.alejoyarce.tipcalculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alejoyarce.tipcalculator.R;
import com.alejoyarce.tipcalculator.TipCalcApp;
import com.alejoyarce.tipcalculator.activities.TipDetailActivity;
import com.alejoyarce.tipcalculator.adapter.OnItemClickListener;
import com.alejoyarce.tipcalculator.adapter.TipAdapter;
import com.alejoyarce.tipcalculator.domain.TipRecord;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdapter tipAdapter;

    public TipHistoryListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);

        initAdapter();
        initRecyclerView();

        return view;
    }

    public void initAdapter() {
        if ( tipAdapter == null ) {
            tipAdapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }

    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tipAdapter);
    }

    @Override
    public void addRecord(TipRecord tipRecord) {
        tipAdapter.add(tipRecord);
    }

    @Override
    public void clearRecords() {
        tipAdapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipCalcApp.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipCalcApp.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipCalcApp.DATE_KEY, tipRecord.getDateFormatted());

        startActivity(intent);
    }
}
