<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Page1.page1}" id="page1">
            <ui:html binding="#{Page1.html1}" id="html1">
                <ui:head binding="#{Page1.head1}" id="head1" title="Vipians">
                    <ui:link binding="#{Page1.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <link href="/Vipians/resources/muni.ico" rel="SHORTCUT ICON"/>
                </ui:head>
                    <frameset border="0" frameborder="yes" name="todo" rows="60,*,25">
                    <frame name="header" scrolling="no" src="/Vipians/faces/Header.jsp"/>
                    <frameset border="3" cols="200,*" frameborder="yes" name="cuerpo">
                        <frameset frameborder="yes" name="izquierda" rows="95,*">
                            <frame marginheight="0" marginwidth="0" name="nav2" scrolling="no" src="/Vipians/faces/Nav2.jsp"/>
                            <frame marginheight="0" marginwidth="0" name="nav1" scrolling="auto" src="/Vipians/faces/Nav1.jsp"/>
                        </frameset>
                        <frame marginheight="0" marginwidth="0" name="main" scrolling="auto" src="/Vipians/faces/Main.jsp"/>
                    </frameset>
                    <frame name="footer" scrolling="no" src="/Vipians/faces/Footer.jsp"/>
                </frameset>
                <noframes>
                    Esta aplicación requiere soporte de frames (marcos).
                </noframes>
                <ui:body>
                    <ui:form binding="#{Page1.form1}" id="form1"/>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
