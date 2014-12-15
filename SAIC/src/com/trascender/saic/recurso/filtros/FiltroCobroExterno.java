package com.trascender.saic.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.saic.recurso.persistent.CobroExterno;

public class FiltroCobroExterno extends FiltroAbstracto<CobroExterno>{

	private static final long serialVersionUID = 8094597473140905542L;
	
	private Object upload;

	public Object getUpload() {
		return upload;
	}

	public void setUpload(Object upload) {
		this.upload = upload;
	}
	
	
}
