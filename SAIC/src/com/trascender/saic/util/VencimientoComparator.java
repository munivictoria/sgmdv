package com.trascender.saic.util;

import java.io.Serializable;
import java.util.Comparator;

import com.trascender.saic.recurso.persistent.Vencimiento;

public class VencimientoComparator implements Comparator<Vencimiento>,Serializable{

	private static final long serialVersionUID = 3754497059686181785L;

	public int compare(Vencimiento o1, Vencimiento o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}
}
