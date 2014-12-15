<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.page1}" id="page1">
            <ui:html binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.html1}" id="html1">
                <ui:head binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.head1}" id="head1" title="Consultar Municipalidad">
                    <ui:link binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.tfNombre}" columns="40" disabled="true"
                                                id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.label5}" for="tfTelefono" id="label5"
                                                styleClass="label" text="Teléfono"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.tfTelefono}" columns="30" disabled="true"
                                                id="tfTelefono" styleClass="textFieldDisabled"/>
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
                                            <ui:label binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.label13}" id="label13" styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td colspan="3"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:staticText binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.stDomicilio}" escape="false"
                                                id="stDomicilio" styleClass="staticText "/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.btnVolver_action}"
                                                binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.idPagina}"/>
                        <ui:hiddenField binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.hidIdSubSesion}" id="hidIdSubSesion" text="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.idSubSesion}"/>
                        <ui:script binding="#{framework$ABMMunicipalidad$ConsultarMunicipalidad.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
