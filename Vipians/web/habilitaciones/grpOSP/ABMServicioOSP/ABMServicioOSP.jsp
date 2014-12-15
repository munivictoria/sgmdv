<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					function cambiarEstado() {

						Medido = document.getElementById("form1:cbMedido");
						Unidad = document.getElementById("form1:ddUnidadMedida");
						BaseConsumo = document.getElementById("form1:tfBaseConsumo");
						ValorExcedente = document.getElementById("form1:tfValorPorExcedente");

						if(Medido.checked == true) {
							Unidad.disabled = false;
							Unidad.style.background = '#FFFFFF';
							BaseConsumo.disabled = false;
							BaseConsumo.style.background = '#FFFFFF';
							ValorExcedente.disabled = false;
							ValorExcedente.style.background = '#FFFFFF';
						} else {
							Unidad.disabled = true;
							Unidad.style.background = '#DDDDDD';

							BaseConsumo.value = "";
							BaseConsumo.disabled = true;
							BaseConsumo.style.background = '#DDDDDD';

							ValorExcedente.value = "";
							ValorExcedente.disabled = true;
							ValorExcedente.style.background = '#DDDDDD';

						}
					}
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();cambiarEstado();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label4}" for="tfCodigo" id="label4" styleClass="label"
											text="Código" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfCodigo}" columns="10" id="tfCodigo"
											onKeyPress="return ValidarNum(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label8}" for="tfCoeficienteCodigo" id="label8"
											styleClass="label" text="Coeficiente Código de Servicio" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfCoeficienteCodigo}" columns="10"
											id="tfCoeficienteCodigo" onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.lblCoeficienteValorTerreno}"
											for="tfCoeficienteValorTerreno" id="lblCoeficienteValorTerreno" styleClass="label" text="Coeficiente Valor de Terreno" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfCoeficienteValorTerreno}" columns="10"
											id="tfCoeficienteValorTerreno" onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.lblCoeficienteValorEdificado}"
											for="tfCoeficienteValorEdificado" id="lblCoeficienteValorEdificado" styleClass="label" text="Coeficiente Valor Edificado" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfCoeficienteValorEdificado}" columns="10"
											id="tfCoeficienteValorEdificado" onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label5}" for="taNombre" id="label5" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.taNombre}" columns="40" id="taNombre" rows="5"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label2}" for="tfValor" id="label2" styleClass="label"
											text="Valor Mínimo" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfValor}" columns="10" id="tfValor"
											onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.staticText1}" escape="false" id="staticText1"
											styleClass="label" text="&amp;nbsp;[$]" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddPeriodicidad" id="lblPeriodicidad" styleClass="label" text="Periodicidad" />
									</td>
									<td colspan="2">
										<ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.ddPeriodicidad}" id="ddPeriodicidad"
											items="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.ddPeriodicidadDefaultOptions.options}" styleClass="TextField" />
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
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label3}" for="cbMedido" id="label3" styleClass="label"
											text="Medido" />
									</td>
									<td>
										<ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.cbMedido}" onClick="cambiarEstado()" id="cbMedido" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label7}" for="ddUnidadMedida" id="label7"
											styleClass="label" text="Unidad de Medida" />
									</td>
									<td>
										<ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.ddUnidadMedida}" id="ddUnidadMedida"
											items="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.ddUnidadMedidaDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label1}" for="tfBaseConsumo" id="label1"
											styleClass="label" text="Base de Consumo" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfBaseConsumo}" columns="10" id="tfBaseConsumo"
											onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.label6}" for="tfValorPorExcedente" id="label6"
											styleClass="label" text="Valor del Excedente" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tfValorPorExcedente}" columns="10"
											onKeyPress="return ValidarFloat(event,this)" id="tfValorPorExcedente" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.staticText2}" escape="false" id="staticText2"
											styleClass="label" text="&amp;nbsp;[$]" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.lblVolcadoEfluentesIndustriales}"
											for="cbVolcadoEfluentesIndustriales" id="lblVolcadoEfluentesIndustriales" styleClass="label"
											text="Volcado Efluentes Industriales" />
									</td>
									<td>
										<ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.cbVolcadoEfluentesIndustriales}"
											id="cbVolcadoEfluentesIndustriales" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.panelAtributoDinamico}"
														id="panelAtributoDinamico">
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
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.btnGuardar_action}"
											binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.btnCancelar_action}"
											binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
