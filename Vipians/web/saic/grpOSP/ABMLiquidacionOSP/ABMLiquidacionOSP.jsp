<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.page1}" id="page1">
			<ui:html binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.html1}" id="html1">
			<ui:head binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.head1}" id="head1" >
				<ui:link binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblDatosObligacion}" id="lblDatosObligacion"
											styleClass="label3" text="Datos de la Obligación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblTituloPeriodo}" id="lblTituloPeriodo" styleClass="label2"
											text="Período" />
									</td>
									<td>
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.tfPeriodo}" columns="20" id="tfPeriodo" maxLength="4"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblObligacion}" id="lblObligacion" styleClass="label2"
											text="Obligación" />
									</td>
									<td>
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taObligacion}" id="taObligacion" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblFormula}" id="lblFormula" styleClass="label2"
											text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taFormula}" id="taFormula" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblParametros}" id="lblParametros" styleClass="label2"
											text="Parámetros" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taParametros}" id="taParametros" columns="60" rows="3"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblParametrosValuadosAlicuota}"
											id="lblParametrosValuadosAlicuota" styleClass="label2" text="Parámetros Valuados Alicuota" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taParametrosValuadosAlicuota}"
											id="taParametrosValuadosAlicuota" columns="70" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblAlicuotasLiquidadas}"
											id="lblAlicuotasLiquidadas" styleClass="label2" text="Alicuotas liquidadas" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taAlicuotasLiquidadas}"
											id="taAlicuotasLiquidadas" columns="70" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblModificadores}" id="lblModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taModificadores}" id="taModificadores" columns="60"
											rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblVencimientos}" id="lblVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taVencimientos}" id="taVencimientos"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblExencion}" id="lblExencion" styleClass="label2"
											text="Exención" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.taExencion}" id="taExencion" styleClass="textField"
											disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblDatosLiquidacion}" id="lblDatosLiquidacion"
											styleClass="label3" text="Datos de la Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblCuotaLiquidada}" id="lblCuotaLiquidada"
											for="tfCuotaLiquidada" styleClass="label" text="Cuota Liquidada" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.tfCuotaLiquidada}" disabled="true" id="tfCuotaLiquidada"
											styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblMonto}" id="lblMonto" for="tfMonto" styleClass="label2"
											text="Monto " />
									</td>
									<td>
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.tfMonto}" id="tfMonto" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblEstado}" id="lblEstado" styleClass="label2" for="tfEstado"
											text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.tfEstado}" id="tfEstado" styleClass="textField"
											disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.lblFechaCancelacion}" id="lblFechaCancelacion"
											for="tfFechaCancelacion" styleClass="label" text="Fecha de Cancelación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.tfFechaCancelacion}" id="tfFechaCancelacion"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.btnCancelar_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.idPagina}" />
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.idSubSesion}" />
					<ui:script binding="#{saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
