<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$ErrorHandler.page1}" id="page1">
            <ui:html binding="#{comunes$ErrorHandler.html1}" id="html1">
                <ui:head binding="#{comunes$ErrorHandler.head1}" id="head1" title="Error!">
                    <ui:link binding="#{comunes$ErrorHandler.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$ErrorHandler.body1}" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(220, 220, 220); -rave-layout: grid">
                    <ui:form binding="#{comunes$ErrorHandler.form1}" id="form1">
                        <div id="cont" onclick="window.close();">
                            <div id="shad">
                                <div id="text" style="text-align: center; font-size: 8pt;">
                                    <b><u>Desarrolladores de Vipians</u></b>
                                    <br/><br/>
                                        Juan Pablo "JP" Burioni<br/>
                                        Mariano "MAL" Lusardi<br/>
                                        Ariel "Mono" Trevisan<br/>
                                        Ignacio "Nacho" Sarnaglia<br/>
                                        Mariano "MarianoKa" Carpio<br/>
                                </div>
                            </div>
                        </div>
                        <div id="cont" style="padding-top:0; margin-top:0;">
                        [<a href="javascript:window.close();">cerrar</a>]
                        </div>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
