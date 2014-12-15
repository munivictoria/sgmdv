<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMLocalidad$ABMLocalidad.page1}" id="page1">
			<ui:html binding="#{framework$ABMLocalidad$ABMLocalidad.html1}" id="html1">
			<ui:head binding="#{framework$ABMLocalidad$ABMLocalidad.head1}" id="head1">
				<ui:link binding="#{framework$ABMLocalidad$ABMLocalidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMLocalidad$ABMLocalidad.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMLocalidad$ABMLocalidad.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMLocalidad$ABMLocalidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMLocalidad$ABMLocalidad.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMLocalidad$ABMLocalidad.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMLocalidad$ABMLocalidad.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMLocalidad$ABMLocalidad.label5}" for="tfCodigoPostal" id="label5" styleClass="label"
											text="Código Postal" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMLocalidad$ABMLocalidad.tfCodigoPostal}" id="tfCodigoPostal" maxLength="4"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMLocalidad$ABMLocalidad.label2}" for="ddProvincia" id="label2" styleClass="label"
											text="Provincia" />
									</td>
									<td nowrap="true">
										<ui:dropDown binding="#{framework$ABMLocalidad$ABMLocalidad.ddProvincia}" id="ddProvincia"
											items="#{framework$ABMLocalidad$ABMLocalidad.ddProvinciaOptions.options}" styleClass="textField" />
										<ui:button action="#{framework$ABMLocalidad$ABMLocalidad.btnSeleccionarProvincia_action}"
											binding="#{framework$ABMLocalidad$ABMLocalidad.btnSeleccionarProvincia}" escape="false" id="btnSeleccionarProvincia" mini="true"
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
									<ui:label binding="#{framework$ABMLocalidad$ABMLocalidad.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{framework$ABMLocalidad$ABMLocalidad.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{framework$ABMLocalidad$ABMLocalidad.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{framework$ABMLocalidad$ABMLocalidad.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true" style="height: 21px">
										<ui:button action="#{framework$ABMLocalidad$ABMLocalidad.btnGuardar_action}"
											binding="#{framework$ABMLocalidad$ABMLocalidad.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMLocalidad$ABMLocalidad.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMLocalidad$ABMLocalidad.btnCancelar_action}"
											binding="#{framework$ABMLocalidad$ABMLocalidad.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMLocalidad$ABMLocalidad.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMLocalidad$ABMLocalidad.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMLocalidad$ABMLocalidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMLocalidad$ABMLocalidad.idSubSesion}" />
					<ui:script binding="#{framework$ABMLocalidad$ABMLocalidad.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
