package remote;

import java.io.Serializable;


public class OperationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Object object;
	private final Exception exception;

	public OperationResponse(Object object) {
		this.object = object;
		this.exception = null;
	}

	public OperationResponse(Object object, Exception exception) {
		this.object = object;
		this.exception = exception;
	}

	public Object getObject() {
		return object;
	}

	public Exception getException() {
		return exception;
	}
}
