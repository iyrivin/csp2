package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//session 종료 v, 쿠키지우기, DB에서 데이터 지우기
		HttpSession session = req.getSession(false);
		session.invalidate();
		resp.sendRedirect("login.html");
		
//		String keyU =  req.getParameter("keyU");
//		Long longKey = Long.parseLong(keyU);
//		PersistenceManager pm = MyPersistenceManager.getManager();
//		UserLoginToken lt = pm.getObjectById(UserLoginToken.class,longKey);
//		pm.deletePersistent(lt);
	}
}
