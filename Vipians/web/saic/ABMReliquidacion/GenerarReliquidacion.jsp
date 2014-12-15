<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMReliquidacion$GenerarReliquidacion.page1}" id="page1">
			<ui:html binding="#{saic$ABMReliquidacion$GenerarReliquidacion.html1}" id="html1">
			<ui:head binding="#{saic$ABMReliquidacion$GenerarReliquidacion.head1}" id="head1" title="Generar Reliquidación">
				<ui:link binding="#{saic$ABMReliquidacion$GenerarReliquidacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					document.getElementById("form1:grpCargando").style.display = "none";

					function activarDesactivarTfValor() {
						var tabla = document.getElementById('form1:table2');

						for ( var i = 1; i < tabla.rows.length; ++i) {
							var tipoValor = tabla.rows[i].cells[2].childNodes[0].value;

							if (tipoValor == "ACTUAL") {
								tabla.rows[i].cells[3].childNodes[0].value = "";
								tabla.rows[i].cells[3].childNodes[0].disabled = true;
							} else if (tipoValor == "FIJO") {
								tabla.rows[i].cells[3].childNodes[0].disabled = false;
							}
						}
					}

					function activarDesactivarTfValorAlicuota() {
						var tabla = document.getElementById('form1:tableAlicuota');
						for ( var i = 1; i < tabla.rows.length; ++i) {
							var tipoValor = tabla.rows[i].cells[2].childNodes[0].value;

							if (tipoValor == "ACTUAL") {
								tabla.rows[i].cells[3].childNodes[0].value = "";
								tabla.rows[i].cells[3].childNodes[0].disabled = true;
							} else if (tipoValor == "FIJO") {
								tabla.rows[i].cells[3].childNodes[0].disabled = false;
							}
						}
					}
 
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$ABMReliquidacion$GenerarReliquidacion.body1}" focus="form1:btnSeleccionarDigesto" id="body1"
				onLoad="parent.footer.location.reload();Init();activarDesactivarTfValor();activarDesactivarTfValorAlicuota(); "
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMReliquidacion$GenerarReliquidacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMReliquidacion$GenerarReliquidacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.label2}" for="tfPersonaFisica" id="label2" styleClass="label"
											text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfPersona}" columns="40" disabled="true" id="tfPersona"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfFecha" id="lblFecha" styleClass="label" text="Fecha de Nuevo Vencimiento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfFecha}" columns="12" id="tfFecha" styleClass="textField"
											disabled="false" />
										<!--<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.stFecha}" escape="false" id="stFecha"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
										<!-- <ui:label id="lblAclaracion" styleClass="label3" text="Nota: Si desea mantener la fecha de vencimiento de la liquidación, deje el campo vacío o con la fecha que posee por defecto."/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblDigesto}" for="tfDigesto" id="lblDigesto" styleClass="label"
											text="Decreto, Ordenanza, Resolución" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfDigesto}" columns="60" disabled="true" id="tfDigesto"
											styleClass="textField" />
										<ui:button action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnSeleccionarDigesto_action}"
											binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnSeleccionarDigesto}" escape="false" id="btnSeleccionarDigesto"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigesto"
											binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnLimpiarDigesto}"
											action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblAplicarInteres}" for="cbAplicarInteres" id="lblAplicarInteres"
											styleClass="label" text="Aplicar intereses" />
									</td>
									<td>
										<ui:checkbox binding="#{saic$ABMReliquidacion$GenerarReliquidacion.cbAplicarInteres}" id="cbAplicarInteres" selected="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblLiquidacion" styleClass="label2" text="Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblFechaEmision}" for="tfFechaEmision" id="lblFechaEmision"
											styleClass="label" text="Fecha de Emisión" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfFechaEmision}" columns="12" id="tfFechaEmision"
											maxLength="4" styleClass="textField" disabled="true" />
										<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.stFechaEmision}" escape="false" id="stFechaEmision"
											styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblObligacion}" id="lblObligacion" styleClass="label"
											text="Obligación" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taObligacion}" columns="80" rows="3" id="taObligacion"
											disabled="true" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblFormula}" id="lblFormula" styleClass="label" text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taFormula}" id="taFormula" disabled="true"
											styleClass="textField" columns="80" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblParametroValor}" id="lblParametroValor" styleClass="label2"
											text="Parámetros - Valores" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taParametroValor}" id="taParametroValor" columns="80" rows="8"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblParametroValuadoAlicuota}" id="lblParametroValuadoAlicuota"
											styleClass="label2" text="Parámetros Alicuota - Valores" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taParametroValuadoAlicuota}" id="taParametroValuadoAlicuota"
											columns="80" rows="8" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblValorTasa}" id="lblValorTasa" for="tfValorTasa"
											styleClass="label2" text="Valor Puro de la Tasa" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfValorTasa}" id="tfValorTasa" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblModificadores}" id="lblModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taModificadores}" id="taModificadores" styleClass="textField"
											columns="80" rows="4" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblVencimientos}" id="lblVencimientos" styleClass="label2"
											text="Vencimiento: Nombre  --  Número  --  Fecha  --  Valor" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td>
										<ui:textArea binding="#{saic$ABMReliquidacion$GenerarReliquidacion.taVencimientos}" id="taVencimientos" styleClass="textField"
											columns="80" rows="3" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblInteres}" id="lblInteres" styleClass="label" text="Interes: " />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfInteres}" id="tfInteres" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblRecargos}" id="lblRecargos" styleClass="label" text="Recargo: " />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfRecargo}" id="tfRecargo" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lblMultas}" id="lblMultas" styleClass="label" text="Multas:    " />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfMultas}" id="tfMultas" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<div>Agregue a la tabla el/los parámetro/s y elija el valor que tomará/n para la Reliquidación de la Tasa.</div>
										<hr />
									</td>
								</tr>
								<tr align="left" nowrap="nowrap">
									<td colspan="2">
										<table>
											<tr>
												<td>
													<ui:listbox binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lbVariables}" id="lbVariables"
														items="#{saic$ABMReliquidacion$GenerarReliquidacion.lbVariablesDefaultOptions.options}" onDblClick="agregarAListBox(this.id);"
														rows="6" styleClass="textField" toolTip="Parámetros Actualmente Utilizados en la Fórmula de la Tasa" />
												</td>
												<td nowrap="nowrap">
													<a4j:commandButton action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnAddParametro_action}"
														binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnAddParametro}" id="btnAddParametro" styleClass="buttonAgregarAjax"
														reRender="table2" oncomplete="activarDesactivarTfValor();" />
													<a4j:commandButton action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnDelParametro_action}"
														binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnDelParametro}" id="btnDelParametro" styleClass="buttonRemoveAjax"
														reRender="table2" oncomplete="activarDesactivarTfValor();" />
												</td>
												<td>
													<ui:table augmentTitle="false" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.table2}" id="table2">
														<ui:tableRowGroup binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableRowGroup2}" id="tableRowGroup2"
															sourceData="#{saic$ABMReliquidacion$GenerarReliquidacion.ldpParametros}" sourceVar="currentRow2">
															<ui:tableColumn align="center" id="tableColumnSeleccion"
																binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumnSeleccion}" valign="middle" width="10">
																<ui:radioButton id="radioButton1" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.radioButton1}"
																	label="" name="buttonGroup" selected="#{saic$ABMReliquidacion$GenerarReliquidacion.RBSelected1}"
																	selectedValue="#{saic$ABMReliquidacion$GenerarReliquidacion.currentRow1}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumn1}" headerText="Nombre Parámetro"
																id="tableColumn1">
																<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.staticText2}" id="staticText2"
																	text="#{currentRow2.value['nombreParametro']}" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumn2}" headerText="Tipo Valor"
																id="tableColumn2">
																<ui:dropDown binding="#{saic$ABMReliquidacion$GenerarReliquidacion.ddTipoValor}" id="ddValor"
																	items="#{saic$ABMReliquidacion$GenerarReliquidacion.ddTipoValorOptions.options}" styleClass="textField"
																	selected="#{currentRow2.value['tipoValor']}" converter="EnumConverter" immediate="false"
																	onChange="activarDesactivarTfValor();" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumn3}" headerText="Valor"
																id="tableColumn3">
																<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.textField1}" id="textField1"
																	text="#{currentRow2.value['valor']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr align="left" nowrap="nowrap">
									<td colspan="2">
										<table>
											<tr>
												<td>
													<ui:listbox binding="#{saic$ABMReliquidacion$GenerarReliquidacion.lbVariablesAlicuota}" id="lbVariablesAlicuota"
														items="#{saic$ABMReliquidacion$GenerarReliquidacion.lbVariablesAlicuotaDefaultOptions.options}"
														onDblClick="agregarAListBox(this.id);" rows="6" styleClass="textField"
														toolTip="Parámetros Actualmente Utilizados en la Fórmula de la Tasa" />
												</td>
												<td nowrap="nowrap">
													<a4j:commandButton action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnAddParametroAlicuota_action}"
														binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnAddParametroAlicuota}" id="btnAddParametroAlicuota"
														styleClass="buttonAgregarAjax" reRender="form1:tableAlicuota" oncomplete="activarDesactivarTfValorAlicuota();"/>
													<a4j:commandButton action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnDelParametroAlicuota_action}"
														binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnDelParametroAlicuota}" id="btnDelParametroAlicuota"
														styleClass="buttonRemoveAjax" reRender="form1:tableAlicuota" oncomplete="activarDesactivarTfValorAlicuota();"/>
												</td>
												<td>
													<ui:table augmentTitle="false" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableAlicuota}" id="tableAlicuota">
														<ui:tableRowGroup binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableRowGroupAlicuota}" id="tableRowGroupAlicuota"
															sourceData="#{saic$ABMReliquidacion$GenerarReliquidacion.ldpNuevosParametrosFormulaAlicuota}" sourceVar="currentRowAlicuota"> 
															<ui:tableColumn align="center" id="tableColumnSeleccion"
																binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumnSeleccionAlicuota}" valign="middle" width="10">
																<ui:radioButton id="radioButtonAlicuota" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.radioButtonAlicuota}"
																	label="" name="buttonGroupAlicuota" selected="#{saic$ABMReliquidacion$GenerarReliquidacion.RBSelectedAlicuota}"
																	selectedValue="#{saic$ABMReliquidacion$GenerarReliquidacion.currentRowAlicuota}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumnAlicuota1}" headerText="Nombre Parámetro"
																id="tableColumnAlicuota1">
																<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.stParametroAlicuota}" id="stParametroAlicuota"
																	text="#{currentRowAlicuota.value['nombreParametro']}" />
															</ui:tableColumn>  
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumnAlicuota2}" headerText="Tipo Valor"
																id="tableColumnAlicuota2">
																<ui:dropDown binding="#{saic$ABMReliquidacion$GenerarReliquidacion.ddTipoValorAlicuota}" id="ddTipoValorAlicuota"
																	items="#{saic$ABMReliquidacion$GenerarReliquidacion.ddTipoValorAlicuotaOptions.options}" styleClass="textField"
																	selected="#{currentRowAlicuota.value['tipoValor']}" converter="EnumConverter" immediate="false"
																	onChange="activarDesactivarTfValorAlicuota();" /> 
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tableColumnAlicuota3}" headerText="Valor"
																id="tableColumnAlicuota3">
																<ui:textField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.tfValorAlicuota}" id="tfValorAlicuota"
																	text="#{currentRowAlicuota.value['valor']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$ABMReliquidacion$GenerarReliquidacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnGenerarReliquidacion_action}"
											binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnGenerarReliquidacion}" id="btnGenerarReliquidacion"
											onClick="mostrarProgreso();" styleClass="button" text="Generar Reliquidación" />
										<ui:staticText binding="#{saic$ABMReliquidacion$GenerarReliquidacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMReliquidacion$GenerarReliquidacion.btnCancelar_action}"
											binding="#{saic$ABMReliquidacion$GenerarReliquidacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMReliquidacion$GenerarReliquidacion.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMReliquidacion$GenerarReliquidacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMReliquidacion$GenerarReliquidacion.idSubSesion}" />
					<ui:script binding="#{saic$ABMReliquidacion$GenerarReliquidacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
