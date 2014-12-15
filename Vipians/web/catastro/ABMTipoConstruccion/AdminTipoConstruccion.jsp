<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.head1}" id="head1" title="Administración de Tipos de Construcción">
				<ui:link binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.label1}" id="label1" style="" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.tfNombre}" columns="40" id="tfNombre"
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
															<ui:checkbox binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnBuscar}"
												action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnReiniciar_action}"
												binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnCancelar_action}"
												binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnSeleccionar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.stSeparadorSeleccionar}" escape="false"
														styleClass="barraSeparadoraVertical" id="staticText6" />
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnAgregar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnModificar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnEliminar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.stSeparadorAccion}" escape="false"
														styleClass="barraSeparadoraVertical" id="staticText8" />
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnConsultar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnExportar_action}"
														binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMTipoConstruccion$AdminTipoConstruccion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
