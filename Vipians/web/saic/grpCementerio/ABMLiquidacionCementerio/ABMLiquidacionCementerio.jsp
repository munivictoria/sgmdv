<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.page1}" id="page1">
			<ui:html binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.html1}" id="html1">
			<ui:head binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.head1}" id="head1" >
				<ui:link binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblDatosObligacion}"
											id="lblDatosObligacion" styleClass="label3" text="Datos de la Obligación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblTituloPeriodo}" id="lblTituloPeriodo"
											styleClass="label2" text="Período" />
									</td>
									<td>
										<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.tfPeriodo}" columns="20"
											id="tfPeriodo" maxLength="4" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblObligacion}" id="lblObligacion"
											styleClass="label2" text="Obligación" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taObligacion}" id="taObligacion"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblFormula}" id="lblFormula"
											styleClass="label2" text="Fórmula" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taFormula}" id="taFormula"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblParametros}" id="lblParametros"
											styleClass="label2" text="Parámetros" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taParametros}" id="taParametros"
											columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblParametrosValuadosAlicuota}"
											id="lblParametrosValuadosAlicuota" styleClass="label2" text="Parámetros Valuados Alícuota" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taParametrosValuadosAlicuota}"
											id="taParametrosValuadosAlicuota" columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblModificadores}" id="lblModificadores"
											styleClass="label2" text="Modificadores" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taModificadores}"
											id="taModificadores" columns="60" rows="3" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblVencimientos}" id="lblVencimientos"
											styleClass="label2" text="Vencimientos" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taVencimientos}" id="taVencimientos"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblExencion}" id="lblExencion"
											styleClass="label2" text="Exención" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.taExencion}" id="taExencion"
											styleClass="textField" disabled="true" columns="60" rows="3" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblDatosLiquidacion}"
											id="lblDatosLiquidacion" styleClass="label3" text="Datos de la Liquidación" />
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblCuotaLiquidada}"
											id="lblCuotaLiquidada" for="tfCuotaLiquidada" styleClass="label" text="Cuota Liquidada" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.tfCuotaLiquidada}" disabled="true"
											id="tfCuotaLiquidada" styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblMonto}" id="lblMonto" for="tfMonto"
											styleClass="label2" text="Monto " />
									</td>
									<td>
										<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.tfMonto}" id="tfMonto"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblEstado}" id="lblEstado"
											styleClass="label2" for="tfEstado" text="Estado" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.tfEstado}" id="tfEstado"
											styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.lblFechaCancelacion}"
											id="lblFechaCancelacion" for="tfFechaCancelacion" styleClass="label" text="Fecha de Cancelación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.tfFechaCancelacion}"
											id="tfFechaCancelacion" styleClass="textField" disabled="true" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.btnCancelar_action}"
											binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.idPagina}" />
					<ui:hiddenField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.idSubSesion}" />
					<ui:script binding="#{saic$grpCementerio$ABMLiquidacionCementerio$ABMLiquidacionCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
