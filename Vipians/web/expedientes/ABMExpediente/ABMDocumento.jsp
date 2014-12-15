<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMExpediente$ABMDocumento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMExpediente$ABMDocumento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMExpediente$ABMDocumento.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMExpediente$ABMDocumento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMExpediente$ABMDocumento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMExpediente$ABMDocumento.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMExpediente$ABMDocumento.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMExpediente$ABMDocumento.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMExpediente$ABMDocumento.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblNroRegistro}" for="tfNroRegistro" id="lblNroRegistro"
											styleClass="label" text="Nro Registro" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMExpediente$ABMDocumento.tfNroRegistro}" columns="40" id="tfNroRegistro"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblFecha}" for="tfFecha" id="lblFecha" styleClass="label"
											text="Fecha Apertura" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMExpediente$ABMDocumento.tfFecha}" columns="20" id="tfFecha" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblUbicacion}" for="tfUbicacion" id="lblUbicacion" styleClass="label"
											text="Ubicación" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMExpediente$ABMDocumento.tfUbicacion}" columns="40" id="tfUbicacion" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblObservacion}" for="tfObservaciones" id="lblObservaciones"
											styleClass="label" text="Observaciones" />
									</td>
									<td>
										<ui:textArea binding="#{expedientes$ABMExpediente$ABMDocumento.taObservacion}" columns="60" id="taObservaciones"
											styleClass="textArea" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{expedientes$ABMExpediente$ABMDocumento.lblEstado}" for="ddEstado" id="label10" styleClass="label"
											text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{expedientes$ABMExpediente$ABMDocumento.ddEstado}"
											items="#{expedientes$ABMExpediente$ABMDocumento.ddEstadoDefaultOptions.options}" id="ddEstado" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{expedientes$ABMExpediente$ABMDocumento.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMExpediente$ABMDocumento.btnGuardar_action}"
											binding="#{expedientes$ABMExpediente$ABMDocumento.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMExpediente$ABMDocumento.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMExpediente$ABMDocumento.btnCancelar_action}"
											binding="#{expedientes$ABMExpediente$ABMDocumento.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMDocumento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMExpediente$ABMDocumento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMDocumento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMExpediente$ABMDocumento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMExpediente$ABMDocumento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
