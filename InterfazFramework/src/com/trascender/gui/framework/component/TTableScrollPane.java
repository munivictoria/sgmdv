package com.trascender.gui.framework.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JScrollPane;
import javax.swing.Timer;

public class TTableScrollPane extends JScrollPane {

	private static final long serialVersionUID = -4868705419256733998L;
	
	private Timer timer;
	private boolean cargando;
	private int anim = 0; 
	
	public boolean isCargando() {
		return cargando;
	}
	
	public void setCargando(boolean cargando) {
		this.cargando = cargando;
		if (this.cargando==true){
			anim = 0;
			timer = new Timer(300,new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					repaint();					
				}
			});
			timer.start();
		}
		else{
			timer.stop();
			timer = null;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (this.isCargando()) {
			Graphics2D locG = (Graphics2D)g;
			String mensaje = " buscando ";
			for (int i=0;i<anim;i++){
				mensaje = "·" + mensaje + "·";
			}
			anim = (anim>1) ? 0 : anim+1;
			
			Rectangle locRectangulo = locG.getClipBounds();
			
			int x = new Double(locRectangulo.getCenterX()).intValue();
			int y = new Double(locRectangulo.getCenterY()).intValue();
			
			locG.setColor(Color.LIGHT_GRAY);
			
			//locG.fillRoundRect(x-60, y-20, 120, 40, 15, 15);
			//locG.fillRect(x-60, y-20, 120, 40);
			locG.fill3DRect(x-60, y-20, 120, 40, true);
			
			Font locFuente = new Font("Verdana",Font.BOLD,12);
			locG.setColor(Color.BLUE);
			locG.setFont(locFuente);
			
			FontRenderContext locFontRenderContext = locG.getFontRenderContext();
			Rectangle2D locLimitesTexto = locFuente.getStringBounds(mensaje, locFontRenderContext);
			
			int xString = new Double(x - locLimitesTexto.getCenterX()).intValue();
			int yString = new Double(y - locLimitesTexto.getCenterY()).intValue();
			locG.drawString(mensaje, xString, yString);
		}
	}
}
