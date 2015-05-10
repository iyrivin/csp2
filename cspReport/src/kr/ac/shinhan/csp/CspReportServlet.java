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
		resp.getWriter().println("<tr> <td > " + " 이름 : " + name + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " 학번 : " + stdId + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " 전화번호 : " + phone + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " 메일주소 : " + email + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " 카카오톡 ID : " + kakao + "</td> </tr>");
		
		if (check != true)
				resp.getWriter().println("<tr> <td > " + " 팀장여부 : 팀원"  + "</td> </tr>");
		else
			resp.getWriter().println("<tr> <td > " + " 팀장여부 : 팀장"  + "</td> </tr>");
		resp.getWriter().println("<tr> <td > " + " Git ID : " + gitId + "</td> </tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("이 등록되었습니다. ");
		resp.getWriter().println("<a href=" +"index.html" + ">" + "처음으로" + "</a>"+"</br>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
