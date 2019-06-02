

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;


public class test {    
     
	   public static void main(String args[]) throws Exception {    
	     //为了简单起见，所有的异常都直接往外抛    
	     String host = "172.16.82.12";  //要连接的服务端IP地址    
	     int port = 1301;   //要连接的服务端对应的监听端口    
	      //与服务端建立连接    
	      Socket client = new Socket(host, port);    
	     //建立连接后就可以往服务端写数据了    
	      Writer writer = new OutputStreamWriter(client.getOutputStream());  
	      String str="013001101630012018040434572567287579589418E009                00000     <?xml version=\"1.0\" encoding=\"gbk\" ?><ebank><JYM>E009</JYM><BillNo>130555103111320180211164239739</BillNo><CorpAcct>06012100001116111</CorpAcct><BSsDate>20180404</BSsDate><BSRBoxMemo/><BSRName>犍为县塘坝煤矿</BSRName><BSROrgCode/><BSRAcct>118536989322</BSRAcct><BSRBankNo>104665384385</BSRBankNo><BSRBankName>中国银行犍为支行</BSRBankName><DisTransfer>EM00</DisTransfer><DZid/></ebank>";
	      StringBuffer sb=new StringBuffer("00000");
	      sb.append(str.getBytes("GBK"));
	      sb.append(str);
	      writer.write(sb.toString());      
	     
	      BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));    
	      System.out.println("===");
	     
	      String ret = input.readLine();  
	      System.out.println(ret);
	      writer.flush();//写完后要记得flush    
	      writer.close();  
	      client.close();    
	   }    
	   
	   
	   
	   
	  
	       
}   


