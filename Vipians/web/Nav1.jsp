<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Nav1.page1}" id="page1">
            <ui:html binding="#{Nav1.html1}" id="html1">
                <ui:head binding="#{Nav1.head1}" id="head1" title="Navegación">
                	<ui:script id="jQuery" url="/resources/javascript/jQuery/jQuery.js" />
                    <ui:link binding="#{Nav1.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script>
                    <![CDATA[
     						$(document).ready(function() {
     							var $divArbol = $('#form1\\:trRecursos_children');
     							var $arreglo = $divArbol.find("div[class = 'TreeContent TreeImgHeight']");
     							
     							for (var i = 0; i < $arreglo.length; i++) {
     								var $cadaDiv = $arreglo.eq(i);
     								$cadaDiv.css("cursor","pointer");

     								$cadaDiv.mouseover(function() {
     									$(this).css("background-color", "rgb(203, 220, 175)");
     								}).mouseleave(function() {
     									$(this).css("background-color", "");
     								});
     								
     								$cadaDiv.on("click", function () {
     									var id = this.id.replace(/:/g, "\\:").replace("Text","\\:turner");
     									$("#"+id).click();
     								});
     							}
     						});
     					]]>
				</script>
                </ui:head>
                <ui:body   onLoad="Init();" binding="#{Nav1.body1}" id="body1" style="rgb(236, 236, 242); -rave-layout: grid">
                    <ui:form binding="#{Nav1.form1}" id="form1">
                        <div class="nav" id="nav1" name="nav1">
                            <table cellspacing="0">
                                <thead>
                                    <tr valign="middle">
                                        <td>
                                            <ui:label binding="#{Nav1.label1}" id="label1" text="Lista de Recursos"/>
                                        </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <ui:messageGroup binding="#{Nav1.messageGroup1}" id="messageGroup1"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <ui:tree binding="#{Nav1.trRecursos}" clientSide="true" id="trRecursos" styleClass="arbol"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--<div id="divGrey" name="divGrey" style="display:none;" class="clsPageGreyDiv"></div>-->                       
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
