<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.head1}" id="head1" >
                    <ui:link binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                    <ui:body binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.body1}" focus="form1:tfNombre" id="body1"
                             onLoad="parent.footer.location.reload();Init();Init2();"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.label4}" for="tfNombre"
                                                      id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.tfNombre}" columns="40"
                                                          id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.label1}"
                                                      for="ddTipoObligacion" id="label1" styleClass="label" text="Tipo de Obligación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.ddTipoObligacion}"
                                                         id="ddTipoObligacion"
                                                         items="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.ddTipoObligacionDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.label5}" for="taDescripcion"
                                                      id="label5" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.taDescripcion}"
                                                         columns="40" id="taDescripcion" rows="5" styleClass="textField" maxLength="1000"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.label2}" id="label2"
                                                      styleClass="label2" text="Composición de la Plantilla"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.gpBotones}" id="gpBotones">
                                                <ui:button
                                                    action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarDocumento_action}"
                                                    binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarDocumento}"
                                                    id="btnAgregarDocumento" styleClass="button" text="Agregar Documento"/>
                                                <ui:button
                                                    action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarPermiso_action}"
                                                    binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarPermiso}"
                                                    id="btnAgregarPermiso" styleClass="button" text="Agregar Permiso"/>
                                                <ui:button
                                                    action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarSellado_action}"
                                                    binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnAgregarSellado}"
                                                    id="btnAgregarSellado" styleClass="button" text="Agregar Sellado"/>
                                                <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.staticText1}"
                                                               escape="false" id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button
                                                    action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnQuitarElemento_action}"
                                                    binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnQuitarElemento}"
                                                    id="btnQuitarElemento" onClick="return(confirm(&quot;¿Está seguro de Quitar el Elemento?&quot;));" styleClass="button" text="Quitar Elemento"/>
                                            </ui:panelGroup>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:tree
                                                binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.trAgregarPlantillaObligacion}"
                                                clientSide="true" id="trAgregarPlantillaObligacion" styleClass="arbolABM">
                                                <ui:treeNode binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.tnRaiz}"
                                                             expanded="true" id="tnRaiz" text="Raíz de la Plantilla" url="#" actionListener="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.eventAction}" >
                                        <f:facet name="image" />
                                    </ui:treeNode>
                                </ui:tree>
                                </td>
                                </tr>
                                <tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
                                <tr>
                                    <td colspan="2">
                                        <ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.messageGroup}"
                                                         id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnGuardar_action}"
                                                       binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnGuardar}" id="btnGuardar"
                                                       styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.stSeparador}"
                                                           escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnCancelar_action}"
                                                       binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.btnCancelar}" id="btnCancelar"
                                                       styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.scriptFinalM1}" id="scriptFinalM1" url="/resources/javascript/addEvent.js"/>                        
                        <ui:script binding="#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
