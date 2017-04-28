package com.mitendero.tribuo.mitendero.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mitendero.tribuo.mitendero.R;
import com.mitendero.tribuo.mitendero.fragments.VentasFragment;
import com.mitendero.tribuo.mitendero.jpa.Productos;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScannerActivity extends BaseScannerActivity implements ZBarScannerView.ResultHandler {

    private FragmentManager fragmentManager;

    private ZBarScannerView mScannerView;
    private FloatingActionButton fab;
    private ArrayList<Productos> scannedList;
    private ScannerThread scannerThread;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner);
        setupToolbar();
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        contentFrame.addView(mScannerView);

        scannedList = new ArrayList<>();

        scannerThread = new ScannerThread();

        fragmentManager = getSupportFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.finish_btn);


    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        try {
            Toast.makeText(this, "Retrieving data...", Toast.LENGTH_SHORT).show();
            JSONObject jsonObject = null;
            String jsonString = scannerThread.execute(rawResult.getContents()).get();
            Gson gson = new Gson();
            Productos p = gson.fromJson(jsonString, Productos.class);
            scannedList.add(p);
            Toast.makeText(this, p.getNombreProducto(), Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScannerView.resumeCameraPreview(ScannerActivity.this);
                }
            }, 2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void returnToVentas (View v) {
        fragmentManager.beginTransaction().replace(R.id.viewpager, new VentasFragment()).commit();

    }



}