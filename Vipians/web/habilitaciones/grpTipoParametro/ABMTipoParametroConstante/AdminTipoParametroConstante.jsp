<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.head1}" id="head1"
				title="Administración de Parámetros Constantes">
				<ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.body1}" focus="form1:tfNombre"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.stTitulo}"
										id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.label1}"
																for="tfNombre" id="label1" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.tfNombre}"
																columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox
																binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table
																binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnBuscar}"
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnReiniciar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnReiniciar}"
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnCancelar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.paginatedTable}"
											styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.groupPanel1}"
													id="groupPanel1" style="">
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnSeleccionar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.stSeparadorSeleccionar}"
														escape="false" id="stSeparador2" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnAgregar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnModificar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnEliminar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.stSeparadorAccion}"
														escape="false" id="stSeparador3" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnConsultar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.textoSeparador}"
														escape="false" id="stSeparador4" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnExportar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.lblEncontrados}"
											id="lblEncontrados" styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.scriptFinal1}"
						id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
