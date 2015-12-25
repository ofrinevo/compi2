package lir;

import java.util.ArrayList;
import java.util.List;


public class ClassLayout {
	private String className;
	private List<MethodStrc> methods;
	private List<String> fields;
	
	public ClassLayout(String className, ClassLayout superClassLayout) {
		this.className = "_DV_" + className;
		this.methods = new ArrayList<MethodStrc>();
		this.fields = new ArrayList<String>();
		if (superClassLayout != null) {
			this.methods.addAll(superClassLayout.methods);
			this.fields.addAll(superClassLayout.fields);
		}
	}

	
	public String getClassName() {
		return className;
	}

	
	public void addMethod(String methodName) {
		MethodStrc methodStrc;
		if (!methodName.equals("main")) {
			int existingMethodStrcIndex = findMethodIndex(methods, methodName);
			if (existingMethodStrcIndex != -1)  {
				methodStrc = new MethodStrc(methodName, this.className);
				methods.remove(existingMethodStrcIndex);
				methods.add(existingMethodStrcIndex, methodStrc);
				return;
			}
		}
		methodStrc = new MethodStrc(methodName, this.className);
		methods.add(methodStrc);
		
	}

	
	public void addField(String fieldName) {
		fields.add(fieldName);
	}
	
	
	public int getAllocatedSize() {
		return (fields.size() + 1) * 4;
	}
	
	public int getFieldIndex(String fieldName) {
		return fields.indexOf(fieldName) + 1;
	}
	
	public int getMethodIndex(String methodName) {
		int i = 0;
		for (MethodStrc methodStrc : methods) {
			if (methodStrc.methodName.equals(methodName))
				return i;
			i++;
		}
		
		return -1;
	}
	
	public String getMethodString(String methodName) {
		for (MethodStrc methodStrc : methods) {
			if (methodStrc.methodName.equals(methodName))
				return methodStrc.toString();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(className +": [");
		
		for (int i = 0; i < methods.size(); i++) {
			sb.append(methods.get(i).toString());
			if(i < methods.size() - 1)
				sb.append(",");
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
	private int findMethodIndex(List<MethodStrc> list, String methodName) {
		int i = 0;
		for (MethodStrc methodStrc : list) {
			if (methodStrc.methodName.equals(methodName))
				return i;
			i++;
		}
		
		return -1;
	}
	private class MethodStrc {
		private String methodName;
		private String clsName;
		
		public MethodStrc(String methodName, String clsName) {
			this.methodName = methodName;
			this.clsName = clsName;
		}
		
		@Override
		public String toString() {
			if (methodName.equals("main"))
				return  "_ic_main";
			return  clsName + "_" + methodName;
		}
	}
}
