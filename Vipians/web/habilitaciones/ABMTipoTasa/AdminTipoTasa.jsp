<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.head1}" id="head1" title="Administración de Fórmulas de Cálculo">
				<ui:link binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.label2}" for="tfNombre" id="label2" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.label1}" for="ddTipoObligacion" id="label1" styleClass="label"
																text="Tipo de Obligación" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddTipoObligacion}" id="ddTipoObligacion"
																items="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddTipoObligacionDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.lblEstado}" for="ddEstadoObligacion" id="lblEstado"
																styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddEstadoObligacion}" id="ddEstadoObligacion"
																items="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddEstadoObligacionDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnBuscar}"
												action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnReiniciar_action}"
												binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnCancelar_action}"
												binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:label binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.label3}" for="ddTipoObligacionParaAgregar" id="label3"
											styleClass="label" text="Tipo de Obligación" />
										<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.staticText4}" escape="false" id="staticText4"
											styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
										<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddTipoObligacionParaAgregar}" id="ddTipoObligacionParaAgregar"
											items="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.ddTipoObligacionParaAgregarDefaultOptions.options}" styleClass="textField">
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td nowrap="no">
										<ui:table binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.gpBotones1}" id="gpBotones1">
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnSeleccionar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.stSeparadorSeleccionar}" escape="false" id="staticText5" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnAgregar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnModificar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnModificar}" id="btnModificar"
															onClick="return (confirm(&quot;¿Está seguro que desea modificar la Fórmula de Cálculo?&quot;));" styleClass="button"
															text="Modificar" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnEliminar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnEliminar}" disabled="false" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.stSeparadorAccion}" escape="false" id="staticText6" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnClonarFormula_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnClonarFormula}" id="btnClonarFormula" styleClass="button"
															text="Clonar Fórmula" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnActivarFormula_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnActivarFormula}" id="btnActivarFormula"
															onClick="return(confirm(&quot;¿Está seguro que desea ACTIVAR la Fórmula de Cálculo?&quot;));" styleClass="button"
															text="Activar Fórmula" />
														<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.staticText12}" escape="false" id="staticText12"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnConsultar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.staticText13}" escape="false" id="staticText13"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnExportar_action}"
															binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
															onClick="return exportarReporte()" />
														<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
														<ui:imageHyperlink binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
                    document.getElementById('form1:tfNombre').focus();
                        </script>
					<ui:hiddenField binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$ABMTipoTasa$AdminTipoTasa.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
