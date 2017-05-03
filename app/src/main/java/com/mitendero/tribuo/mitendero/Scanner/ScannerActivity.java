package com.mitendero.tribuo.mitendero.Scanner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.mitendero.tribuo.mitendero.MainActivity;
import com.mitendero.tribuo.mitendero.R;
import com.mitendero.tribuo.mitendero.jpa.Productos;

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
    private ImageButton torch_btn;
    private boolean isTorchOn;
    private Snackbar mySnackbar;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner);
        setupToolbar();
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        contentFrame.addView(mScannerView);

        scannedList = new ArrayList<>();


        fragmentManager = getSupportFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.finish_btn);

        isTorchOn = false;

        torch_btn = (ImageButton) findViewById(R.id.torch_btn);

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
    public void handleResult(final Result rawResult) {
        mySnackbar = Snackbar.make(mScannerView, "Retrieving data", Snackbar.LENGTH_LONG);
        mySnackbar.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                retrieveData(rawResult.getContents());
            }
        }, 500);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        }, 2000);
    }

    public void returnToVentas(View v) {
        Intent i = new Intent(ScannerActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void toggleFlash(View V) {

        if (isTorchOn) {
            torch_btn.setImageResource(R.drawable.torch_off);
        } else {
            torch_btn.setImageResource(R.drawable.torch_on);
        }
        isTorchOn = !isTorchOn;
        mScannerView.setFlash(isTorchOn);
    }


    private void retrieveData(String barcode) {
        scannerThread = new ScannerThread();
        try {
            //Retrieve from database
            String jsonString = scannerThread.execute(barcode).get();
            Gson gson = new Gson();
            Productos p = gson.fromJson(jsonString, Productos.class);

            scannedList.add(p);


            if (p != null) {
                mySnackbar = Snackbar.make(findViewById(R.id.scanner_layout), p.getNombreProducto(), Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scannedList.remove(scannedList.size() - 1);
                    }
                });
                mySnackbar.setActionTextColor(Color.BLUE);
            } else {
                mySnackbar = Snackbar.make(mScannerView, "Producto No Registrado", Snackbar.LENGTH_LONG);
            }
            mySnackbar.show();


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }



}