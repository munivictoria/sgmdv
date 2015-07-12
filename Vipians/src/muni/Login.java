/*
 * Login.java
 *
 * Created on 22 de agosto de 2006, 15:11
 * Copyright Asus
 */

package muni;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.PasswordField;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemUsuario;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class Login extends AbstractPageBean {
	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

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

	private PanelLayout lpPanel1 = new PanelLayout();

	public PanelLayout getLpPanel1() {
		return lpPanel1;
	}

	public void setLpPanel1(PanelLayout pl) {
		this.lpPanel1 = pl;
	}

	private Button btnIngresar = new Button();

	public Button getBtnIngresar() {
		return btnIngresar;
	}

	public void setBtnIngresar(Button b) {
		this.btnIngresar = b;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private Hyperlink hyperlink1 = new Hyperlink();

	public Hyperlink getHyperlink1() {
		return hyperlink1;
	}

	public void setHyperlink1(Hyperlink h) {
		this.hyperlink1 = h;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private PanelLayout lpIngreso = new PanelLayout();

	public PanelLayout getLpIngreso() {
		return lpIngreso;
	}

	public void setLpIngreso(PanelLayout pl) {
		this.lpIngreso = pl;
	}

	private PanelLayout lpFalloIngreso = new PanelLayout();

	public PanelLayout getLpFalloIngreso() {
		return lpFalloIngreso;
	}

	public void setLpFalloIngreso(PanelLayout pl) {
		this.lpFalloIngreso = pl;
	}

	private StaticText stMensaje = new StaticText();

	public StaticText getStMensaje() {
		return stMensaje;
	}

	public void setStMensaje(StaticText st) {
		this.stMensaje = st;
	}

	private Button btnVolver = new Button();

	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button b) {
		this.btnVolver = b;
	}

	private Label lblUser = new Label();

	public Label getLblUser() {
		return lblUser;
	}

	public void setLblUser(Label l) {
		this.lblUser = l;
	}

	private TextField tfUser = new TextField();

	public TextField getTfUser() {
		return tfUser;
	}

	public void setTfUser(TextField tf) {
		this.tfUser = tf;
	}

	private Label lblPassword = new Label();

	public Label getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(Label l) {
		this.lblPassword = l;
	}

	private PasswordField pfPassword = new PasswordField();

	public PasswordField getPfPassword() {
		return pfPassword;
	}

	public void setPfPassword(PasswordField pf) {
		this.pfPassword = pf;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public Login() {
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
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
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
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected RequestBean1 getRequestBean1() {
		return (RequestBean1) getBean("RequestBean1");
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
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("Login Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>

		Usuario userActivo = null;
		try {
			if(this.getSessionBean1() != null) {
				long llave = this.getSessionBean1().getLlave();
				if(llave != 0) {
					userActivo = this.getComunicationBean().getRemoteSystemUsuario().findUsuarioPorLlave(llave);
					if(userActivo != null) {
						this.getExternalContext().redirect("/Vipians/faces/Page1.jsp");
					}
				}
			}
			// if (userActivo == null) this.getExternalContext().redirect("/Vipians/faces/Login.jsp");
		} catch(Exception e) {
		}

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	@Override
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	@Override
	public void prerender() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	public String btnIngresar_action() {
		Object us = tfUser.getText();
		Object ps = pfPassword.getText();
		String retorno = null;
		// Object a = null;
		String remoteAddr = null;
		String remoteHost = null;
		String remotePort = null;

		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
			remoteAddr = req.getRemoteAddr();
			remoteHost = req.getRemoteHost();
			remotePort = String.valueOf(req.getRemotePort());
		} catch(Exception ex) {
		}

		if(us != null && ps != null) {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				if(session != null)
					session.invalidate();

				long key = this.getRemoteSystemUsuario().login(us.toString(), ps.toString(), remoteAddr, remotePort, remoteHost);
				this.getSessionBean1().setLlave(key);

				this.getRemoteSystemUsuario().setLlave(key);
				Usuario user = this.getRemoteSystemUsuario().findUsuarioPorLlave(key);
				System.out.println("user------------> " + user.toString());

				if(user != null) {
					this.getSessionBean1().setUsuario(user);
					
					Long idPersona = new Long(user.getIdPersonaFisica());
					System.out.println("id------------> " + idPersona);
					this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(key);
					PersonaFisica persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(idPersona.longValue());
					this.getSessionBean1().setPersonaUsuario(persona);

					this.getPfPassword().setText(null);
					
					// Se recuperan los reportes del usuario y se los filtra por id de recurso en un mapa...
					List<Reporte> listaReportesDelUsuario = new ArrayList<Reporte>();
					try {
						if(this.getRemoteSystemParametro() != null) {
							this.getRemoteSystemParametro().setLlave(key);
							listaReportesDelUsuario = this.getRemoteSystemParametro().getListaReportesPorUsuario(user.getIdUsuario());
						}

						for(Reporte cadaReporte : listaReportesDelUsuario) {
							this.getSessionBean1().getMapaDeListasReportesDelUsuario().add(cadaReporte.getIdRecurso(), cadaReporte);
						}
					} catch(Exception e) {
						e.printStackTrace();
					}

					retorno = "Login_Page1";
				}

			} catch(Exception ex) {
				retorno = null;
				log("Login_IngresarError: ", ex);
				stMensaje.setText(ex.getMessage());
				lpIngreso.setRendered(false);
				lpFalloIngreso.setRendered(true);
			}
		} else {
			error("Complete el Nombre de Usuario y la Contrase\361a.");
		}
		return retorno;
	}

	private SystemUsuario getRemoteSystemUsuario() {
		SystemUsuario remoteSystemUsuario = null;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			props.put(Context.PROVIDER_URL, "localhost:1099");

			Context ctx = new InitialContext(props);

			remoteSystemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		} catch(NamingException e) {
			e.printStackTrace();
		}

		return remoteSystemUsuario;
	}

	private SystemParametro getRemoteSystemParametro() {
		SystemParametro remoteSystemParametro = null;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			props.put(Context.PROVIDER_URL, "localhost:1099");

			Context ctx = new InitialContext(props);

			remoteSystemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);
		} catch(NamingException e) {
			e.printStackTrace();
		}

		return remoteSystemParametro;
	}

	public String btnVolver_action() {
		pfPassword.setValue(null);
		lpIngreso.setRendered(true);
		lpFalloIngreso.setRendered(false);
		return null;
	}

}
