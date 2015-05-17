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
public class EntryServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 분기하는 서블릿, 쿠키에 데이터가 있으면 메인화면을 보여주고
		// 쿠키가 없으면 로그인화면을 보여준다.
		String token = "";
		Cookie[] cookieList = req.getCookies();
		
		if(cookieList == null)
			resp.sendRedirect("login.html");
		
		else{
			for(Cookie c : cookieList)
				if (c.getName().equals("token"))
					token = c.getValue();
			
			
			PersistenceManager manager = MyPersistenceManager.getManager();
			Query q = manager.newQuery(UserLoginToken.class);
			q.setFilter("token == tokenParam");
			q.declareParameters("String tokenParam");
			List<UserLoginToken> tokenList = (List<UserLoginToken>)q.execute(token);
			
			// 저장된 쿠키가 없거나 로그인 아이디가 없을 경우
			if(tokenList.size() == 0 || token.equals(""))
				resp.sendRedirect("login.html");
			
			else{
				UserLoginToken ult = tokenList.get(0);
				String expireDate = ult.getExpireDate();
				String today = new Date().toString();
				
				if(expireDate.compareTo(today)>0){
					// 세션 
					HttpSession session = req.getSession();
					session.setAttribute("userId", ult.getUserAccount());
					resp.sendRedirect("index.html");
					
					//Token Change
					String newToken = UUID.randomUUID().toString();
					Cookie c = new Cookie("token", newToken);
					c.setMaxAge(60 * 60 * 24 * 30);
					resp.addCookie(c);
					ult.setToken(newToken);
					manager.makePersistent(ult);
				}
				else
					resp.sendRedirect("login.html");
			}
		}
	}
}
