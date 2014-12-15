<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Inicio.page1}" id="page1">
            <ui:html binding="#{Inicio.html1}" id="html1">
                <ui:head binding="#{Inicio.head1}" id="head1" title="Vipians">
                    <link href="/Vipians/resources/muni.ico" rel="shortcut icon"/>
                    <script language="javaScript">
                        document.write("<br/> <br/><b>&amp;nbsp;&amp;nbsp;&amp;nbsp;Redireccionando...</b>");
                        location.replace("/Vipians/faces/Login.jsp");
                    </script>
                    <noscript>
                        <br/>
                        <br/>
                        <br/>
                        <div class="noScript">
                            <i>Vipians :: Administración de Vipianscipalidades</i>
                            <br/>
                            <br/>
                            ..:: El Sistema requiere JavaScript para su correcto funcionamiento ::..
                            <br/>
                            Habilítelo y vuelva a cargar la página [F5]
                        </div>
                    </noscript>
                    <ui:link binding="#{Inicio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{Inicio.body1}" id="body1" style="background-color: rgb(240, 240, 240); -rave-layout: grid">
                    <ui:form binding="#{Inicio.form1}" id="form1"/>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
