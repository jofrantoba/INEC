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

package com.inec.server.ws.gestionMantenimiento.model;

/**
 * Model definition for Notificacion.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the gestionMantenimiento. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Notificacion extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeNotificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeProgramacionNotificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String estado;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaNotificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fechaNotificacionFiltro;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String idNotificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long version;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeNotificacion() {
    return codeNotificacion;
  }

  /**
   * @param codeNotificacion codeNotificacion or {@code null} for none
   */
  public Notificacion setCodeNotificacion(java.lang.String codeNotificacion) {
    this.codeNotificacion = codeNotificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodeProgramacionNotificacion() {
    return codeProgramacionNotificacion;
  }

  /**
   * @param codeProgramacionNotificacion codeProgramacionNotificacion or {@code null} for none
   */
  public Notificacion setCodeProgramacionNotificacion(java.lang.String codeProgramacionNotificacion) {
    this.codeProgramacionNotificacion = codeProgramacionNotificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEstado() {
    return estado;
  }

  /**
   * @param estado estado or {@code null} for none
   */
  public Notificacion setEstado(java.lang.String estado) {
    this.estado = estado;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaNotificacion() {
    return fechaNotificacion;
  }

  /**
   * @param fechaNotificacion fechaNotificacion or {@code null} for none
   */
  public Notificacion setFechaNotificacion(com.google.api.client.util.DateTime fechaNotificacion) {
    this.fechaNotificacion = fechaNotificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFechaNotificacionFiltro() {
    return fechaNotificacionFiltro;
  }

  /**
   * @param fechaNotificacionFiltro fechaNotificacionFiltro or {@code null} for none
   */
  public Notificacion setFechaNotificacionFiltro(java.lang.String fechaNotificacionFiltro) {
    this.fechaNotificacionFiltro = fechaNotificacionFiltro;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdNotificacion() {
    return idNotificacion;
  }

  /**
   * @param idNotificacion idNotificacion or {@code null} for none
   */
  public Notificacion setIdNotificacion(java.lang.String idNotificacion) {
    this.idNotificacion = idNotificacion;
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
  public Notificacion setVersion(java.lang.Long version) {
    this.version = version;
    return this;
  }

  @Override
  public Notificacion set(String fieldName, Object value) {
    return (Notificacion) super.set(fieldName, value);
  }

  @Override
  public Notificacion clone() {
    return (Notificacion) super.clone();
  }

}
