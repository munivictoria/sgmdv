<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.head1.title}" />
								</caption>
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
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.ddEstado}" id="ddEstado"
																items="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnBuscar}"
												action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnReiniciar_action}"
												binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnCancelar_action}"
												binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" />
							</div>
							<div class="divGeneral">
								<table class="general">
									<tr>
										<td>
											<ui:table binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.paginatedTable}" id="table1">
												<f:facet name="actionsTop">
													<ui:panelGroup binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.groupPanel1}" id="groupPanel1" style="">
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnSeleccionar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stSeparadorSeleccionar}" escape="false"
															id="stSeparador2" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnAgregar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnAgregar}" id="btnAgregar" styleClass="button"
															text="Agregar" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnModificar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnEliminar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stSeparadorAccion}" escape="false"
															id="stSeparador3" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnConsultar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnActivar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnActivar}" id="btnActivar" styleClass="button"
															text="Recuperar Trámite" />
														<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stSeparador4}" escape="false" id="stSeparador4"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnExportar_action}"
															binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</f:facet>
											</ui:table>
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2">
											<ui:label binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.lblEncontrados}" id="lblEncontrados" styleClass="label2"
												text="Registros Encontrados: " />
											<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.stCantidadRegistros}" id="stCantidadRegistros"
												styleClass="staticText" />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>