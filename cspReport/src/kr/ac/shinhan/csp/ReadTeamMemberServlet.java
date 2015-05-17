package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ReadTeamMemberServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key);
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		
		TeamMember tm = pm.getObjectById(TeamMember.class, longKey);
		
		String name = tm.getName();
		String stdId = tm.getStdId();
		String phone = tm.getPhone();
		String email = tm.getEmail();
		String kakao = tm.getKakao();
		String gitId = tm.getGitId();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>"+ "팀원 정보 수정" + "<h1>");
		resp.getWriter().println("<form method = 'POST' action =/updatemember?key="+ tm.getKey() +">");
		resp.getWriter().println("<table border= 1>");
		
		resp.getWriter().println("<tr>"+"<td>"+"이름"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'name'" + "value="+name+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"학번"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'stdId'" +  "value="+stdId+ ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"전화번호"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'phone'" + "value="+phone+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"메일주소"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'email'" + "value="+email+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"카카오톡 아이디"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'kakao'" + "value="+kakao+  ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"팀장여부"+"</td>"+"<td>"+"<input type ="+"'checkbox'" +"name="+ "'teamMan'" + "value="+"'true'" + ">" + "</td>"+"</tr>");
		resp.getWriter().println("<tr>"+"<td>"+"GitHub Id"+"</td>"+"<td>"+"<input type ="+"'text'" +"name="+ "'gitId'" + "value="+gitId+  ">" + "</td>"+"</tr>");
		
		resp.getWriter().println("</table>");
		resp.getWriter().println("<input type= 'submit' value= 'update'>");
		resp.getWriter().println("</form>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
				
	}
}