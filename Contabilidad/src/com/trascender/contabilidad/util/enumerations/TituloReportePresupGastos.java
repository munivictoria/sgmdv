package com.trascender.contabilidad.util.enumerations;


public enum TituloReportePresupGastos {
	EROGACION_FINALIDAD("Por Finalidad y Función"),
	EROGACION_ECONOMICA("Económicas y por Objeto");

	private String nombre;

	private TituloReportePresupGastos(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
