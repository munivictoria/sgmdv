<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.page1}" id="page1">
			<ui:html binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.html1}" id="html1">
			<ui:head binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.head1}" id="head1">
				<ui:link binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblDatosObligacion}" id="lblDatosObligacion"
											styleClass="label3" text="Datos de la Obligación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblTituloPeriodo}" id="lblTituloPeriodo" styleClass="label2"
											text="Período" />
									</td>
									<td>
										<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfPeriodo}" columns="20" id="tfPeriodo" maxLength="4"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblObligacion}" id="lblObligacion" styleClass="label2"
											text="Obligación" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taObligacion}" id="taObligacion" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblPersona}" id="lblPersona"
                                                      styleClass="label2" text="Persona"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taPersona}" id="taPersona" columns="60" rows="3"
                                                         styleClass="textField" disabled="true"/>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        </td>
                                    </tr>
                                    </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblFormula}" id="lblFormula" styleClass="label2"
											text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taFormula}" id="taFormula" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblParametros}" id="lblParametros" styleClass="label2"
											text="Parámetros" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taParametros}" id="taParametros" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblModificadores}" id="lblModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taModificadores}" id="taModificadores" columns="60"
											rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblVencimientos}" id="lblVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taVencimientos}" id="taVencimientos"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblExencion}" id="lblExencion" styleClass="label2"
											text="Exención" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taExencion}" id="taExencion" styleClass="textField"
											disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblFechaObligacion}" id="lblFechaObligacion"
                                                      styleClass="label" text="Fecha Emisión: "/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfFechaObligacion}" columns="20"
                                                disabled="true" id="tfFechaObligacion" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblPersona}" for="tfPersona" id="lblPersona"
                                                styleClass="label" text="Persona: "/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" maxLength="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblObligacion}" id="lblObligacion" for="taObligacion"
                                                      styleClass="label" text="Obligación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.taObligacion}" id="taObligacion" columns="60" rows="3"
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
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblDatosLiquidacion}" id="lblDatosLiquidacion"
											styleClass="label3" text="Datos de la Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblCuotaLiquidada}" id="lblCuotaLiquidada"
											for="tfCuotaLiquidada" styleClass="label" text="Cuota Liquidada" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfCuotaLiquidada}" disabled="true" id="tfCuotaLiquidada"
											styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblMonto}" id="lblMonto" for="tfMonto" styleClass="label2"
											text="Monto " />
									</td>
									<td>
										<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfMonto}" id="tfMonto" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblEstado}" id="lblEstado" styleClass="label2" for="tfEstado"
											text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfEstado}" id="tfEstado" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.lblFechaCancelacion}" id="lblFechaCancelacion"
											for="tfFechaCancelacion" styleClass="label" text="Fecha de Cancelación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.tfFechaCancelacion}" id="tfFechaCancelacion"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.btnCancelar_action}"
											binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.idSubSesion}" />
					<ui:script binding="#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
