<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.page1}" id="page1">
			<ui:html binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.html1}" id="html1">
			<ui:head binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.head1}" id="head1">
				<ui:link binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')" focus="form1:tfDescripcion">
				<ui:form binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
										<div class="div" style="width: 250px; height: 15px;">Usuarios</div>
										<ui:table id="table2" width="500" style="-moz-border-radius: 0px 0px 5px 5px; padding: 10px 5px;">
											<ui:tableRowGroup id="tableRowGroup2" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableRowGroup2}"
												sourceData="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ldpUsuarios}" sourceVar="currentRowUser">
												<ui:tableColumn align="center" id="tableColumn6"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn6}" valign="middle" width="10">
													<ui:radioButton id="radioButton2" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.radioButton2}" label=""
														name="buttonGroupUser" selected="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.RBSelected2}"
														selectedValue="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn7" headerText="Usuario" sort="user"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn7}">
													<ui:staticText id="staticText1" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.staticText1}"
														text="#{currentRowUser.value['user']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel2" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.groupPanel2}">
													<ui:button id="btnAgregarUsuario" styleClass="button" text="Agregar" toolTip="Agregar Usuario"
														binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnAgregarUsuario}"
														action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnAgregarUsuario_action}" />
													<a4j:commandButton id="btnQuitarUsuario" value="Quitar" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '');"
														binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnQuitarUsuario}"
														action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnQuitarUsuario_action}" />
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
									<td colspan="2">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.label1}" id="label5" styleClass="label1"
											text="Opciones" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table id="table1" width="50%">
											<ui:tableRowGroup id="tableRowGroup1" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableRowGroup1}"
												sourceData="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ldpOpciones}" sourceVar="currentRowOpcion">
												<ui:tableColumn align="center" id="tableColumn1"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn1}" valign="middle" width="10">
													<ui:radioButton id="radioButton1" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.radioButton1}" label=""
														name="buttonGroup" selected="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.RBSelected1}"
														selectedValue="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn2" headerText="Nombre" sort="nombreAtributo"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn2}">
													<ui:label id="label2" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.label2}"
														text="#{currentRowOpcion.value['nombreAtributo']}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn3" headerText="Orden" sort="orden"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn3}">
													<ui:textField id="textField2" columns="4" styleClass="textField"
														binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.textField2}" text="#{currentRowOpcion.value['orden']}"
														onKeyPress="return ValidarNum(event)" maxLength="4" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn4" headerText="Alias" sort="nombreAtributoTabla"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn4}">
													<ui:textField id="textField3" columns="40" styleClass="textField"
														binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.textField3}"
														text="#{currentRowOpcion.value['nombreAtributoTabla']}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn5" headerText="Tipo" sort="tipoDato"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn5}">
													<ui:dropDown binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ddTipoDato}" id="ddTipoDato"
														items="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ddTipoDatoOptions.options}" styleClass="textField"
														selected="#{currentRowOpcion.value['tipoDato']}" converter="EnumConverter" immediate="false" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn8" headerText="Ancho" sort="anchoColumna"
													binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.tableColumn8}">
													<ui:textField id="textField4" columns="6" styleClass="textField"
														binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.textField4}"
														text="#{currentRowOpcion.value['anchoColumna']}" onKeyPress="return ValidarNum(event)" maxLength="6" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel1" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.groupPanel1}">
													<ui:dropDown id="ddOpciones" binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ddOpciones}"
														items="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.ddOpcionesOptions.options}" styleClass="textField" />
													<ui:staticText text=" | " escape="false"
														id="staticText1" styleClass="barraSeparadoraVertical" />
													<a4j:commandButton binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnAgregarOpcion}"
														action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnAgregarOpcion_action}" id="btnAgregarOpcion"
														value="Agregar" styleClass="btnAjax" reRender="table1" />
													<a4j:commandButton binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnQuitarOpcion}"
														action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnQuitarOpcion_action}" id="btnQuitarOpcion"
														value="Quitar" styleClass="btnAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnGuardar_action}"
											binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnCancelar_action}"
											binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.idSubSesion}" />
					<ui:script binding="#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>