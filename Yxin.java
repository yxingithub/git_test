import yxinsssss
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;





public class Yxin {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//				String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?><agent.xml><head><serviceId>171045</serviceId><waiblius>1002112110</waiblius><waibriqi>1111111111</waibriqi></head><body><provinceCode>500</provinceCode></body></agent.xml>";
//				try {
//					System.out.println(str.getBytes("GBK").length);
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		//test1();
		//test2();
		String str ="[[\"张三\",\"23\"],[\"李四\",\"32\"]]";
		List<Person> list = getListByStr(str);
		for(Person person :list){
			//System.out.println(person.toString());
		}
//		for(Iterator i = list.iterator(); i.hasNext();){
//			Person person = (Person) i.next();
//			System.out.println(person.toString());
//		} 
		
		net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(list);
		System.out.println(json);
		
		test3();
	}
	private static List getListByStr(String str) {
		List<Person> li = new ArrayList();
		try{
			String[] srr = str.split("],");
			
			for(int a=0;a<srr.length;a++){
				
				String s = srr[a];
				s = s.replace("[[", "").replace("]", "").replace("[", "").replace("\"", "");
				String[] arr = s.split(",");
				Person person = new Person(arr[0],arr[1]);
				li.add(person);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return li;
	}
		
	public static void test1(){
		String resJSON = "[{\"id\": \"1\",\"name\": \"jeep\"},{\"id\": \"2\",\"name\":\"bmw\"}]";
		if(resJSON.startsWith("[")){
			  StringBuffer sbu = new StringBuffer();
			  sbu.append("{\"list\":");
			  sbu.append(resJSON);
			  sbu.append("}");
			  resJSON=sbu.toString();
		  }
		 System.out.println(resJSON);
	}
	public static void test2(){
		
		String resJSON = "[[\"张三\",\"23\"],[\"李四\",\"32\"],[{\"name\":\"王五\",\"age\":\"26\"},{\"name\":\"王五\",\"age\":\"26\"}]]";
		JSONArray a =JSONArray.fromObject(resJSON);
		//System.out.println(a);
		Object[] arr= a.toArray();
		System.out.println(arr[2]);
//		List<Person> list = new ArrayList<Person>();
//		for(int i = 0; i < arr.length; i++){
//			new Person("1","2");
//			
//		}
	}
	
	public static void test3(){
		
		String sql = "select USR_NAME from IM_USER where USR_NAME in (%s) order by USR_MODITIME desc";
		List list = new ArrayList();
		list.add("张三");
		list.add("测试");
		list.add("零售部人员");
		list.add("罗红霞");
		String names="";
		for(Object obj : list){
			names+=",'"+obj.toString()+"'";
		}
		names = names.substring(1);
		System.out.println(names);
		sql = String.format(sql, names);
	
		System.out.println("sql="+sql);
	}


}


