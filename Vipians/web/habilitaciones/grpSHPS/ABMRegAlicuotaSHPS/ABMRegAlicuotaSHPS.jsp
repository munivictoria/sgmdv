<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.lblCodigoCiiu}" for="tfCodigoCiiu"
											id="lblCodigoCiiu" styleClass="label" text="Codigo Ciiu" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.tfCodigoCiiu}" columns="40" disabled="true"
											id="tfCodigoCiiu" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnSeleccionarCodigoCiiu_action}"
											binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnSeleccionarCodigoCiiu}" escape="false"
											id="btnSeleccionarCodigoCiiu" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.label5}" for="taNombre" id="label5"
											styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.taNombre}" columns="40" id="taNombre"
											rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.label1}" for="ddTipo" id="label1"
											styleClass="label" text="Tipo Alícuota" />
									</td>
									<td>
										<ui:dropDown binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.ddTipo}" id="ddTipo"
											items="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.ddTipoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.label2}" for="tfValor" id="label2"
											styleClass="label" text="Alícuota" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.tfValor}" columns="10" id="tfValor"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.label3}" for="tfMinimo" id="label3"
											styleClass="label" text="Mínimo" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.tfMinimo}" columns="10" id="tfMinimo"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
