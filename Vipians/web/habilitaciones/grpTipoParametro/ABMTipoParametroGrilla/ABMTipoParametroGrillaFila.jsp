<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" 
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.body1}" focus="form1:tfNumero" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.head1.title}"/>
                                </caption>
                                <tbody>
                                	<tr>
                                		<td>
	                                        <br/>
	                                    </td>
                                	</tr>
                                	<tr>
                                		<td align="right" nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.lbNumero}" for="tfNumero" id="lbNumero" styleClass="label" text="Numero" />
										</td>
										<td nowrap="nowrap">
											<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tfNumero}" id="tfNumero" styleClass="textField" columns="30" />
										</td>
                                	</tr>
                                	<tr>
                                		<td align="right" nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.lbCondicion}" for="taCondicion" id="lbCondicion" styleClass="label" text="Condicion" />
										</td>
										<td nowrap="nowrap">
											<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.taCondicion}" id="taCondicion" columns="60" rows="1" />
										</td>
                                	</tr>
                                	<tr>
		                               	<td nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.lblListaValores}" id="lblListaValores" styleClass="label2" text="Lista de valores" />
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnAgregarValor_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnAgregarValor}" 
											id="btnAgregarVariable" value="Agregar" styleClass="btnAjax" reRender="tableListaValores"/>

											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnQuitarValor_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnQuitarValor}" 
											id="btnQuitarVariable" onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));" 
											styleClass="btnAjax" reRender="tableListaValores" value="Quitar" />
										</td>
									</tr>
                                	<tr>
										<td colspan="4" nowrap="nowrap">
											<div style="overflow: auto; width: 790px;">
												<ui:table augmentTitle="false" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableListaValores}" id="tableListaValores">
													<ui:tableRowGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableRowGroupListaValores}" id="tableRowGroupListaValores" sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.ldpValores}" sourceVar="varValores">
														<ui:tableColumn align="center" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableColumn1}" id="tableColumn1" valign="middle" width="10">
															<ui:radioButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.radioButton1}" id="radioButton1" label="" name="buttonGroup1" selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.seleccion}" selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.var}" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableColumn2}" headerText="Numero" id="tableColumn2" sort="numero">
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tfNumeroValor}" id="tfNumeroValor" text="#{varValores.value['nroColumna']}" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableColumn3}" headerText="Condicion" id="tableColumn3" sort="condicion">
															<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.taCondicionValor}" id="taCondicionValor" text="#{varValores.value['condicion']}" styleClass="textFieldDisabled" columns="50" rows="1" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.tableColumn4}" headerText="Valor" id="tableColumn4" sort="valor">
															<ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.taValor}" id="taValor"  columns="30" rows="1" styleClass="textFieldDisabled" text="#{varValores.value['valor']}" />
														</ui:tableColumn>
													</ui:tableRowGroup>
												</ui:table>
											</div>
										</td>
									</tr>
                                	
                                </tbody>
                                <tfoot>
                                   <tr>
                                       <td align="right" colspan="4" nowrap="true">
                                           <ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnGuardar_action}"
                                               binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                           <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                           <ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnCancelar_action}"
                                               binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                       </td>
                                   </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>     
                  </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>