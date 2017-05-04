package com.mitendero.tribuo.mitendero.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;


import com.mitendero.tribuo.mitendero.R;
import com.mitendero.tribuo.mitendero.jpa.Categorias;
import com.mitendero.tribuo.mitendero.jpa.Fabricantes;
import com.mitendero.tribuo.mitendero.jpa.Marcas;
import com.mitendero.tribuo.mitendero.jpa.Presentaciones;
import com.mitendero.tribuo.mitendero.jpa.Productos;
import com.mitendero.tribuo.mitendero.jpa.Subcategorias;
import com.mitendero.tribuo.mitendero.jpa.Sucursales;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by Camilo Aguado on 4/29/2017.
 */

public class VentasAdapter extends ArrayAdapter<Productos> {

    private ArrayList<Productos> list;

    Context mContext;

    private NumberFormat format;

    static final private String LOCALE = "es_CO";
    private static int minValue = 1;
    private static int maxValue = 99;

    public VentasAdapter(ArrayList<Productos> data, Context context) {
        super(context, R.layout.ventas_list_header, data);
        this.list = data;
        this.mContext = context;
        format = NumberFormat.getInstance(new Locale(LOCALE));
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Productos item = getItem(position);

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ventas_list_header, parent, false);
            holder._price = (TextView) convertView.findViewById(R.id.price);
            holder._quantity = (NumberPicker) convertView.findViewById(R.id.quantity);
            holder._priceUnit = (TextView) convertView.findViewById(R.id.unit_price);
            holder._presentation = (TextView) convertView.findViewById(R.id.presentation);
            holder._prodName = (TextView) convertView.findViewById(R.id.prod_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        holder._prodName.setText(item.getNombreProducto());

        holder._priceUnit.setText("$" + format.format(item.getPrecioSugerido()));

        String presentacion = item.getPresentaciones().getCantidad() + " " + item.getPresentaciones().getUnidadMedida();
        holder._presentation.setText(presentacion);

        int quantity = 0;

        holder._quantity.setMinValue(minValue);
        holder._quantity.setMaxValue(maxValue);

        /** quantity = list.stream()
         .filter(c -> item.getIdProducto().equals(c.getIdProducto()))
         .count();**/

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdProducto() == item.getIdProducto()) {
                quantity++;
            }
        }
        //remove other occurrences of the same product
        for (int i = position + 1; i < list.size(); i++) {
            if (list.get(i).getIdProducto().equals(item.getIdProducto()))
                list.remove(i);

        }
        notifyDataSetChanged();

        holder._quantity.setValue(quantity);
        holder._quantity.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        holder._quantity.setWrapSelectorWheel(false);

        holder._quantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int oldTotal = item.getPrecioSugerido() * oldVal;
                int newTotal = item.getPrecioSugerido() * newVal;
                int tpint = 0;
                String tp = VentasFragment.totalPriceView.getText().toString();
                if (!(tp.equals("") || tp == null)) {
                    tp = tp.replace(".", "");
                    tp = tp.replace("$", "");
                    tpint = Integer.parseInt(tp);
                }

                tpint = tpint - oldTotal + newTotal;

                VentasFragment.totalPriceView.setText("$" + format.format(tpint));
                holder._price.setText("$" + format.format(newTotal));
            }
        });

        int total = item.getPrecioSugerido() * holder._quantity.getValue();
        holder._price.setText("$" + format.format(total));
        String tp = VentasFragment.totalPriceView.getText().toString();
        int tpint = 0;
        if (!(tp.equals("") || tp == null)) {
            tp = tp.replace(".", "");
            tp = tp.replace("$", "");
            tpint = Integer.parseInt(tp);

        }
        tpint += total;
        VentasFragment.totalPriceView.setText("$" + format.format(tpint));
        return convertView;
    }

    static private class ViewHolder {
        TextView _price;
        NumberPicker _quantity;
        TextView _priceUnit;
        TextView _presentation;
        TextView _prodName;
    }


}
