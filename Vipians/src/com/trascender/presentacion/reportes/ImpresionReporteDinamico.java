/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.reportes;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author analia
 */
public abstract class ImpresionReporteDinamico {

    public static synchronized JasperPrint imprimirLista(List listaResultados, TableRowGroup tabla, String tituloReporte) {
        try {

            Style titleStyle = new Style();
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            titleStyle.setFont(new Font(15, "Arial", true));

            Style subtitleStyle = new Style();
            subtitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            Style columnStyle = new Style();
            columnStyle.setFont(new Font(12, "Arial", true));
            columnStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            DynamicReportBuilder drb = new DynamicReportBuilder();
            drb.setIgnorePagination(true);
            drb.setMargins(0, 0, 0, 0);
            drb.setUseFullPageWidth(true);
            drb.setTitle(tituloReporte).setTitleStyle(titleStyle).setSubtitleStyle(subtitleStyle).setSubtitle("Generado en la fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date())).setUseFullPageWidth(true).setPrintBackgroundOnOddRows(true);

            List<TableColumn> listaAtributos = getListaAtributos(tabla);
            Class clase = listaResultados.get(0).getClass();
            for (TableColumn cadaTableColumn : listaAtributos) {
//                Field cadaField = clase.getDeclaredField((String)cadaTableColumn.getSort());
                String nombreField = cadaTableColumn.getSort().toString();
                Method methodGeter = getGeter(clase, nombreField);
                if (methodGeter.getReturnType().getName().contains("trascender")) {
                    AbstractColumn columnaCustomExpression = ColumnBuilder.getNew().setCustomExpression(new CustomTrascenderImplementation(nombreField)).setTitle(cadaTableColumn.getHeaderText()).setHeaderStyle(columnStyle).build();
                    drb.addColumn(columnaCustomExpression);
                    drb.addField(nombreField, Object.class);
                } else if (methodGeter.getReturnType().equals(Boolean.class) || methodGeter.getReturnType().equals(boolean.class)) {
                    AbstractColumn columnaCustomExpression = ColumnBuilder.getNew().setCustomExpression(new BooleanCustomExpression(nombreField)).setTitle(cadaTableColumn.getHeaderText()).setHeaderStyle(columnStyle).build();
                    drb.addColumn(columnaCustomExpression);
                    drb.addField(nombreField, Object.class);
                } else if (methodGeter.getReturnType().equals(Character.class)) {
                    AbstractColumn columnaCustomExpression = ColumnBuilder.getNew().setCustomExpression(new CharacterCustomExpression(nombreField)).setTitle(cadaTableColumn.getHeaderText()).setHeaderStyle(columnStyle).build();
                    drb.addColumn(columnaCustomExpression);
                    drb.addField(nombreField, Object.class);
                } else if (methodGeter.getReturnType().equals(Date.class)){
                    AbstractColumn columnaCustomExpression = ColumnBuilder.getNew().setCustomExpression(new DateCustomExpression(nombreField)).setTitle(cadaTableColumn.getHeaderText()).setHeaderStyle(columnStyle).build();
                    drb.addColumn(columnaCustomExpression);
                    drb.addField(nombreField, Object.class);
                } else {
                    AbstractColumn column = ColumnBuilder.getNew().setColumnProperty(nombreField, methodGeter.getReturnType()).setTitle(cadaTableColumn.getHeaderText()).setHeaderStyle(columnStyle).build();
                    drb.addColumn(column);
                }
            }
            DynamicReport dr = drb.build();

            JRDataSource ds = new JRBeanCollectionDataSource(listaResultados);
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
            return jp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<TableColumn> getListaAtributos(TableRowGroup pTabla) {
        List listaResultado = new ArrayList();
        for (Iterator it = pTabla.getTableColumnChildren(); it.hasNext();) {
            TableColumn locTableColumn = (TableColumn) it.next();
            String sort = (String) locTableColumn.getSort();
            if (sort != null) {
                listaResultado.add(locTableColumn);
            }
        }
        return listaResultado;
    }

    private static Method getGeter(Class clase, String nombreField) throws Exception {
        String locNombreField = capitalizeOnlyFirst(nombreField);
        Method methodGeter;
        try {
            methodGeter = clase.getDeclaredMethod("get" + locNombreField, null);
        } catch (NoSuchMethodException e) {
            try {
                methodGeter = clase.getDeclaredMethod("is" + locNombreField, null);
            } catch (NoSuchMethodException ex) {
                if (!clase.getSuperclass().equals(Object.class)) {
                    methodGeter = getGeter(clase.getSuperclass(), nombreField);
                } else {
                    throw e;
                }
            }
        }
        return methodGeter;
    }

    private static String capitalizeOnlyFirst(String pString) {
        char[] charArray = pString.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        return new String(charArray);
    }
}
