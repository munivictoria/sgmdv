<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMSecretaria$ABMSecretaria.page1}" id="page1">
			<ui:html binding="#{framework$ABMSecretaria$ABMSecretaria.html1}" id="html1">
			<ui:head binding="#{framework$ABMSecretaria$ABMSecretaria.head1}" id="head1">
				<ui:link binding="#{framework$ABMSecretaria$ABMSecretaria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMSecretaria$ABMSecretaria.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" 
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMSecretaria$ABMSecretaria.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMSecretaria$ABMSecretaria.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMSecretaria$ABMSecretaria.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMSecretaria$ABMSecretaria.lbNombre}" for="tfNombre" id="lbNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMSecretaria$ABMSecretaria.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMSecretaria$ABMSecretaria.lbCodigoImputacion}" for="tfCodigoImputacion" id="lbCodigoImputacion"
											styleClass="label" text="Código de Imputación" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMSecretaria$ABMSecretaria.tfCodigoImputacion}" columns="20" id="tfCodigoImputacion"
											styleClass="textField" />
										<ui:staticText text="(admite varios valores separados por coma)" id="stAclaracionSecretaria"
											style="color: grey; font-size: 11px; margin-left: 5px; " />
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="groupPanel" binding="#{framework$ABMSecretaria$ABMSecretaria.groupPanel}">
											<tr>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{framework$ABMSecretaria$ABMSecretaria.lbArea}" id="lbArea" styleClass="label2" text="Áreas" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:table id="table1" width="50%" binding="#{framework$ABMSecretaria$ABMSecretaria.tablaArea}">
														<ui:tableRowGroup id="tableRowGroup1" binding="#{framework$ABMSecretaria$ABMSecretaria.tableRowGroup1}"
															sourceData="#{framework$ABMSecretaria$ABMSecretaria.ldpAreas}" sourceVar="currentRowArea">
															<ui:tableColumn id="tableColumn2" headerText="Nombre" sort="nombre"
																binding="#{framework$ABMSecretaria$ABMSecretaria.tableColumn1}">
																<ui:staticText id="staticText1" binding="#{framework$ABMSecretaria$ABMSecretaria.staticText1}"
																	text="#{currentRowArea.value['nombre']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMSecretaria$ABMSecretaria.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMSecretaria$ABMSecretaria.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMSecretaria$ABMSecretaria.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMSecretaria$ABMSecretaria.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMSecretaria$ABMSecretaria.btnGuardar_action}"
											binding="#{framework$ABMSecretaria$ABMSecretaria.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMSecretaria$ABMSecretaria.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMSecretaria$ABMSecretaria.btnCancelar_action}"
											binding="#{framework$ABMSecretaria$ABMSecretaria.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMSecretaria$ABMSecretaria.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMSecretaria$ABMSecretaria.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMSecretaria$ABMSecretaria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMSecretaria$ABMSecretaria.idSubSesion}" />
					<ui:script binding="#{framework$ABMSecretaria$ABMSecretaria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
