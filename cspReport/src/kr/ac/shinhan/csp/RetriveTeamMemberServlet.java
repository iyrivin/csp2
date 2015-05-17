package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RetriveTeamMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		Query qry = pm.newQuery(TeamMember.class);
		List<TeamMember> memberList = (List<TeamMember>) qry.execute();
		
		String id = req.getParameter("userID");
		HttpSession session = req.getSession(false);
	
		resp.getWriter().println( session.getAttribute("userID") +" ȯ���մϴ�. ");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");

		resp.getWriter().println("<table border='1'>");
		resp.getWriter().println("<tr>");
		resp.getWriter().println("<th>"+"�̸�" + "</th>" );	
		resp.getWriter().println("<th>"+"�й�" + "</th>" );
		resp.getWriter().println("<th>"+"��ȭ��ȣ" + "</th>" );
		resp.getWriter().println("<th>"+"�����ּ�" + "</th>" );
		resp.getWriter().println("<th>"+"īī���� ���̵�" + "</th>" );
		resp.getWriter().println("<th>"+"���忩��" + "</th>" );
		resp.getWriter().println("<th>"+"GitHub ID" + "</th>" );
		resp.getWriter().println("<th>"+"ȸ������" + "</th>" );
		resp.getWriter().println("</tr>");
		
		for(TeamMember tm:memberList) {
			resp.getWriter().println("<tr>");
			resp.getWriter().println("<td>"+ "<a href =/readteammember?key="+tm.getKey()+">"+ tm.getName()+"</td>");
			resp.getWriter().println("<td>" + tm.getStdId()+ "</td>");
			resp.getWriter().println("<td>" + tm.getPhone()+ "</td>");
			resp.getWriter().println("<td>" + tm.getEmail()+ "</td>");
			resp.getWriter().println("<td>" + tm.getKakao()+ "</td>");
			
			if(tm.isTeamMan() ==true)
				resp.getWriter().println("<td>" + "����" + "</td>");
			else
				resp.getWriter().println("<td>" + "����" + "</td>");
			
			resp.getWriter().println("<td>" + tm.getGitId()+ "</td>");
			resp.getWriter().println("<td>" + "<a href =/deletemember?key="+tm.getKey()+ ">"+"����" + "</td>");
			resp.getWriter().println("</tr>");
		}
		resp.getWriter().println("</table>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
