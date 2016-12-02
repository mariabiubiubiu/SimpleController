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
		//����SAXReader����
		SAXReader reader = new SAXReader();
		//��ȡ�ļ� ת����Document
		Document document = reader.read(new File("src/xml/controller.xml"));
		//��ȡ���ڵ�Ԫ�ض���
		Element root = document.getRootElement();
		//����
		listNodes(root);
	}
	
	//������ǰ�ڵ��µ����нڵ�
	public void listNodes(Element node){
		System.out.println("��ǰ�ڵ�����ƣ�" + node.getName());
		//���Ȼ�ȡ��ǰ�ڵ���������Խڵ�
		List<Attribute> list = node.attributes();
		//�������Խڵ�
		for(Attribute attribute : list){
			System.out.println("����"+attribute.getName() +":" + attribute.getValue());
		}
		//�����ǰ�ڵ����ݲ�Ϊ�գ������
		if(!(node.getTextTrim().equals(""))){
			 System.out.println( node.getName() + "��" + node.getText());  
			 m.put(node.getText(),node.getName());
			 ap.put(node.getName(),node.getText());
		}
		//ͬʱ������ǰ�ڵ�����������ӽڵ�
		//ʹ�õݹ�
		Iterator<Element> iterator = node.elementIterator();
		while(iterator.hasNext()){
			Element e = iterator.next();
			listNodes(e);
		}
	}

	
}

