<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.head1}" id="head1" title="Modificar Inspector">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.label4}" for="tfNombre" id="label4" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.label1}" for="tfPersona" id="label1"
											styleClass="label" text="Persona" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnSeleccionarPersona_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnSeleccionarPersona}" escape="false"
											id="btnSeleccionarPersona" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnLimpiarPersona}"
											action="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="true">
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$ModificarInspector.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
