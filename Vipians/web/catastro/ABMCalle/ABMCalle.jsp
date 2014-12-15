<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCalle$ABMCalle.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCalle$ABMCalle.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCalle$ABMCalle.head1}" id="head1">
				<ui:link binding="#{catastro$ABMCalle$ABMCalle.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCalle$ABMCalle.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCalle$ABMCalle.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMCalle$ABMCalle.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMCalle$ABMCalle.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 15px">
										<ui:label binding="#{catastro$ABMCalle$ABMCalle.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:textField binding="#{catastro$ABMCalle$ABMCalle.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMCalle$ABMCalle.label1}" for="tfTipoCalle" id="label1" styleClass="label" text="Tipo de Calle" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:textField binding="#{catastro$ABMCalle$ABMCalle.tfTipoCalle}" columns="40" disabled="true" id="tfTipoCalle"
											styleClass="textField" />
										<ui:button action="#{catastro$ABMCalle$ABMCalle.btnSeleccionarTipoCalle_action}"
											binding="#{catastro$ABMCalle$ABMCalle.btnSeleccionarTipoCalle}" escape="false" id="btnSeleccionarTipoCalle" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMCalle$ABMCalle.label4}" for="tfCodigo" id="label4" styleClass="label" text="Código" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMCalle$ABMCalle.tfCodigo}" onKeyPress="return ValidarNum(event)" id="tfCodigo"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap" />
									<td />
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{catastro$ABMCalle$ABMCalle.label5}" id="label5" styleClass="label5" text="Zonas" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table id="table1" width="50%">
											<ui:tableRowGroup id="tableRowGroup1" binding="#{catastro$ABMCalle$ABMCalle.tableRowGroup1}"
												sourceData="#{catastro$ABMCalle$ABMCalle.ldpZonas}" sourceVar="currentRowZona">
												<ui:tableColumn align="center" id="tableColumn1" binding="#{catastro$ABMCalle$ABMCalle.tableColumn1}" valign="middle" width="10">
													<ui:radioButton id="radioButton1" binding="#{catastro$ABMCalle$ABMCalle.radioButton1}" label="" name="buttonGroup"
														selected="#{catastro$ABMCalle$ABMCalle.RBSelected1}" selectedValue="#{catastro$ABMCalle$ABMCalle.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn2" headerText="Nombre" sort="zona" binding="#{catastro$ABMCalle$ABMCalle.tableColumn2}">
													<ui:staticText id="staticText1" binding="#{catastro$ABMCalle$ABMCalle.staticText1}" text="#{currentRowZona.value['zona']}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn3" headerText="Zonificación" sort="#{currentRowZona.value.zona.zonificacion.nombre}"
													binding="#{catastro$ABMCalle$ABMCalle.tableColumn3}">
													<ui:staticText id="staticText2" binding="#{catastro$ABMCalle$ABMCalle.staticText2}"
														text="#{currentRowZona.value.zona.zonificacion.nombre}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel1" binding="#{catastro$ABMCalle$ABMCalle.groupPanel1}">
													<ui:button id="btnAgregarZona" styleClass="button" text="Agregar" toolTip="Agregar Zona"
														binding="#{catastro$ABMCalle$ABMCalle.btnAgregarZona}" action="#{catastro$ABMCalle$ABMCalle.btnAgregarZona_action}" />
													<a4j:commandButton id="btnQuitarZona" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');" binding="#{catastro$ABMCalle$ABMCalle.btnQuitarZona}"
														action="#{catastro$ABMCalle$ABMCalle.btnQuitarZona_action}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
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
									<ui:label binding="#{catastro$ABMCalle$ABMCalle.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMCalle$ABMCalle.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMCalle$ABMCalle.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMCalle$ABMCalle.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMCalle$ABMCalle.btnGuardar_action}" binding="#{catastro$ABMCalle$ABMCalle.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMCalle$ABMCalle.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMCalle$ABMCalle.btnCancelar_action}" binding="#{catastro$ABMCalle$ABMCalle.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMCalle$ABMCalle.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMCalle$ABMCalle.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCalle$ABMCalle.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCalle$ABMCalle.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCalle$ABMCalle.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
