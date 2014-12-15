<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.body1}" focus="form1:ddTipoObligacion" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.head1.title}"/>
                                </caption>
                                <tbody>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr> 
                                      <tr>
									<td align="right" nowrap="nowrap">
									<ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.lblTipoObligacion}"
											for="ddTipoObligacion" id="lblTipoObligacion" styleClass="label" text="Tipo Obligación" />
									</td>
									<td nowrap="nowrap">
									<ui:dropDown binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.ddTipoObligacion}"
											id="ddTipoObligacion" items="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.ddTipoObligacionDefaultOptions.options}"
											styleClass="textField" /></td>
											
											 <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.tfNombre}" id="tfNombre" styleClass="textField" columns="30" />
                                    </td>
								</tr>
								
								<tr>
								<td align="right" nowrap="nowrap" >
									<ui:label text="Plan por defecto"  styleClass="label" id="lblPlanPorDefecto" /> 
								</td>
								<td>
								<ui:checkbox binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.cbPlanPorDefecto}" id="cbPlanPorDefecto"></ui:checkbox>
								</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
							</tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.btnGuardar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.btnCancelar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
