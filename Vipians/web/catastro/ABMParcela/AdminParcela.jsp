<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMParcela$AdminParcela.page1}" id="page1">
			<ui:html binding="#{catastro$ABMParcela$AdminParcela.html1}" id="html1">
			<ui:head binding="#{catastro$ABMParcela$AdminParcela.head1}" id="head1" title="Administración de Parcelas">
				<ui:link binding="#{catastro$ABMParcela$AdminParcela.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
				    	var nombreBean = "catastro$ABMParcela$AdminParcela";
				    
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
							calendarioEnTextField("#form1:tfFechaPlanoMensura");
							calendarioEnTextField("#form1:tfFechaPlanoConstruccion");
						}

						function focusearTfPersonaSeleccionada() {
							$("#form1\\:tfPersonaSeleccionada").focus();
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{catastro$ABMParcela$AdminParcela.body1}" id="body1" focus="form1:btnSeleccionarCuadra"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMParcela$AdminParcela.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMParcela$AdminParcela.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
										<td>
											<ui:panelGroup id="pgRadioButtons">
												<ui:radioButtonGroup styleClass="optgroup" binding="#{catastro$ABMParcela$AdminParcela.rbgTipoBusqueda}"
													items="#{catastro$ABMParcela$AdminParcela.ddTipoBusquedaDefaultOptions.options}" id="rbgTipoBusqueda" columns="2"
													labelLevel="2" onClick="mostrar()">
												</ui:radioButtonGroup>
											</ui:panelGroup>
										</td>
										<td>
											<ui:panelGroup id="pgChkBox">
												<ui:checkbox binding="#{catastro$ABMParcela$AdminParcela.tablaBusquedaLogs.ckbBuscarPorLogs}" />
											</ui:panelGroup>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr id="trCampos">
										<td colspan="4" align="center">
											<ui:panelGroup id="pgParametros1">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.lblNroParcela}" for="tfNroParcela" id="lblNroParcela"
																styleClass="label" text="Nº de Parcela" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNroParcela}" columns="10" id="tfNroParcela"
																styleClass="textField" />
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label3}" for="tfNumeroRegistro" id="label3" styleClass="label"
																text="Nº de Registro" style="margin-left:10px" />
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNumeroRegistro}" columns="10" id="tfNumeroRegistro"
																styleClass="textField" style="margin-left:10px" />
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label4}" for="tfNroPartidaProvincial" id="label4" styleClass="label"
																text="Nº de Partida Provincial" style="margin-left:10px" />
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNroPartidaProvincial}" columns="10" id="tfNroPartidaProvincial"
																styleClass="textField" style="margin-left:10px" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label29}" for="tfPersonaSeleccionada" id="label29" styleClass="label"
																text="Propietario" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfPersonaSeleccionada}" columns="45" id="tfPersonaSeleccionada"
																styleClass="#{catastro$ABMParcela$AdminParcela.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{catastro$ABMParcela$AdminParcela.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarPersonaFisica_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica"
																mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarPersonaJuridica_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{catastro$ABMParcela$AdminParcela.btnLimpiarPersona}"
																action="#{catastro$ABMParcela$AdminParcela.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.labelCalle}" for="ddCalle" id="labelCalle" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddCalle}" id="ddCalle"
																items="#{catastro$ABMParcela$AdminParcela.ddCalleOptions.options}" styleClass="textField"
																valueChangeListener="#{catastro$ABMParcela$AdminParcela.valueChangeEvent(event)}" onChange="this.form.submit()" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarCalle_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarCalle}" escape="false" id="btnSeleccionarParcela" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:ddCalle, form1:ddCalleComienza, form1:ddCalleTermina" title="Limpiar"
																binding="#{catastro$ABMParcela$AdminParcela.btnLimpiarCalle}"
																action="#{catastro$ABMParcela$AdminParcela.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.labelDomicilio}" for="tfNumeroDomicilio" id="labelDomicilio"
																styleClass="label" text="Nº de Domicilio" style="margin-left:10px" />
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfDomicilio}" columns="10" id="tfNumeroDomicilio"
																styleClass="textField" style="margin-left:10px" />
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap" align="right">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label7}" for="ddCalleComienza" id="label7" style="margin-left: 15px"
																styleClass="label" text="Calle Comienza" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddCalleComienza}" id="ddCalleComienza"
																items="#{catastro$ABMParcela$AdminParcela.ddCalleComienzaOptions.options}" styleClass="textField"
																valueChangeListener="#{catastro$ABMParcela$AdminParcela.valueChangeEventDdCalleComienza(event)}"
																onChange="this.form.submit()" />
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label8}" for="ddCalleTermina" id="label8" styleClass="label"
																text="Calle Termina" />
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddCalleFinaliza}" id="ddCalleTermina"
																items="#{catastro$ABMParcela$AdminParcela.ddCalleFinalizaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr id="trCuadra" style="display: none">
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label1}" for="tfCuadra" id="label1" style="" styleClass="label"
																text="Cuadra" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfCuadra}" columns="45" disabled="true" id="tfCuadra"
																styleClass="textField" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarCuadra_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarCuadra}" escape="false" id="btnSeleccionarCuadra" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra" title="Limpiar"
																binding="#{catastro$ABMParcela$AdminParcela.btnLimpiarCuadra}"
																action="#{catastro$ABMParcela$AdminParcela.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr id="trManzana" style="display: none">
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label2}" for="tfManzana" id="label2" styleClass="label" text="Manzana" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfManzana}" columns="45" disabled="true" id="tfManzana"
																styleClass="textField" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarManzana_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarManzana}" escape="false" id="btnSeleccionarManzana" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarManzana" reRender="form1:tfManzana" title="Limpiar"
																binding="#{catastro$ABMParcela$AdminParcela.btnLimpiarManzana}"
																action="#{catastro$ABMParcela$AdminParcela.btnLimpiarManzana_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr id="trZona" style="display: none">
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.labelZona}" for="ddZona" id="labelZona" styleClass="label" text="Zona" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddZona}" id="ddZona"
																items="#{catastro$ABMParcela$AdminParcela.ddZonaOptions.options}" styleClass="textField" />
															<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionarZona_action}"
																binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionarZona}" escape="false" id="btnSeleccionarZona" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarZona" title="Limpiar" reRender="form1:ddZona"
																binding="#{catastro$ABMParcela$AdminParcela.btnLimpiarZona}"
																action="#{catastro$ABMParcela$AdminParcela.btnLimpiarZona_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr id="trEstado" style="display: none">
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.lbEstado}" id="lbEstado" styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{catastro$ABMParcela$AdminParcela.ddEstadoOptions.options}" />
														</td>
													</tr>
													<tr>
														<td>
															<br></br>
														</td>
													</tr>
													<tr id="trLabel" style="display: none">
														<td colspan="4">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label20}" id="label20" styleClass="label2"
																text="Nomenclatura Catastral" />
														</td>
													</tr>
													<tr id="trOpcionesAvanzadas" style="display: none">
														<td colspan="4">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																<tr>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label21}" id="label21" styleClass="label" text="Dpto." />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label22}" id="label22" styleClass="label" text="Pedania" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label23}" id="label23" styleClass="label" text="Circun." />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label24}" id="label24" styleClass="label" text="Distrito" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label27}" id="label27" styleClass="label" text="Sub-Dis." />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label28}" id="label28" styleClass="label" text="Sección/Poligono" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label30}" id="label30" styleClass="label" text="Quinta" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label31}" id="label31" styleClass="label" text="Chacra o Fracción" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label35}" id="label35" styleClass="label" text="Lote" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfDpto}" columns="4" id="tfDpto" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfPedania}" columns="4" id="tfPedania" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfCircunscripcion}" columns="4" id="tfCircunscripcion"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfDistrito}" columns="4" id="tfDistrito" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfSubDistrito}" columns="4" id="tfSubDistrito"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfSeccionPoligono}" columns="10" id="tfSeccionPoligono"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfQuinta}" columns="4" id="tfQuinta" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfChacraFraccion}" columns="10" id="tfChacraFraccion"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfLote}" columns="10" id="tfLote" styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr id="trOpcionesAvanzadas2" style="display: none">
														<td colspan="4">
															<table>
																<tbody>
																	<tr rowspan="2">
																		<td colspan="3">
																			<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
																			<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																				<tr>
																					<td colspan="4">
																						<ui:panelGroup binding="#{catastro$ABMParcela$AdminParcela.panelAtributoDinamico}" id="panelAtributoDinamico">
																							<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																						</ui:panelGroup>
																					</td>
																				</tr>
																			</table>
																		</td>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$AdminParcela.label36}" for="tfNumeroMatricula" id="label36" styleClass="label"
																				text="Nº de Matrícula" />
																		</td>
																		<td nowrap="nowrap">
																			<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNumeroMatricula}" columns="10" id="tfNumeroMatricula"
																				styleClass="textField" />
																		</td>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$AdminParcela.label37}" for="tfNumeroCuenta" id="label37" styleClass="label"
																				text="Nº de Cuenta" />
																		</td>
																		<td nowrap="nowrap">
																			<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNumeroCuenta}" columns="10" id="tfNumeroCuenta"
																				styleClass="textField" />
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
									<tr id="trPlanoMensura" style="display: none">
										<td colspan="4" align="center">
											<ui:panelGroup id="pgParametros2">
												<table>
													<tr>
														<td align="center" nowrap="nowrap" colspan="2">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label38}" id="label38" styleClass="label42" text="Números" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap" colspan="2">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 50%;">
																<tr>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label39}" for="tfNroPlanoMensura" id="label39" styleClass="label"
																			text="Plano" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNroPlanoMensura}" columns="10" id="tfNroPlanoMensura"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label40}" id="label40" styleClass="label" text="Folio" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfFolioPlanoMensura}" columns="10" id="tfFolioPlanoMensura"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label41}" id="label41" styleClass="label" text="Tomo" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfTomoPlanoMensura}" columns="10" id="tfTomoPlanoMensura"
																			styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.lbFechaPlanoMensura}" id="lbFechaPlanoMensura" styleClass="label"
																text="Fecha de Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfFechaPlanoMensura}" id="tfFechaPlanoMensura"
																styleClass="textField" columns="10" />
															<!--<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.staticText12}" escape="false"
                                                                           id="staticText12" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label42}" id="label42" style="" styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddEstadoPlanoMensura}" id="ddEstadoPlanoMensura"
																items="#{catastro$ABMParcela$AdminParcela.ddEstadoPlanoMensuraOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{catastro$ABMParcela$AdminParcela.panelAtributoDinamicoPlanoMensura}"
																			id="panelAtributoDinamicoPlanoMensura">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
									<tr id="trPlanoConstruccion" style="display: none">
										<td colspan="4" align="center">
											<ui:panelGroup id="pgParametros3">
												<table>
													<tr>
														<td align="center" nowrap="nowrap" colspan="2">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label43}" id="label43" styleClass="label47" text="Números" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap" colspan="2">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 50%;">
																<tr>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label44}" for="tfNroPlanoConstruccion" id="label44"
																			styleClass="label" text="Plano" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfNroPlanoConstruccion}" columns="10"
																			id="tfNroPlanoConstruccion" styleClass="textField" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label45}" id="label45" styleClass="label" text="Folio" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfFolioPlanoConstruccion}" columns="10"
																			id="tfFolioPlanoConstruccion" styleClass="textField" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$AdminParcela.label46}" id="label46" styleClass="label" text="Tomo" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfTomoPlanoConstruccion}" columns="10"
																			id="tfTomoPlanoConstruccion" styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.lbFechaPlanoConstruccion}" id="lbFechaPlanoConstruccion"
																styleClass="label" text="Fecha de Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMParcela$AdminParcela.tfFechaPlanoConstruccion}" id="tfFechaPlanoConstruccion"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMParcela$AdminParcela.label47}" id="label47" style="" styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{catastro$ABMParcela$AdminParcela.ddEstadoPlanoConstruccion}" id="ddEstadoPlanoConstruccion"
																items="#{catastro$ABMParcela$AdminParcela.ddEstadoPlanoConstruccionOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{catastro$ABMParcela$AdminParcela.panelAtributoDinamicoPlanoConstruccion}"
																			id="panelAtributoDinamicoPlanoConstruccion">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
									<tr id="trTituloPropiedad" style="display: none">
										<td colspan="4" align="center">
											<ui:panelGroup id="pgParametros4">
												<table>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{catastro$ABMParcela$AdminParcela.panelAtributoDinamicoTituloPropiedad}"
																			id="panelAtributoDinamicoTituloPropiedad">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<ui:table binding="#{catastro$ABMParcela$AdminParcela.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="4">
											<a4j:commandButton binding="#{catastro$ABMParcela$AdminParcela.btnBuscar}"
												action="#{catastro$ABMParcela$AdminParcela.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMParcela$AdminParcela.btnReiniciar_action}"
												binding="#{catastro$ABMParcela$AdminParcela.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												oncomplete="mostrar(); cargarComportamientoJQuery();"
												reRender="form1:pgParametros1, form1:pgParametros2, form1:pgParametros3, form1:pgParametros4, form1:pgRadioButtons, form1:pgChkBox, form1:table1, form1:tablaBusquedaLogs ,form1:stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMParcela$AdminParcela.btnCancelar_action}"
												binding="#{catastro$ABMParcela$AdminParcela.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMParcela$AdminParcela.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMParcela$AdminParcela.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMParcela$AdminParcela.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSeleccionar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnAgregar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnModificar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnEliminar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnSubdividirSubparcela_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnSubdividirSubparcela}" id="btnSubdividirSubparcela" styleClass="button"
														text="Subdividir" disabled="true" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnVolantesCatastrales_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnVolantesCatastrales}" id="btnVolantesCatastrales" styleClass="button"
														onClick="return (confirm(&quot;¿Está seguro que desea generar un Volante Catastral?&quot;));" text="Generar Volante Catastral" />
													<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnConsultar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMParcela$AdminParcela.textoSeparador}" escape="false" id="staticText10"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnImprimirReporte_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" />
													<ui:button action="#{catastro$ABMParcela$AdminParcela.btnExportar_action}"
														binding="#{catastro$ABMParcela$AdminParcela.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMParcela$AdminParcela.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMParcela$AdminParcela.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMParcela$AdminParcela.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarCuadra')
								.focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMParcela$AdminParcela.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMParcela$AdminParcela.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMParcela$AdminParcela.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMParcela$AdminParcela.idSubSesion}" />
					<ui:script binding="#{catastro$ABMParcela$AdminParcela.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMParcela$AdminParcela.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
			<script>
				window.onLoad = inicializarBotones();

				function inicializarBotones() {
					radio0 = document.getElementById('form1:rbgTipoBusqueda_0');
					radio1 = document.getElementById('form1:rbgTipoBusqueda_1');
					radio2 = document.getElementById('form1:rbgTipoBusqueda_2');
					radio4 = document.getElementById('form1:rbgTipoBusqueda_4');

					if (!radio0.checked &amp;&amp; !radio1.checked) {
						radio0.checked = true;
					}

					if (radio1.checked) {
						var ver = "";
						trLabel = document.getElementById('trLabel');
						trOpcionesAvanzadas = document.getElementById('trOpcionesAvanzadas');
						trOpcionesAvanzadas2 = document.getElementById('trOpcionesAvanzadas2');
						trCuadra = document.getElementById('trCuadra');
						trManzana = document.getElementById('trManzana');
						trLabel.style.display = ver;
						trOpcionesAvanzadas.style.display = ver;
						trOpcionesAvanzadas2.style.display = ver;
						trCuadra.style.display = ver;
						trManzana.style.display = ver;
						trZona.style.display = ver;
						trEstado.style.display = ver;
					}
				}

				function mostrar() {
					ver = "none";
					radio0 = document.getElementById('form1:rbgTipoBusqueda_0');
					radio1 = document.getElementById('form1:rbgTipoBusqueda_1');
					radio2 = document.getElementById('form1:rbgTipoBusqueda_2');
					radio3 = document.getElementById('form1:rbgTipoBusqueda_3');
					radio4 = document.getElementById('form1:rbgTipoBusqueda_4');

					if (radio0.checked || radio1.checked) {
						if (radio1.checked) {
							ver = "";
						}
						trLabel = document.getElementById('trLabel');
						trOpcionesAvanzadas = document.getElementById('trOpcionesAvanzadas');
						trOpcionesAvanzadas2 = document.getElementById('trOpcionesAvanzadas2');
						trCuadra = document.getElementById('trCuadra');
						trManzana = document.getElementById('trManzana');
						trZona = document.getElementById('trZona');
						trEstado = document.getElementById('trEstado');
						trLabel.style.display = ver;
						trOpcionesAvanzadas.style.display = ver;
						trOpcionesAvanzadas2.style.display = ver;
						trCuadra.style.display = ver;
						trManzana.style.display = ver;
						trZona.style.display = ver;
						trEstado.style.display = ver;

						trCampos = document.getElementById('trCampos');
						trPlanoMensura = document.getElementById('trPlanoMensura');
						trPlanoConstruccion = document.getElementById('trPlanoConstruccion');
						trPlanoMensura.style.display = "none";
						trPlanoConstruccion.style.display = "none";
						trCampos.style.display = "";
						trTituloPropiedad.style.display = "none";
					}

					if (radio2.checked) {
						trCampos = document.getElementById('trCampos');
						trPlanoMensura = document
								.getElementById('trPlanoMensura');
						trPlanoConstruccion = document
								.getElementById('trPlanoConstruccion');
						trCampos.style.display = "none";
						trPlanoConstruccion.style.display = "none";
						trPlanoMensura.style.display = "";
						trTituloPropiedad.style.display = "none";
					}
					if (radio3.checked) {
						trCampos = document.getElementById('trCampos');
						trPlanoMensura = document.getElementById('trPlanoMensura');
						trPlanoConstruccion = document.getElementById('trPlanoConstruccion');
						trCampos.style.display = "none";
						trPlanoMensura.style.display = "none";
						trPlanoConstruccion.style.display = "";
						trTituloPropiedad.style.display = "none";
					}
					if (radio4.checked) {
						trCampos = document.getElementById('trCampos');
						trPlanoMensura = document.getElementById('trPlanoMensura');
						trPlanoConstruccion = document.getElementById('trPlanoConstruccion');
						trCampos.style.display = "none";
						trPlanoMensura.style.display = "none";
						trPlanoConstruccion.style.display = "none";
						trCampos = document.getElementById('trCampos');
						trTituloPropiedad = document.getElementById('trTituloPropiedad');
						trCampos.style.display = "none";
						trTituloPropiedad.style.display = "none";
						trTituloPropiedad.style.display = "";
					}
				}
			</script>
		</ui:page>
	</f:view>
</jsp:root>
