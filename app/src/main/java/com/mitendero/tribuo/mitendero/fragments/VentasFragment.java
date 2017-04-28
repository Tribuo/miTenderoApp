package com.mitendero.tribuo.mitendero.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitendero.tribuo.mitendero.R;
import com.mitendero.tribuo.mitendero.Scanner.ScannerActivity;

public class VentasFragment extends Fragment {

    public VentasFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ventas, container, false);
    }

    public void launchScanActivity(View v) {
        Intent intent = new Intent(getActivity(), ScannerActivity.class);
        startActivity(intent);
    }


}
