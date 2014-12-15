<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" 
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.page1}" id="page1">
			<ui:html binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.html1}" id="html1">
			<ui:head binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.head1}" id="head1">
				<ui:link binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.body1}" focus="form1:tfAnio" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.lblUsuarioSuplente}"
											for="tfUsuarioSuplente" id="lblUsuarioSuplente" styleClass="label" text="Usuario Suplente" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.tfUsuarioSuplente}" columns="40"
											disabled="true" id="tfUsuarioSuplente" styleClass="textField" />
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnSeleccionarUsuario_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnSeleccionarUsuario}" escape="false"
											id="btnSeleccionarUsuario" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarUsuario" reRender="form1:tfUsuarioSuplente"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnLimpiarUsuario}"
											action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnLimpiarUsuario_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.lblUsuarios}" for="ddUsuarios"
											id="lblUsuarios" styleClass="label" text="Usuario Suplido" />
									</td>
									<td>
										<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.ddUsuarios}" id="ddUsuarios"
											styleClass="textField"
											items="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.ddUsuariosDefaultOptions.options}" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.lblFechaDesde}" for="tfFechaDesde"
											id="lblFechaDesde" styleClass="label" text="Fecha Suplencia Desde" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.tfFechaDesde}" id="tfFechaDesde"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.lblFechaHasta}" for="tfFechaHasta"
											id="lblFechaHasta" styleClass="label" text="Fecha Suplencia Hasta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.tfFechaHasta}" id="tfFechaHasta"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.messageGroup}"
											id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnGuardar_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnCancelar_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.idSubSesion}" />
					<ui:script binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
