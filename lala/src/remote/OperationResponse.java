package remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "operationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "object")
	private Object object;
	@XmlElement(name = "exception")
	private Exception exception;
	@XmlElement(name = "listOfObjects")
	  @XmlElementWrapper(name = "objects")
	private List listOfObjects;

	public OperationResponse() {
	}

	public OperationResponse(Object object) {
		this(object,null);
	}

	public OperationResponse(Object object, Exception exception) {
		if (object instanceof List) {
			this.listOfObjects = (List) object;
		} else {
			this.object = object;
		}
		this.exception = exception;
	}

	public Object getObject() {
		 if (listOfObjects != null)
	            return listOfObjects;
	        else
	            return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List getListOfObjects() {
		return listOfObjects;
	}

	public void setListOfObjects(List objects) {
		this.listOfObjects = objects;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
