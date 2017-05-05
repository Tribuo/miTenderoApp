package com.mitendero.tribuo.mitendero.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mitendero.tribuo.mitendero.R;
import com.mitendero.tribuo.mitendero.Scanner.ScannerActivity;
import com.mitendero.tribuo.mitendero.jpa.Categorias;
import com.mitendero.tribuo.mitendero.jpa.Fabricantes;
import com.mitendero.tribuo.mitendero.jpa.Marcas;
import com.mitendero.tribuo.mitendero.jpa.Presentaciones;
import com.mitendero.tribuo.mitendero.jpa.Productos;
import com.mitendero.tribuo.mitendero.jpa.Subcategorias;

import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class VentasFragment extends Fragment {

    public static ArrayList<Productos> listaProductos;
    private static Context context;
    private ListView listView;
    private static VentasAdapter ventasAdapter;
    public static TextView totalPriceView;

    static final private String LOCALE = "es_CO";

    private NumberFormat format;

    public static int TOTALPRICE = 0;

    public VentasFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ConstraintLayout ll = (ConstraintLayout) inflater.inflate(R.layout.fragment_ventas, container, false);

        listView = (ListView) ll.findViewById(R.id.list_prod_venta);

        listaProductos = new ArrayList<>();


        VentasFragment.context = getContext();

        ventasAdapter = new VentasAdapter(listaProductos, getContext());

        listView.setAdapter(ventasAdapter);


        int itemsCount = listView.getAdapter().getCount();

        format = NumberFormat.getInstance(new Locale(LOCALE));

        totalPriceView = (TextView) ll.findViewById(R.id.total_price);
        totalPriceView.setText("$"+TOTALPRICE);

        dummyList();
        return ll;

    }

    public void launchScanActivity(View v) {
        Intent intent = new Intent(getActivity(), ScannerActivity.class);
        startActivity(intent);
    }

    private void dummyList() {
        Fabricantes fabricantes = new Fabricantes("Babaria");
        Marcas marcas = new Marcas(fabricantes, "Club Colombia");
        Categorias categorias = new Categorias("Babidas");
        Subcategorias subcategorias = new Subcategorias(categorias, "Alcoholicas");
        Presentaciones presentaciones = new Presentaciones(330, "mL");
        Productos productos = new Productos(marcas, presentaciones, subcategorias, "Club Colombia Negra", "7702004003775", "Cerveza");
        productos.setPrecioSugerido(2500);
        productos.setIdProducto(1);
        listaProductos.add(productos);
        Productos productos2 = new Productos(marcas, presentaciones, subcategorias, "Club Colombia Dorada", "7702004003508", "Cerveza");
        productos2.setPrecioSugerido(2000);
        productos2.setIdProducto(2);
        listaProductos.add(productos2);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
