/*
 * Nav2.java
 *
 * Created on 22 de agosto de 2006, 11:14
 * Copyright Asus
 */

package muni;

import java.io.IOException;
import java.rmi.NoSuchObjectException;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.StaticText;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class Nav2 extends AbstractPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
	}

	private Page page1 = new Page();

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page p) {
		this.page1 = p;
	}

	private Html html1 = new Html();

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html h) {
		this.html1 = h;
	}

	private Head head1 = new Head();

	public Head getHead1() {
		return head1;
	}

	public void setHead1(Head h) {
		this.head1 = h;
	}

	private Link link1 = new Link();

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	private Body body1 = new Body();

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body b) {
		this.body1 = b;
	}

	private Form form1 = new Form();

	public Form getForm1() {
		return form1;
	}

	public void setForm1(Form f) {
		this.form1 = f;
	}

	private StaticText stPersona = new StaticText();

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText st) {
		this.stPersona = st;
	}

	private Hyperlink hypSalir = new Hyperlink();

	public Hyperlink getHypSalir() {
		return hypSalir;
	}

	public void setHypSalir(Hyperlink h) {
		this.hypSalir = h;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Hyperlink hypAyuda = new Hyperlink();

	public Hyperlink getHypAyuda() {
		return hypAyuda;
	}

	public void setHypAyuda(Hyperlink h) {
		this.hypAyuda = h;
	}

	private Hyperlink hypOpciones = new Hyperlink();

	public Hyperlink getHypOpciones() {
		return hypOpciones;
	}

	public void setHypOpciones(Hyperlink h) {
		this.hypOpciones = h;
	}

	private Hyperlink hypInicio = new Hyperlink();

	public Hyperlink getHypInicio() {
		return hypInicio;
	}

	public void setHypInicio(Hyperlink h) {
		this.hypInicio = h;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public Nav2() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationCajaBean getCommunicationCajaBean() {
		return (CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationComprasBean getCommunicationComprasBean() {
		return (CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationSAICBean getCommunicationSAICBean() {
		return (CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ámbito.
	 * </p>
	 */
	protected ComunicationCatastroBean getComunicationCatastroBean() {
		return (ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected ComunicationBean getComunicationBean() {
		return (ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
		return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
	}

	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected RequestBean1 getRequestBean1() {
		return (RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected SessionBean1 getSessionBean1() {
		return (SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 *
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
	 */
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("Nav2 Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		try {
			Usuario userActivo = null;
			if(this.getSessionBean1() != null) {
				long llave = this.getSessionBean1().getLlave();
				userActivo = this.getComunicationBean().getRemoteSystemUsuario().findUsuarioPorLlave(llave);
				if(userActivo == null) {
					this.getExternalContext().redirect("/Vipians/faces/Login.jsp");
				}
			}
		} catch(TrascenderException e) {
			try {
				this.getExternalContext().redirect("/Vipians/faces/Login.jsp");
			} catch(IOException ex) {
			}
		} catch(Exception e) {
		}

		// if (this.getSessionBean1().getUsuario() == null) {
		// try {
		// this.getExternalContext().redirect("/Muni");
		// } catch (Exception ex) {
		//
		// }
		// }

		try {
			String ip = this.getExternalContext().getInitParameter("ipServer");
			String port = this.getExternalContext().getInitParameter("puertoServer");
			String helpApp = this.getExternalContext().getInitParameter("helpApp");
			this.getHypAyuda().setUrl("http://" + ip + ":" + port + "/" + helpApp);
		} catch(Exception ex) {
		}

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	public void prerender() {
		try {
			String nombre = "";
			if(this.getSessionBean1().getPersonaUsuario() != null) {
				nombre = this.getSessionBean1().getPersonaUsuario().getNombre();

				if(this.getSessionBean1().getUsuario() != null) {
					nombre += "<br/> [ " + this.getSessionBean1().getUsuario().getUser() + " ]";
				}
			}
			this.getStPersona().setText(nombre);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.getStPersona().setText(null);
		}
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	public String salir_action() {
		System.out.println("Accion salir-----------");
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			if(this.getSessionBean1() != null && this.getSessionBean1().getLlave() != 0L) {
				this.getComunicationBean().getRemoteSystemUsuario().logout(this.getSessionBean1().getLlave());
			}
			if(session != null) {
				session.invalidate();
			}
			// this.getExternalContext().redirect("/Muni");
		} catch(NoSuchObjectException nsoe) {
			// No se pudo recuperar un archivo .ser que manten�a la sesion del Usuario.
			// java.rmi.NoSuchObjectException: Could not activate; failed to restore state; CausedByException is:
			// [JBOSS_HOME]\server\default\tmp\sessions\SystemUsuario-f17onoec-5\f17y73hi-w.ser (El sistema no puede hallar el archivo especificado)
		} catch(Exception ex) {
			log("LogOutError_", ex);
			error("Salir: " + ex.getMessage());
		}

		return "Login";
	}
}
