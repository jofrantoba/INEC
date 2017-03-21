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
 * Model definition for ReturnValue.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the gestionUsuario. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ReturnValue extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nameClass;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Incidencia returnIncidencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Incidencia> returnListIncidencia;

  static {
    // hack to force ProGuard to consider Incidencia used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(Incidencia.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Notificacion> returnListNotificacion;

  static {
    // hack to force ProGuard to consider Notificacion used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(Notificacion.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<PartidoPolitico> returnListPartidoPolitico;

  static {
    // hack to force ProGuard to consider PartidoPolitico used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(PartidoPolitico.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<PosicionFiscalizador> returnListPosicionFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<TipoIncidencia> returnListTipoIncidencia;

  static {
    // hack to force ProGuard to consider TipoIncidencia used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(TipoIncidencia.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Ubigeo> returnListUbigeo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<UsuarioFiscalizador> returnListUsuarioFiscalizador;

  static {
    // hack to force ProGuard to consider UsuarioFiscalizador used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(UsuarioFiscalizador.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Zona> returnListZona;

  static {
    // hack to force ProGuard to consider Zona used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(Zona.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private PartidoPolitico returnPartidoPolitico;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private TipoIncidencia returnTipoIncidencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Ubigeo returnUbigeo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private UsuarioFiscalizador returnUsuarioFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Zona returnZona;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Object valueReturn;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNameClass() {
    return nameClass;
  }

  /**
   * @param nameClass nameClass or {@code null} for none
   */
  public ReturnValue setNameClass(java.lang.String nameClass) {
    this.nameClass = nameClass;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Incidencia getReturnIncidencia() {
    return returnIncidencia;
  }

  /**
   * @param returnIncidencia returnIncidencia or {@code null} for none
   */
  public ReturnValue setReturnIncidencia(Incidencia returnIncidencia) {
    this.returnIncidencia = returnIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Incidencia> getReturnListIncidencia() {
    return returnListIncidencia;
  }

  /**
   * @param returnListIncidencia returnListIncidencia or {@code null} for none
   */
  public ReturnValue setReturnListIncidencia(java.util.List<Incidencia> returnListIncidencia) {
    this.returnListIncidencia = returnListIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Notificacion> getReturnListNotificacion() {
    return returnListNotificacion;
  }

  /**
   * @param returnListNotificacion returnListNotificacion or {@code null} for none
   */
  public ReturnValue setReturnListNotificacion(java.util.List<Notificacion> returnListNotificacion) {
    this.returnListNotificacion = returnListNotificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<PartidoPolitico> getReturnListPartidoPolitico() {
    return returnListPartidoPolitico;
  }

  /**
   * @param returnListPartidoPolitico returnListPartidoPolitico or {@code null} for none
   */
  public ReturnValue setReturnListPartidoPolitico(java.util.List<PartidoPolitico> returnListPartidoPolitico) {
    this.returnListPartidoPolitico = returnListPartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<PosicionFiscalizador> getReturnListPosicionFiscalizador() {
    return returnListPosicionFiscalizador;
  }

  /**
   * @param returnListPosicionFiscalizador returnListPosicionFiscalizador or {@code null} for none
   */
  public ReturnValue setReturnListPosicionFiscalizador(java.util.List<PosicionFiscalizador> returnListPosicionFiscalizador) {
    this.returnListPosicionFiscalizador = returnListPosicionFiscalizador;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<TipoIncidencia> getReturnListTipoIncidencia() {
    return returnListTipoIncidencia;
  }

  /**
   * @param returnListTipoIncidencia returnListTipoIncidencia or {@code null} for none
   */
  public ReturnValue setReturnListTipoIncidencia(java.util.List<TipoIncidencia> returnListTipoIncidencia) {
    this.returnListTipoIncidencia = returnListTipoIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Ubigeo> getReturnListUbigeo() {
    return returnListUbigeo;
  }

  /**
   * @param returnListUbigeo returnListUbigeo or {@code null} for none
   */
  public ReturnValue setReturnListUbigeo(java.util.List<Ubigeo> returnListUbigeo) {
    this.returnListUbigeo = returnListUbigeo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<UsuarioFiscalizador> getReturnListUsuarioFiscalizador() {
    return returnListUsuarioFiscalizador;
  }

  /**
   * @param returnListUsuarioFiscalizador returnListUsuarioFiscalizador or {@code null} for none
   */
  public ReturnValue setReturnListUsuarioFiscalizador(java.util.List<UsuarioFiscalizador> returnListUsuarioFiscalizador) {
    this.returnListUsuarioFiscalizador = returnListUsuarioFiscalizador;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Zona> getReturnListZona() {
    return returnListZona;
  }

  /**
   * @param returnListZona returnListZona or {@code null} for none
   */
  public ReturnValue setReturnListZona(java.util.List<Zona> returnListZona) {
    this.returnListZona = returnListZona;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public PartidoPolitico getReturnPartidoPolitico() {
    return returnPartidoPolitico;
  }

  /**
   * @param returnPartidoPolitico returnPartidoPolitico or {@code null} for none
   */
  public ReturnValue setReturnPartidoPolitico(PartidoPolitico returnPartidoPolitico) {
    this.returnPartidoPolitico = returnPartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public TipoIncidencia getReturnTipoIncidencia() {
    return returnTipoIncidencia;
  }

  /**
   * @param returnTipoIncidencia returnTipoIncidencia or {@code null} for none
   */
  public ReturnValue setReturnTipoIncidencia(TipoIncidencia returnTipoIncidencia) {
    this.returnTipoIncidencia = returnTipoIncidencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Ubigeo getReturnUbigeo() {
    return returnUbigeo;
  }

  /**
   * @param returnUbigeo returnUbigeo or {@code null} for none
   */
  public ReturnValue setReturnUbigeo(Ubigeo returnUbigeo) {
    this.returnUbigeo = returnUbigeo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public UsuarioFiscalizador getReturnUsuarioFiscalizador() {
    return returnUsuarioFiscalizador;
  }

  /**
   * @param returnUsuarioFiscalizador returnUsuarioFiscalizador or {@code null} for none
   */
  public ReturnValue setReturnUsuarioFiscalizador(UsuarioFiscalizador returnUsuarioFiscalizador) {
    this.returnUsuarioFiscalizador = returnUsuarioFiscalizador;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Zona getReturnZona() {
    return returnZona;
  }

  /**
   * @param returnZona returnZona or {@code null} for none
   */
  public ReturnValue setReturnZona(Zona returnZona) {
    this.returnZona = returnZona;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Object getValueReturn() {
    return valueReturn;
  }

  /**
   * @param valueReturn valueReturn or {@code null} for none
   */
  public ReturnValue setValueReturn(java.lang.Object valueReturn) {
    this.valueReturn = valueReturn;
    return this;
  }

  @Override
  public ReturnValue set(String fieldName, Object value) {
    return (ReturnValue) super.set(fieldName, value);
  }

  @Override
  public ReturnValue clone() {
    return (ReturnValue) super.clone();
  }

}
