<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMDomicilio$ModificarDomicilio.page1}" id="page1">
			<ui:html binding="#{comunes$ABMDomicilio$ModificarDomicilio.html1}" id="html1">
			<ui:head binding="#{comunes$ABMDomicilio$ModificarDomicilio.head1}" id="head1" title="Modificar Domicilio">
				<ui:link binding="#{comunes$ABMDomicilio$ModificarDomicilio.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{comunes$ABMDomicilio$ModificarDomicilio.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMDomicilio$ModificarDomicilio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{comunes$ABMDomicilio$ModificarDomicilio.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{comunes$ABMDomicilio$ModificarDomicilio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label15}" for="tfLocalidad" id="label15" styleClass="label"
											text="Localidad" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfLocalidad}" columns="40" disabled="true" id="tfLocalidad"
											styleClass="textField" />
										<ui:button action="#{comunes$ABMDomicilio$ModificarDomicilio.btnSeleccionarLocalidad_action}"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnSeleccionarLocalidad}" escape="false" id="btnSeleccionarLocalidad"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarLocalidad" reRender="form1:tfLocalidad"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnLimpiarLocalidad}"
											action="#{comunes$ABMDomicilio$ModificarDomicilio.btnLimpiarLocalidad_action}" styleClass="buttonLimpiarAjax" />
									</td>

									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label16}" for="tfCalle" id="label16" styleClass="label"
											text="Calle / Ruta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfCalle}" columns="40" id="tfCalle" styleClass="textField" />
										<ui:dropDown binding="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalle}" id="ddCalle"
											items="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalleOptions.options}" styleClass="textField"
											valueChangeListener="#{comunes$ABMDomicilio$ModificarDomicilio.valueChangeEvent(event)}" onChange="this.form.submit()" />
										<ui:button action="#{comunes$ABMDomicilio$ModificarDomicilio.btnSeleccionarCalle_action}"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle, form1:ddCalle, form1:ddCalleComienza, form1:ddCalleTermina"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnLimpiarCalle}"
											action="#{comunes$ABMDomicilio$ModificarDomicilio.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label17}" for="tfNumero" id="label17" styleClass="label"
											text="Número / Km" />
									</td>
									<td align="left" nowrap="nowrap">
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfNumero}" columns="10" id="tfNumero" styleClass="textField" />
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label25}" for="ddCalleComienza" id="label25" styleClass="label"
											text="Calle Comienza" style="margin-left: 15px" />
										<ui:dropDown binding="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalleComienza}" id="ddCalleComienza"
											items="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalleComienzaOptions.options}" styleClass="textField"
											valueChangeListener="#{comunes$ABMDomicilio$ModificarDomicilio.valueChangeEventDdCalleComienza(event)}"
											onChange="this.form.submit()" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label26}" for="ddCalleTermina" id="label26" styleClass="label"
											text="Calle Termina " />
									</td>
									<td nowrap="nowrap" align="left">
										<ui:dropDown binding="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalleFinaliza}" id="ddCalleTermina"
											items="#{comunes$ABMDomicilio$ModificarDomicilio.ddCalleFinalizaOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label18}" for="tfPiso" id="label18" styleClass="label" text="Piso" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfPiso}" columns="10" id="tfPiso" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label19}" for="tfDepartamento" id="label19" styleClass="label"
											text="Departamento" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfDepartamento}" id="tfDepartamento" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label20}" for="tfBarrio" id="label20" styleClass="label"
											text="Barrio" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfBarrio}" id="tfBarrio" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label21}" for="tfManzana" id="label21" styleClass="label"
											text="Manzana" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfManzana}" id="tfManzana" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label23}" for="tfTorre" id="label23" styleClass="label" text="Torre" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfTorre}" id="tfTorre" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label24}" for="tfEscalera" id="label24" styleClass="label"
											text="Escalera" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfEscalera}" id="tfEscalera" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$ModificarDomicilio.label22}" for="tfSector" id="label22" styleClass="label"
											text="Sector" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$ModificarDomicilio.tfSector}" id="tfSector" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{comunes$ABMDomicilio$ModificarDomicilio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{comunes$ABMDomicilio$ModificarDomicilio.btnGuardar_action}"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnGuardar}" id="btnGuardar" styleClass="button" text="Aceptar" />
										<ui:staticText binding="#{comunes$ABMDomicilio$ModificarDomicilio.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{comunes$ABMDomicilio$ModificarDomicilio.btnCancelar_action}"
											binding="#{comunes$ABMDomicilio$ModificarDomicilio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{comunes$ABMDomicilio$ModificarDomicilio.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMDomicilio$ModificarDomicilio.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMDomicilio$ModificarDomicilio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMDomicilio$ModificarDomicilio.idSubSesion}" />
					<ui:script binding="#{comunes$ABMDomicilio$ModificarDomicilio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
