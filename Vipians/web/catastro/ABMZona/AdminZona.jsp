<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMZona$AdminZona.page1}" id="page1">
			<ui:html binding="#{catastro$ABMZona$AdminZona.html1}" id="html1">
			<ui:head binding="#{catastro$ABMZona$AdminZona.head1}" id="head1" title="Administración de Zonas">
				<ui:link binding="#{catastro$ABMZona$AdminZona.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMZona$AdminZona.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMZona$AdminZona.form1}" id="form1">
					<div class="divAdmin" style="left: -4px; top: 0px; position: absolute">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMZona$AdminZona.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMZona$AdminZona.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMZona$AdminZona.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMZona$AdminZona.label1}" id="label1" style="" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMZona$AdminZona.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMZona$AdminZona.label2}" id="label2" style="" styleClass="label" text="Zonificación" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMZona$AdminZona.tfZonificacion}" columns="40" id="tfZonificacion" styleClass="textField" />
															<ui:button action="#{catastro$ABMZona$AdminZona.btnSeleccionarZonificacion_action}"
																binding="#{catastro$ABMZona$AdminZona.btnSeleccionarZonificacion}" escape="false" id="btnSeleccionarZonificacion"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarZonificacion" reRender="form1:tfZonificacion" title="Limpiar"
																binding="#{catastro$ABMZona$AdminZona.btnLimpiarZonificacion}"
																action="#{catastro$ABMZona$AdminZona.btnLimpiarZonificacion_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMZona$AdminZona.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMZona$AdminZona.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td style="height: 19px"></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2" style="height: 29px">
											<a4j:commandButton binding="#{catastro$ABMZona$AdminZona.btnBuscar}" action="#{catastro$ABMZona$AdminZona.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMZona$AdminZona.btnReiniciar_action}"
												binding="#{catastro$ABMZona$AdminZona.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMZona$AdminZona.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMZona$AdminZona.btnCancelar_action}" binding="#{catastro$ABMZona$AdminZona.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMZona$AdminZona.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMZona$AdminZona.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMZona$AdminZona.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMZona$AdminZona.btnSeleccionar_action}" binding="#{catastro$ABMZona$AdminZona.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMZona$AdminZona.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZona$AdminZona.btnAgregar_action}" binding="#{catastro$ABMZona$AdminZona.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMZona$AdminZona.btnModificar_action}" binding="#{catastro$ABMZona$AdminZona.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMZona$AdminZona.btnEliminar_action}" binding="#{catastro$ABMZona$AdminZona.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMZona$AdminZona.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZona$AdminZona.btnConsultar_action}" binding="#{catastro$ABMZona$AdminZona.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMZona$AdminZona.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZona$AdminZona.btnExportar_action}" binding="#{catastro$ABMZona$AdminZona.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMZona$AdminZona.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMZona$AdminZona.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMZona$AdminZona.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMZona$AdminZona.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMZona$AdminZona.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMZona$AdminZona.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMZona$AdminZona.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMZona$AdminZona.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMZona$AdminZona.idSubSesion}" />
					<ui:script binding="#{catastro$ABMZona$AdminZona.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMZona$AdminZona.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
