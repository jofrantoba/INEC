/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inec.view.uiutil;

/**
 *
 * @author root
 */
public interface IFilter<T> {
    boolean isValid(T value,String filter);
}
