package resolution;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.ActionMapping;
import entity.Result;


public class ResolutionXML {
	
	// 保存action的集合
	private Map<String,ActionMapping> allActions;
		
	public ResolutionXML() {
		allActions = new HashMap<String,ActionMapping>();
		this.Init();
		
	}
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("传入参数有误，请查看controller.xml配置的路径。");
		}
		
		ActionMapping actionMapping = allActions.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("没有请求的资源");
		}
		return actionMapping;
	}
		
	private void Init() {
		/********DOM4J读取配置文件***********/
		try{
			// 1. 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			Document document =null;
			try {
				//document = reader.read(new File("src/xml/controller.xml"));
				document = reader.read(new File("C:/Users/maria/Workspaces/MyEclipse 2015 CI/SimpleController/src/xml/MyXml.xml"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取根节点元素对象
			Element root = document.getRootElement();
//			InputStream inStream = this.getClass().getResourceAsStream("C:/Users/maria/Workspaces/MyEclipse 2015 CI/SimpleController/src/xml/MyXml.xml");
//			// 2. 加载文件
//			Document  doc = reader.read(inStream);
			
//			// 3. 获取根
		//	Element root = doc.getRootElement();
			// 4. 得到package节点下，  所有的action子节点
			List<Element> listAction = root.elements("action");
			// 5.遍历 ，封装
			for (Element ele_action : listAction) {
				// 5.1 封装一个ActionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				// 5.2 封装当前action节点下的class视图
				Element ele_name = ele_action.element("name");
				actionMapping.setName(ele_name.getText());
	
				Element ele_class = ele_action.element("class");
				actionMapping.setClassName(ele_class.element("name").getText());
				actionMapping.setMethod(ele_class.element("method").getText());

				// 5.3 封装当前aciton节点下所有的结果视图
				Map<String,Result> results = new HashMap<String, Result>();
				
				// 5.4 得到当前action节点下所有的result子节点
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // 当前迭代的每一个元素都是 <result...>
					 Element ele_result = it.next();
					 
					 // 封装对象
					 Result res = new Result();
					 res.setName(ele_result.element("name").getText());
					 res.setType(ele_result.element("type").getText());
					 res.setValue(ele_result.element("value").getText());
					 
					 // 添加到集合
					 results.put(res.getName(), res);
				 }
				
				// 设置到actionMapping中
				actionMapping.setResults(results);
				
				// 5.x actionMapping添加到map集合
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
