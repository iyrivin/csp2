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
		// �б��ϴ� ����, ��Ű�� �����Ͱ� ������ ����ȭ���� �����ְ�
		// ��Ű�� ������ �α���ȭ���� �����ش�.
		String id = null;
		String userID = req.getParameter("userID");
		
		Cookie[] cookieList = req.getCookies();
		
		for(Cookie c : cookieList){
			if (c.getName().equals("token"))
				id = c.getValue();
		}
		
		if (id != null){
//			 ��¥ Ȯ���ϴ� ���ǽ�
//			 if (��¥�� ������){ // ���̸� ��ū UUID  ���� 
//				���ǿ� ���
//				HttpSession session = req.getSession();
//				if (session.isNew()){
//						
//				}
//				index.html ����
				resp.sendRedirect("index.html");
//			}
//			 
		}
		else{
//			login.html ����
			resp.sendRedirect("login.html");
		}
	}
}
