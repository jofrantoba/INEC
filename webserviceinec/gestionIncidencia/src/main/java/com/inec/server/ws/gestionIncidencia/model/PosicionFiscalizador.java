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
 * on 2016-12-24 at 05:49:47 UTC 
 * Modify at your own risk.
 */

package com.inec.server.ws.gestionIncidencia.model;

/**
 * Model definition for PosicionFiscalizador.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the gestionIncidencia. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class PosicionFiscalizador extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codePosicionFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codeUsuarioFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String departamento;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String distrito;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fechaFormat;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String idPosicionFiscalizador;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double latitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String provincia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long version;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCodePosicionFiscalizador() {
    return codePosicionFiscalizador;
  }

  /**
   * @param codePosicionFiscalizador codePosicionFiscalizador or {@code null} for none
   */
  public PosicionFiscalizador setCodePosicionFiscalizador(java.lang.String codePosicionFiscalizador) {
    this.codePosicionFiscalizador = codePosicionFiscalizador;
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
  public PosicionFiscalizador setCodeUsuarioFiscalizador(java.lang.String codeUsuarioFiscalizador) {
    this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDepartamento() {
    return departamento;
  }

  /**
   * @param departamento departamento or {@code null} for none
   */
  public PosicionFiscalizador setDepartamento(java.lang.String departamento) {
    this.departamento = departamento;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDistrito() {
    return distrito;
  }

  /**
   * @param distrito distrito or {@code null} for none
   */
  public PosicionFiscalizador setDistrito(java.lang.String distrito) {
    this.distrito = distrito;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFechaFormat() {
    return fechaFormat;
  }

  /**
   * @param fechaFormat fechaFormat or {@code null} for none
   */
  public PosicionFiscalizador setFechaFormat(java.lang.String fechaFormat) {
    this.fechaFormat = fechaFormat;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdPosicionFiscalizador() {
    return idPosicionFiscalizador;
  }

  /**
   * @param idPosicionFiscalizador idPosicionFiscalizador or {@code null} for none
   */
  public PosicionFiscalizador setIdPosicionFiscalizador(java.lang.String idPosicionFiscalizador) {
    this.idPosicionFiscalizador = idPosicionFiscalizador;
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
  public PosicionFiscalizador setLatitud(java.lang.Double latitud) {
    this.latitud = latitud;
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
  public PosicionFiscalizador setLongitud(java.lang.Double longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getProvincia() {
    return provincia;
  }

  /**
   * @param provincia provincia or {@code null} for none
   */
  public PosicionFiscalizador setProvincia(java.lang.String provincia) {
    this.provincia = provincia;
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
  public PosicionFiscalizador setVersion(java.lang.Long version) {
    this.version = version;
    return this;
  }

  @Override
  public PosicionFiscalizador set(String fieldName, Object value) {
    return (PosicionFiscalizador) super.set(fieldName, value);
  }

  @Override
  public PosicionFiscalizador clone() {
    return (PosicionFiscalizador) super.clone();
  }

}
