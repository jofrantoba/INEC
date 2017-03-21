package com.inec.shared;

import java.io.Serializable;
import java.util.HashMap;

public class ListFilterBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nameQuery;
	private HashMap<String,Object> paramFilter;
	
	public String getNameQuery() {
		return nameQuery;
	}
	public HashMap<String, Object> getParamFilter() {
		return paramFilter;
	}
	public void setNameQuery(String nameQuery) {
		this.nameQuery = nameQuery;
	}
	public void setParamFilter(HashMap<String, Object> paramFilter) {
		this.paramFilter = paramFilter;
	}	
}
