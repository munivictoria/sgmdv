<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" 
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.head1.title}"/>
                                </caption>
                                <tbody>
	                                <tr>
	                                    <td>
	                                        <br/>
	                                    </td>
	                                </tr> 
	                                <tr>
	                                	<td align="right" nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
										</td>
										<td nowrap="nowrap">
											<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tfNombre}" id="tfNombre" styleClass="textField" columns="30" />
										</td>
	                                </tr>
	                                <tr>
		                               	<td nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.lblListaVariables}" id="lblListaVariables" styleClass="label2" text="Variables" />
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnAgregarVariable_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnAgregarVariable}" 
											id="btnAgregarVariable" value="Agregar" styleClass="btnAjax" reRender="tableListaVariables"/>
											
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnQuitarVariable_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnQuitarVariable}" 
											id="btnQuitarVariable" onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));" 
											styleClass="btnAjax" reRender="tableListaVariables" value="Quitar" />
										</td>
									</tr>
									<tr>
										<td colspan="4" nowrap="nowrap">
											<div style="overflow: auto; width: 790px;">
												<ui:table augmentTitle="false" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableListaVariables}" id="tableListaVariables">
													<ui:tableRowGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableRowGroupListaVariables}" id="tableRowGroupListaVariables" sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.ldpVariables}" sourceVar="var">
														<ui:tableColumn align="center" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn1}" id="tableColumn1" valign="middle" width="10">
															<ui:radioButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.radioButton1}" id="radioButton1" label="" name="buttonGroup1" selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.seleccion}" selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.var}" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn2}" headerText="Nombre" id="tableColumn2" sort="nombre">
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tfNombreVariable}" id="tfNombreVariable" text="#{var.value['nombre']}" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn3}" headerText="Descripcion" id="tableColumn3" sort="descripcion">
															<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.taDescripcion}" id="taDescripcion" text="#{var.value['descripcion']}" columns="60" rows="3" />
														</ui:tableColumn>
													</ui:tableRowGroup>
												</ui:table>
											</div>
										</td>
									</tr>
									<tr>
                                		<td colspan="4">
                                			<hr></hr>
                                		</td>
                                	</tr>
									<tr>
										<td colspan="4" nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.lblListaFilas}" id="lblListaFilas" styleClass="label2" text="Filas" />
										</td>
									</tr>
									<tr>
										<td colspan="4" nowrap="nowrap">
											<ui:table augmentTitle="false" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableListaFila}" id="tableListaFila" width="604">
											<ui:tableRowGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableRowGroupListaFila}" id="tableRowGroupListaFila" sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.ldpFilas}" sourceVar="varFilas">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn4}" id="tableColumn4" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.radioButton2}" id="radioButton2" label="" name="buttonGroup2" selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.seleccionFila}" selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.varFilas}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Número" id="tcNumero" sort="nroFila">
													<ui:staticText id="stNumero" text="#{varFilas.value['nroFila']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn5}" headerText="Condicion" id="tableColumn5" sort="condicion">
													<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.taCondicion}" id="taCondicion" styleClass="textFieldDisabled" columns="50" rows="1" disabled="true" text="#{varFilas.value['condicion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.tableColumn6}" headerText="Valores" id="tableColumn6" sort="valor">
													<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.taValor}" columns="50" rows="1" disabled="true" id="taValor" styleClass="textFieldDisabled" text="#{varFilas.value['stringColumnas']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.groupPanel1}" id="groupPanel1">
													<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnAgregarFila_action}" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnAgregarFila}" id="btnAgregarFila" styleClass="btnAjax" value="Agregar" reRender="tableListaFila" />
													<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnModificarFila_action}" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnModificarFila}" id="btnModificarFila" styleClass="btnAjax" value="Modificar" reRender="tableListaFila" />
													<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnQuitarFila_action}" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnQuitarFila}" id="btnQuitarFila" onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar el Interes?&quot;));" styleClass="btnAjax" reRender="tableListaFila" value="Quitar" />
													<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnClonarFila_action}" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnClonarFila}" id="btnClonarFila" styleClass="btnAjax" reRender="tableListaFila" value="Clonar fila" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.stSeparadorLocal}" escape="false" id="stSeparadorLocal" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
													<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnConsultarFila_action}" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnConsultarFila}" id="btnConsultarFila" styleClass="btnAjax" reRender="tableListaFila" value="Consultar" />
												</ui:panelGroup>
											</f:facet>
											</ui:table>
										</td>
									</tr>
									 <tr>
										<td colspan="4">
											<a4j:outputPanel ajaxRendered="true">
												<ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
											</a4j:outputPanel>
										</td>
									</tr>
                                </tbody>
                                 <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnGuardar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnCancelar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>     
                  </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>