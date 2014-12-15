<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMProvincia$ABMProvincia.page1}" id="page1">
			<ui:html binding="#{framework$ABMProvincia$ABMProvincia.html1}" id="html1">
			<ui:head binding="#{framework$ABMProvincia$ABMProvincia.head1}" id="head1">
				<ui:link binding="#{framework$ABMProvincia$ABMProvincia.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMProvincia$ABMProvincia.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMProvincia$ABMProvincia.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMProvincia$ABMProvincia.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMProvincia$ABMProvincia.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMProvincia$ABMProvincia.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMProvincia$ABMProvincia.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMProvincia$ABMProvincia.label5}" for="tfAbreviatura" id="label5" styleClass="label"
											text="Abreviatura" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMProvincia$ABMProvincia.tfAbreviatura}" columns="10" id="tfAbreviatura" maxLength="20"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMProvincia$ABMProvincia.label2}" for="tfCodigo" id="label2" styleClass="label" text="Código" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMProvincia$ABMProvincia.tfCodigo}" id="tfCodigo" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMProvincia$ABMProvincia.label1}" for="ddPais" id="label1" styleClass="label" text="País" />
									</td>
									<td nowrap="true">
										<ui:dropDown binding="#{framework$ABMProvincia$ABMProvincia.ddPais}" id="ddPais"
											items="#{framework$ABMProvincia$ABMProvincia.ddPaisOptions.options}" styleClass="textField" />
										<ui:button action="#{framework$ABMProvincia$ABMProvincia.btnSeleccionarPais_action}"
											binding="#{framework$ABMProvincia$ABMProvincia.btnSeleccionarPais}" escape="false" id="btnSeleccionarPais" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{framework$ABMProvincia$ABMProvincia.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{framework$ABMProvincia$ABMProvincia.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{framework$ABMProvincia$ABMProvincia.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{framework$ABMProvincia$ABMProvincia.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{framework$ABMProvincia$ABMProvincia.btnGuardar_action}"
											binding="#{framework$ABMProvincia$ABMProvincia.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMProvincia$ABMProvincia.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMProvincia$ABMProvincia.btnCancelar_action}"
											binding="#{framework$ABMProvincia$ABMProvincia.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMProvincia$ABMProvincia.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMProvincia$ABMProvincia.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMProvincia$ABMProvincia.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMProvincia$ABMProvincia.idSubSesion}" />
					<ui:script binding="#{framework$ABMProvincia$ABMProvincia.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
