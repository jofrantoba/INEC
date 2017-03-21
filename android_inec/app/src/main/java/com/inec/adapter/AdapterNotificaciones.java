package com.inec.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inec.server.ws.gestionUsuario.model.Notificacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import inec.com.R;

/**
 * Created by root on 26/11/16.
 */

public class AdapterNotificaciones extends BaseAdapter {

    private Context contextActivity;
    public Activity activity;

    private List<Notificacion> items=new ArrayList<>();
    private LayoutInflater layoutInflater;
    public AdapterNotificaciones(Activity contextActivity) {
        this.contextActivity = contextActivity;
        activity=contextActivity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setData(List<Notificacion> data) {
        this.items = data;
    }

    @Override
    public Notificacion getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View itemView = convertView;
        ViewHolder holder;
        if (itemView == null) {
            if (layoutInflater == null)
                layoutInflater = (LayoutInflater) contextActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(R.layout.ui_notificacion, viewGroup, false);
            holder = new ViewHolder();
            holder.uir_fecha = (TextView)itemView.findViewById(R.id.uinot_txt_fecha);
            itemView.setTag(holder);
        }
        else {
            holder = (ViewHolder)itemView.getTag();
        }
        final Notificacion beanNotificacion= items.get(position);
        if(beanNotificacion.getVersion()!=null) {
            holder.uir_fecha.setText(new SimpleDateFormat().format(beanNotificacion.getVersion()));
        }else{
            holder.uir_fecha.setText("");
        }
        return(itemView);
    }
    static class ViewHolder {
        TextView uir_fecha;
    }

}
