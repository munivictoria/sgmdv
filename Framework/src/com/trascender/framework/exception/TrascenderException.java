/**
 * 
 */
package com.trascender.framework.exception;

import javax.ejb.ApplicationException;


/**
 * @author mariano
 *
 */
@ApplicationException(rollback = true)
public abstract class TrascenderException extends Exception {

	private static final long serialVersionUID = 1840165211077091484L;
	
	protected int codeTrascenderException=0;
	
	protected String msg;
	
	public TrascenderException(int pCodeSystemException) {
		super();	
		codeTrascenderException = pCodeSystemException;	
		this.setMsg();
	}
	
	public TrascenderException() {
	}

	public int getCodeTrascenderException() {
		return codeTrascenderException;
	}

	protected void setCodeTrascenderException(int pCodeSystemException) {
		codeTrascenderException = pCodeSystemException;
	}	
	
	
	protected abstract void setMsg();
		
	public String getMessage(){
		return this.msg;
	}
	
}
