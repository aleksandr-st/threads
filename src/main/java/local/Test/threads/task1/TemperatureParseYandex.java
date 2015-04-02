package local.Test.threads.task1;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TemperatureParseYandex implements Callable<Integer> {
	private String cityId;
	private static final String XML_URL = "http://export.yandex.ru/weather-ng/forecasts/"; 
	
	public TemperatureParseYandex(String cityId){
		this.cityId = cityId;
	}
	
	public Integer call(){
		int avgTemperature = -1000;
		
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(XML_URL+cityId+".xml");
			NodeList nodeList = doc.getElementsByTagName("fact");
			NodeList childs = nodeList.item(0).getChildNodes();
			for (int i = 0; i < childs.getLength(); i++){
				if ("temperature".equals(childs.item(i).getNodeName())){
					avgTemperature = Integer.parseInt(childs.item(i).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return avgTemperature;
	}

}
