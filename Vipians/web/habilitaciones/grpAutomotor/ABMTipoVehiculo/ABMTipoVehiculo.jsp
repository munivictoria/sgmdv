<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.body1}" focus="form1:tfCodigo" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.lblCodigo}" for="tfCodigo" id="lblCodigo" styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.tfCodigo}" id="tfCodigo" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.lblDescripcion}" for="tfDescripcion" id="lblDescripcion" styleClass="label" text="Descripcion" />                 
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.taDescripcion}" id="taDescripcion" columns="40" styleClass="textField" maxLength="200"/>
                                        </td>
                                    </tr>
								<tr>
									<td><br> </br></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">
											Atributos Dinámicos</div>
										<table border="0" class="tablaInterna"
											style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4"><ui:panelGroup
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
							</tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.btnGuardar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.btnCancelar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
