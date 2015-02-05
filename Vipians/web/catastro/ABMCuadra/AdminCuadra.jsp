<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCuadra$AdminCuadra.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCuadra$AdminCuadra.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCuadra$AdminCuadra.head1}" id="head1" title="Administración de Cuadras">
				<ui:link binding="#{catastro$ABMCuadra$AdminCuadra.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCuadra$AdminCuadra.body1}" focus="form1:btnSeleccionarCalle" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCuadra$AdminCuadra.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="Administración de Cuadras" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{catastro$ABMCuadra$AdminCuadra.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label1}" for="ddCalle" id="label1" style="" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMCuadra$AdminCuadra.ddCalle}" id="ddCalle"
																items="#{catastro$ABMCuadra$AdminCuadra.ddCalleOptions.options}" styleClass="textField" />
															<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalle_action}"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:ddCalle" title="Limpiar"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarCalle}"
																action="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label2}" for="ddCalleComienza" id="label2" styleClass="label"
																text="Comienza en" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMCuadra$AdminCuadra.ddCalleComienza}" id="ddCalleComienza"
																items="#{catastro$ABMCuadra$AdminCuadra.ddCalleComienzaOptions.options}" styleClass="textField" />
															<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalleComienza_action}"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalleComienza}" escape="false" id="btnSeleccionarCalleComienza"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarComienza" reRender="form1:ddCalleComienza" title="Limpiar"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarComienza}"
																action="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarComienza_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label3}" for="ddCalleFinaliza" id="label3" styleClass="label"
																text="Termina en" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMCuadra$AdminCuadra.ddCalleFinaliza}" id="ddCalleTermina"
																items="#{catastro$ABMCuadra$AdminCuadra.ddCalleFinalizaOptions.options}" styleClass="textField" />
															<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalleFinaliza_action}"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarCalleFinaliza}" escape="false" id="btnSeleccionarCalleFinaliza"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTerminaEn" reRender="form1:ddCalleTermina" title="Limpiar"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarTerminaEn}"
																action="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarTerminaEn_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.lblManzana}" for="tfManzanaCuadra" id="lblManzana" styleClass="label"
																text="Manzana" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCuadra$AdminCuadra.tfManzana}" columns="39" disabled="true" id="tfManzanaCuadra"
																styleClass="textField" />
															<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarManzana_action}"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionarManzana}" escape="false" id="btnSeleccionarManzana" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarManzanaCuadra" reRender="form1:tfManzanaCuadra" title="Limpiar"
																binding="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarManzanaCuadra}"
																action="#{catastro$ABMCuadra$AdminCuadra.btnLimpiarManzanaCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 22px">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label4}" id="label4" styleClass="label" text="Tipo Numeración" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMCuadra$AdminCuadra.ddTipoNumeracion}" id="ddTipoNumeracion"
																items="#{catastro$ABMCuadra$AdminCuadra.ddTipoNumeracionDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label5}" for="tfNumeracionDesde" id="label5" styleClass="label"
																text="Desde Nº" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCuadra$AdminCuadra.tfNumeracionDesde}" columns="10"
																onKeyPress="return ValidarNum(event)" id="tfNumeracionDesde" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.label6}" for="tfNumeracionHasta" id="label6" styleClass="label"
																text="Hasta Nº" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCuadra$AdminCuadra.tfNumeracionHasta}" columns="10"
																onKeyPress="return ValidarNum(event)" id="tfNumeracionHasta" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMCuadra$AdminCuadra.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMCuadra$AdminCuadra.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{catastro$ABMCuadra$AdminCuadra.btnBuscar}"
												action="#{catastro$ABMCuadra$AdminCuadra.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMCuadra$AdminCuadra.btnReiniciar_action}"
												binding="#{catastro$ABMCuadra$AdminCuadra.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnCancelar_action}" binding="#{catastro$ABMCuadra$AdminCuadra.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMCuadra$AdminCuadra.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMCuadra$AdminCuadra.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCuadra$AdminCuadra.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionar_action}"
														binding="#{catastro$ABMCuadra$AdminCuadra.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnAgregar_action}" binding="#{catastro$ABMCuadra$AdminCuadra.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnModificar_action}"
														binding="#{catastro$ABMCuadra$AdminCuadra.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnEliminar_action}"
														binding="#{catastro$ABMCuadra$AdminCuadra.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnConsultar_action}"
														binding="#{catastro$ABMCuadra$AdminCuadra.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMCuadra$AdminCuadra.textoSeparador}" escape="false" id="staticText12" />
													<ui:button action="#{catastro$ABMCuadra$AdminCuadra.btnExportar_action}"
														binding="#{catastro$ABMCuadra$AdminCuadra.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMCuadra$AdminCuadra.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMCuadra$AdminCuadra.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMCuadra$AdminCuadra.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMCuadra$AdminCuadra.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarCalle').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMCuadra$AdminCuadra.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCuadra$AdminCuadra.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCuadra$AdminCuadra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCuadra$AdminCuadra.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCuadra$AdminCuadra.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMCuadra$AdminCuadra.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
