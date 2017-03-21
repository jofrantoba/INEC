/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-10-17 16:43:55 UTC)
 * on 2016-12-24 at 05:49:44 UTC 
 * Modify at your own risk.
 */

package com.inec.server.ws.gestionMantenimiento;

/**
 * Service definition for GestionMantenimiento (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link GestionMantenimientoRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class GestionMantenimiento extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.22.0 of the gestionMantenimiento library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://inec-145618.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "gestionMantenimiento/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public GestionMantenimiento(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  GestionMantenimiento(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertarPartidoPolitico".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link InsertarPartidoPolitico#execute()} method to invoke the
   * remote operation.
   *
   * @param ippCodePartidoPolitico
   * @param ippLider
   * @param ippNombrePartidoPolitico
   * @return the request
   */
  public InsertarPartidoPolitico insertarPartidoPolitico(java.lang.String ippCodePartidoPolitico, java.lang.String ippLider, java.lang.String ippNombrePartidoPolitico) throws java.io.IOException {
    InsertarPartidoPolitico result = new InsertarPartidoPolitico(ippCodePartidoPolitico, ippLider, ippNombrePartidoPolitico);
    initialize(result);
    return result;
  }

  public class InsertarPartidoPolitico extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "insertarPartidoPolitico";

    /**
     * Create a request for the method "insertarPartidoPolitico".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link InsertarPartidoPolitico#execute()} method to invoke
     * the remote operation. <p> {@link InsertarPartidoPolitico#initialize(com.google.api.client.googl
     * eapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param ippCodePartidoPolitico
     * @param ippLider
     * @param ippNombrePartidoPolitico
     * @since 1.13
     */
    protected InsertarPartidoPolitico(java.lang.String ippCodePartidoPolitico, java.lang.String ippLider, java.lang.String ippNombrePartidoPolitico) {
      super(GestionMantenimiento.this, "POST", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
      this.ippCodePartidoPolitico = com.google.api.client.util.Preconditions.checkNotNull(ippCodePartidoPolitico, "Required parameter ippCodePartidoPolitico must be specified.");
      this.ippLider = com.google.api.client.util.Preconditions.checkNotNull(ippLider, "Required parameter ippLider must be specified.");
      this.ippNombrePartidoPolitico = com.google.api.client.util.Preconditions.checkNotNull(ippNombrePartidoPolitico, "Required parameter ippNombrePartidoPolitico must be specified.");
    }

    @Override
    public InsertarPartidoPolitico setAlt(java.lang.String alt) {
      return (InsertarPartidoPolitico) super.setAlt(alt);
    }

    @Override
    public InsertarPartidoPolitico setFields(java.lang.String fields) {
      return (InsertarPartidoPolitico) super.setFields(fields);
    }

    @Override
    public InsertarPartidoPolitico setKey(java.lang.String key) {
      return (InsertarPartidoPolitico) super.setKey(key);
    }

    @Override
    public InsertarPartidoPolitico setOauthToken(java.lang.String oauthToken) {
      return (InsertarPartidoPolitico) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertarPartidoPolitico setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertarPartidoPolitico) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertarPartidoPolitico setQuotaUser(java.lang.String quotaUser) {
      return (InsertarPartidoPolitico) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertarPartidoPolitico setUserIp(java.lang.String userIp) {
      return (InsertarPartidoPolitico) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String ippCodePartidoPolitico;

    /**

     */
    public java.lang.String getIppCodePartidoPolitico() {
      return ippCodePartidoPolitico;
    }

    public InsertarPartidoPolitico setIppCodePartidoPolitico(java.lang.String ippCodePartidoPolitico) {
      this.ippCodePartidoPolitico = ippCodePartidoPolitico;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String ippLider;

    /**

     */
    public java.lang.String getIppLider() {
      return ippLider;
    }

    public InsertarPartidoPolitico setIppLider(java.lang.String ippLider) {
      this.ippLider = ippLider;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String ippNombrePartidoPolitico;

    /**

     */
    public java.lang.String getIppNombrePartidoPolitico() {
      return ippNombrePartidoPolitico;
    }

    public InsertarPartidoPolitico setIppNombrePartidoPolitico(java.lang.String ippNombrePartidoPolitico) {
      this.ippNombrePartidoPolitico = ippNombrePartidoPolitico;
      return this;
    }

    @Override
    public InsertarPartidoPolitico set(String parameterName, Object value) {
      return (InsertarPartidoPolitico) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertarTipoIncidencia".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link InsertarTipoIncidencia#execute()} method to invoke the
   * remote operation.
   *
   * @param itiCodeTipoIncidencia
   * @return the request
   */
  public InsertarTipoIncidencia insertarTipoIncidencia(java.lang.String itiCodeTipoIncidencia) throws java.io.IOException {
    InsertarTipoIncidencia result = new InsertarTipoIncidencia(itiCodeTipoIncidencia);
    initialize(result);
    return result;
  }

  public class InsertarTipoIncidencia extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "insertarTipoIncidencia";

    /**
     * Create a request for the method "insertarTipoIncidencia".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link InsertarTipoIncidencia#execute()} method to invoke the
     * remote operation. <p> {@link InsertarTipoIncidencia#initialize(com.google.api.client.googleapis
     * .services.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param itiCodeTipoIncidencia
     * @since 1.13
     */
    protected InsertarTipoIncidencia(java.lang.String itiCodeTipoIncidencia) {
      super(GestionMantenimiento.this, "POST", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
      this.itiCodeTipoIncidencia = com.google.api.client.util.Preconditions.checkNotNull(itiCodeTipoIncidencia, "Required parameter itiCodeTipoIncidencia must be specified.");
    }

    @Override
    public InsertarTipoIncidencia setAlt(java.lang.String alt) {
      return (InsertarTipoIncidencia) super.setAlt(alt);
    }

    @Override
    public InsertarTipoIncidencia setFields(java.lang.String fields) {
      return (InsertarTipoIncidencia) super.setFields(fields);
    }

    @Override
    public InsertarTipoIncidencia setKey(java.lang.String key) {
      return (InsertarTipoIncidencia) super.setKey(key);
    }

    @Override
    public InsertarTipoIncidencia setOauthToken(java.lang.String oauthToken) {
      return (InsertarTipoIncidencia) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertarTipoIncidencia setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertarTipoIncidencia) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertarTipoIncidencia setQuotaUser(java.lang.String quotaUser) {
      return (InsertarTipoIncidencia) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertarTipoIncidencia setUserIp(java.lang.String userIp) {
      return (InsertarTipoIncidencia) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String itiCodeTipoIncidencia;

    /**

     */
    public java.lang.String getItiCodeTipoIncidencia() {
      return itiCodeTipoIncidencia;
    }

    public InsertarTipoIncidencia setItiCodeTipoIncidencia(java.lang.String itiCodeTipoIncidencia) {
      this.itiCodeTipoIncidencia = itiCodeTipoIncidencia;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String itiDescripcion;

    /**

     */
    public java.lang.String getItiDescripcion() {
      return itiDescripcion;
    }

    public InsertarTipoIncidencia setItiDescripcion(java.lang.String itiDescripcion) {
      this.itiDescripcion = itiDescripcion;
      return this;
    }

    @Override
    public InsertarTipoIncidencia set(String parameterName, Object value) {
      return (InsertarTipoIncidencia) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertarUbigeo".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link InsertarUbigeo#execute()} method to invoke the remote
   * operation.
   *
   * @param iuCodeUbigeo
   * @param iuDepartamento
   * @return the request
   */
  public InsertarUbigeo insertarUbigeo(java.lang.String iuCodeUbigeo, java.lang.String iuDepartamento) throws java.io.IOException {
    InsertarUbigeo result = new InsertarUbigeo(iuCodeUbigeo, iuDepartamento);
    initialize(result);
    return result;
  }

  public class InsertarUbigeo extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "insertarUbigeo";

    /**
     * Create a request for the method "insertarUbigeo".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link InsertarUbigeo#execute()} method to invoke the remote
     * operation. <p> {@link InsertarUbigeo#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param iuCodeUbigeo
     * @param iuDepartamento
     * @since 1.13
     */
    protected InsertarUbigeo(java.lang.String iuCodeUbigeo, java.lang.String iuDepartamento) {
      super(GestionMantenimiento.this, "POST", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
      this.iuCodeUbigeo = com.google.api.client.util.Preconditions.checkNotNull(iuCodeUbigeo, "Required parameter iuCodeUbigeo must be specified.");
      this.iuDepartamento = com.google.api.client.util.Preconditions.checkNotNull(iuDepartamento, "Required parameter iuDepartamento must be specified.");
    }

    @Override
    public InsertarUbigeo setAlt(java.lang.String alt) {
      return (InsertarUbigeo) super.setAlt(alt);
    }

    @Override
    public InsertarUbigeo setFields(java.lang.String fields) {
      return (InsertarUbigeo) super.setFields(fields);
    }

    @Override
    public InsertarUbigeo setKey(java.lang.String key) {
      return (InsertarUbigeo) super.setKey(key);
    }

    @Override
    public InsertarUbigeo setOauthToken(java.lang.String oauthToken) {
      return (InsertarUbigeo) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertarUbigeo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertarUbigeo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertarUbigeo setQuotaUser(java.lang.String quotaUser) {
      return (InsertarUbigeo) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertarUbigeo setUserIp(java.lang.String userIp) {
      return (InsertarUbigeo) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String iuCodeUbigeo;

    /**

     */
    public java.lang.String getIuCodeUbigeo() {
      return iuCodeUbigeo;
    }

    public InsertarUbigeo setIuCodeUbigeo(java.lang.String iuCodeUbigeo) {
      this.iuCodeUbigeo = iuCodeUbigeo;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String iuDepartamento;

    /**

     */
    public java.lang.String getIuDepartamento() {
      return iuDepartamento;
    }

    public InsertarUbigeo setIuDepartamento(java.lang.String iuDepartamento) {
      this.iuDepartamento = iuDepartamento;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String iuProvincia;

    /**

     */
    public java.lang.String getIuProvincia() {
      return iuProvincia;
    }

    public InsertarUbigeo setIuProvincia(java.lang.String iuProvincia) {
      this.iuProvincia = iuProvincia;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String iuDistrito;

    /**

     */
    public java.lang.String getIuDistrito() {
      return iuDistrito;
    }

    public InsertarUbigeo setIuDistrito(java.lang.String iuDistrito) {
      this.iuDistrito = iuDistrito;
      return this;
    }

    @Override
    public InsertarUbigeo set(String parameterName, Object value) {
      return (InsertarUbigeo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertarZona".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link InsertarZona#execute()} method to invoke the remote
   * operation.
   *
   * @param izDepartamento
   * @param izDistrito
   * @param izNombreZona
   * @param izProvincia
   * @return the request
   */
  public InsertarZona insertarZona(java.lang.String izDepartamento, java.lang.String izDistrito, java.lang.String izNombreZona, java.lang.String izProvincia) throws java.io.IOException {
    InsertarZona result = new InsertarZona(izDepartamento, izDistrito, izNombreZona, izProvincia);
    initialize(result);
    return result;
  }

  public class InsertarZona extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "insertarZona";

    /**
     * Create a request for the method "insertarZona".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link InsertarZona#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertarZona#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param izDepartamento
     * @param izDistrito
     * @param izNombreZona
     * @param izProvincia
     * @since 1.13
     */
    protected InsertarZona(java.lang.String izDepartamento, java.lang.String izDistrito, java.lang.String izNombreZona, java.lang.String izProvincia) {
      super(GestionMantenimiento.this, "POST", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
      this.izDepartamento = com.google.api.client.util.Preconditions.checkNotNull(izDepartamento, "Required parameter izDepartamento must be specified.");
      this.izDistrito = com.google.api.client.util.Preconditions.checkNotNull(izDistrito, "Required parameter izDistrito must be specified.");
      this.izNombreZona = com.google.api.client.util.Preconditions.checkNotNull(izNombreZona, "Required parameter izNombreZona must be specified.");
      this.izProvincia = com.google.api.client.util.Preconditions.checkNotNull(izProvincia, "Required parameter izProvincia must be specified.");
    }

    @Override
    public InsertarZona setAlt(java.lang.String alt) {
      return (InsertarZona) super.setAlt(alt);
    }

    @Override
    public InsertarZona setFields(java.lang.String fields) {
      return (InsertarZona) super.setFields(fields);
    }

    @Override
    public InsertarZona setKey(java.lang.String key) {
      return (InsertarZona) super.setKey(key);
    }

    @Override
    public InsertarZona setOauthToken(java.lang.String oauthToken) {
      return (InsertarZona) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertarZona setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertarZona) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertarZona setQuotaUser(java.lang.String quotaUser) {
      return (InsertarZona) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertarZona setUserIp(java.lang.String userIp) {
      return (InsertarZona) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String izDepartamento;

    /**

     */
    public java.lang.String getIzDepartamento() {
      return izDepartamento;
    }

    public InsertarZona setIzDepartamento(java.lang.String izDepartamento) {
      this.izDepartamento = izDepartamento;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String izDistrito;

    /**

     */
    public java.lang.String getIzDistrito() {
      return izDistrito;
    }

    public InsertarZona setIzDistrito(java.lang.String izDistrito) {
      this.izDistrito = izDistrito;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String izNombreZona;

    /**

     */
    public java.lang.String getIzNombreZona() {
      return izNombreZona;
    }

    public InsertarZona setIzNombreZona(java.lang.String izNombreZona) {
      this.izNombreZona = izNombreZona;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String izProvincia;

    /**

     */
    public java.lang.String getIzProvincia() {
      return izProvincia;
    }

    public InsertarZona setIzProvincia(java.lang.String izProvincia) {
      this.izProvincia = izProvincia;
      return this;
    }

    @Override
    public InsertarZona set(String parameterName, Object value) {
      return (InsertarZona) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listPartidoPolitico".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link ListPartidoPolitico#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListPartidoPolitico listPartidoPolitico() throws java.io.IOException {
    ListPartidoPolitico result = new ListPartidoPolitico();
    initialize(result);
    return result;
  }

  public class ListPartidoPolitico extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "listPartidoPolitico";

    /**
     * Create a request for the method "listPartidoPolitico".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link ListPartidoPolitico#execute()} method to invoke the
     * remote operation. <p> {@link ListPartidoPolitico#initialize(com.google.api.client.googleapis.se
     * rvices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListPartidoPolitico() {
      super(GestionMantenimiento.this, "GET", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListPartidoPolitico setAlt(java.lang.String alt) {
      return (ListPartidoPolitico) super.setAlt(alt);
    }

    @Override
    public ListPartidoPolitico setFields(java.lang.String fields) {
      return (ListPartidoPolitico) super.setFields(fields);
    }

    @Override
    public ListPartidoPolitico setKey(java.lang.String key) {
      return (ListPartidoPolitico) super.setKey(key);
    }

    @Override
    public ListPartidoPolitico setOauthToken(java.lang.String oauthToken) {
      return (ListPartidoPolitico) super.setOauthToken(oauthToken);
    }

    @Override
    public ListPartidoPolitico setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListPartidoPolitico) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListPartidoPolitico setQuotaUser(java.lang.String quotaUser) {
      return (ListPartidoPolitico) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListPartidoPolitico setUserIp(java.lang.String userIp) {
      return (ListPartidoPolitico) super.setUserIp(userIp);
    }

    @Override
    public ListPartidoPolitico set(String parameterName, Object value) {
      return (ListPartidoPolitico) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listTipoIncidencia".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link ListTipoIncidencia#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListTipoIncidencia listTipoIncidencia() throws java.io.IOException {
    ListTipoIncidencia result = new ListTipoIncidencia();
    initialize(result);
    return result;
  }

  public class ListTipoIncidencia extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "listTipoIncidencia";

    /**
     * Create a request for the method "listTipoIncidencia".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link ListTipoIncidencia#execute()} method to invoke the
     * remote operation. <p> {@link ListTipoIncidencia#initialize(com.google.api.client.googleapis.ser
     * vices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListTipoIncidencia() {
      super(GestionMantenimiento.this, "GET", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListTipoIncidencia setAlt(java.lang.String alt) {
      return (ListTipoIncidencia) super.setAlt(alt);
    }

    @Override
    public ListTipoIncidencia setFields(java.lang.String fields) {
      return (ListTipoIncidencia) super.setFields(fields);
    }

    @Override
    public ListTipoIncidencia setKey(java.lang.String key) {
      return (ListTipoIncidencia) super.setKey(key);
    }

    @Override
    public ListTipoIncidencia setOauthToken(java.lang.String oauthToken) {
      return (ListTipoIncidencia) super.setOauthToken(oauthToken);
    }

    @Override
    public ListTipoIncidencia setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListTipoIncidencia) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListTipoIncidencia setQuotaUser(java.lang.String quotaUser) {
      return (ListTipoIncidencia) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListTipoIncidencia setUserIp(java.lang.String userIp) {
      return (ListTipoIncidencia) super.setUserIp(userIp);
    }

    @Override
    public ListTipoIncidencia set(String parameterName, Object value) {
      return (ListTipoIncidencia) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listUbigeo".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link ListUbigeo#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListUbigeo listUbigeo() throws java.io.IOException {
    ListUbigeo result = new ListUbigeo();
    initialize(result);
    return result;
  }

  public class ListUbigeo extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "listUbigeo";

    /**
     * Create a request for the method "listUbigeo".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link ListUbigeo#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListUbigeo#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListUbigeo() {
      super(GestionMantenimiento.this, "GET", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListUbigeo setAlt(java.lang.String alt) {
      return (ListUbigeo) super.setAlt(alt);
    }

    @Override
    public ListUbigeo setFields(java.lang.String fields) {
      return (ListUbigeo) super.setFields(fields);
    }

    @Override
    public ListUbigeo setKey(java.lang.String key) {
      return (ListUbigeo) super.setKey(key);
    }

    @Override
    public ListUbigeo setOauthToken(java.lang.String oauthToken) {
      return (ListUbigeo) super.setOauthToken(oauthToken);
    }

    @Override
    public ListUbigeo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListUbigeo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListUbigeo setQuotaUser(java.lang.String quotaUser) {
      return (ListUbigeo) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListUbigeo setUserIp(java.lang.String userIp) {
      return (ListUbigeo) super.setUserIp(userIp);
    }

    @Override
    public ListUbigeo set(String parameterName, Object value) {
      return (ListUbigeo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listZona".
   *
   * This request holds the parameters needed by the gestionMantenimiento server.  After setting any
   * optional parameters, call the {@link ListZona#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListZona listZona() throws java.io.IOException {
    ListZona result = new ListZona();
    initialize(result);
    return result;
  }

  public class ListZona extends GestionMantenimientoRequest<com.inec.server.ws.gestionMantenimiento.model.ReturnValue> {

    private static final String REST_PATH = "listZona";

    /**
     * Create a request for the method "listZona".
     *
     * This request holds the parameters needed by the the gestionMantenimiento server.  After setting
     * any optional parameters, call the {@link ListZona#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListZona#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListZona() {
      super(GestionMantenimiento.this, "GET", REST_PATH, null, com.inec.server.ws.gestionMantenimiento.model.ReturnValue.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListZona setAlt(java.lang.String alt) {
      return (ListZona) super.setAlt(alt);
    }

    @Override
    public ListZona setFields(java.lang.String fields) {
      return (ListZona) super.setFields(fields);
    }

    @Override
    public ListZona setKey(java.lang.String key) {
      return (ListZona) super.setKey(key);
    }

    @Override
    public ListZona setOauthToken(java.lang.String oauthToken) {
      return (ListZona) super.setOauthToken(oauthToken);
    }

    @Override
    public ListZona setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListZona) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListZona setQuotaUser(java.lang.String quotaUser) {
      return (ListZona) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListZona setUserIp(java.lang.String userIp) {
      return (ListZona) super.setUserIp(userIp);
    }

    @Override
    public ListZona set(String parameterName, Object value) {
      return (ListZona) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link GestionMantenimiento}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link GestionMantenimiento}. */
    @Override
    public GestionMantenimiento build() {
      return new GestionMantenimiento(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link GestionMantenimientoRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setGestionMantenimientoRequestInitializer(
        GestionMantenimientoRequestInitializer gestionmantenimientoRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(gestionmantenimientoRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
