package com.inec.adapter;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.ui_editar_incidencia.UiEditarIncidenciaImpl;
import com.inec.ui_listar_incidencia.UiListarIncidenciaImpl;
import com.inec.ui_reporte.UiReporte;
import com.inec.ui_reporte.UiReporteImpl;
import com.inec.utils.AppController;
import com.inec.utils.FeedImageView;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import harmony.java.awt.Color;
import inec.com.R;


import static android.content.Context.NOTIFICATION_SERVICE;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.beanIncidencia;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.btnLeft;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.btnRight;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.imgFotografia;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.listUrlFoto;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.mFileDescriptor;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.mPdfRenderer;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.posicion;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtCantidadGaleria;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDescripcionGaleria;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDisplayInfoCoordenadas;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDisplayInfoDescripcion;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDisplayInfoDireccion;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDisplayInfoFecha;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.txtDisplayInfoTipI;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.urlFoto;


/**
 * Created by root on 23/09/16.
 */
public class AdapterListarIncidencia extends BaseAdapter {
    private Context contextActivity;
    public Activity activity;
    private final static String NOMBRE_DIRECTORIO = "INEC_Reportes";
    private static String NOMBRE_DOCUMENTO = "";
    private final static String ETIQUETA_ERROR = "ERROR";


    private List<Incidencia> items=new ArrayList<>();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private LayoutInflater layoutInflater;
    public AdapterListarIncidencia(Activity contextActivity) {
        this.contextActivity = contextActivity;
        activity=contextActivity;
    }


    @Override
    public int getCount() {
        return items.size();
    }


    public void setData(List<Incidencia> data) {
        this.items = data;
    }


