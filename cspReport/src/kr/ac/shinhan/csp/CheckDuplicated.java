package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckDuplicated extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		String id = req.getParameter("id");
		String userID = req.getParameter("userID");
		//String dupCheck = req.getParameter("dupCheck");
		
		Query qry = MyPersistenceManager.getManager().newQuery(UserAccount.class);
		
		qry.setFilter("userID == idParam");
		qry.declareParameters("String idParam");	
		List<UserAccount> userAccount = (List<UserAccount>) qry.execute(id);
		
		for(UserAccount ua:userAccount){
			if(ua.getName().equals("userID")){		
				resp.getWriter().println("<html>");
				resp.getWriter().println("<body>");
				resp.getWriter().println("<script language ="+ "javascript" + ">");
				resp.getWriter().println("alert( "+ "'중복된 아이디입니다.'" + ")");
				resp.getWriter().println("history.go(-1)");
				resp.getWriter().println("</script>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");		
			}
			else{
				resp.getWriter().println("<html>");
				resp.getWriter().println("<body>");
				resp.getWriter().println("<script language ="+ "javascript" + ">");
				resp.getWriter().println("alert( "+ "'사용가능한 아이디입니다.'" + ")");
				resp.getWriter().println("history.go(-1)");
				resp.getWriter().println("</script>");
				resp.getWriter().println("</body>");
				resp.getWriter().println("</html>");
				
			}	
		}
	}
}
