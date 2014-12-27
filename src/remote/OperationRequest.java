package remote;

import java.io.Serializable;

public class OperationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String methodName;
    private final Object[] args;

    public OperationRequest(String methodName, Object ... args) {
        this.methodName = methodName;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getMethodName() {
        return methodName;
    }
}
