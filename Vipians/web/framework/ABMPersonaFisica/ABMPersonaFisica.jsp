<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.page1}" id="page1">
			<ui:html binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.html1}" id="html1">
			<ui:head binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.head1}" id="head1">
				<ui:link binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						function cargarComportamientoJQuery() {
							mascaraCuimEnTextField("#form1:tfCuil");
							calendarioEnTextField("#form1:tfNacimiento");
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.body1}" focus="form1:tfNumeroDocumento" id="body1"
				onLoad="parent.footer.location.reload(); Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')" >
				<ui:form binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdSubSesion hidIdPagina | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMPersonaFisica$ABMPersonaFisica.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label5}" for="tfNumeroDocumento" id="label5" styleClass="label"
											text="Documento" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddTipoDocumento}" id="ddTipoDocumento"
											items="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddTipoDocumentoDefaultOptions.options}" styleClass="textField" />
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfNumeroDocumento}" id="tfNumeroDocumento" maxLength="8"
											onKeyPress="return ValidarNum(event,this)" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label4}" for="tfCuil" id="label4" styleClass="label" text="CUIL" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfCuil}" id="tfCuil" styleClass="textField"
											maxLength="13" />
										<a4j:commandButton binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnGenerarCuit}" id="btnGenerarCuit"
											styleClass="btnAjax" value="Generar" onmousedown="validarDniSexo()" />
									</td>
								</tr>
								<script type="text/javascript">
									<![CDATA[
									function validarDniSexo() {
										tfDni = document.getElementById('form1:tfNumeroDocumento');
										ddSexo = document.getElementById('form1:ddSexo');
										tfCuil = document.getElementById('form1:tfCuil');

										var dni = tfDni.value.trim();
										if (dni == "") return;			
										
										var sexo = ddSexo.value;
										if (sexo == "") {
											alert("Seleccione el sexo"); 
											return;
										}

										var largo = dni.length;
										for (var i = largo ; i < 8 ; i++) dni = "0"+dni; 										

										var valor = tfCuil.value.trim();
										if(tfCuil.value.trim() === "-        -" || tfCuil.value.trim() === "") {
											valor = "";
										}
										
										if (valor != ""
												&& !confirm("El campo CUIL contiene un valor, ¿desea reemplazarlo por el generado?")) return;

										generarCuit(dni, sexo);
									}

									function generarCuit(dni, sexo) {
										var nroInicial;
										if (sexo == "MASCULINO") {
											nroInicial = 20;
										} else {
											nroInicial = 27;
										}
										var NroInicialYDocumento = nroInicial
												+ dni;

										var arrayValidador = new Array(10); // enteros
										arrayValidador[0] = parseInt(NroInicialYDocumento[0]) * 5;
										arrayValidador[1] = parseInt(NroInicialYDocumento[1]) * 4;
										arrayValidador[2] = parseInt(NroInicialYDocumento[2]) * 3;
										arrayValidador[3] = parseInt(NroInicialYDocumento[3]) * 2;
										arrayValidador[4] = parseInt(NroInicialYDocumento[4]) * 7;
										arrayValidador[5] = parseInt(NroInicialYDocumento[5]) * 6;
										arrayValidador[6] = parseInt(NroInicialYDocumento[6]) * 5;
										arrayValidador[7] = parseInt(NroInicialYDocumento[7]) * 4;
										arrayValidador[8] = parseInt(NroInicialYDocumento[8]) * 3;
										arrayValidador[9] = parseInt(NroInicialYDocumento[9]) * 2;

										var numero = 0;
										for ( var i = 0; i < 10; i++) {
											numero = numero + arrayValidador[i];
										}
										var restoDivision = parseInt(numero % 11);

										var digitoVerificador;
										if (restoDivision == 0) {
											digitoVerificador = 0;
										} else if (restoDivision == 1
												&& sexo == "MASCULINO") {
											digitoVerificador = 9;
											nroInicial = "23";
										} else if (restoDivision == 1
												&& sexo == "FEMENINO") {
											digitoVerificador = 4;
											nroInicial = "23";
										} else {
											digitoVerificador = 11 - restoDivision;
										}

										tfCuil = document
												.getElementById('form1:tfCuil');
										tfCuil.value = nroInicial + "-"
												+ dni + "-"
												+ digitoVerificador;
									}
									]]>
								</script>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label1}" for="tfApellido" id="label1" styleClass="label"
											text="Apellido" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfApellido}" columns="40" id="tfApellido"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label3}" for="tfNombre" id="label3" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
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
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label8}" for="ddSexo" id="label8" styleClass="label" text="Sexo" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddSexo}" id="ddSexo"
											items="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddSexoDefaultOptions.options}" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label2}" for="ddEstadoCivil" id="label2" styleClass="label"
											text="Estado Civil" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddEstadoCivil}" id="ddEstadoCivil"
											items="#{framework$ABMPersonaFisica$ABMPersonaFisica.ddEstadoCivilDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label6}" for="tfNacimiento" id="label6" styleClass="label"
											text="Fecha de Nacimiento" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfNacimiento}" id="tfNacimiento" styleClass="textField"
											columns="10" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label9}" for="tfEdad" id="label9" styleClass="label" text="Edad"
											rendered="false" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfEdad}" columns="5" id="tfEdad" styleClass="textField"
											onKeyDown="return ValidarNum(event,this)" rendered="false" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label7}" for="cbJubilado" id="label7" styleClass="label"
											text="Jubilado" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.cbJubilado}" id="cbJubilado" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label10}" for="tfTelefono" id="label10" styleClass="label"
											text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfTelefono}" columns="30" id="tfTelefono"
											styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label11}" for="tfCelular" id="label11" styleClass="label"
											text="Celular" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfCelular}" columns="30" id="tfCelular"
											styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label12}" for="tfEmail" id="label12" styleClass="label"
											text="E-mail" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tfEmail}" columns="40" id="tfEmail" styleClass="textField" />
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
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label13}" id="label13" styleClass="label" text="Domicilio Fiscal" />
									</td>
									<td colspan="1">
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCopiarDomicilioPostal_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCopiarDomicilioPostal}" escape="false" id="btnCopiarDomicilioPostal"
											mini="true" styleClass="buttonAgregar" text="&amp;nbsp;" toolTip="Copiar Domicilio Postal" />
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnSeleccionarDomicilio_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.label14}" id="label14" styleClass="label" text="Domicilio Postal" />
									</td>
									<td colspan="3">
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCopiarDomicilioFiscal_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCopiarDomicilioFiscal}" escape="false" id="btnCopiarDomicilioFiscal"
											mini="true" styleClass="buttonAgregar" text="&amp;nbsp;" toolTip="Copiar Domicilio Fiscal" />
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnSeleccionarDomicilioPostal_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="1" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.stDomicilio}" escape="false" id="stDomicilio"
											styleClass="staticText" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3" nowrap="nowrap">
										<ui:staticText binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.stDomicilioPostal}" escape="false" id="stDomicilioPostal"
											styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.panelAtributoDinamico}" id="panelAtributoDinamico">
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
									<td align="right">
										<ui:label binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnGuardar_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCancelar_action}"
											binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMPersonaFisica$ABMPersonaFisica.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPersonaFisica$ABMPersonaFisica.idSubSesion}" />
					<ui:script binding="#{framework$ABMPersonaFisica$ABMPersonaFisica.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
