<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.page1}" id="page1">
			<ui:html binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.html1}" id="html1">
			<ui:head binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.head1}" id="head1"
				title="Consultar Liquidación: Tasa Menor">
				<ui:link binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblDatosObligacion}"
											id="lblDatosObligacion" styleClass="label3" text="Datos de la Obligación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblTituloPeriodo}" id="lblTituloPeriodo"
											styleClass="label2" text="Período" />
									</td>
									<td>
										<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.tfPeriodo}" columns="20" id="tfPeriodo"
											maxLength="4" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblObligacion}" id="lblObligacion"
											styleClass="label2" text="Obligación" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taObligacion}" id="taObligacion"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblFormula}" id="lblFormula"
											styleClass="label2" text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taFormula}" id="taFormula" columns="60"
											rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblParametros}" id="lblParametros"
											styleClass="label2" text="Parámetros" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taParametros}" id="taParametros"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblParametrosValuadosAlicuota}"
											id="lblParametrosValuadosAlicuota" styleClass="label2" text="Parámetros Valuados Alicuota" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taParametrosValuadosAlicuota}"
											id="taParametrosValuadosAlicuota" columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblModificadores}" id="lblModificadores"
											styleClass="label2" text="Modificadores" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taModificadores}" id="taModificadores"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblVencimientos}" id="lblVencimientos"
											styleClass="label2" text="Vencimientos" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taVencimientos}" id="taVencimientos"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblExencion}" id="lblExencion"
											styleClass="label2" text="Exención" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.taExencion}" id="taExencion"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblDatosLiquidacion}"
											id="lblDatosLiquidacion" styleClass="label3" text="Datos de la Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblCuotaLiquidada}" id="lblCuotaLiquidada"
											for="tfCuotaLiquidada" styleClass="label" text="Cuota Liquidada" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.tfCuotaLiquidada}" disabled="true"
											id="tfCuotaLiquidada" styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblMonto}" id="lblMonto" for="tfMonto"
											styleClass="label2" text="Monto " />
									</td>
									<td>
										<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.tfMonto}" id="tfMonto"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblEstado}" id="lblEstado"
											styleClass="label2" for="tfEstado" text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.tfEstado}" id="tfEstado"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.lblFechaCancelacion}"
											id="lblFechaCancelacion" for="tfFechaCancelacion" styleClass="label" text="Fecha de Cancelación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.tfFechaCancelacion}"
											id="tfFechaCancelacion" styleClass="textField" disabled="true" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.btnCancelar_action}"
											binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.idSubSesion}" />
					<ui:script binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$ABMLiquidacionTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
