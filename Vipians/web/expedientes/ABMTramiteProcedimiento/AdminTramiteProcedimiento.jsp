<?xml version="1.0" encoding="UTF-8"?>

<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->

<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.head1}" id="head1"
				title="Administración Trámites de Procedimiento">
				<ui:link binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.head1.title}" />
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
											<ui:panelGroup binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
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
											<a4j:commandButton binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnBuscar}"
												action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnReiniciar_action}"
												binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnCancelar_action}"
												binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.messageGroup}" id="messageGroup"
									showDetail="true" showSummary="false" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnSeleccionar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnAgregar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnModificar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnEliminar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnConsultar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.stSeparador4}" escape="false"
														id="stSeparador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnExportar_action}"
														binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>