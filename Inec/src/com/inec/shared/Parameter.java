package com.inec.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Parameter implements IsSerializable {
	private List<String[]> listUrlCoordenadas;

	public List<String[]> getListUrlCoordenadas() {
		return listUrlCoordenadas;
	}

	public void setListUrlCoordenadas(List<String[]> listUrlCoordenadas) {
		this.listUrlCoordenadas = listUrlCoordenadas;
	}
}
