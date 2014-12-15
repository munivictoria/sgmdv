<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.page1}" id="page1">
			<ui:html binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.html1}" id="html1">
			<ui:head binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.head1}" id="head1">
				<ui:link binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.body1}" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.form1}" id="form1" enctype="multipart/form-data">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMMunicipalidad$ABMMunicipalidad.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.label4}" for="tfNombre" id="label4" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.label5}" for="tfTelefono" id="label5" styleClass="label"
											text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tfTelefono}" columns="30" id="tfTelefono"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stDomicilio}" escape="false" id="stDomicilio"
											styleClass="staticText " />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.label13}" id="label13" styleClass="label" text="Domicilio Postal" />
									</td>
									<td colspan="3">
										<ui:button action="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnSeleccionarDomicilio_action}"
											binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label id="lbLogo" text="Logo:" for="uploadArchivo" />
									</td>
									<td>
										<ui:image id="imagenLogo" url="/faces/ImageServlet" style="height: 50px;" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stEncabezado}" for="tfEncabezado" id="lbEncabezado"
											styleClass="label" text="Encabezado" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tfEncabezado}" columns="20" id="tfEncabezado"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stSubEncabezado}" for="tfSubEncabezado" id="lbSubEncabezado"
											styleClass="label" text="Sub Encabezado" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tfSubEncabezado}" columns="20" id="tfSubEncabezado"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stVariosServicios}" id="stVariosServicios"
											styleClass="label" text="Varios Servicios OSP" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.cbVariosServicios}" id="cbVariosServicios" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stNumerarSolSum}" id="stNumerarSolSum" styleClass="label"
											text="Numerar Solicitudes de Suministros por Área" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.cbNumerarSolSum}" id="cbNumerarSolSum" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stNombreCuentaConcatenado}" id="stNombreCuentaConcatenado"
											styleClass="label" text="Nombre de Cuenta Concatenado" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.cbNombreCuentaConcatenado}" id="cbNombreCuentaConcatenado" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stMontoFacturaVariaoc}" id="stMontoFacturaVariaoc"
											styleClass="label" text="Permitir Facturar con monto distinto a la Orden de Compra" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.cbMontoFacturaVariaoc}" id="cbMontoFacturaVariaoc" />
									</td>
								</tr>
								<tr>
									<td id="ocultable" style="display: none;">
										<ui:upload id="uploadArchivo" label="Logo" uploadedFile="#{framework$ABMMunicipalidad$ABMMunicipalidad.uploadedFile}"
											required="false" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.lblRutaReporte}" id="lblRutaReporte" styleClass="label" text="Ruta Reportes" />
									</td>
									<td colspan="4" style="padding-right: 200px">
										<ui:textField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tfRutaReporte}" id="tfRutaReporte" columns="60"/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnGuardar_action}"
											binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnCancelar_action}"
											binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMMunicipalidad$ABMMunicipalidad.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMMunicipalidad$ABMMunicipalidad.idSubSesion}" />
					<ui:script binding="#{framework$ABMMunicipalidad$ABMMunicipalidad.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
			<script>
				window.onload = function() {
					var imagen = document.getElementById("form1:imagenLogo");
					var upload = document
							.getElementById("form1:uploadArchivo_com.sun.rave.web.ui.upload");

					imagen.onclick = ejecutarUpload;
					upload.addEventListener('change', handleFileSelect, false);

				}
				function ejecutarUpload() {
					var upload = document
							.getElementById("form1:uploadArchivo_com.sun.rave.web.ui.upload");
					upload.click();
				}
				function handleFileSelect(evt) {
					var files = evt.target.files; // Toma la lista de archivos del objeto upload
					var imagen = document.getElementById("form1:imagenLogo");

					// Recorre los objetos que tomo el upload
					for ( var i = 0, f; f = files[i]; i++) {

						// Si son imagenes determina la extencion
						if (!f.type.match('image.*')) {
							continue;
						}

						var reader = new FileReader();

						// Captura informacion del archivo
						reader.onload = (function(theFile) {
							return function(e) {
								// Agrega datos del objeto del upload a la etiqueta de la imagen
								imagen.src = e.target.result;
								imagen.title = escape(theFile.name);
							};
						})(f);

						// Graba los cambios de la etiqueta imagen
						reader.readAsDataURL(f);
					}
				}
			</script>
		</ui:page>
	</f:view>
</jsp:root>