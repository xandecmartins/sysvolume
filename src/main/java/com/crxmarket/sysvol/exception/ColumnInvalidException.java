package com.crxmarket.sysvol.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ColumnInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ColumnInvalidException(String message) {
		super(message);
	}
}
