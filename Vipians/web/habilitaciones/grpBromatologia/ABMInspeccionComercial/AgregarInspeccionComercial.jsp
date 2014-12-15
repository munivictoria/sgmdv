<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.head1}" id="head1"
				title="Agregar Inspección Local Comercial">
				<ui:link binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script type="text/javascript">
                                 function ValidarFecha(e){
                                   tecla = (document.all) ? e.keyCode : e.which;
                                    if (tecla == 8) return true;
                                   
                                   patron =/[0-9/]/;
                                   te = String.fromCharCode(tecla);

                                   return patron.test(te);
                                 }
                          </script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.body1}" focus="form1:tfFecha"
				id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.label4}" for="tfInspector"
											id="label4" styleClass="label" text="Inspector" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.tfInspector}"
											columns="40" disabled="true" id="tfInspector" styleClass="textField" />
										<ui:button
											action="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnSeleccionarInspector_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnSeleccionarInspector}"
											escape="false" id="btnSeleccionarInspector" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarInspector" reRender="form1:tfInspector"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnLimpiarInspector}"
											action="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnLimpiarInspector_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.label2}" for="tfFecha"
											id="label2" styleClass="label" text="Fecha" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.tfFecha}"
											onKeyPress="return ValidarFecha(event)" id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)"
											maxLength="10" />
										<!--<ui:staticText
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.label5}" for="ddEstado"
											id="label5" styleClass="label" text="Estado" />
									</td>
									<td>
										<ui:dropDown binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.ddEstado}" id="ddEstado"
											items="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.ddEstadoDefaultOptions.options}"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.label3}" for="taDescripcion"
											id="label3" styleClass="label" text="Descripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.taDescripcion}"
											columns="40" id="taDescripcion" rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnGuardar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnGuardar}" id="btnGuardar"
											styleClass="button" text="Aceptar" />
										<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.stSeparador}"
											escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnCancelar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$AgregarInspeccionComercial.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
