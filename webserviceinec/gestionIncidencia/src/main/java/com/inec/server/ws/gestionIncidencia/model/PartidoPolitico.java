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
 * Model definition for PartidoPolitico.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the gestionIncidencia. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class PartidoPolitico extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String codePartidoPolitico;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String idPartidoPolitico;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lider;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nombrePartido;

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
  public java.lang.String getCodePartidoPolitico() {
    return codePartidoPolitico;
  }

  /**
   * @param codePartidoPolitico codePartidoPolitico or {@code null} for none
   */
  public PartidoPolitico setCodePartidoPolitico(java.lang.String codePartidoPolitico) {
    this.codePartidoPolitico = codePartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdPartidoPolitico() {
    return idPartidoPolitico;
  }

  /**
   * @param idPartidoPolitico idPartidoPolitico or {@code null} for none
   */
  public PartidoPolitico setIdPartidoPolitico(java.lang.String idPartidoPolitico) {
    this.idPartidoPolitico = idPartidoPolitico;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLider() {
    return lider;
  }

  /**
   * @param lider lider or {@code null} for none
   */
  public PartidoPolitico setLider(java.lang.String lider) {
    this.lider = lider;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNombrePartido() {
    return nombrePartido;
  }

  /**
   * @param nombrePartido nombrePartido or {@code null} for none
   */
  public PartidoPolitico setNombrePartido(java.lang.String nombrePartido) {
    this.nombrePartido = nombrePartido;
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
  public PartidoPolitico setOperacion(java.lang.String operacion) {
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
  public PartidoPolitico setVersion(java.lang.Long version) {
    this.version = version;
    return this;
  }

  @Override
  public PartidoPolitico set(String fieldName, Object value) {
    return (PartidoPolitico) super.set(fieldName, value);
  }

  @Override
  public PartidoPolitico clone() {
    return (PartidoPolitico) super.clone();
  }

}