    @Override
    public Incidencia getItem(int position) {
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
            itemView = layoutInflater.inflate(R.layout.ui_listar_incidencia_item, viewGroup, false);
            holder = new ViewHolder();
            holder.nombrePartido = (TextView)itemView.findViewById(R.id.id_nombre_partido);
            holder.timestamp = (TextView)itemView.findViewById(R.id.id_fecha);
            holder.ubigeo = (TextView)itemView.findViewById(R.id.id_ubigeo);
            holder.feedImageView = (FeedImageView) itemView.findViewById(R.id.id_img_fotografia);
            holder.myToolbar = (Toolbar) itemView.findViewById(R.id.id_menu_card);
            holder.myToolbar.inflateMenu(R.menu.menu_card);
            itemView.setTag(holder);
        }
        else {
            holder = (ViewHolder)itemView.getTag();
        }
        final Incidencia beanIncidenciaAdapter= items.get(position);
        if(beanIncidenciaAdapter.getNombrePartidoPolitico()!=null) {
            holder.nombrePartido.setText(beanIncidenciaAdapter.getNombrePartidoPolitico());
        }else{
            holder.nombrePartido.setText("");
        }
        if(beanIncidenciaAdapter.getBeanUbigeo()!=null) {
            holder.ubigeo.setText(beanIncidenciaAdapter.getBeanUbigeo().getDepartamento()+"-"+
                    beanIncidenciaAdapter.getBeanUbigeo().getProvincia()+"-"+
                    beanIncidenciaAdapter.getBeanUbigeo().getDistrito());
        }else{
            holder.ubigeo.setText("");
        }
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(beanIncidenciaAdapter.getVersion(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);
        if (beanIncidenciaAdapter.getListUrlFoto()!= null) {
            if(!beanIncidenciaAdapter.getListUrlFoto().isEmpty()){
                holder.feedImageView.setImageUrl(beanIncidenciaAdapter.getListUrlFoto().get(0), imageLoader);
                holder.feedImageView.setVisibility(View.VISIBLE);
                holder.feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                    @Override
                    public void onError() {
                    }
                    @Override
                    public void onSuccess() {
                    }
                });
            }
        } else {
            holder.feedImageView.setVisibility(View.GONE);
        }
        holder.feedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUrlFoto= beanIncidenciaAdapter.getListUrlFoto();
                urlFoto = listUrlFoto.get(0);
                posicion=0;
                if (!urlFoto.equals("")) {
                    txtCantidadGaleria.setText("("+(posicion+1)+"/"+listUrlFoto.size()+")");
                    txtDescripcionGaleria.setText(beanIncidenciaAdapter.getNombrePartidoPolitico()+":  "+new SimpleDateFormat().format(beanIncidenciaAdapter.getVersion()));
                    txtDisplayInfoTipI.setText(beanIncidenciaAdapter.getCodeTipoIncidencia());
                    txtDisplayInfoDireccion.setText(beanIncidenciaAdapter.getDireccion());
                    txtDisplayInfoDescripcion.setText(beanIncidenciaAdapter.getDescripcion());
                    txtDisplayInfoCoordenadas.setText(beanIncidenciaAdapter.getListLatitud().get(posicion)+","+beanIncidenciaAdapter.getListLongitud().get(posicion));
                    txtDisplayInfoFecha.setText(new SimpleDateFormat().format(beanIncidenciaAdapter.getVersion()));
                    imgFotografia.setImageUrl(urlFoto, imageLoader);
                    beanIncidencia=beanIncidenciaAdapter;
                    imgFotografia.setVisibility(View.VISIBLE);
                } else {
                    imgFotografia.setVisibility(View.GONE);
                }
                btnLeft.setVisibility(View.GONE);
                if(listUrlFoto.size()==1){
                    btnRight.setVisibility(View.GONE);
                }else{
                    btnRight.setVisibility(View.VISIBLE);
                }


            }
        });
        holder.myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_editar:
                        Intent intent = new Intent(activity, UiEditarIncidenciaImpl.class);
                        intent.putExtra("bundle_descripcion",beanIncidenciaAdapter.getDescripcion());
                        intent.putExtra("bundle_direccion",beanIncidenciaAdapter.getDireccion());
                        intent.putExtra("bundle_tipoincidencia",beanIncidenciaAdapter.getCodeTipoIncidencia());
                        intent.putExtra("bundle_codeincidencia",beanIncidenciaAdapter.getCodeIncidencia());
                        activity.startActivity(intent);
                        break;
                    case R.id.item_reporte:
                        ProgressDialog progress = new ProgressDialog(activity);
                        progress.setMessage("Generando Reporte, por favor espere...");
                        new MyTask(progress, activity,beanIncidenciaAdapter).execute();
                        break;
                }
                return true;
            }
        });
        return(itemView);
    }
    static class ViewHolder {
        TextView nombrePartido;
        TextView timestamp;
        TextView ubigeo;
        FeedImageView feedImageView;
        Toolbar myToolbar;
    }


    public static String PATH=null;
    public void generarReporte(Incidencia beanIncidencia) {
        Document documento = new Document();
        try {
            NOMBRE_DOCUMENTO="Inec_ra_"+beanIncidencia.getVersion()+".pdf";
            List<String> listUrlFoto= beanIncidencia.getListUrlFoto();
            List<Double> listLatitud= beanIncidencia.getListLatitud();
            List<Double> listLongitud= beanIncidencia.getListLongitud();
            File f = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPdf = new FileOutputStream(
                    f.getAbsolutePath());
            PATH= f.getAbsolutePath();
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
            documento.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 22,
                    Font.BOLD, Color.black);
            documento.add(new Paragraph("INEC - Reporte de Actividad", font));
            documento.add(new Paragraph("\n"));
            font = FontFactory.getFont(FontFactory.HELVETICA, 18,
                    Font.BOLD, Color.black);
            documento.add(new Paragraph("\t FECHA: "+new SimpleDateFormat().format(beanIncidencia.getVersion()), font));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("\n"));
            Paragraph myParagraph= new Paragraph();
            font = FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.NORMAL, Color.black);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\n\nDETALLE DE ACTIVIDAD: \n\n");
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nPARTIDO POLITICO: \n\n\t"+beanIncidencia.getNombrePartidoPolitico());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nTIPO ACTIVIDAD: \n\n\t"+beanIncidencia.getCodeTipoIncidencia());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nDIRECCION: \n\n\t"+beanIncidencia.getDireccion());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nDESCRIPCION: \n\n\t"+beanIncidencia.getDescripcion());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nUBICACION: \n\n\t"+beanIncidencia.getBeanUbigeo().getDepartamento()+"-"+
                    beanIncidencia.getBeanUbigeo().getProvincia()+"-"+
                    beanIncidencia.getBeanUbigeo().getDistrito());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\n" +
                    "\n\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n" +
                    "\n\n\n\n\n");
            documento.add(myParagraph);
            List<Image> listImage= new ArrayList<>();
            for (int i = 0; i < listUrlFoto.size(); i++) {
                Bitmap bitmap = getBitmapFromURL(listUrlFoto.get(i));
                Bitmap bm=Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/2,bitmap.getHeight()/2,true);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                Image imagen = Image.getInstance(stream.toByteArray());
                imagen.setAlignment(Image.ALIGN_CENTER);
                listImage.add(imagen);
            }
            PdfPTable tabla = new PdfPTable(1);
            for (int i = 0; i < listImage.size(); i++) {
                PdfPCell cellOne = new PdfPCell();
                cellOne.setPadding(20);
                cellOne.setImage(listImage.get(i));
                tabla.addCell(cellOne);
            }
            documento.add(tabla);
        } catch (DocumentException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } catch (IOException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());


        } finally {
            documento.close();
        }
    }

    public void generarReporteV2(Incidencia beanIncidencia) {
        Document documento = new Document();
        try {
            NOMBRE_DOCUMENTO="Inec_ra_"+beanIncidencia.getVersion()+".pdf";
            File f = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPdf = new FileOutputStream(
                    f.getAbsolutePath());
            PATH= f.getAbsolutePath();
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
            documento.open();
            Paragraph mParagraph = new Paragraph();
            mParagraph.setAlignment(Element.ALIGN_CENTER);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 22,
                    Font.BOLD, Color.black);
            mParagraph.setFont(font);
            mParagraph.add("REPORTE DE ACTIVIDAD DIARIA");
            documento.add(mParagraph);
            documento.add(new Paragraph("\n"));
            font = FontFactory.getFont(FontFactory.HELVETICA, 18,
                    Font.NORMAL, Color.black);
            documento.add(new Paragraph("FECHA: "+new SimpleDateFormat().format(beanIncidencia.getVersion()), font));
            documento.add(new Paragraph("\n"));
            Paragraph myParagraph= new Paragraph();
            font = FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.NORMAL, Color.black);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\n\nACTIVIDAD: \n");
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\n"+beanIncidencia.getDescripcion());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\nCOORDENADAS: \n\n");
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add(beanIncidencia.getLatitud()+","+ beanIncidencia.getLongitud());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\n\nUBICACION: \n\n");
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nDEPARTAMENTO: "+beanIncidencia.getBeanUbigeo().getDepartamento());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nPROVINCIA: "+
                    beanIncidencia.getBeanUbigeo().getProvincia());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(font);
            myParagraph.add("\nDISTRITO: "+
                    beanIncidencia.getBeanUbigeo().getDistrito());
            documento.add(myParagraph);
            myParagraph= new Paragraph();
            myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.BOLD, Color.black));
            myParagraph.add("\n" +
                    "\n\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n\n" +
                    "\n" +
                    "\n\n\n\n\n");
            documento.add(myParagraph);
            double latitud =beanIncidencia.getLatitud();
            double longitud= beanIncidencia.getLongitud();
            String url = "http://maps.google.com/maps/api/staticmap?center=" + latitud + "," + longitud+ "&zoom=15&size=400x400&sensor=false&markers=color:red|label:.|"+latitud+","+longitud+"";
            Log.v("coorT",url);
            Bitmap bitmap = getBitmapFromURL(url);
            Bitmap bm=Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);// quality 0..100 .
            Image imagen = Image.getInstance(stream.toByteArray());
            imagen.setAlignment(Image.ALIGN_CENTER);
            PdfPTable tabla = new PdfPTable(1);
            PdfPCell cellOne = new PdfPCell();
