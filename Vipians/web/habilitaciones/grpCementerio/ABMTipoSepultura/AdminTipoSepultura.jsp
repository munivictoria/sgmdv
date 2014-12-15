<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.head1}" id="head1"
				title="Administración de Tipo Sepultura">
				<ui:link binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.lbCodigo}" for="tfCodigo" id="lbCodigo"
																styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.tfCodigo}" columns="40"
																id="tfCodigo" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.lbDescripcion}" for="taDescripcion"
																id="lbDescripcion" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textArea binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.taDescripcion}" columns="40"
																id="taDescripcion" styleClass="textArea" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table border="0" width="600">
																<tr>
																	<td>
																		<ui:label id="lblVacio" styleClass="label" text="" />
																	</td>
																	<td>
																		<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
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
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnBuscar}"
												action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnReiniciar_action}"
												binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.staticText1}" escape="false"
												id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnCancelar_action}"
												binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.paginatedTable}" id="table1"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnSeleccionar_action}"
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.stSeparadorSeleccionar}"
														id="separador_1" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnAgregar_action}"
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnModificar_action}"
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnEliminar_action}"
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnConsultar_action}"
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
