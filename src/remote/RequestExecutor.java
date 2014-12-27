package remote;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestExecutor {
	private final Object controller;

	public RequestExecutor(Object controller) {
		this.controller = controller;
	}

	public OperationResponse executeRequest(OperationRequest request)
			throws NoSuchMethodException, IllegalAccessException {
		Class clazz = controller.getClass();
		Method m = getMethod(clazz, request.getMethodName());
		try {
			Object res = m.invoke(controller, request.getArgs());
			return new OperationResponse(res);
		} catch (InvocationTargetException e) {
			return new OperationResponse(null,
					(Exception) e.getTargetException());
		}
	}

	private Method getMethod(Class clazz, String methodName)
			throws NoSuchMethodException {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {
			if (methodName.equals(m.getName()))
				return m;
		}
		throw new NoSuchMethodException("No method '" + methodName
				+ "' was found in class " + clazz);
	}
}
