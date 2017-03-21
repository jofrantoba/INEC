package com.inec.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


import com.inec.server.ws.gestionMantenimiento.model.Ubigeo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import inec.com.R;

/**
 * Created by root on 15/10/16.
 */
public class AdapterDistrito extends BaseAdapter {
    public static String TAG=AdapterPartido.class.getSimpleName();
    private Ubigeo selectedItem;
    private Integer indexSelected;
    private List<Ubigeo> datos=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterDistrito(Context context) {
        this.context=context;
        this.layoutInflater=(LayoutInflater.from(context));
    }

    public void setData(List<Ubigeo> data){
        datos=data;
    }

    public void insert(Ubigeo object) {
        datos.add(object);
    }

    public void setSelectedIndex(int index) {
        indexSelected=index;
        selectedItem=datos.get(indexSelected);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    public void clear() {
        datos.clear();
    }

    public void add(Ubigeo object) {
        datos.add(object);
    }

    public void addAll(Collection<? extends Ubigeo> collection) {
        Integer index=datos.size();
        Iterator<? extends Ubigeo> iterador=collection.iterator();
        while(iterador.hasNext()){
            Ubigeo bean=iterador.next();
            add(bean);
            index=index+1;
        }
    }

    public void addAll(Ubigeo... items) {
        Integer index=datos.size();
        for(int i=0;i<items.length;i++){
            Ubigeo bean=items[i];
            add(bean);
            index=index+1;
        }
    }

    public void remove(Ubigeo object) {
        datos.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    public void setNotifyOnChange(boolean notifyOnChange) {
        if(notifyOnChange){
            notifyDataSetChanged();
        }
    }
    public Context getContext() {
        return context;
    }

    @Override
    public Ubigeo getItem(int position) {
        return datos.get(position);
    }

    public int getPosition(Ubigeo item) {
        return datos.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getCodeUbigeo().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        indexSelected=position;
        selectedItem=datos.get(position);
        Ubigeo bean=datos.get(position);
        convertView = layoutInflater.inflate(R.layout.ui_combo_distrito, null);
        TextView txtNomUbigeo=(TextView) convertView.findViewById(R.id.txt_partido);
        txtNomUbigeo.setText(bean.getDistrito());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Ubigeo bean=datos.get(position);
        convertView = layoutInflater.inflate(R.layout.ui_combo_distrito, null);
        TextView txtNomUbigeo=(TextView) convertView.findViewById(R.id.txt_partido);
        txtNomUbigeo.setText(bean.getDistrito());
        return convertView;
    }
}
