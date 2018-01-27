package com.csmprojects.sanvaada;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by chathuranga on 8/12/17.
 */
public class MainGridViewPagerFragment extends Fragment {

    List<GifItem> items;
    GridView mainGrid;
    Main mainActivity;
    MainGridViewAdapter adapter;
    ByteArrayOutputStream out;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_grid_view_layout, container, false);
        int itemListNo = getArguments().getInt("itemlistno");
        mainActivity = (Main) getActivity();
        mainGrid = (GridView) view.findViewById(R.id.mainGridView);
        adapter = new MainGridViewAdapter(getActivity(), itemListNo);
        mainGrid.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        out = new ByteArrayOutputStream();
        mainGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                out.reset();
                GifItem item = (GifItem) adapter.getItem(i);
                mainActivity.displayItemsList.add(item);
                mainActivity.displayAdapter.notifyDataSetChanged();
                mainActivity.startMainAnimation();


            }
        });
    }
}
