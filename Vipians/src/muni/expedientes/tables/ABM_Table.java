package muni.expedientes.tables;

import com.trascender.presentacion.abstracts.ABMPageBean;


public abstract class ABM_Table extends ABMPageBean{

	
	/// TODO MOVER ESTOS METODOS UN NIVEL EN LA GERARQUIA Y ELIMINAR ESTA CLASE 
	
	
	protected String capitalizeOnlyFirst(String pString) {
        char[] charArray = pString.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        return new String(charArray);
    }
	
	
	protected String quitar_action(TableBean pTable) {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			pTable.quitarElemento();
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;

	}

	protected String quitarTodos_action(TableBean pTable) {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				pTable.quitarTodosLosElementos();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;

	}
	
	public abstract void getElementosPila();

	
	protected abstract void setElementosPila(); 
	
}
