<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.page1}" id="page1">
			<ui:html binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.html1}" id="html1">
			<ui:head binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.head1}" id="head1">
				<ui:link binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					function agregarPropiedad(pId) {
						var opciones = document.getElementById(pId).options;
						var indice = document.getElementById(pId).selectedIndex;

						var taToString = document.getElementById("form1:taToString");
						taToString.value = taToString.value + "$" + opciones[indice].text + "$ ";

						taToString.focus();
					}
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')" focus="form1:tfDescripcion">
				<ui:form binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lblRecurso}" for="tfRecurso" id="lblRecurso"
											styleClass="label" text="Recurso" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tfRecurso}" columns="40" disabled="true"
											id="tfRecurso" styleClass="textFieldDisabled" />
										<ui:button action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnSeleccionarRecurso_action}"
											binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnSeleccionarRecurso}" escape="false"
											id="btnSeleccionarRecurso" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarRecurso" reRender="form1:tfRecurso" title="Limpiar"
											binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnLimpiarRecurso}"
											action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnLimpiarRecurso_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lblToString}" for="taToString" id="lblToString"
											styleClass="label" text="toString" />
									</td>
									<td nowrap="nowrap" colspan="2">
										<ui:textArea binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.taToString}" id="taToString" columns="40"
											rows="4" maxLength="500" />
										<ui:listbox binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lbPropiedades}" id="lbPropiedades"
											items="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lbPropiedadesOptions.options}"
											onDblClick="agregarPropiedad(this.id);" rows="5" style="width:300px" styleClass="textField"
											toolTip="Haga doble clic para insertar una propiedad." />
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<h:outputText id="otAyudaToString" escape="false"
											value="Utilice los atributos de la entidad entre signos $. Para hacer uso del signo $ literalmente, usar '$'.&lt;br&gt; &lt;u&gt;Ejemplo&lt;/u&gt;: El nombre es: $Nombre$, tiene $Edad$ años y su sueldo es de '$' $Sueldo$." />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lblNombreAlias}" for="tfNombreAlias"
											id="lblNombreAlias" styleClass="label" text="Nombre Alias" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tfNombreAlias}" columns="40" id="tfDescripcion"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.lblOrden}" for="tfOrden" id="lblOrden"
											styleClass="label" text="Orden" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tfOrden}" columns="10" id="tfOrden"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
										<div class="div" style="width: 250px; height: 15px;">Atributos Tabla</div>
										<ui:table id="table1" width="500" style="-moz-border-radius: 0px 0px 5px 5px; padding: 10px 5px;">
											<ui:tableRowGroup id="tableRowGroup" binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tableRowGroup1}"
												sourceData="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.ldpConjuntoAtributosTabla}" sourceVar="currentRow">
												<ui:tableColumn align="center" id="tableColumn1"
													binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tableColumn1}" valign="middle" width="10">
													<ui:radioButton id="radioButton1" binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.radioButton1}" label=""
														name="buttonGroup" selected="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.RBSelected1}"
														selectedValue="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn2" headerText="Usuarios" sort=""
													binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tableColumn2}">
													<ui:textArea id="textArea1" binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.textArea1}"
														text="#{currentRow.value['stringUsuarios']}" disabled="true" styleClass="textFieldDisabled" rows="2" columns="20" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn3" headerText="Columnas" sort=""
													binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.tableColumn3}">
													<ui:textArea id="textArea2" binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.textArea2}"
														text="#{currentRow.value['stringColumnas']}" disabled="true" styleClass="textFieldDisabled" rows="2" columns="50" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel1" binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.groupPanel1}">
													<ui:button id="btnAgregarConjuntoAtributosTabla" styleClass="button" text="Agregar" toolTip="Agregar Conjunto Atributos Tabla"
														binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnAgregarConjuntoAtributosTabla}"
														action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnAgregarConjuntoAtributosTabla_action}" />
													<ui:button id="btnModificarConjuntoAtributosTabla" styleClass="button" text="Modificar"
														toolTip="Modificar Conjunto Atributos Tabla"
														binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnModificarConjuntoAtributosTabla}"
														action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnModificarConjuntoAtributosTabla_action}" />
													<a4j:commandButton id="btnQuitarConjuntoAtributosTabla" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');"
														binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnQuitarConjuntoAtributosTabla}"
														action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnQuitarConjuntoAtributosTabla_action}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnGuardar_action}"
											binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnCancelar_action}"
											binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.idSubSesion}" />
					<ui:script binding="#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>