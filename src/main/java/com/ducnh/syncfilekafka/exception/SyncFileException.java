package com.ducnh.syncfilekafka.exception;

public class SyncFileException extends RuntimeException{

	private static final long serialVersionUID = -756963947676187908L;
	
	public SyncFileException(String message) {
		super(message);
	}

	public SyncFileException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public SyncFileException(Throwable throwable) {
		super(throwable);
	}
}
