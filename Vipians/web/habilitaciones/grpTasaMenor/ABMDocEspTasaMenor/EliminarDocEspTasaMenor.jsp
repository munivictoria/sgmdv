<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.head1}" id="head1" title="Eliminar Obligación: Tasa Menor">
                    <ui:link binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.body1}" focus="form1:tfNumeroLibretaSanitaria" id="body1"
                         onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="Eliminar Obligación: Obligacion Tasa Menor"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.label5}" for="tfNombre" id="label5"
                                                      styleClass="label" text="Nombre del Documento"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.tfNombre}" columns="40"
                                                          disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.label4}" for="tfFechaInicio" id="label4"
                                                      styleClass="label" text="Inicio de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.tfFechaInicio}" disabled="true"
                                                          id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.staticText1}" escape="false"
                                                           id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.label6}" for="tfFechaCese" id="label6"
                                                      rendered="false" styleClass="label" text="Cese de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.tfFechaCese}" id="tfFechaCese"
                                                          maxLength="10" rendered="false" styleClass="textField"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.staticText2}" escape="false"
                                                           id="staticText2" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.label16}" for="tfPersona" id="label16"
                                                      styleClass="label" text="Persona Solicitante"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.tfPersona}" columns="40"
                                                          disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.lblParcela}" for="tfParcela"
                                                      id="lblParcela" styleClass="label" text="Parcela"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.tfParcela}" columns="40"
                                                          disabled="true" id="tfParcela" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.label1}" id="label1" styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td nowrap="nowrap"/>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td colspan="3">
                                            <ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.stDomicilioPostal}" escape="false"
                                                           id="stDomicilioPostal" styleClass="staticText"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div  class="div" style="width: 290px; height: 15px;"> Atributos Dinámicos </div>
                                            <table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
                                                <tr>
                                                    <td colspan="4">         
                                                        <ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.panelAtributoDinamico}" id="panelAtributoDinamico">
                                                            <!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
                                                        </ui:panelGroup>
                                                    </td>
                                                </tr> 
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.btnEliminar_action}"
                                                       binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.btnEliminar}" id="btnEliminar"
                                                       styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.stSeparador}" escape="false"
                                                           id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.btnCancelar_action}"
                                                       binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.btnCancelar}" id="btnCancelar"
                                                       styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$EliminarDocEspTasaMenor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
