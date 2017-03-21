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
 * on 2016-12-24 at 05:49:52 UTC 
 * Modify at your own risk.
 */

package com.inec.server.ws.gestionUsuario.model;

/**
 * Model definition for Incidencia.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the gestionUsuario. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Incidencia extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Ubigeo beanUbigeo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeIncidencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codePartidoPolitico;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeTipoIncidencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeUbigeo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeUsuarioFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeZona;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String descripcion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String direccion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaCelular;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaServidor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String idIncidencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double latitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> listLatitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> listLongitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> listUrlFoto;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nombrePartidoPolitico;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String operacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long version;

  /**
   * @return value or {@code null} for none
   */
  public Ubigeo getBeanUbigeo() {
    return beanUbigeo;
  }

  /**
   * @param beanUbigeo beanUbigeo or {@code null} for none
   */
  public Incidencia setBeanUbigeo(Ubigeo beanUbigeo) {
    this.beanUbigeo = beanUbigeo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeIncidencia() {
    return codeIncidencia;
  }

  /**
   * @param codeIncidencia codeIncidencia or {@code null} for none
   */
  public Incidencia setCodeIncidencia(java.lang.String codeIncidencia) {
    this.codeIncidencia = codeIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodePartidoPolitico() {
    return codePartidoPolitico;
  }

  /**
   * @param codePartidoPolitico codePartidoPolitico or {@code null} for none
   */
  public Incidencia setCodePartidoPolitico(java.lang.String codePartidoPolitico) {
    this.codePartidoPolitico = codePartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeTipoIncidencia() {
    return codeTipoIncidencia;
  }

  /**
   * @param codeTipoIncidencia codeTipoIncidencia or {@code null} for none
   */
  public Incidencia setCodeTipoIncidencia(java.lang.String codeTipoIncidencia) {
    this.codeTipoIncidencia = codeTipoIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeUbigeo() {
    return codeUbigeo;
  }

  /**
   * @param codeUbigeo codeUbigeo or {@code null} for none
   */
  public Incidencia setCodeUbigeo(java.lang.String codeUbigeo) {
    this.codeUbigeo = codeUbigeo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeUsuarioFiscalizador() {
    return codeUsuarioFiscalizador;
  }

  /**
   * @param codeUsuarioFiscalizador codeUsuarioFiscalizador or {@code null} for none
   */
  public Incidencia setCodeUsuarioFiscalizador(java.lang.String codeUsuarioFiscalizador) {
    this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeZona() {
    return codeZona;
  }

  /**
   * @param codeZona codeZona or {@code null} for none
   */
  public Incidencia setCodeZona(java.lang.String codeZona) {
    this.codeZona = codeZona;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescripcion() {
    return descripcion;
  }

  /**
   * @param descripcion descripcion or {@code null} for none
   */
  public Incidencia setDescripcion(java.lang.String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDireccion() {
    return direccion;
  }

  /**
   * @param direccion direccion or {@code null} for none
   */
  public Incidencia setDireccion(java.lang.String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaCelular() {
    return fechaCelular;
  }

  /**
   * @param fechaCelular fechaCelular or {@code null} for none
   */
  public Incidencia setFechaCelular(com.google.api.client.util.DateTime fechaCelular) {
    this.fechaCelular = fechaCelular;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaServidor() {
    return fechaServidor;
  }

  /**
   * @param fechaServidor fechaServidor or {@code null} for none
   */
  public Incidencia setFechaServidor(com.google.api.client.util.DateTime fechaServidor) {
    this.fechaServidor = fechaServidor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdIncidencia() {
    return idIncidencia;
  }

  /**
   * @param idIncidencia idIncidencia or {@code null} for none
   */
  public Incidencia setIdIncidencia(java.lang.String idIncidencia) {
    this.idIncidencia = idIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLatitud() {
    return latitud;
  }

  /**
   * @param latitud latitud or {@code null} for none
   */
  public Incidencia setLatitud(java.lang.Double latitud) {
    this.latitud = latitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getListLatitud() {
    return listLatitud;
  }

  /**
   * @param listLatitud listLatitud or {@code null} for none
   */
  public Incidencia setListLatitud(java.util.List<java.lang.Double> listLatitud) {
    this.listLatitud = listLatitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getListLongitud() {
    return listLongitud;
  }

  /**
   * @param listLongitud listLongitud or {@code null} for none
   */
  public Incidencia setListLongitud(java.util.List<java.lang.Double> listLongitud) {
    this.listLongitud = listLongitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getListUrlFoto() {
    return listUrlFoto;
  }

  /**
   * @param listUrlFoto listUrlFoto or {@code null} for none
   */
  public Incidencia setListUrlFoto(java.util.List<java.lang.String> listUrlFoto) {
    this.listUrlFoto = listUrlFoto;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLongitud() {
    return longitud;
  }

  /**
   * @param longitud longitud or {@code null} for none
   */
  public Incidencia setLongitud(java.lang.Double longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNombrePartidoPolitico() {
    return nombrePartidoPolitico;
  }

  /**
   * @param nombrePartidoPolitico nombrePartidoPolitico or {@code null} for none
   */
  public Incidencia setNombrePartidoPolitico(java.lang.String nombrePartidoPolitico) {
    this.nombrePartidoPolitico = nombrePartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getOperacion() {
    return operacion;
  }

  /**
   * @param operacion operacion or {@code null} for none
   */
  public Incidencia setOperacion(java.lang.String operacion) {
    this.operacion = operacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getVersion() {
    return version;
  }

  /**
   * @param version version or {@code null} for none
   */
  public Incidencia setVersion(java.lang.Long version) {
    this.version = version;
    return this;
  }

  @Override
  public Incidencia set(String fieldName, Object value) {
    return (Incidencia) super.set(fieldName, value);
  }

  @Override
  public Incidencia clone() {
    return (Incidencia) super.clone();
  }

}
