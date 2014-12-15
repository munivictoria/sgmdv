<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$PaginaImpresion.page1}" id="page1">
            <ui:html binding="#{comunes$PaginaImpresion.html1}" id="html1">
                <ui:head binding="#{comunes$PaginaImpresion.head1}" id="head1" title="Impresión">
                    <ui:link binding="#{comunes$PaginaImpresion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$PaginaImpresion.body1}" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(236, 236, 242); -rave-layout: grid">
                    <ui:form binding="#{comunes$PaginaImpresion.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{comunes$PaginaImpresion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$PaginaImpresion.head1.title}"/>
                                    </caption>
                                    <tbody>
                                        <tr>
                                            <td colspan="6" nowrap="true" style="border-bottom:1px solid #999;">
                                                <ui:label binding="#{comunes$PaginaImpresion.label1}" id="label1" styleClass="label2" text="Información de la Impresión"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="justify" colspan="6">
                                                <ui:staticText binding="#{comunes$PaginaImpresion.staticText2}" escape="false" id="staticText2"
                                                    styleClass="label" text="Se ha solicitado la impresión de:&amp;nbsp;&amp;nbsp;"/>
                                                <ui:staticText binding="#{comunes$PaginaImpresion.stObjeto}" escape="false" id="stObjeto" styleClass="label"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6">
                                                <ui:messageGroup binding="#{comunes$PaginaImpresion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </ui:form>
                    <ui:form binding="#{comunes$PaginaImpresion.form2}" id="form2" target="_blank">
                        <div class="divAdmin" style="top:130px; border-top: 1px solid #000; padding-top:3px; padding-left:4px; ">
                            <ui:button action="#{comunes$PaginaImpresion.btnPrevisualizar_action}" binding="#{comunes$PaginaImpresion.btnPrevisualizar}"
                                id="btnPrevisualizar" styleClass="button" text="Previsualizar"/>
                        </div>
                    </ui:form>
                    <ui:form binding="#{comunes$PaginaImpresion.form3}" id="form3">
                        <div class="divAdmin" style="top:130px; left: 110px; border-top: 1px solid #000; padding-top: 3px; width:680px;">
                            <ui:staticText binding="#{comunes$PaginaImpresion.staticText1}" escape="false" id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                            <ui:button action="#{comunes$PaginaImpresion.btnVolver_action}" binding="#{comunes$PaginaImpresion.btnVolver}" id="btnVolver"
                                styleClass="button" text="Volver"/>
                        </div>
                        <ui:hiddenField binding="#{comunes$PaginaImpresion.hidIdPagina}" id="hidIdPagina" text="#{comunes$PaginaImpresion.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$PaginaImpresion.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$PaginaImpresion.idSubSesion}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
