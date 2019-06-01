import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringTest {
	
	    public static void main(String[] args) {
	    	
	    	 /**第一种方法*/
	        String s = "987654321088123about我是谁";
	        System.out.println("直接输出："+s);

	        StringBuilder b = new StringBuilder(s);
	        s = b.reverse().toString();
	        System.out.println("反转输出："+s);
	        
	        
	        inputValidate("<IMG");
	        
	        
	    }
	    
	    public static void inputValidate (String pValue){
	    	Pattern invalidInputPattern = Pattern.compile("<[\\s\\x00]*SCRIPT|SELECT\\s|INSERT\\s|DELETE\\s|UPDATE\\s|DROP\\s|<!--|-->|<FRAME|<IFRAME|<FRAMESET|<NOFRAME|<PLAINTEXT|<A\\s|<LINK|<MAP|<BGSOUND|<IMG|<FORM|<INPUT|<SELECT|<OPTION|<TEXTAREA|<APPLET|<OBJECT|<EMBED|<NOSCRIPT|<STYLE|ALERT[\\s\\x00]*\\(");
	    	pValue = pValue.toUpperCase();
	    	System.out.println(pValue);
			Matcher matcher = invalidInputPattern.matcher(pValue); // 操作的字符串 
			boolean flag =matcher.find();
			System.out.println(flag);
	    }
	    
	

}
