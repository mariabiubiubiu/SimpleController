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
	
	// ����action�ļ���
	private Map<String,ActionMapping> allActions;
		
	public ResolutionXML() {
		allActions = new HashMap<String,ActionMapping>();
		this.Init();
		
	}
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("�������������鿴controller.xml���õ�·����");
		}
		
		ActionMapping actionMapping = allActions.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("û���������Դ");
		}
		return actionMapping;
	}
		
	private void Init() {
		/********DOM4J��ȡ�����ļ�***********/
		try{
			// 1. �õ�������
			SAXReader reader = new SAXReader();
			// �õ�src/mystruts.xml  �ļ���
			Document document =null;
			try {
				//document = reader.read(new File("src/xml/controller.xml"));
				document = reader.read(new File("C:/Users/maria/Workspaces/MyEclipse 2015 CI/SimpleController/src/xml/MyXml.xml"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��ȡ���ڵ�Ԫ�ض���
			Element root = document.getRootElement();
//			InputStream inStream = this.getClass().getResourceAsStream("C:/Users/maria/Workspaces/MyEclipse 2015 CI/SimpleController/src/xml/MyXml.xml");
//			// 2. �����ļ�
//			Document  doc = reader.read(inStream);
			
//			// 3. ��ȡ��
		//	Element root = doc.getRootElement();
			// 4. �õ�package�ڵ��£�  ���е�action�ӽڵ�
			List<Element> listAction = root.elements("action");
			// 5.���� ����װ
			for (Element ele_action : listAction) {
				// 5.1 ��װһ��ActionMapping����
				ActionMapping actionMapping = new ActionMapping();
				// 5.2 ��װ��ǰaction�ڵ��µ�class��ͼ
				Element ele_name = ele_action.element("name");
				actionMapping.setName(ele_name.getText());
	
				Element ele_class = ele_action.element("class");
				actionMapping.setClassName(ele_class.element("name").getText());
				actionMapping.setMethod(ele_class.element("method").getText());

				// 5.3 ��װ��ǰaciton�ڵ������еĽ����ͼ
				Map<String,Result> results = new HashMap<String, Result>();
				
				// 5.4 �õ���ǰaction�ڵ������е�result�ӽڵ�
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // ��ǰ������ÿһ��Ԫ�ض��� <result...>
					 Element ele_result = it.next();
					 
					 // ��װ����
					 Result res = new Result();
					 res.setName(ele_result.element("name").getText());
					 res.setType(ele_result.element("type").getText());
					 res.setValue(ele_result.element("value").getText());
					 
					 // ��ӵ�����
					 results.put(res.getName(), res);
				 }
				
				// ���õ�actionMapping��
				actionMapping.setResults(results);
				
				// 5.x actionMapping��ӵ�map����
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
