<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMDomicilio$AgregarDomicilio.page1}" id="page1">
			<ui:html binding="#{comunes$ABMDomicilio$AgregarDomicilio.html1}" id="html1">
			<ui:head binding="#{comunes$ABMDomicilio$AgregarDomicilio.head1}" id="head1" title="Agregar Domicilio">
				<ui:link binding="#{comunes$ABMDomicilio$AgregarDomicilio.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{comunes$ABMDomicilio$AgregarDomicilio.body1}" focus="form1:btnSeleccionarLocalidad" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMDomicilio$AgregarDomicilio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{comunes$ABMDomicilio$AgregarDomicilio.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{comunes$ABMDomicilio$AgregarDomicilio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label15}" for="tfLocalidad" id="label15" styleClass="label"
											text="Localidad" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfLocalidad}" columns="40" disabled="true" id="tfLocalidad"
											styleClass="textField" />
										<ui:button action="#{comunes$ABMDomicilio$AgregarDomicilio.btnSeleccionarLocalidad_action}"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnSeleccionarLocalidad}" escape="false" id="btnSeleccionarLocalidad"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarLocalidad" reRender="form1:tfLocalidad"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnLimpiarLocalidad}"
											action="#{comunes$ABMDomicilio$AgregarDomicilio.btnLimpiarLocalidad_action}" styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label16}" for="tfCalle" id="label16" styleClass="label"
											text="Calle / Ruta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfCalle}" columns="40" id="tfCalle" styleClass="textField" />
										<ui:dropDown binding="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalle}" id="ddCalle"
											items="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalleOptions.options}" styleClass="textField"
											valueChangeListener="#{comunes$ABMDomicilio$AgregarDomicilio.valueChangeEvent(event)}" onChange="this.form.submit()" />
										<ui:button action="#{comunes$ABMDomicilio$AgregarDomicilio.btnSeleccionarCalle_action}"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle, form1.ddCalle, form1:ddCalleComienza, form1:ddCalleFinaliza"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnLimpiarCalle}"
											action="#{comunes$ABMDomicilio$AgregarDomicilio.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="left" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label17}" for="tfNumero" id="label17" styleClass="label"
											text="Número / Km" />
									</td>
									<td nowrap="nowrap" align="left">
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfNumero}" columns="10" id="tfNumero" styleClass="textField" />

										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label25}" for="ddCalleComienza" id="label25" style="margin-left: 15px"
											styleClass="label" text="Calle Comienza" />

										<ui:dropDown binding="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalleComienza}" id="ddCalleComienza"
											items="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalleComienzaOptions.options}" styleClass="textField"
											valueChangeListener="#{comunes$ABMDomicilio$AgregarDomicilio.valueChangeEventDdCalleComienza(event)}"
											onChange="this.form.submit()" />
									</td>
									<td nowrap="nowrap" align="right">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label26}" for="ddCalleTermina" id="label26" styleClass="label"
											text="Calle Termina" />
									</td>
									<td nowrap="nowrap" align="left">
										<ui:dropDown binding="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalleFinaliza}" id="ddCalleTermina"
											items="#{comunes$ABMDomicilio$AgregarDomicilio.ddCalleFinalizaOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label18}" for="tfPiso" id="label18" styleClass="label" text="Piso" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfPiso}" columns="10" id="tfPiso" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label19}" for="tfDepartamento" id="label19" styleClass="label"
											text="Departamento/Oficina" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfDepartamento}" id="tfDepartamento" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label20}" for="tfBarrio" id="label20" styleClass="label" text="Barrio" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfBarrio}" id="tfBarrio" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label21}" for="tfManzana" id="label21" styleClass="label"
											text="Manzana" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfManzana}" id="tfManzana" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label23}" for="tfTorre" id="label23" styleClass="label" text="Torre" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfTorre}" id="tfTorre" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label24}" for="tfEscalera" id="label24" styleClass="label"
											text="Escalera" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfEscalera}" id="tfEscalera" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMDomicilio$AgregarDomicilio.label22}" for="tfSector" id="label22" styleClass="label" text="Sector" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMDomicilio$AgregarDomicilio.tfSector}" id="tfSector" styleClass="textField" />
									</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{comunes$ABMDomicilio$AgregarDomicilio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{comunes$ABMDomicilio$AgregarDomicilio.btnGuardar_action}"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnGuardar}" id="btnGuardar" styleClass="button" text="Aceptar" />
										<ui:staticText binding="#{comunes$ABMDomicilio$AgregarDomicilio.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{comunes$ABMDomicilio$AgregarDomicilio.btnCancelar_action}"
											binding="#{comunes$ABMDomicilio$AgregarDomicilio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<script>
                            document.getElementById('form1:btnSeleccionarLocalidad').focus();
                        </script>
					<ui:hiddenField binding="#{comunes$ABMDomicilio$AgregarDomicilio.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMDomicilio$AgregarDomicilio.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMDomicilio$AgregarDomicilio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMDomicilio$AgregarDomicilio.idSubSesion}" />
					<ui:script binding="#{comunes$ABMDomicilio$AgregarDomicilio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
