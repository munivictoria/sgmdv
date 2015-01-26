package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="CONFIGURACION_ACCESOS_DIRECTOS")
public class ConfiguracionAccesosDirectos implements Serializable{

	private static final long serialVersionUID = -1375569539058939733L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_conf_accesos_directos")
	@SequenceGenerator(name = "gen_id_conf_accesos_directos", sequenceName = "gen_id_conf_accesos_directos", allocationSize = 1)
	@Column(name = "ID_CONFIGURACION_ACCESOS_DIRECTOS")
	private long idConfiguracionAccesosDirectos = -1;
	
	@ManyToOne
	@JoinColumn(name ="ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "configuracion")
	private List<AccesoDirecto> listaAccesosDirecto = new ArrayList<AccesoDirecto>();

	public long getIdConfiguracionAccesosDirectos() {
		return idConfiguracionAccesosDirectos;
	}

	public void setIdConfiguracionAccesosDirectos(
			long pIdConfiguracionAccesosDirectos) {
		idConfiguracionAccesosDirectos = pIdConfiguracionAccesosDirectos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}

	public List<AccesoDirecto> getListaAccesosDirecto() {
		return listaAccesosDirecto;
	}

	public void setListaAccesosDirecto(List<AccesoDirecto> pListaAccesosDirecto) {
		listaAccesosDirecto = pListaAccesosDirecto;
	}

	public boolean addAccesoDirecto(Long pIdRecurso) {
		if (!yaEsta(pIdRecurso)) {
			AccesoDirecto locAcceso = new AccesoDirecto();
			locAcceso.setIdRecurso(pIdRecurso);
			locAcceso.setConfiguracion(this);
			this.listaAccesosDirecto.add(locAcceso);
			return true;
		}
		return false;
	}
	
	private boolean yaEsta(Long pIdRecurso) {
		for (AccesoDirecto cadaAcceso : listaAccesosDirecto) {
			if (cadaAcceso.getIdRecurso() == pIdRecurso.longValue()) {
				return true;
			}
		}
		return false;
	}

}
