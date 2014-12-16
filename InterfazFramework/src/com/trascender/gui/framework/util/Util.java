package com.trascender.gui.framework.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;

public class Util {
	
	public static Rectangle getBoundsColumnaLabel(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_LBL_POS_INI_X,
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.LBL_WIDTH, 
				ConstantesTamanio.LBL_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaInputTextField(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_WIDTH, 
				ConstantesTamanio.TF_HEIGHT);
	}
	
	//Nuevo
	public static Rectangle getBoundsColumnaInputTextFieldFechaDesde(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_FECHA_WIDTH, 
				ConstantesTamanio.TF_FECHA_HEIGHT);
	}
	
	//Nuevo - Marina
	public static Rectangle getBoundsColumnaInputTextFieldImporte(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_IMPORTE_WIDTH, 
				ConstantesTamanio.TF_IMPORTE_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaInputTextFieldFechaHasta(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X+ ConstantesTamanio.TF_FECHA_WIDTH + ConstantesSeparacion.SEPARADOR_HORIZONTAL, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_FECHA_WIDTH, 
				ConstantesTamanio.TF_FECHA_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaInputComboBox(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.CBX_WIDTH, 
				ConstantesTamanio.CBX_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaInputTextArea(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TA_WIDTH, 
				ConstantesTamanio.TA_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaSeleccion(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X + ConstantesTamanio.TF_WIDTH + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				ConstantesTamanio.PNL_BOTONES_SELECCION_HEIGHT);
	}
	
	public static Rectangle getBoundsColumnaSeleccionPersona(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X + ConstantesTamanio.TF_WIDTH + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.PNL_BOTONES_SELECCION_PERSONA_WIDTH,
				ConstantesTamanio.PNL_BOTONES_SELECCION_PERSONA_HEIGHT);
	}

	public static Rectangle getBoundsBotonesPnlVertical(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_LBL_POS_INI_X,
		ConstantesPosicion.COLUMN_LBL_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y,
		ConstantesTamanio.BTN_WIDTH, 
		ConstantesTamanio.BTN_HEIGHT);
	}
	
	public static Dimension getTamanioVentanaABM(int pCantidadFilas) {
		return new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilas*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y);
	}
	
	public static Rectangle getBoundsColumnaCheckBox(int pNumFila) {
		return new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X - 4,
		ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumFila*ConstantesSeparacion.INCREMENTO_Y,
		ConstantesTamanio.CHK_WIDTH, 
		ConstantesTamanio.CHK_HEIGHT);
	}
	
	public static Dimension getTamanioVentanaABMSeleccion(int pCantidadFilas) {
		return new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
			ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
			ConstantesTamanio.TF_WIDTH+
			ConstantesSeparacion.SEPARADOR_HORIZONTAL+
			ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
			ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilas*
			ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y);
	}
	
	public static Periodo getPeriodoMensual(Calendar pFecha) {
		Calendar locFechaInicio = Calendar.getInstance();
		Calendar locFechaFin = Calendar.getInstance();
		
		locFechaInicio.set(Calendar.YEAR, pFecha.get(Calendar.YEAR));
		locFechaInicio.set(Calendar.MONTH, pFecha.get(Calendar.MONTH));
		locFechaInicio.set(Calendar.DAY_OF_MONTH,pFecha.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		
		locFechaFin.set(Calendar.YEAR, pFecha.get(Calendar.YEAR));
		locFechaFin.set(Calendar.MONTH, pFecha.get(Calendar.MONTH));
		locFechaFin.set(Calendar.DAY_OF_MONTH,pFecha.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		double locPorcentaje = (double) pFecha.get(Calendar.DAY_OF_MONTH)
				/ (double) (pFecha.getActualMaximum(Calendar.DAY_OF_MONTH));

		Periodo locPeriodo = new Periodo();
		locPeriodo.setFechaFin(locFechaFin);
		locPeriodo.setFechaInicio(locFechaInicio);
//		locPeriodo.setPorcentajePeriodo(locPorcentaje);
//		locPeriodo.setNombrePeriodo("Mes "
//				+ (new SimpleDateFormat("MMMM")).format(pFecha.getTime()));
//		
//		
//		locPeriodo.setNumeroPeriodo((locPeriodo.getFechaInicio().get(Calendar.MONTH)+1));
//		locPeriodo.setNombrePeriodoReducido("Mes "+locPeriodo.getNumeroPeriodo()+"/12 de "+locPeriodo.getFechaInicio().get(Calendar.YEAR));
//		locPeriodo.setPeriodicidad(Periodicidad.MENSUAL);
		return locPeriodo;
	}
}