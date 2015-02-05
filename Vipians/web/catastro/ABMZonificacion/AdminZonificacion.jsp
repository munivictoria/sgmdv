<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMZonificacion$AdminZonificacion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMZonificacion$AdminZonificacion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMZonificacion$AdminZonificacion.head1}" id="head1"
				title="Administración de Tipos de Transacciones Catastrales">
				<ui:link binding="#{catastro$ABMZonificacion$AdminZonificacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMZonificacion$AdminZonificacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMZonificacion$AdminZonificacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="Administración de Zonificación" />
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
											<ui:panelGroup binding="#{catastro$ABMZonificacion$AdminZonificacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMZonificacion$AdminZonificacion.label1}" id="label1" style="" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMZonificacion$AdminZonificacion.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMZonificacion$AdminZonificacion.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMZonificacion$AdminZonificacion.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMZonificacion$AdminZonificacion.btnBuscar}"
												action="#{catastro$ABMZonificacion$AdminZonificacion.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMZonificacion$AdminZonificacion.btnReiniciar_action}"
												binding="#{catastro$ABMZonificacion$AdminZonificacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnCancelar_action}"
												binding="#{catastro$ABMZonificacion$AdminZonificacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMZonificacion$AdminZonificacion.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMZonificacion$AdminZonificacion.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMZonificacion$AdminZonificacion.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnSeleccionar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnAgregar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnModificar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnEliminar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnConsultar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMZonificacion$AdminZonificacion.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMZonificacion$AdminZonificacion.btnExportar_action}"
														binding="#{catastro$ABMZonificacion$AdminZonificacion.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMZonificacion$AdminZonificacion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMZonificacion$AdminZonificacion.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMZonificacion$AdminZonificacion.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMZonificacion$AdminZonificacion.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMZonificacion$AdminZonificacion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMZonificacion$AdminZonificacion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMZonificacion$AdminZonificacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMZonificacion$AdminZonificacion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMZonificacion$AdminZonificacion.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMZonificacion$AdminZonificacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
