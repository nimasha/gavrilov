package remote;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String methodName;
	private Object[] args;
	public OperationRequest() {
	}
	public OperationRequest(String methodName, Object... args) {
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
