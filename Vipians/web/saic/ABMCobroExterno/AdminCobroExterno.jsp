<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMCobroExterno$AdminCobroExterno.page1}" id="page1">
			<ui:html binding="#{saic$ABMCobroExterno$AdminCobroExterno.html1}" id="html1">
			<ui:head binding="#{saic$ABMCobroExterno$AdminCobroExterno.head1}" id="head1" title="Administración de Cobros Externos">
				<ui:link binding="#{saic$ABMCobroExterno$AdminCobroExterno.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMCobroExterno$AdminCobroExterno.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMCobroExterno$AdminCobroExterno.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMCobroExterno$AdminCobroExterno.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMCobroExterno$AdminCobroExterno.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$ABMCobroExterno$AdminCobroExterno.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td align="right">
															<ui:label binding="#{saic$ABMCobroExterno$AdminCobroExterno.lblNombreArchivo}" id="lblNombreArchivo" styleClass="label"
																text="Archivo" for="tfNombreArchivo" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{saic$ABMCobroExterno$AdminCobroExterno.tfNombreArchivo}" id="tfNombreArchivo" styleClass="textField"
																style="padding-left:0pt !important;" columns="40" disabled="true" />
															<ui:upload binding="#{saic$ABMCobroExterno$AdminCobroExterno.upload}" id="uploadArchivo" styleClass="accionSubir"
																style="display:none" valueChangeListener="#{saic$ABMCobroExterno$AdminCobroExterno.procesarArchivo}" />
															<ui:label binding="#{saic$ABMCobroExterno$AdminCobroExterno.lblAgregarArchivo}" id="lblAgregarArchivo"
																styleClass="buttonAgregar" style="height:15px; padding-top: 2px" onClick="procesarDatosLabelUpload()" />
															<ui:label binding="#{saic$ABMCobroExterno$AdminCobroExterno.lblEliminarArchivo}" id="lblEliminarArchivo"
																onClick="borrarArchivo()"
																style="font-weight: bold; font-size: 10px; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; background-color: #EEE; background-image: url(/Vipians/resources/imagenes/botones/eraser13.png) ! important; padding: 2px 9px; cursor: pointer; background-repeat: no-repeat;" />
															<ui:button text="Procesar seleccionado" styleClass="button"
																action="#{saic$ABMCobroExterno$AdminCobroExterno.btnProcesarArchivo_action}" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<br />
															<br />
															<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{saic$ABMCobroExterno$AdminCobroExterno.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{saic$ABMCobroExterno$AdminCobroExterno.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnBuscar}"
												action="#{saic$ABMCobroExterno$AdminCobroExterno.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMCobroExterno$AdminCobroExterno.btnReiniciar_action}"
												binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$ABMCobroExterno$AdminCobroExterno.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnCancelar_action}"
												binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMCobroExterno$AdminCobroExterno.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMCobroExterno$AdminCobroExterno.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMCobroExterno$AdminCobroExterno.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnSeleccionar_action}"
														binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{saic$ABMCobroExterno$AdminCobroExterno.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<!--<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnAgregar_action}" binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnModificar_action}" binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnEliminar_action}" binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText text="#{saic$ABMCobroExterno$AdminCobroExterno.textoSeparador}" escape="false" id="stSeparador3" />
													<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnConsultar_action}" binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{saic$ABMCobroExterno$AdminCobroExterno.textoSeparador}" escape="false" id="stSeparador4" />-->
													<ui:button action="#{saic$ABMCobroExterno$AdminCobroExterno.btnExportar_action}"
														binding="#{saic$ABMCobroExterno$AdminCobroExterno.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{saic$ABMCobroExterno$AdminCobroExterno.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{saic$ABMCobroExterno$AdminCobroExterno.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$ABMCobroExterno$AdminCobroExterno.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$ABMCobroExterno$AdminCobroExterno.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{saic$ABMCobroExterno$AdminCobroExterno.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMCobroExterno$AdminCobroExterno.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMCobroExterno$AdminCobroExterno.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMCobroExterno$AdminCobroExterno.idSubSesion}" />
					<ui:script binding="#{saic$ABMCobroExterno$AdminCobroExterno.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
			<script>
				<![CDATA[

				function procesarDatosLabelUpload() {
					var upload = document.getElementById("form1:uploadArchivo");
					upload.click();
					upload.addEventListener('change', cambiarEstado, false);
				}

				function cambiarEstado(evt) {

					var files = evt.target.files;
					for(var i = 0, f; f = files[i]; i++) {
						var reader = new FileReader();
						reader.onload = (function(theFile) {
							return function(e) {
								nombre = f.name;
							};
						})(f);

						var lab = document.getElementById("form1:tfNombreArchivo");

						lab.value = "";
						lab.innerHTML = "";
						lab.value = f.name;

						reader.readAsDataURL(f);
					}
				}

				function borrarArchivo() {
					var nameArchivo = document.getElementById("form1:tfNombreArchivo");
					nameArchivo.value = "";
				}
				]]>
			</script>
		</ui:page>
	</f:view>
</jsp:root>
