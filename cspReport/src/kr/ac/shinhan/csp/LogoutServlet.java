package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key);
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		UserLoginToken ult = pm.getObjectById(UserLoginToken.class,longKey);
		pm.deletePersistent(ult);
		
		HttpSession session = req.getSession(false);
		
		Cookie[] cookieList= req.getCookies();
		for(Cookie cookie: cookieList) {
			if(cookie.getName().equals("token")){
				cookie.setValue(null);
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
		}
		
		session.invalidate();
		resp.sendRedirect("login.html");
		
	}
}
