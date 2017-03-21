package com.inec.uicapturarfoto;

import java.io.File;

/**
 * Created by root on 10/10/16.
 */

public interface InterCapturarFoto {

    void capturaFoto();
    void TomarFoto();
    File getFileImage();
    void VerImagen();
    void addImagen();
    void initListener();
    void initComponents();

}
