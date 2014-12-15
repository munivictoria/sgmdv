<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.page1}" id="page1">
			<ui:html binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.html1}" id="html1">
			<ui:head binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.head1}" id="head1" >
				<ui:link binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblDatosObligacion}" id="lblDatosObligacion"
											styleClass="label3" text="Datos de la Obligación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblTituloPeriodo}" id="lblTituloPeriodo" styleClass="label2"
											text="Período" />
									</td>
									<td>
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfPeriodo}" columns="20" id="tfPeriodo" maxLength="4"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblObligacion}" id="lblObligacion" styleClass="label2"
											text="Obligación" />
									</td>
									<td>
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ConsultarLiquidacionOSP.taObligacion}" id="taObligacion" columns="60"
											rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblFormula}" id="lblFormula" styleClass="label2"
											text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taFormula}" id="taFormula" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblParametros}" id="lblParametros" styleClass="label2"
											text="Parámetros" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taParametros}" id="taParametros" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblModificadores}" id="lblModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taModificadores}" id="taModificadores" columns="60"
											rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblVencimientos}" id="lblVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taVencimientos}" id="taVencimientos"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblExencion}" id="lblExencion" styleClass="label2"
											text="Exención" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taExencion}" id="taExencion" styleClass="textField"
											disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblFechaObligacion}" id="lblFechaObligacion"
                                                      styleClass="label" text="Fecha Emisión: "/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfFechaObligacion}" columns="20"
                                                disabled="true" id="tfFechaObligacion" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblPersona}" for="tfPersona" id="lblPersona"
                                                styleClass="label" text="Persona: "/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" maxLength="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblObligacion}" id="lblObligacion" for="taObligacion"
                                                      styleClass="label" text="Obligación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.taObligacion}" id="taObligacion" columns="60" rows="3"
                                                          styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>-->
								<tr>
									<td></td>
								</tr>
								<!--<tr>
                                        <td colspan="2">
                                            <br/>
                                            <hr/>
                                        </td>
                                    </tr>-->
								<tr>
									<td>
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblDatosLiquidacion}" id="lblDatosLiquidacion"
											styleClass="label3" text="Datos de la Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblCuotaLiquidada}" id="lblCuotaLiquidada"
											for="tfCuotaLiquidada" styleClass="label" text="Cuota Liquidada" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfCuotaLiquidada}" disabled="true" id="tfCuotaLiquidada"
											styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblMonto}" id="lblMonto" for="tfMonto" styleClass="label2"
											text="Monto " />
									</td>
									<td>
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfMonto}" id="tfMonto" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblEstado}" id="lblEstado" styleClass="label2" for="tfEstado"
											text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfEstado}" id="tfEstado" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblFechaCancelacion}" id="lblFechaCancelacion"
											for="tfFechaCancelacion" styleClass="label" text="Fecha de Cancelación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfFechaCancelacion}" id="tfFechaCancelacion"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap ="nowrap">
                                            <ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblFechaVencimiento}" for="tfFechaVencimiento"
                                            id="lblFechaVencimiento" styleClass="label" text="Fecha Vencimiento"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfFechaVencimiento}" id="tfFechaVencimiento" disabled="true"
                                                          styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.lblTipoDeuda}" id="lblTipoDeuda" for="tfTipoDeuda" styleClass="label"
                                                      text="Tipo de Deuda"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.tfTipoDeuda}" id="tfTipoDeuda" styleClass="textField"
                                                          disabled="true"/>
                                        </td>
                                    </tr>
                                    -->
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.btnCancelar_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.idPagina}" />
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.idSubSesion}" />
					<ui:script binding="#{saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
