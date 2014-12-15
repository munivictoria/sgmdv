<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.body1}" focus="form1:tfCodigo" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.lblCodigo}" for="tfCodigo" id="lblCodigo" styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.tfCodigo}" id="tfCodigo" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
								<tr>
									<td><br> </br></td>
								</tr>
								<tr>
								<td colspan="4">
                                        <div  class="div" style="width: 290px; height: 15px;"> Atributos Dinámicos </div>
                                        <table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
                                            <tr>
                                                <td colspan="4">         
                                                    <ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.panelAtributoDinamico}" id="panelAtributoDinamico">
                                                        <!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
                                                    </ui:panelGroup>
                                                </td>
                                            </tr> 
                                        </table>
                                    </td>
                                </tr>
                                <tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
							<!-- 	<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarcaS.tablaLogs}" id="tbLogsAuditoria"/></td></tr>   -->
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.btnGuardar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.btnCancelar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
