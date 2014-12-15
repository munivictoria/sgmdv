<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.page1}" id="page1">
			<ui:html binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.html1}" id="html1">
			<ui:head binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.head1}" id="head1" title="Generar Liquidaciones de SHPS">
				<ui:link binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					document.getElementById("form1:grpCargando").style.display = "none";

					function mostrarProgreso() {
						tabla = document.getElementById("form1:grpCargando");
						tabla.style.display = "block";
					}

					function confirmarLiquidacionSinPersona() {
						var tfPersona = document.getElementById('form1:tfPersona');
						var tfNroInscripcion = document.getElementById('form1:tfNroInscripcion');
						var retorno = (tfPersona.value != '' || tfNroInscripcion.value != '')
								|| confirm('No ha seleccionado ninguna Persona.\nSe liquidará la tasa para todos los contribuyentes. ¿Desea continuar?');
						if(retorno) {
							mostrarProgreso();
						}
						return retorno;
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.label7}" id="label7" styleClass="label2"
											text="Para liquidar Rubros de Alícuota Porcentual" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.label2}" for="tfPersona" id="label2"
											styleClass="label" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textField" />
										<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarPersonaFisica_action}"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarPersonaFisica}" escape="false"
											id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
										<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarPersonaJuridica_action}"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarPersonaJuridica}" escape="false"
											id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
										<a4j:commandButton id="btnLimpiarPersonaFisica" reRender="form1:tfPersona"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarPersona}"
											action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" align="right" >
										<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblNroInscripcion}" id="lblNroInscripcion" styleClass="label"
											text="Número Inscripción">
										</ui:label>
									</td>
									<td>
										<ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.tfNroInscripcion}" id="tfNroInscripcion" styleClass="textField" columns="10">
										</ui:textField>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table border="0" width="600">
											<tr>
												<td>
													<ui:label id="lblVacio" styleClass="label" text="" />
												</td>
												<td>
													<ui:panelGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<!--
                                    Queda comentada la opcion de liquidar por Rubros y Declaraciones Juradas, por que no tiene
                                    sentido a partir del cambio que deja asociar mas de un Rubro por Obligacion.
                                    
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.label8}" id="label8" styleClass="label2" text="Para liquidar Rubros de Alícuota Fija"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.label6}" for="tfRubro" id="label6"
                                                styleClass="label" text="Rubro"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.tfRubro}" columns="40"
                                                disabled="true" id="tfRubro" styleClass="textField"/>
                                            <ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarRubro_action}"
                                                binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarRubro}" escape="false"
                                                id="btnSeleccionarRubro" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarRubro_action}"
                                                binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarRubro}" escape="false"
                                                id="btnLimpiarRubro" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                        
                                    </tr>
                                   
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblLiquidarDDJJSHPS}" id="lblLiquidarDDJJSHPS"
                                            styleClass="label2" text="Para liquidar Declaraciones Juradas de SHPS"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblDDJJSHPS}" for="tfDDJJSHPS" id="lblDDJJSHPS"
                                            styleClass="label" text="Declaración Jurada"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.tfDDJJSHPS}" columns="40"
                                            disabled="true" id="tfDDJJSHPS" styleClass="textField"/>
                                            <ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarDDJJSHPS_action}"
                                            binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnSeleccionarDDJJSHPS}" escape="false"
                                            id="btnSeleccionarDDJJSHPS" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarDDJJSHPS_action}"
                                            binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnLimpiarDDJJSHPS}" escape="false"
                                            id="btnLimpiarDDJJSHPS" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                     -->
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" colspan="2">
										<table>
											<tbody>
												<tr>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblAnios}" for="ddAnios" id="lblAnios"
															styleClass="label" text="Año" />
														<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddAnios}" id="ddAnios" styleClass="textField"
															items="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddAniosOptions.options}"
															valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.eventoSeleccionAnio(evento)}">
															<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblCalendarios}" for="ddCalendarios"
															id="lblCalendarios" styleClass="label" text="Calendario" />
														<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddCalendarios}" id="ddCalendarios"
															styleClass="textField" items="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddCalendariosOptions.options}"
															valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.eventoSeleccionCalendario(event)}">
															<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
															styleClass="label" text="Periodo" />
														<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddPeriodos}" id="ddPeriodos"
															styleClass="textField" items="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddPeriodosOptions.options}"
															valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.eventoSeleccionPeriodo(event)}">
															<a4j:support event="onChange" reRender="form1:ddCuotas " />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.lblCuotas}" for="ddCuotas" id="lblCuotas"
															styleClass="label" text="Cuota" />
														<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddCuotas}" id="ddCuotas" styleClass="textField"
															items="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.ddCuotasOptions.options}" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.messageGroup1}" id="messageGroup1"
											showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
								<!--<tr>
                                        <td class="notifABM clrNota" colspan="2">
                                            <b>Nota:</b> Si no selecciona una Persona se liquidarán las Obligaciones que tengan asociadas un Rubro de Alícuota Fija.
                                        </td>
                                    </tr>-->
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
										<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnGenerarLiquidacionPrueba}" id="btnGenerarLiquidacionPrueba"
											styleClass="button" text="Generar Prueba Liquidaciones"
											onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
										<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.stSeparador1}" escape="false" id="stSeparador1"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
											onClick="return confirmarLiquidacionSinPersona();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnCancelar_action}"
											binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" disabled="false" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.idPagina}" />
					<ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.idSubSesion}" />
					<ui:script binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<h:panelGrid binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.grpCargando}" columns="2" id="grpCargando"
						style="display:none; padding-left:10px;" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.image1}" id="image1"
							url="/resources/imagenes/abm/loading.gif" />
						<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS.staticText1}" escape="false" id="staticText1"
							styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
					</h:panelGrid>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
