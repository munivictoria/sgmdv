<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCuadra$ABMCuadra.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCuadra$ABMCuadra.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCuadra$ABMCuadra.head1}" id="head1">
				<ui:link binding="#{catastro$ABMCuadra$ABMCuadra.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCuadra$ABMCuadra.body1}" focus="form1:ddCalle" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')" >
				<ui:form binding="#{catastro$ABMCuadra$ABMCuadra.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMCuadra$ABMCuadra.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMCuadra$ABMCuadra.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label15}" for="ddCalle" id="label15" styleClass="label" text="Calle / Ruta" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:dropDown binding="#{catastro$ABMCuadra$ABMCuadra.ddCalle}" id="ddCalle"
											items="#{catastro$ABMCuadra$ABMCuadra.ddCalleOptions.options}" styleClass="textField" />
										<ui:button action="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalle_action}"
											binding="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalle" reRender="form1:ddCalle" title="Limpiar" binding="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalle}"
											action="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label24}" for="ddCalleComienza" id="label24" styleClass="label"
											text="Comienza en" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:dropDown binding="#{catastro$ABMCuadra$ABMCuadra.ddCalleComienza}" id="ddCalleComienza"
											items="#{catastro$ABMCuadra$ABMCuadra.ddCalleComienzaOptions.options}" styleClass="textField" />
										<ui:button action="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalleComienza_action}"
											binding="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalleComienza}" escape="false" id="btnSeleccionarCalleComienza" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalleComienza" reRender="form1:ddCalleComienza" title="Limpiar"
											binding="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalleComienza}"
											action="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalleComienza_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label22}" for="ddCalleFinaliza" id="label22" styleClass="label"
											text="Finaliza en" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:dropDown binding="#{catastro$ABMCuadra$ABMCuadra.ddCalleFinaliza}" id="ddCalleFinaliza"
											items="#{catastro$ABMCuadra$ABMCuadra.ddCalleFinalizaOptions.options}" styleClass="textField" />
										<ui:button action="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalleFinaliza_action}"
											binding="#{catastro$ABMCuadra$ABMCuadra.btnSeleccionarCalleFinaliza}" escape="false" id="btnSeleccionarCalleFinaliza" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalleFinaliza" reRender="form1:ddCalleFinaliza" title="Limpiar"
											binding="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalleFinaliza}"
											action="#{catastro$ABMCuadra$ABMCuadra.btnLimpiarCalleFinaliza_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label17}" for="ddTipoNumeracion" id="label17" styleClass="label"
											text="Tipo Numeración" />
									</td>
									<td colspan="3">
										<ui:dropDown binding="#{catastro$ABMCuadra$ABMCuadra.ddTipoNumeracion}" id="ddTipoNumeracion"
											items="#{catastro$ABMCuadra$ABMCuadra.ddTipoNumeracionDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label18}" for="tfNumeracionDesde" id="label18" styleClass="label"
											text="Desde Nº" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMCuadra$ABMCuadra.tfNumeracionDesde}" onKeyPress="return ValidarNum(event,this)" columns="10"
											id="tfNumeracionDesde" styleClass="textField" />
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label1}" for="tfNumeracionHasta" id="label1" styleClass="label" text="Hasta Nº" />
										<ui:textField binding="#{catastro$ABMCuadra$ABMCuadra.tfNumeracionHasta}" onKeyPress="return ValidarNum(event,this)" columns="10"
											id="tfNumeracionHasta" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label20}" for="tfMetrosLineales" id="label20" styleClass="label"
											text="Metros Lineales" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMCuadra$ABMCuadra.tfMetrosLineales}" onKeyPress="return ValidarFloat(event,this)" columns="10"
											id="tfMetrosLineales" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true"></td>
									<td></td>
									<td align="right" nowrap="true"></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.label4}" id="label5" styleClass="label4" text="Zonas" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table id="table1" width="50%">
											<ui:tableRowGroup id="tableRowGroup1" binding="#{catastro$ABMCuadra$ABMCuadra.tableRowGroup1}"
												sourceData="#{catastro$ABMCuadra$ABMCuadra.ldpZonas}" sourceVar="currentRowZona">
												<ui:tableColumn align="center" id="tableColumn1" binding="#{catastro$ABMCuadra$ABMCuadra.tableColumn1}" valign="middle"
													width="10">
													<ui:radioButton id="radioButton1" binding="#{catastro$ABMCuadra$ABMCuadra.radioButton1}" label="" name="buttonGroup"
														selected="#{catastro$ABMCuadra$ABMCuadra.RBSelected1}" selectedValue="#{catastro$ABMCuadra$ABMCuadra.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn2" headerText="Nombre" sort="nombre" binding="#{catastro$ABMCuadra$ABMCuadra.tableColumn2}">
													<ui:staticText id="staticText1" binding="#{catastro$ABMCuadra$ABMCuadra.staticText1}" text="#{currentRowZona.value['zona']}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn3" headerText="Zonificación" sort="#{currentRowZona.value.zona.zonificacion.nombre}"
													binding="#{catastro$ABMCuadra$ABMCuadra.tableColumn3}">
													<ui:staticText id="staticText2" binding="#{catastro$ABMCuadra$ABMCuadra.staticText2}"
														text="#{currentRowZona.value['zona'].zonificacion.nombre}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel1" binding="#{catastro$ABMCuadra$ABMCuadra.groupPanel1}">
													<ui:button id="btnAgregarZona" styleClass="button" text="Agregar" toolTip="Agregar Zona"
														binding="#{catastro$ABMCuadra$ABMCuadra.btnAgregarZona}" action="#{catastro$ABMCuadra$ABMCuadra.btnAgregarZona_action}" />
													<a4j:commandButton id="btnQuitarZona" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');" binding="#{catastro$ABMCuadra$ABMCuadra.btnQuitarZona}"
														action="#{catastro$ABMCuadra$ABMCuadra.btnQuitarZona_action}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</tbody>
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
												<ui:panelGroup binding="#{catastro$ABMCuadra$ABMCuadra.panelAtributoDinamico}" id="panelAtributoDinamico">
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
									<ui:label binding="#{catastro$ABMCuadra$ABMCuadra.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMCuadra$ABMCuadra.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMCuadra$ABMCuadra.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMCuadra$ABMCuadra.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="true" style="height: 21px">
										<ui:button action="#{catastro$ABMCuadra$ABMCuadra.btnGuardar_action}" binding="#{catastro$ABMCuadra$ABMCuadra.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMCuadra$ABMCuadra.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMCuadra$ABMCuadra.btnCancelar_action}" binding="#{catastro$ABMCuadra$ABMCuadra.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMCuadra$ABMCuadra.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMCuadra$ABMCuadra.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCuadra$ABMCuadra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCuadra$ABMCuadra.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCuadra$ABMCuadra.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
