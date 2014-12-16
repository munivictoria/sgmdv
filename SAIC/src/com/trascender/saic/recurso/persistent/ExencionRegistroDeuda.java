package com.trascender.saic.recurso.persistent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.saic.exception.SaicException;

@Entity
@PrimaryKeyJoinColumn(name = "ID_EXENCION")
@Table(name = "EXENCION_REGISTRO_DEUDA")
public class ExencionRegistroDeuda extends Exencion implements Cloneable {

	public static final long serialVersionUID = -4849477571291304847L;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "exencionRegistroDeuda")
	private Set<RegistroExencionRegistroDeuda> listaRegistrosExencion = new HashSet<RegistroExencionRegistroDeuda>();

	public Set<RegistroExencionRegistroDeuda> getListaRegistrosExencion() {
		return listaRegistrosExencion;
	}

	public void setListaRegistrosExencion(
			Set<RegistroExencionRegistroDeuda> pListaRegistrosExencion) {
		this.listaRegistrosExencion = pListaRegistrosExencion;
	}

	public void addRegistroDeudaExento(
			RegistroExencionRegistroDeuda pRegistroDeudaExento)
			throws Exception {
		try {
			if (pRegistroDeudaExento.getExencionRegistroDeuda() != null
					&& !pRegistroDeudaExento.getExencionRegistroDeuda().equals(
							this)) {
				throw new SaicException(800);
			}

//			if (this.getCuotaLiquidacion() != null) {
//				if (!this.getCuotaLiquidacion().equals(
//						pRegistroDeudaExento.getRegistroDeuda().getCuotaLiquidacion())) {
//					throw new SaicException(801);
//				}
//			} else {
//				throw new SaicException(802);
//			}

			if (!this.listaRegistrosExencion.contains(pRegistroDeudaExento)) {
				this.listaRegistrosExencion.add(pRegistroDeudaExento);
				pRegistroDeudaExento.setExencionRegistroDeuda(this);
				// pRegistroDeudaExento.getRegistroDeuda().setRegistroExencionRegistroDeuda(pRegistroDeudaExento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void removeRegistroDeudaExento(
			RegistroExencionRegistroDeuda pRegistroExencionRegistroDeuda)
			throws Exception {
		try {
			if (this.listaRegistrosExencion
					.contains(pRegistroExencionRegistroDeuda)) {
				pRegistroExencionRegistroDeuda.setExencionRegistroDeuda(null);
				pRegistroExencionRegistroDeuda.getRegistroDeuda().setEstado(
						pRegistroExencionRegistroDeuda.getRegistroDeuda()
								.getEstadoAnterior());
				if (pRegistroExencionRegistroDeuda.getRegistroDeuda() instanceof LiquidacionTasa) {
					// La idea es recuperar los datos para que recalcule el
					// monto ahora que no tiene exencion
					LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) pRegistroExencionRegistroDeuda
							.getRegistroDeuda();
					locLiquidacionTasa.getMonto();
					locLiquidacionTasa.getMontoExencion();
				}
				pRegistroExencionRegistroDeuda.setReferenciaNotaHCD(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public ExencionRegistroDeuda clone() throws CloneNotSupportedException {
		ExencionRegistroDeuda locExencionRegistroDeuda = new ExencionRegistroDeuda();
		locExencionRegistroDeuda = (ExencionRegistroDeuda) super.clone();
		return locExencionRegistroDeuda;
	}

	public static ExencionRegistroDeuda copy(
			ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception {
		ExencionRegistroDeuda locExencionRegistroDeudaCopy = new ExencionRegistroDeuda();
		locExencionRegistroDeudaCopy.setIdExencion(pExencionRegistroDeuda
				.getIdExencion());
		locExencionRegistroDeudaCopy.setNombre(pExencionRegistroDeuda
				.getNombre());
		locExencionRegistroDeudaCopy.setDigestoMunicipal(pExencionRegistroDeuda
				.getDigestoMunicipal());
		locExencionRegistroDeudaCopy.setEstado(pExencionRegistroDeuda
				.getEstado());

		locExencionRegistroDeudaCopy.setMotivo(pExencionRegistroDeuda
				.getMotivo());
//		locExencionRegistroDeudaCopy.setCuotaLiquidacion(pExencionRegistroDeuda
//				.getCuotaLiquidacion());
		locExencionRegistroDeudaCopy
				.setPeriodicidadCuotas(pExencionRegistroDeuda
						.getPeriodicidadCuotas());
		locExencionRegistroDeudaCopy
				.setPeriodicidadCuotas(pExencionRegistroDeuda
						.getPeriodicidadCuotas());
//		locExencionRegistroDeudaCopy.setPorcentaje(pExencionRegistroDeuda
//				.getPorcentaje());
//		locExencionRegistroDeudaCopy.setTipoExencion(pExencionRegistroDeuda
//				.getTipoExencion());

		locExencionRegistroDeudaCopy
				.setListaFirmas(new ArrayList<FirmaPermiso>());
		locExencionRegistroDeudaCopy.getListaFirmas().addAll(
				pExencionRegistroDeuda.getListaFirmas());

		locExencionRegistroDeudaCopy
				.setListaRegistrosExencion(new HashSet<RegistroExencionRegistroDeuda>());
		for (RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : pExencionRegistroDeuda
				.getListaRegistrosExencion()) {
			System.out.println("nota "
					+ cadaRegistroExencionRegistroDeuda.getReferenciaNotaHCD());
			RegistroExencionRegistroDeuda locRegistroExencionRegistroDeuda = new RegistroExencionRegistroDeuda();
			locRegistroExencionRegistroDeuda
					.setExencionRegistroDeuda(locExencionRegistroDeudaCopy);
			locRegistroExencionRegistroDeuda
					.setIdRegistroExencionRegistroDeuda(cadaRegistroExencionRegistroDeuda
							.getIdRegistroExencionRegistroDeuda());
			locRegistroExencionRegistroDeuda
					.setReferenciaNotaHCD(cadaRegistroExencionRegistroDeuda
							.getReferenciaNotaHCD());
			locRegistroExencionRegistroDeuda
					.setRegistroDeuda(cadaRegistroExencionRegistroDeuda
							.getRegistroDeuda());

			locExencionRegistroDeudaCopy
					.addRegistroDeudaExento(locRegistroExencionRegistroDeuda);
		}
		// locExencionRegistroDeudaCopy.getListaRegistrosExencion().addAll(pExencionRegistroDeuda.getListaRegistrosExencion());

		return locExencionRegistroDeudaCopy;

	}

}
