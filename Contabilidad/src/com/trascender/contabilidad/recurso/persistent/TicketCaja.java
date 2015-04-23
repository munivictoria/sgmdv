package com.trascender.contabilidad.recurso.persistent;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

@Entity
@Table(name = "TICKET_CAJA")
public class TicketCaja implements Serializable{
	public static final long serialVersionUID = 8346270710647687762L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_ticket_caja")
	@SequenceGenerator(name = "gen_id_ticket_caja", sequenceName = "gen_id_ticket_caja",allocationSize = 1)
	@Column(name="ID_TICKET_CAJA")
	private long idTicketCaja=-1;
	private Date fecha;
	private Time hora;
	private Integer numero;
	
	@Column(name = "IMPORTE")
	private Double importeTotal;
	
	public enum Estado{ACTIVO, CANCELADO, DEVUELTO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	@Column(name = "CODIGO_BARRAS")
	private String codigoBarras;
		
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	//Relación con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CAJA")
	private Caja caja;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "ticketCaja", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DetalleTicketCaja> detalles=new HashSet<DetalleTicketCaja>();
	
	@ManyToOne
	@JoinColumn(name = "ID_PLANILLA_DIARIA_CAJA")
	private PlanillaDiariaCaja planillaDiariaCaja;
	
	@OneToMany(mappedBy = "ticketCaja", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PagoTicket> listaPagosTicket = new ArrayList<PagoTicket>();
	
	public boolean tieneDetallesConDeudaReatachada() {
		for (DetalleTicketCaja cadaDetalle : detalles) {
			if (cadaDetalle.getRegistroDeudaReatachado()) {
				return true;
			}
		}
		return false;
	}
	
	public List<PagoTicket> getListaPagosTicket() {
		return listaPagosTicket;
	}

	public void setListaPagosTicket(List<PagoTicket> listaPagosTicket) {
		this.listaPagosTicket = listaPagosTicket;
	}
	
	public void addPagoTicket(PagoTicket pPagoTicket){
		pPagoTicket.setTicketCaja(this);
		this.listaPagosTicket.add(pPagoTicket);
	}
	
	public Double getMontoPagosTicket(){
		Double resultado = 0d;
		for (PagoTicket cadaPago : this.listaPagosTicket){
			resultado += cadaPago.getMonto();
		}
		return resultado;
	}
	
	public boolean validarMontoPagos() {
		return this.getImporteTotal().equals(this.getMontoPagosTicket());
	}

	public PlanillaDiariaCaja getPlanillaDiariaCaja() {
		return planillaDiariaCaja;
	}

	public void setPlanillaDiariaCaja(PlanillaDiariaCaja planillaDiariaCaja) {
		this.planillaDiariaCaja = planillaDiariaCaja;
	}

	public Set<DetalleTicketCaja> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<DetalleTicketCaja> detalles) {
		this.detalles = detalles;
	}

	public long getIdTicketCaja() {
		return idTicketCaja;
	}

	public void setIdTicketCaja(long idTicketCaja) {
		this.idTicketCaja = idTicketCaja;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getStringPersona(){
		Pagable locDeuda = this.detalles.iterator().next().getDeuda();
		if (locDeuda == null) {
			return "";
		}
		return this.detalles.iterator().next().getDeuda().getPersona().toString();
	}
	
	public String getNroParcela(){
		if(this.getDetalles().iterator().next().getDeuda() instanceof LiquidacionTasa){
			DocHabilitanteEspecializado locDocumento = ((LiquidacionTasa)this.getDetalles().iterator().next().getDeuda()).getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			if(locDocumento.getParcela() != null && locDocumento.getParcela().getNroParcela() != null){
				return locDocumento.getParcela().getNroParcela().toString();
			}
		}
		return "";
	}
	
	public Double getImporteInteres() {
		Double interes = new Double(0);
		for (DetalleTicketCaja cadaDetalle : detalles) {
			if (cadaDetalle.getDeuda() instanceof RegistroDeuda) {
				interes += ((RegistroDeuda)cadaDetalle.getDeuda()).getInteres();
			}
		}
		return interes;
	}
	
	@Override
	public String toString() {
		return "TicketCaja [numero=" + numero + ", fecha=" + fecha + ", importeTotal=" + importeTotal + " ]";
	}
	
}
