<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.lblNombreTramiteCatalogo}"
											for="ddTramiteCatalogo" id="lblNombreTramiteCatalogo" styleClass="label" text="TramiteCatalogo" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:dropDown binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.ddTramiteCatalogo}" id="ddTramiteCatalogo"
											items="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.ddTramiteCatalogoOptions.options}" styleClass="textField" />
										<ui:button action="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnSeleccionarTramiteCatalogo_action}"
											binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnSeleccionarTramiteCatalogo}" escape="false"
											id="btnSeleccionarTramiteCatalogo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarTramiteCatalogo" reRender="form1:ddTramiteCatalogo"
											binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnLimpiarTramiteCatalogo}"
											action="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnLimpiarTramiteCatalogo_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnGuardar_action}"
											binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnCancelar_action}"
											binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
