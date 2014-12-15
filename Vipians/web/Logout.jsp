<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Logout.page1}" id="page1">
            <ui:html binding="#{Logout.html1}" id="html1">
                <ui:head binding="#{Logout.head1}" id="head1" title="Vipians">
                </ui:head>
                <ui:body binding="#{Logout.body1}" id="body1" style="background-color: rgb(240, 240, 240); -rave-layout: grid">
                    <ui:form binding="#{Logout.form1}" id="form1"/>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>

