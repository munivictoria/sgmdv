package com.trascender.contabilidad.gui.abmIngresoTesoreria;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class MovimientoCajaIngresoTableModel extends TAbstractTableModel<MovimientoCajaIngreso> {

	private static final long serialVersionUID = -5184417561426562047L;
	
	public MovimientoCajaIngresoTableModel() throws Exception {
		super(MovimientoCajaIngreso.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Ticket Caja", MovimientoCajaIngreso.class.getDeclaredField("detalleTicket"), DetalleTicketCaja.class));
//			locListaColumnas.add(new TColumn("Cuenta", MovimientoCaja.class.getDeclaredField("cuenta"), Cuenta.class));
//			locListaColumnas.add(new TColumn("Fecha", MovimientoCaja.class.getDeclaredField("fecha"), Date.class));
//			locListaColumnas.add(new TColumn("Importe", MovimientoCaja.class.getDeclaredField("importe"), Double.class));
			//EN LA LOGICA EL TO STRING DEL DETALLE TICKET CAJA DEVUELVE LOS DATOS DEL TICKET
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
