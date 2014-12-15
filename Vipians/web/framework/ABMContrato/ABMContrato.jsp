<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMContrato$ABMContrato.page1}" id="page1">
			<ui:html binding="#{framework$ABMContrato$ABMContrato.html1}" id="html1">
			<ui:head binding="#{framework$ABMContrato$ABMContrato.head1}" id="head1">
				<ui:link binding="#{framework$ABMContrato$ABMContrato.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
                        var _decimales = 2;

                        function getById(id) {
                            elem = document.getElementById(id);
                            return elem;
                        }

                        function formatNumber(number) {
                            number = Math.round( number * Math.pow(10,_decimales) ) / Math.pow(10,_decimales);
                            cantDecimales = 0;
                            if (number.toString().indexOf(".") >= 0) cantDecimales = number.toString().substring( number.toString().indexOf(".")+1 ).length;
                            if (number == Math.floor(number)) number = number + ".";
                            for (i = 0; i < (_decimales-cantDecimales); i++) number += "0";
                            return number;
                        }

                        function multiplicar(valor1, valor2) {
                            return (valor1 * valor2);
                        }

                        function calcularTotal() {
                            tfCantidadCuotas   = getById("form1:tfCantidadCuotas");
                            tfPrecioCuotas     = getById("form1:tfPrecioCuotas");
                            tfTotal             =getById("form1:tfTotal");

                            tfTotal.value = formatNumber(multiplicar(tfCantidadCuotas.value, tfPrecioCuotas.value));
                        }

                        var nombreBean = "framework$ABMContrato$ABMContrato";
    				    
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
							calendarioEnTextField("#form1:tfFechaInicio");
							calendarioEnTextField("#form1:tfFechaFin");
						}

						function focusearTfPersonaSeleccionada() {
							$("#form1\\:tfPersona").focus();
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});                   
                    ]]>
                </script>
			</ui:head>
			<ui:body binding="#{framework$ABMContrato$ABMContrato.body1}" focus="form1:tfCodigoContrato" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')" >
				<ui:form binding="#{framework$ABMContrato$ABMContrato.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMContrato$ABMContrato.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMContrato$ABMContrato.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.label4}" for="tfCodigoContrato" id="label4" styleClass="label"
											text="Código Contrato" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfCodigoContrato}" id="tfCodigoContrato" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.label1}" for="tfPersona" id="label1" styleClass="label" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfPersona}" columns="40" id="tfPersona"
											styleClass="#{framework$ABMContrato$ABMContrato.hayPersona ? 'textFieldDisabled' : 'textField'}"
											disabled="#{framework$ABMContrato$ABMContrato.hayPersona}" />
										<ui:button action="#{framework$ABMContrato$ABMContrato.btnSeleccionarPersonaFisica_action}"
											binding="#{framework$ABMContrato$ABMContrato.btnSeleccionarPersonaFisica}" id="btnSeleccionarPersonaFisica" mini="true"
											styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
										<ui:button action="#{framework$ABMContrato$ABMContrato.btnSeleccionarPersonaJuridica_action}"
											binding="#{framework$ABMContrato$ABMContrato.btnSeleccionarPersonaJuridica}" id="btnSeleccionarPersonaJuridica" mini="true"
											styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
											binding="#{framework$ABMContrato$ABMContrato.btnLimpiarPersona}"
											action="#{framework$ABMContrato$ABMContrato.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
											oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.label3}" for="tfFechaInicio" id="label3" styleClass="label"
											text="Fecha Inicio" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfFechaInicio}" id="tfFechaInicio" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{framework$ABMContrato$ABMContrato.staticText1}" escape="false" id="staticText1"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.label2}" for="tfFechaFin" id="label2" styleClass="label" text="Fecha Fin" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfFechaFin}" id="tfFechaFin" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{framework$ABMContrato$ABMContrato.staticText3}" escape="false" id="staticText3"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.lblCantidadCuotas}" for="tfCantidadCuotas" id="lblCantidadCuotas"
											styleClass="label" text="Cantidad de Cuotas" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfCantidadCuotas}" id="tfCantidadCuotas" maxLength="10"
											onKeyPress="return ValidarNum(event,this)" onKeyUp="calcularTotal()"  styleClass="textField"/>
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.lblPrecioCuotas}" for="tfPrecioCuotas" id="lblPrecioCuotas"
											styleClass="label" text="Precio por Cuota" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfPrecioCuotas}" id="tfPrecioCuotas" maxLength="10"
											onKeyUp="calcularTotal()" onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.label5}" for="taDescripcion" id="label5" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMContrato$ABMContrato.taDescripcion}" columns="40" id="taDescripcion" rows="5"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMContrato$ABMContrato.lblTotal}" for="tfTotal" id="lblTotal" styleClass="label" text="Total" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMContrato$ABMContrato.tfTotal}" disabled="true" id="tfTotal" maxLength="10"
											onKeyUp="calcularTotal()" style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="0.00" />
									</td>
								</tr>

								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMContrato$ABMContrato.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{framework$ABMContrato$ABMContrato.btnGuardar_action}"
											binding="#{framework$ABMContrato$ABMContrato.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMContrato$ABMContrato.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMContrato$ABMContrato.btnCancelar_action}"
											binding="#{framework$ABMContrato$ABMContrato.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMContrato$ABMContrato.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMContrato$ABMContrato.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMContrato$ABMContrato.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMContrato$ABMContrato.idSubSesion}" />
					<ui:script binding="#{framework$ABMContrato$ABMContrato.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
					<script>
						<![CDATA[
                    calcularTotal();
                    /*sumarTotalARefinanciar();*/
                ]]></script>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
