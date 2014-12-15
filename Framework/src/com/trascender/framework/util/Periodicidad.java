package com.trascender.framework.util;

public enum Periodicidad {
	DIARIO("Día",0,0,1),
	QUINCENAL("Quincena",0,0,15),
	MENSUAL("Mes",0,1,0),
	BIMESTRAL("Bimestre",0,2,0),
	TRIMESTRAL("Trimestre",0,3,0),
	CUATRIMESTRAL("Cuatrimestre",0,4,0),
	SEMESTRAL("Semestre",0,6,0),
	ANUAL("Año",1,0,0);

	private String nombrePeriodo;
	private Integer meses;
	private Integer dias;
	private Integer años;
	
	public static Integer getCantidadPeriodosPorAnio(Periodicidad pPeriodicidad){
		switch (pPeriodicidad) {
			case DIARIO: return 365;
			case QUINCENAL: return 24;
			case MENSUAL: return 12;
			case BIMESTRAL: return 6;
			case TRIMESTRAL: return 4;
			case CUATRIMESTRAL: return 3;
			case SEMESTRAL: return 2;
			case ANUAL: return 1;
			default: return null;
		}
	}
	
	public String getNombrePeriodo() {
		return nombrePeriodo;
	}

	private Periodicidad(String nombrePeriodo, Integer pAño, Integer pMeses, Integer pDias){
		this.nombrePeriodo=nombrePeriodo;
		this.meses = pMeses;
		this.años = pAño;
		this.dias  =pDias;
	}
	@Override
	public String toString() {
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}


	public Integer getAños() {
		return años;
	}
	public Integer getDias() {
		return dias;
	}
	public Integer getMeses() {
		return meses;
	}
	
}

