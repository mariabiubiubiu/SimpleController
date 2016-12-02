package entity;

import java.util.Map;


public class ActionMapping {
	private String name;	// 请求路径名称
	private String className;	// 处理action路径的全名
	private String method;	// 处理方法
	private Map<String, Result> results;	// 结果视图集合
	
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
