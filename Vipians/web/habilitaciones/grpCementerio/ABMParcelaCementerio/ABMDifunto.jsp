<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.body1}" focus="form1:tfPatente" id="body1"
                         onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.lblPersona}" for="tfPersona" id="lblPersona" styleClass="label" text="Persona"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.tfPersona}" columns="40" disabled="true"
                                                id="tfPersona" styleClass="textField"/>
                                            <ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnSeleccionarPersona_action}"
                                                binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnSeleccionarPersona}" escape="false"
                                                id="btnSeleccionarPersona" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                        </td>
										<td align="right" nowrap="nowrap"><ui:label
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.lblFechaDeceso}"
												id="lblFechaDeceso" styleClass="label"
												text="Fecha Deceso" for="tfFechaDeceso" /></td>
										<td><ui:textField
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.tfFechaDeceso}"
												id="tfFechaDeceso" styleClass="textField"
												onKeyUp="mascara(this,'/',patronFecha,true)"
												maxLength="10" /></td>
									</tr>
									<tr>
									 <td align="right" nowrap="nowrap">
                                            <ui:label for="taDifunto" id="lblDifunto" styleClass="label" text="Difunto"/>
                                        </td>
										<td>
											<ui:textArea binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.taDifunto}"
												id="taDifunto" styleClass="textArea" columns="40"/> 
										</td>
									</tr>
                                    <tr>
												<td align="right" nowrap="nowrap"><ui:label
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.lblCausaDeceso}"
												id="lblCausaDeceso" styleClass="label"
												text="Causa Deceso" for="tfCausaDeceso" /></td>
										<td>
										<ui:textArea
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.taCausaDeceso}"
												id="taCausaDeceso" styleClass="textArea" columns="40"/> 
												</td>
									<td colspan="4" nowrap="nowrap">
										<table border="0" class="tablaInterna"
											style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
									<tr><td nowrap="nowrap">
									<ui:checkbox binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.ckbInmunoinfecciosa}" id="ckbInmunoinfecciosa" label="Inmunoinfección"/>
									<ui:checkbox binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.ckbCremado}" id="ckbCremado" label="Cremado"/>
									<ui:checkbox binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.ckbReducido}" id="ckbReducido" label="Reducido"/>
									</td></tr>
									</table>
									</td>
								</tr>
								<tr>
									<td colspan="2"><ui:messageGroup
											binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.messageGroup}"
											id="messageGroup" styleClass="grupoMsg" /></td>
								</tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnGuardar_action}"
                                                       binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnGuardar}" id="btnGuardar"
                                                       styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.stSeparador}" escape="false"
                                                           id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnCancelar_action}"
                                                       binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.btnCancelar}" id="btnCancelar"
                                                       styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>