package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
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
		
		String id = req.getParameter("id");
		HttpSession session = req.getSession(false);
		
//		String idc = null;
//		Cookie[] cookieList = req.getCookies();
//		for(Cookie c : cookieList){
//			if(c.getName().equals("token"))
//				idc = c.getValue();
//		}
//		
//		if (idc != null) {
//			
			resp.getWriter().println( session.getAttribute("id") +" 환영합니다. ");
		
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
	
			resp.getWriter().println("<table border='1'>");
			resp.getWriter().println("<tr>");
			resp.getWriter().println("<th>"+"이름" + "</th>" );	
			resp.getWriter().println("<th>"+"학번" + "</th>" );
			resp.getWriter().println("<th>"+"전화번호" + "</th>" );
			resp.getWriter().println("<th>"+"메일주소" + "</th>" );
			resp.getWriter().println("<th>"+"카카오톡 아이디" + "</th>" );
			resp.getWriter().println("<th>"+"팀장여부" + "</th>" );
			resp.getWriter().println("<th>"+"GitHub ID" + "</th>" );
			resp.getWriter().println("<th>"+"회원삭제" + "</th>" );
			resp.getWriter().println("</tr>");
			
			for(TeamMember tm:memberList) {
				resp.getWriter().println("<tr>");
				resp.getWriter().println("<td>"+ "<a href =/readteammember?key="+tm.getKey()+">"+ tm.getName()+"</td>");
				resp.getWriter().println("<td>" + tm.getStdId()+ "</td>");
				resp.getWriter().println("<td>" + tm.getPhone()+ "</td>");
				resp.getWriter().println("<td>" + tm.getEmail()+ "</td>");
				resp.getWriter().println("<td>" + tm.getKakao()+ "</td>");
				
				if(tm.isTeamMan() ==true)
					resp.getWriter().println("<td>" + "팀장" + "</td>");
				else
					resp.getWriter().println("<td>" + "팀원" + "</td>");
				
				resp.getWriter().println("<td>" + tm.getGitId()+ "</td>");
				resp.getWriter().println("<td>" + "<a href =/deletemember?key="+tm.getKey()+ ">"+"삭제" + "</td>");
				resp.getWriter().println("</tr>");
			}
			resp.getWriter().println("</table>");
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		}
	}
//}