<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$ABMDomicilio$ConsultarDomicilio.page1}" id="page1">
            <ui:html binding="#{comunes$ABMDomicilio$ConsultarDomicilio.html1}" id="html1">
                <ui:head binding="#{comunes$ABMDomicilio$ConsultarDomicilio.head1}" id="head1" title="Consultar Domicilio">
                    <ui:link binding="#{comunes$ABMDomicilio$ConsultarDomicilio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$ABMDomicilio$ConsultarDomicilio.body1}" focus="form1:tfNumero" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(242, 242, 242); -rave-layout: grid">
                    <ui:form binding="#{comunes$ABMDomicilio$ConsultarDomicilio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{comunes$ABMDomicilio$ConsultarDomicilio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$ABMDomicilio$ConsultarDomicilio.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label15}" for="tfLocalidad" id="label15"
                                                styleClass="label" text="Localidad"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfLocalidad}" columns="40" disabled="true"
                                                id="tfLocalidad" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label16}" for="tfCalle" id="label16" styleClass="label" text="Calle / Ruta"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfCalle}" columns="40" id="tfCalle" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label17}" for="tfNumero" id="label17"
                                                styleClass="label" text="Número / Km"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfNumero}" columns="10" id="tfNumero" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label1}" id="label1" styleClass="label" text="Código Postal"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfCodigoPostal}" id="tfCodigoPostal" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label18}" for="tfPiso" id="label18" styleClass="label" text="Piso"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfPiso}" columns="10" id="tfPiso" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label19}" for="tfDepartamento" id="label19"
                                                styleClass="label" text="Departamento"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfDepartamento}" id="tfDepartamento" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label20}" for="tfBarrio" id="label20"
                                                styleClass="label" text="Barrio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfBarrio}" id="tfBarrio" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label21}" for="tfManzana" id="label21"
                                                styleClass="label" text="Manzana"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfManzana}" id="tfManzana" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label23}" for="tfTorre" id="label23" styleClass="label" text="Torre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfTorre}" id="tfTorre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label24}" for="tfEscalera" id="label24"
                                                styleClass="label" text="Escalera"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfEscalera}" id="tfEscalera" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMDomicilio$ConsultarDomicilio.label22}" for="tfSector" id="label22"
                                                styleClass="label" text="Sector"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.tfSector}" id="tfSector" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{comunes$ABMDomicilio$ConsultarDomicilio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{comunes$ABMDomicilio$ConsultarDomicilio.btnVolver_action}"
                                                binding="#{comunes$ABMDomicilio$ConsultarDomicilio.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.hidIdPagina}" id="hidIdPagina" text="#{comunes$ABMDomicilio$ConsultarDomicilio.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$ABMDomicilio$ConsultarDomicilio.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$ABMDomicilio$ConsultarDomicilio.idSubSesion}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
