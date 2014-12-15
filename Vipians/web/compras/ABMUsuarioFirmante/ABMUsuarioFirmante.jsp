<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.page1}" id="page1">
			<ui:html binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.html1}" id="html1">
			<ui:head binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.head1}" id="head1">
				<ui:link binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.body1}" focus="form1:btnSeleccionarUsuario" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.lblUsuario}" for="tfUsuario" id="lblUsuario" styleClass="label"
											text="Usuario" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.tfUsuario}" columns="40" disabled="true" id="tfUsuario"
											styleClass="textField" />
										<ui:button action="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnSeleccionarUsuario_action}"
											binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnSeleccionarUsuario}" escape="false" id="btnSeleccionarUsuario"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
									</td>
									<td align="right" nowrap="nowrap" style="padding-right: 150px">
										<ui:checkbox binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.cbImprimeOrdenNueva}" id="cbImprimeOrdenNueva" />
										<ui:label binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.lblImprimeOrdenNueva}" id="lblImprimeOrdenNueva" styleClass="label"
											text="Imprime Orden Nueva" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnGuardar_action}"
											binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnCancelar_action}"
											binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<td colspan="4">
								<ui:messageGroup binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
							</td>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.idSubSesion}" />
					<ui:script binding="#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>