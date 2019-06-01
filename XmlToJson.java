
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class XmlToJson {

    public static void main(String[] args) {
    	//xml转json
    	 String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><realName>test</realName><identityNumber>411525152242417185276</identityNumber><phone>1314456788</phone><user><sex>nan</sex><name>zhangsan</name><age>23</age></user><user><sex>nv</sex><name>lisi</name><age>18</age></user></request>";
//    	 String str = xml2json(xml);
//         System.out.println("to_json" + str);
         //xmlDocument转json
         Document doc = null;
         try {
        	 FileInputStream fis = new FileInputStream("E:\\lsworkspace\\helloWord\\WebContent\\resources\\customer.xml");
             byte[] b = new byte[fis.available()];
             fis.read(b);
             String str = new String(b);
             System.out.println(str);
             doc = DocumentHelper.parseText(str);
             // 转为可解析对象
            // doc = DocumentHelper.parseText(xml);
             String newStr = xml2json(str);
             System.out.println("to_json" + newStr);
         } catch (DocumentException e) {
             e.printStackTrace();
         }
         //json转xml
      //   String newxml = json2xml(str);
      //   System.out.println("xml:" + newxml);
//    	JSONObject jsonObject = new JSONObject();
//        jsonObject.put("username", "horizon");
//        JSONArray jsonArray = new JSONArray();
//        JSONObject dataJson = new JSONObject();
//        jsonArray.add(jsonObject);
//        //jsonArray.add(jsonObject);
//        dataJson.put("data", jsonArray);
//        System.out.println(dataJson.toString());
//
//        String xml = json2xml(dataJson.toString());
//        System.out.println("xml:" + xml);
//        String str = xml2json(xml);
//        System.out.println("to_json" + str);
         catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    /**
     * 将xml字符串<STRONG>转换</STRONG>为JSON字符串
     * 
     * @param xmlString
     *            xml字符串
     * @return JSON<STRONG>对象</STRONG>
     */
    public static String xml2json(String xmlString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xmlString);
        return json.toString(1);
    }

    /**
     * 将xmlDocument<STRONG>转换</STRONG>为JSON<STRONG>对象</STRONG>
     * 
     * @param xmlDocument
     *            XML Document
     * @return JSON<STRONG>对象</STRONG>
     */
    public static String xml2json(Document xmlDocument) {
        return xml2json(xmlDocument.toString());
    }

    /**
     * JSON(数组)字符串<STRONG>转换</STRONG>成XML字符串
     * 
     * @param jsonString
     * @return
     */
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        // return xmlSerializer.write(JSONArray.fromObject(jsonString));//这种方式只支持JSON数组
    }
}