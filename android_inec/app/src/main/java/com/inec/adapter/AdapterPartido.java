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


import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import inec.com.R;

/**
 * Created by root on 15/10/16.
 */
public class AdapterPartido extends BaseAdapter {
    public static String TAG=AdapterPartido.class.getSimpleName();
    private PartidoPolitico selectedItem;
    private Integer indexSelected;
    private List<PartidoPolitico> datos=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterPartido(Context context) {
        this.context=context;
        this.layoutInflater=(LayoutInflater.from(context));
    }

    public void setData(List<PartidoPolitico> data){
        datos.clear();
        for (int i = 0; i < data.size(); i++) {
            insert(data.get(i));
        }
        if(!datos.isEmpty()){
            selectedItem=datos.get(0);
            setSelectedIndex(0);
        }
    }


    public void insert(PartidoPolitico object) {
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

    public void add(PartidoPolitico object) {
        datos.add(object);
    }

    public void addAll(Collection<? extends PartidoPolitico> collection) {
        Integer index=datos.size();
        Iterator<? extends PartidoPolitico> iterador=collection.iterator();
        while(iterador.hasNext()){
            PartidoPolitico bean=iterador.next();
            add(bean);
            index=index+1;
        }
    }

    public void addAll(PartidoPolitico... items) {
        Integer index=datos.size();
        for(int i=0;i<items.length;i++){
            PartidoPolitico bean=items[i];
            add(bean);
            index=index+1;
        }
    }

    public void remove(PartidoPolitico object) {
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
    public PartidoPolitico getItem(int position) {
        return datos.get(position);
    }

    public int getPosition(PartidoPolitico item) {
        return datos.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getCodePartidoPolitico().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        indexSelected=position;
        selectedItem=datos.get(position);
        PartidoPolitico bean=datos.get(position);
        convertView = layoutInflater.inflate(R.layout.ui_combo_partidopolitico, null);
        TextView txtNomPartidoPolitico=(TextView) convertView.findViewById(R.id.txt_partido);
        txtNomPartidoPolitico.setText(bean.getNombrePartido());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        PartidoPolitico bean=datos.get(position);
        convertView = layoutInflater.inflate(R.layout.ui_combo_partidopolitico, null);
        TextView txtNomPartidoPolitico=(TextView) convertView.findViewById(R.id.txt_partido);
        txtNomPartidoPolitico.setText(bean.getNombrePartido());
        return convertView;
    }
}
