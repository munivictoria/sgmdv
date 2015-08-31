<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.lblTipo}" for="tfTipo" id="lblTipo"
														styleClass="label" text="Tipo" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.tfTipo}" disabled="true" columns="10"
														id="tfTipo" styleClass="textFieldDisabled" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.lblNombre}" for="tfNombre" id="lblNombre"
														styleClass="label" text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.tfNombre}" columns="40" id="tfNombre"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.lblReporte}" for="tfReporte" id="lblReporte"
														styleClass="label" text="Reporte" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.tfReporte}" columns="30" id="tfReporte"
														styleClass="textFieldDisabled" disabled="true" />
													<ui:button action="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnBuscarReporte_action}"
														binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnBuscarReporte}" escape="false" id="btnBuscarReporte"
														mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarReporte" reRender="form1:tfReporte" title="Limpiar"
														binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnLimpiarReporte}"
														action="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnLimpiarReporte_action}" styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:messageGroup binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.messageGroup}" id="messageGroup"
														styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnGuardar_action}"
											binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnCancelar_action}"
											binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>