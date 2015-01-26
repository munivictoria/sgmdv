<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Main.page1}" id="page1">
            <ui:html binding="#{Main.html1}" id="html1">
                <ui:head binding="#{Main.head1}" id="head1" title="Página de Bienvenida">
                    <ui:link binding="#{Main.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{Main.body1}" focus="form1:textField4" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(236, 236, 242); -rave-layout: grid">
                    <ui:form binding="#{Main.form1}" id="form1">
                        <!--<div class="main">
                            <div class="titulo">
                                Bienvenido&amp;nbsp;a Vipians©<br/>Sistema de Administración de Vipianscipalidades
                            </div>
                        </div>-->
                        <div id="cont">
                            <div id="shad">
                                <div id="text">
                                    Bienvenido&amp;nbsp;a Vipians<sup>®</sup><br/>Sistema de Gestión de Municipalidades
                                    <!--
                                    <hr/>
                                    <span style="text-align:justify;">
                                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam malesuada libero at diam. Cras dolor. Proin viverra nibh ac tellus. Nullam tincidunt elementum ipsum. Suspendisse nisi turpis, egestas at, aliquet nec, ultricies et, pede. Sed lorem purus, consectetuer vel, rhoncus non, rutrum sit amet, urna. Vivamus elementum auctor magna. Etiam quis mauris et erat blandit cursus. Duis sollicitudin neque a felis. Nam facilisis leo id risus. Nam euismod, justo vitae egestas molestie, pede nisl tristique pede, sit amet venenatis tellus leo at libero.</p>
                                        <p>Nam nulla. Ut vitae tellus id dui tempus pretium. Sed lacinia, magna et lobortis vehicula, felis sem hendrerit est, sed accumsan nisl pede sit amet mauris. Praesent id enim a enim molestie consequat. Etiam posuere aliquet arcu. In tristique felis id nisi. Sed velit metus, auctor vitae, aliquam id, consectetuer sit amet, est. Cras mollis sem. In vitae massa eu risus porta lobortis. Praesent faucibus, sapien et varius pellentesque, dui quam ornare tortor, vitae elementum dui nisi a ante.</p>
                                        <p>Phasellus fringilla justo vitae felis. Sed nibh tortor, viverra eu, aliquam in, porta vel, lacus. Mauris commodo pede non dolor facilisis ultrices. Curabitur vel lorem. Vestibulum ipsum. Praesent mauris erat, pellentesque sed, consectetuer vitae, venenatis sed, tortor. Nullam a magna non mi pulvinar fermentum. Phasellus posuere cursus risus. Sed odio. Proin molestie augue ac ligula. Sed libero. Nullam a nisi. Nulla erat. Nulla ullamcorper, felis vel auctor luctus, elit sapien elementum dui, in fringilla libero mauris eget pede.</p>
                                        <p>Maecenas adipiscing congue sapien. Donec auctor faucibus lacus. Vivamus tempor risus eget justo. Vestibulum accumsan interdum risus. Proin pellentesque, ipsum at pharetra tempor, sem dolor vestibulum dui, sit amet fringilla dui orci nec leo. Sed at nisi eu dolor convallis rhoncus. Nam sem. Vivamus est ipsum, luctus quis, facilisis blandit, porttitor id, mauris. Nullam luctus feugiat massa. Praesent suscipit dolor sed nisi. Phasellus euismod. Aenean luctus magna sit amet sem suscipit vehicula. Ut ac eros at tellus accumsan pretium.</p>
                                    </span>-->
                                </div>
                            </div>
                        </div>
                        <ui:panelGroup binding="#{Main.panelAccesos}"/>
                        <ui:script binding="#{Main.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
