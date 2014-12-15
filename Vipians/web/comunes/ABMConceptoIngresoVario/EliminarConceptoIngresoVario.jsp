<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.page1}" id="page1">
            <ui:html binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.html1}" id="html1">
                <ui:head binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.head1}" id="head1" title="Eliminar Concepto de Ingreso Vario">
                    <ui:link binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.tfNombre}" columns="40" disabled="true"
                                                id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.label5}" for="taDescripcion" id="label5"
                                                styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.taDescripcion}" columns="40"
                                                disabled="true" id="taDescripcion" rows="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.label1}" for="tfValorPorDefecto" id="label1"
                                                styleClass="label" text="Valor por Defecto"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.tfValorPorDefecto}" columns="10"
                                                disabled="true" id="tfValorPorDefecto" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.btnEliminar_action}"
                                                binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.btnCancelar_action}"
                                                binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.hidIdPagina}" id="hidIdPagina" text="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.idSubSesion}"/>
                        <ui:script binding="#{comunes$ABMConceptoIngresoVario$EliminarConceptoIngresoVario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
