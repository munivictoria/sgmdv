<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMReporte$EjecutarReporte.page1}" id="page1">
			<ui:html binding="#{framework$ABMReporte$EjecutarReporte.html1}" id="html1">
			<ui:head binding="#{framework$ABMReporte$EjecutarReporte.head1}" id="head1">
				<ui:link binding="#{framework$ABMReporte$EjecutarReporte.link1}" id="link1" url="/resources/stylesheet.css" />
				<ui:script id="sketch" url="/resources/javascript/jQuery/sketch.js" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						var $parametrosFecha = $("#form1").find("input[id *= 'tfParametro-Fecha']");

						for(var j = 0; j < $parametrosFecha.length; j++) {
							calendarioEnTextField("#" + $parametrosFecha.eq(j).attr("id"));
						}

						// Canvas de firma.. si hay!
						var $divCanvas = $("#form1").find("div[id *= 'divCanvas']");
						var idCanvas = $divCanvas.attr("id").replace(/divCanvas/g, "canvas").replace(/form1:/g, "");

						$divCanvas
								.html("<canvas id='" + idCanvas + "' style='width: 400px; height: 140px; border-style: dashed; background-color: #ADFF5F;'></canvas>");

						$("#" + idCanvas.replace(/:/g, "\\:")).sketch({
							defaultSize: 3,
							download: 'png'
						});

						var $btnGuardar = $("#form1").find("input[id *= 'btnGuardar']");
						$btnGuardar.click(function() {
							var $canvas = $("#form1").find("canvas");
							var context = $canvas[0].getContext('2d');
							var imageData = context.getImageData(0, 0, $canvas.width(), $canvas.height());

							var dibujado = false;
							for(var i = 0; i < imageData.data.length; i++) {
								if(imageData.data[i]) {
									dibujado = true;
									break;
								}
							}
							if(dibujado) {
								$.ajax({
									url: '/Vipians/faces/FirmarServlet',
									type: 'get',
									dataType: 'string',
									data: {
										firma: $("#form1").find("canvas").get(0).toDataURL(),
										id: idCanvas
									}
								});
							}
						});
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMReporte$EjecutarReporte.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMReporte$EjecutarReporte.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMReporte$EjecutarReporte.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMReporte$EjecutarReporte.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label for="tfNombre" id="label4" styleClass="label" text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{framework$ABMReporte$EjecutarReporte.tfNombre}" columns="40" id="tfNombre" styleClass="textField"
														disabled="true" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="2">
													<ui:label id="labelParametros" text="Parametros" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:panelGroup binding="#{framework$ABMReporte$EjecutarReporte.panelParametros}" id="panelParametros" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<a4j:outputPanel ajaxRendered="true">
														<ui:messageGroup binding="#{framework$ABMReporte$EjecutarReporte.messageGroup}" id="messageGroup1" styleClass="grupoMsg" />
													</a4j:outputPanel>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMReporte$EjecutarReporte.btnGuardar_action}"
											binding="#{framework$ABMReporte$EjecutarReporte.btnGuardar}" id="btnGuardar" styleClass="button"
											onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
										<ui:staticText binding="#{framework$ABMReporte$EjecutarReporte.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMReporte$EjecutarReporte.btnCancelar_action}"
											binding="#{framework$ABMReporte$EjecutarReporte.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMReporte$EjecutarReporte.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMReporte$EjecutarReporte.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMReporte$EjecutarReporte.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMReporte$EjecutarReporte.idSubSesion}" />
					<ui:script binding="#{framework$ABMReporte$EjecutarReporte.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>