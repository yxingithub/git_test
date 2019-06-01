import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SjkHttpServletTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			resp.setHeader("content-type", "text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			String resJSON = "{\"menu\": [{\"id\": \"1\",\"name\": \"jeep\"},{\"id\": \"2\",\"name\":\"bmw\"}]}";
			out.print(resJSON);
			out.flush();
			out.close();


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
