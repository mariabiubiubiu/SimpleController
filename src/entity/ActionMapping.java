package entity;

import java.util.Map;


public class ActionMapping {
	private String name;	// ����·������
	private String className;	// ����action·����ȫ��
	private String method;	// ������
	private Map<String, Result> results;	// �����ͼ����
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Result> getResults() {
		return results;
	}
	public void setResults(Map<String, Result> results) {
		this.results = results;
	}
	
}
