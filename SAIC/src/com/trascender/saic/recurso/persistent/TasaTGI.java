package com.trascender.saic.recurso.persistent;


//@Entity
//@PrimaryKeyJoinColumn(name = "ID_DOC_GENERADOR_DEUDA")
//@Table(name = "TASA_TGI")
public class TasaTGI extends Tasa {

	public static final long serialVersionUID = 1540051730473108452L;

	public TasaTGI() {
		super();
	}

	public TasaTGI(TasaTGI pTasaTGIAnterior) {
		this.setPeriodicidadConfigurada(pTasaTGIAnterior
				.getPeriodicidadConfigurada());
		this.setDescripcion(pTasaTGIAnterior.getDescripcion());
		this.setDocGeneradorDeudaAnterior(pTasaTGIAnterior);
		this.setNombre(pTasaTGIAnterior.getNombre());
		this.setObligacion(pTasaTGIAnterior.getObligacion());
		this.setPeriodicidad(pTasaTGIAnterior.getPeriodicidad());

		//		this.setTipoTasa(pTasaTGIAnterior.getTipoTasa());
	}



}
