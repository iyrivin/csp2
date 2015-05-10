package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CspReportServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String stdId = req.getParameter("stdId");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String kakao = req.getParameter("kakao");
		String gitId = req.getParameter("gitId");
		boolean check = req.getParameter("teamMan") != null;
		//Database Insert
		TeamMember tm = new TeamMember(name, stdId, phone, email, kakao, gitId, check);
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		pm.makePersistent(tm);
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		resp.getWriter().println("<table border = 1 >");
		resp.getWriter().println("<tr> <td > " + " �̸� : " + name + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " �й� : " + stdId + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " ��ȭ��ȣ : " + phone + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " �����ּ� : " + email + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " īī���� ID : " + kakao + "</td> </tr>");
		
		if (check != true)
				resp.getWriter().println("<tr> <td > " + " ���忩�� : ����"  + "</td> </tr>");
		else
			resp.getWriter().println("<tr> <td > " + " ���忩�� : ����"  + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " Git ID : " + gitId + "</td> </tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("�� ��ϵǾ����ϴ�. ");
		resp.getWriter().println("<a href=" +"index.html" + ">" + "ó������" + "</a>"+"</br>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
