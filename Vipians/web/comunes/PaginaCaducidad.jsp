<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$PaginaCaducidad.page1}" id="page1">
            <ui:html binding="#{comunes$PaginaCaducidad.html1}" id="html1">
                <ui:head binding="#{comunes$PaginaCaducidad.head1}" id="head1" title="La Página ha Caducado">
                    <ui:link binding="#{comunes$PaginaCaducidad.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$PaginaCaducidad.body1}" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(220, 220, 220); -rave-layout: grid">
                    <ui:form binding="#{comunes$PaginaCaducidad.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <ui:alert binding="#{comunes$PaginaCaducidad.alert1}" detail="La página a la que desea acceder a caducado." id="alert1"
                                    linkAction="#{comunes$PaginaCaducidad.alert1_action}" linkTarget="_self" linkText="Continuar..."
                                    summary="La Página ha Caducado" type="error"/>
                            </div>
                        </div>
                        <ui:hiddenField binding="#{comunes$PaginaCaducidad.hidPaginaSiguiente}" id="hidPaginaSiguiente" text="#{comunes$PaginaCaducidad.paginaSiguiente}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
