package com.inec.ui_reporte;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.inec.server.ws.gestionUsuario.model.PosicionFiscalizador;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import harmony.java.awt.Color;

import inec.com.R;

/**
 * Created by root on 01/11/16.
 */

public class UiReporte extends AppCompatActivity implements InterUiReporte, View.OnClickListener {
    private final static String NOMBRE_DIRECTORIO = "INEC_Reportes";
    private  static String NOMBRE_DOCUMENTO = "";
    private final static String ETIQUETA_ERROR = "ERROR";
    public static ImageButton uir_button_download_report;
    public static TextView uir_show_datepicker;
    public static String myDate;//para el filtro en java
    public static String documentoFecha;

    interface CurrentView {
        int OPTIONS_LAYOUT = 1;
        int READ_LAYOUT = 2;
    }

    /**
     * FrameLayout child views. We will manage our UI to one layout
     * Hide/Show these views as per requirement
     */
    LinearLayout optionsLayout;
    LinearLayout readLayout;
    private static int currentView;


    MenuItem closeOption;

    private static int currentPage = 0;

    ImageView pdfView;
    Button next;
    Button previous;

    private PdfRenderer.Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_reporte);
        uir_button_download_report = (ImageButton)findViewById(R.id.uir_button_download_report);
        uir_show_datepicker = (TextView)findViewById(R.id.uir_show_datepicker);

        optionsLayout = (LinearLayout) findViewById(R.id.uicp_layout_content);
        readLayout = (LinearLayout) findViewById(R.id.read_layout);


        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(this);

        pdfView = (ImageView) findViewById(R.id.pdfView);

        currentView = CurrentView.OPTIONS_LAYOUT;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.next:
                currentPage++;
                showPage(currentPage);
                break;
            case R.id.previous:
                currentPage--;
                showPage(currentPage);
                break;
        }

    }


    private void showPage(int index) {
        if (mPdfRenderer == null || mPdfRenderer.getPageCount() <= index
                || index < 0) {
            return;
        }
        try {
            if (mCurrentPage != null) {
                mCurrentPage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        next.setVisibility(View.VISIBLE);
        previous.setVisibility(View.VISIBLE);
        if(mPdfRenderer.getPageCount() <= index+1){
            next.setVisibility(View.INVISIBLE);
        }
        if(index ==0){
            previous.setVisibility(View.INVISIBLE);
        }

        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);
        mCurrentPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        pdfView.setImageBitmap(bitmap);
    }


    @Override
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public  void uirLayoutBackClick(View v){
        finish();
        onBackPressed();
    }


    public  void uirLayoutBackClickClose(View v) {
        int id = v.getId();
        if (id == R.id.uir_layout_click2) {
            if (currentView == CurrentView.OPTIONS_LAYOUT) {
                updateView(CurrentView.READ_LAYOUT);
            } else if (currentView == CurrentView.READ_LAYOUT) {
                updateView(CurrentView.OPTIONS_LAYOUT);
            }
        }
    }

    @Override
    public  void downloadReport(View v){
       String fecha= uir_show_datepicker.getText().toString();
        if(fecha.isEmpty()){
            Toast.makeText(this, R.string.ireport_valideDatePicker, Toast.LENGTH_LONG).show();
        }else{
           loadGenerarReporte(myDate);
        }
    }

    public static String PATH=null;
    @Override
    public void generarReporte(String fecha, List<PosicionFiscalizador>listaPosicionFiscalizador) {
        Document documento = new Document();
        try {
            NOMBRE_DOCUMENTO="Inec_rrd_"+documentoFecha+".pdf";
            File f = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPdf = new FileOutputStream(
                    f.getAbsolutePath());
            PATH=f.getAbsolutePath();
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
            documento.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 22,
                    Font.BOLD, Color.black);
            documento.add(new Paragraph("INEC - Reporte de Ruta Diaria", font));
            documento.add(new Paragraph("\n"));
            font = FontFactory.getFont(FontFactory.HELVETICA, 18,
                    Font.BOLD, Color.black);
            documento.add(new Paragraph("\t FECHA: "+fecha, font));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("\n"));
            Paragraph myParagraph= new Paragraph();
            font = FontFactory.getFont(FontFactory.HELVETICA, 16,
                    Font.NORMAL, Color.black);
            for (int i = 0; i < listaPosicionFiscalizador.size(); i++) {
                myParagraph= new Paragraph();
                myParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 16,
                        Font.BOLD, Color.black));
                myParagraph.add("RUTA "+(i+1));
                documento.add(myParagraph);
                documento.add(new Paragraph("\n"));
                myParagraph= new Paragraph();
                myParagraph.setFont(font);
                myParagraph.add("\nFECHA: \n\n"+new SimpleDateFormat().format(listaPosicionFiscalizador.get(i).getVersion()));
                documento.add(myParagraph);
                myParagraph= new Paragraph();
                myParagraph.setFont(font);
                myParagraph.add("\nCOORDENADAS: \n\n"+listaPosicionFiscalizador.get(i).getLatitud()+" , "+listaPosicionFiscalizador.get(i).getLongitud());
                documento.add(myParagraph);
                myParagraph= new Paragraph();
                myParagraph.setFont(font);
                myParagraph.add("\nUBICACION: \n\n"+listaPosicionFiscalizador.get(i).getDepartamento()+"-"+
                listaPosicionFiscalizador.get(i).getProvincia()+"-"+listaPosicionFiscalizador.get(i).getDistrito());
                documento.add(myParagraph);
                documento.add(new Paragraph("\n\n\n"));
            }
        } catch (DocumentException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } catch (IOException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());

        } finally {
            documento.close();
            Toast.makeText(this, R.string.ireport_succesfull, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Visite /Download/INEC_Reportes/"+NOMBRE_DOCUMENTO, Toast.LENGTH_LONG).show();
        }
    }

    public void updateView(int updateView) {
        switch (updateView) {
            case CurrentView.OPTIONS_LAYOUT:
                currentView = CurrentView.OPTIONS_LAYOUT;
                optionsLayout.setVisibility(View.VISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.READ_LAYOUT:
                currentView = CurrentView.READ_LAYOUT;
                showPage(currentPage);
                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.VISIBLE);
                break;
        }
    }


    private ParcelFileDescriptor mFileDescriptor;
    private PdfRenderer mPdfRenderer;


    public void openRenderer(String filePath) {
        File file = new File(filePath);
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file,
                    ParcelFileDescriptor.MODE_READ_ONLY);
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void loadGenerarReporte(String fechaFormat) {

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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),R.style.MyDialogTheme, this, year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView uir_show_datepicker = (TextView) getActivity().findViewById(R.id.uir_show_datepicker);
            int captureDia=view.getDayOfMonth();
            int  captureMes=view.getMonth()+1;
            String dia=captureDia+"";
            String mes=captureMes+"";
            if(captureDia<10){
                dia="0"+captureDia;
            }
            if(captureMes<10){
                mes="0"+captureMes;
            }
            myDate=dia+"/"+mes+"/"+view.getYear();
            documentoFecha=dia+"_"+mes+"_"+view.getYear();
            uir_show_datepicker.setText(view.getDayOfMonth()+" / "+captureMes+" / "+view.getYear());
        }
    }


}
