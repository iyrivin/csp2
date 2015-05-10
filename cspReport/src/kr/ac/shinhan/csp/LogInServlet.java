package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogInServlet  extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		//String UserId = req.getParameter("id");
		String password = req.getParameter("password");
		boolean success = false;
		HttpSession session = req.getSession();
		session.setAttribute("id", id);
		
// **** 실습 2-3		
		String userAccountT = req.getParameter("userAccountT");
		String expireDate = req.getParameter("remember");
		String token = req.getParameter("token");
		
		
		MyPersistenceManager.getManager();
		Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
		cookie.setMaxAge(30 * 60);		//클라이언트 쿠키정보를 얼마나 보관할것인지
		resp.addCookie(cookie);
		

		Query qry = MyPersistenceManager.getManager().newQuery(UserAccount.class);
		
		qry.setFilter("userID == idParam");
		qry.declareParameters("String idParam");	
		List<UserAccount> userAccount = (List<UserAccount>) qry.execute(id);

	//	for(int i =0; i<userAccount.lastIndexOf(id); i++){
			if(userAccount.size()==0)
				success=false;
			else if(userAccount.get(0).getPassword().equals(password))
				success=true;
			else
				success=false;
	//	}
// 로그인이 성공했을 때 사용자 id를 세션에 저장
//		
//		if(success){
//			HttpSession session = req.getSession();
//			session.setAttribute("token", id);		//세션에 id를 저장
//			session.getAttribute("id");
//		}
////		//Database Insert
//			// 이 방식으로 UUID, ID, DATE 넣으면 됨
//			// 클래스는 UserLoginToken
//			UserLoginToken lt = new UserLoginToken(token, userAccountT, expireDate);
//			PersistenceManager pm = MyPersistenceManager.getManager();
//			pm.makePersistent(lt);		
//		}
//		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");

		if(!success){
			resp.getWriter().println("Fail to login"); resp.getWriter().println("");
			resp.getWriter().println("<a href='login.html'>Login Again</a>");
		}
		
		if(success)
			resp.sendRedirect("/index.html");
		
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}

}