//            cellOne.setPadding(20);
            cellOne.setImage(imagen);
            tabla.addCell(cellOne);
            documento.add(tabla);
        } catch (DocumentException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } catch (IOException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } finally {
            documento.close();
        }
    }

    public class MyTask extends AsyncTask<Object, Object, Boolean> {


        ProgressDialog progress;
        Activity act;
        Incidencia beanIncidencia;


        public MyTask(ProgressDialog progress, Activity act,Incidencia beanIncidencia) {
            this.progress = progress;
            this.act = act;
            this.beanIncidencia=beanIncidencia;
        }
        public void onPreExecute() {
            progress.show();
        }
        public void onPostExecute(Boolean unused) {
            progress.dismiss();
            Toast.makeText(contextActivity, R.string.ireport_succesfull, Toast.LENGTH_SHORT).show();
            Toast.makeText(contextActivity, "Visite /Download/INEC_Reportes/"+NOMBRE_DOCUMENTO, Toast.LENGTH_LONG).show();
            if(PATH!=null){
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File(PATH);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                PendingIntent pIntent = PendingIntent.getActivity(activity, 0, intent, 0);
                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Notification noti = new NotificationCompat.Builder(activity)
                        .setContentTitle("Abrir Reporte de Actividad")
                        .setSmallIcon(R.mipmap.ic_inec)
                        .setContentText("INEC")
                        .setSound(defaultSoundUri)
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setContentIntent(pIntent).build();


                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                NotificationManager notificationManager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, noti);
            }
        }
        @Override
        protected Boolean doInBackground(Object... params) {
            generarReporteV2(beanIncidencia);
            return true;
        }
    }



    public static File crearFichero(String nombreFichero) throws IOException {
        File ruta = getRuta();
        File fichero = null;
        if (ruta != null)
            fichero = new File(ruta, nombreFichero);
        return fichero;
    }


    public static File getRuta() {
        File ruta = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            ruta = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    NOMBRE_DIRECTORIO);
            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }
        } else {
        }
        return ruta;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(src);
            Bitmap myBitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            return myBitmap;
        } catch (IOException e) {
            FirebaseCrash.report(e.getCause());
            FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
            return null;
        }
    }
}