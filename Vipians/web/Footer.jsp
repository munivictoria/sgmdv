<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Footer.page1}" id="page1">
            <ui:html binding="#{Footer.html1}" id="html1" lang="es">
                <ui:head binding="#{Footer.head1}" id="head1" title="Pie">
                    <ui:link binding="#{Footer.link1}" id="link1" url="/resources/stylesheet.css"/>
                 </ui:head>
                <ui:body binding="#{Footer.body1}" id="body1" style="background-color: rgb(230, 230, 230); -rave-layout: grid">
                    <ui:form binding="#{Footer.form1}" id="form1" style="border-top:1px solid #000">
                        <ui:staticText binding="#{Footer.stRuta}" escape="false" id="stRuta" style="font-size: 10px; left: 7px; top: 6px; position: absolute"/>
                        <jsp:scriptlet>
                            String path = request.getContextPath() + "/faces/comunes/ErrorHandler.jsp";
                        </jsp:scriptlet>
                        <span onClick="javascript:window.open('http://localhost:8080/Vipians/faces/comunes/ErrorHandler.jsp', 'error','toolbar=no, location=no, statusbar=no, menubar=no, width=280, height=180');" style="cursor:default">&amp;nbsp;</span>
                    </ui:form> 
<script><![CDATA[
var ruta = document.getElementById('form1:stRuta');
if (ruta.innerHTML.indexOf('Ninguna') > -1) {
    var theDiv = parent.nav1.document.getElementById('divGrey');
    if (theDiv != null) {
        //alert("EXISTE LA DIV EN NAV .. LA ELIMINO [footer]");
        parent.nav1.document.body.removeChild(theDiv);
    }
    else {
        //alert("NO EXISTE LA DIV EN NAV [footer]");
    }
}
]]></script>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
