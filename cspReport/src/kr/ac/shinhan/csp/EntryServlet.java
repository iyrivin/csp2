package kr.ac.shinhan.csp;

import java.io.IOException;

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
		String id = null;
		String userID = req.getParameter("userID");
		
		Cookie[] cookieList = req.getCookies();
		
		for(Cookie c : cookieList){
			if (c.getName().equals("token"))
				id = c.getValue();
		}
		
		if (id != null){
//			 날짜 확인하는 조건식
//			 if (날짜가 맞으면){ // 참이면 토큰 UUID  변경 
//				세션에 등록
//				HttpSession session = req.getSession();
//				if (session.isNew()){
//						
//				}
//				index.html 연결
				resp.sendRedirect("index.html");
//			}
//			 
		}
		else{
//			login.html 연결
			resp.sendRedirect("login.html");
		}
	}
}
