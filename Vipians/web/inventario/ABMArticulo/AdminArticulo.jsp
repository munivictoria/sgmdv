<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{inventario$ABMArticulo$AdminArticulo.page1}" id="page1">
			<ui:html binding="#{inventario$ABMArticulo$AdminArticulo.html1}" id="html1">
			<ui:head binding="#{inventario$ABMArticulo$AdminArticulo.head1}" id="head1" title="Administración de Artículos">
				<ui:link binding="#{inventario$ABMArticulo$AdminArticulo.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaCompra");
						calendarioEnTextField("#form1:tfFechaEntradaServicio");
					}
					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{inventario$ABMArticulo$AdminArticulo.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload(); Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{inventario$ABMArticulo$AdminArticulo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{inventario$ABMArticulo$AdminArticulo.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{inventario$ABMArticulo$AdminArticulo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label1}" id="label1" style="" styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{inventario$ABMArticulo$AdminArticulo.tfCodigo}" columns="40" id="tfCodigo" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label2}" id="label2" style="" styleClass="label" text="Nombre" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMArticulo$AdminArticulo.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label3}" id="label3" style="" styleClass="label"
																text="Fecha Compra" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMArticulo$AdminArticulo.tfFechaCompra}" columns="40" id="tfFechaCompra"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.staticText1}" escape="false" id="staticText1"
                                                               styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/> -->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label4}" id="label4" style="" styleClass="label"
																text="Fecha de Entrada en Servicio" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMArticulo$AdminArticulo.tfFechaEntradaServicio}" columns="40"
																id="tfFechaEntradaServicio" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.staticText5}" escape="false" id="staticText5"
                                                               styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/> -->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label6}" id="label6" style="" styleClass="label" text="Área" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMArticulo$AdminArticulo.tfArea}" columns="40" id="tfArea" styleClass="textField"
																disabled="true" />
															<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnSeleccionarArea_action}"
																binding="#{inventario$ABMArticulo$AdminArticulo.btnSeleccionarArea}" escape="false" id="btnSeleccionarArea" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnLimpiarArea_action}"
																binding="#{inventario$ABMArticulo$AdminArticulo.btnLimpiarArea}" escape="false" id="btnLimpiarArea" mini="true"
																styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.label5}" id="label5" style="" styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{inventario$ABMArticulo$AdminArticulo.ddEstadoContable}" id="ddEstadoContable"
																items="#{inventario$ABMArticulo$AdminArticulo.ddEstadoContableDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{inventario$ABMArticulo$AdminArticulo.btnBuscar}"
												action="#{inventario$ABMArticulo$AdminArticulo.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{inventario$ABMArticulo$AdminArticulo.btnReiniciar_action}"
												binding="#{inventario$ABMArticulo$AdminArticulo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnCancelar_action}"
												binding="#{inventario$ABMArticulo$AdminArticulo.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<!-- </div>
                                -->
							<div>
								<ui:messageGroup binding="#{inventario$ABMArticulo$AdminArticulo.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{inventario$ABMArticulo$AdminArticulo.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{inventario$ABMArticulo$AdminArticulo.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnSeleccionar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnAgregar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnModificar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnEliminar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnConsultar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{inventario$ABMArticulo$AdminArticulo.textoSeparador}" escape="false" id="stSeparador4" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnActivar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnActivar}" id="btnActivar" styleClass="button" text="Recuperar Bien" />
													<!--     <ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnImprimirReporte_action}"
                                                           binding="#{inventario$ABMArticulo$AdminArticulo.btnImprimirReporte}" id="btnImprimirReporte"
                                                           disabled="true" styleClass="button" text="Visualizar Listado" onClick="newWindow = window.open('ImprimirBien.jsp', '_parent')"/>  -->
													<ui:staticText text="#{inventario$ABMArticulo$AdminArticulo.textoSeparador}" escape="false" id="stSeparador5" />
													<ui:button action="#{inventario$ABMArticulo$AdminArticulo.btnExportar_action}"
														binding="#{inventario$ABMArticulo$AdminArticulo.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{inventario$ABMArticulo$AdminArticulo.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{inventario$ABMArticulo$AdminArticulo.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{inventario$ABMArticulo$AdminArticulo.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{inventario$ABMArticulo$AdminArticulo.hidIdPagina}" id="hidIdPagina"
						text="#{inventario$ABMArticulo$AdminArticulo.idPagina}" />
					<ui:hiddenField binding="#{inventario$ABMArticulo$AdminArticulo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{inventario$ABMArticulo$AdminArticulo.idSubSesion}" />
					<ui:script binding="#{inventario$ABMArticulo$AdminArticulo.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{inventario$ABMArticulo$AdminArticulo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
