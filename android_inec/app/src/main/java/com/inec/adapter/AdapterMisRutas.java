package com.inec.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.inec.server.ws.gestionUsuario.model.PosicionFiscalizador;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import inec.com.R;


/**
 * Created by root on 23/09/16.
 */
public class AdapterMisRutas extends BaseAdapter {
    private Context contextActivity;
    public Activity activity;

    private List<PosicionFiscalizador> items=new ArrayList<>();
    private LayoutInflater layoutInflater;
    public AdapterMisRutas(Activity contextActivity) {
        this.contextActivity = contextActivity;
        activity=contextActivity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setData(List<PosicionFiscalizador> data) {
        this.items = data;
    }

    @Override
    public PosicionFiscalizador getItem(int position) {
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
    public View getView(final int position,View convertView, ViewGroup viewGroup) {
        View itemView = convertView;
        final ViewHolder holder;
        if (itemView == null) {
            if (layoutInflater == null)
                layoutInflater = (LayoutInflater) contextActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(R.layout.ui_ruta, viewGroup, false);
            holder = new ViewHolder();
            holder.uir_fecha = (TextView)itemView.findViewById(R.id.uir_txt_fecha);
            holder.uir_coordenadas = (TextView)itemView.findViewById(R.id.uir_txt_coordenadas);
            holder.uir_ubigeo = (TextView)itemView.findViewById(R.id.uir_txt_ubigeo);
            itemView.setTag(holder);
        }
        else {
            holder = (ViewHolder)itemView.getTag();
        }
        final PosicionFiscalizador beanPosicionFiscalizador= items.get(position);
        if(beanPosicionFiscalizador.getVersion()!=null) {
            holder.uir_fecha.setText(new SimpleDateFormat().format(beanPosicionFiscalizador.getVersion()));
        }else{
            holder.uir_fecha.setText("");
        }
        if(beanPosicionFiscalizador.getLatitud()!=null && beanPosicionFiscalizador.getLongitud()!=null) {
            holder.uir_coordenadas.setText(beanPosicionFiscalizador.getLatitud()+" , "+beanPosicionFiscalizador.getLongitud());
        }else{
            holder.uir_coordenadas.setText("");
        }

        if(beanPosicionFiscalizador.getDepartamento()!=null && beanPosicionFiscalizador.getProvincia()!=null&&
                beanPosicionFiscalizador.getDistrito()!=null) {
            holder.uir_ubigeo.setText(beanPosicionFiscalizador.getDepartamento()+" - "+
                    beanPosicionFiscalizador.getProvincia()+" - "+
                    beanPosicionFiscalizador.getDistrito());
        }else{
            holder.uir_ubigeo.setText(beanPosicionFiscalizador.getDepartamento()+" - "+
                    beanPosicionFiscalizador.getProvincia());
        }
        return(itemView);
    }
    static class ViewHolder {
        TextView uir_fecha;
        TextView uir_coordenadas;
        TextView uir_ubigeo;

    }
}
