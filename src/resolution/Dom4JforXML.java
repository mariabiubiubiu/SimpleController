package resolution;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Init;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.XmlResult;

public class Dom4JforXML {
//	List<Map<String, String>> list;
	public Map<String,String> m=new HashMap<String,String>();
	public Map<String,String> ap=new HashMap<String,String>();
//	int i=0;
	public static void main(String args[]){
//	public void Init(){
		
//		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		//Map<String,String> m =new HashMap<String,String>();
		Dom4JforXML aDom4JforXML=new Dom4JforXML();
		try {
			aDom4JforXML.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------");
		System.out.println(aDom4JforXML.m.get("login"));
	}
	
	public void test() throws Exception{
		//创建SAXReader对象
		SAXReader reader = new SAXReader();
		//读取文件 转换成Document
		Document document = reader.read(new File("src/xml/controller.xml"));
		//获取根节点元素对象
		Element root = document.getRootElement();
		//遍历
		listNodes(root);
	}
	
	//遍历当前节点下的所有节点
	public void listNodes(Element node){
		System.out.println("当前节点的名称：" + node.getName());
		//首先获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		//遍历属性节点
		for(Attribute attribute : list){
			System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
		}
		//如果当前节点内容不为空，则输出
		if(!(node.getTextTrim().equals(""))){
			 System.out.println( node.getName() + "：" + node.getText());  
			 m.put(node.getText(),node.getName());
			 ap.put(node.getName(),node.getText());
		}
		//同时迭代当前节点下面的所有子节点
		//使用递归
		Iterator<Element> iterator = node.elementIterator();
		while(iterator.hasNext()){
			Element e = iterator.next();
			listNodes(e);
		}
	}

	
}

