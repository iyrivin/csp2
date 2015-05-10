package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateMemberServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException { 
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key);
		
		String name = req.getParameter("name");
		String stdId = req.getParameter("stdId");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String kakao = req.getParameter("kakao");
		String gitId = req.getParameter("gitId");		
		boolean check = req.getParameter("teamMan") != null;
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		TeamMember tm =  pm.getObjectById(TeamMember.class,longKey);
		
		tm.setName(name);
		tm.setStdId(stdId);
		tm.setPhone(phone);
		tm.setEmail(email);
		tm.setKakao(kakao);
		tm.setGitId(gitId);
		tm.setTeamMan(check);
		pm.close();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>" + "다음과 같이 해당 팀원의 정보가 수정되었습니다" + "</h1>");
		resp.getWriter().println("<table border=1>");
		resp.getWriter().println("<tr>"+ "<td>" +"이름  " +"</td>" +"<td>" + name + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"학번  " +"</td>" +"<td>" + stdId + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"전화번호 : " +"</td>" +"<td>" + phone + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"메일주소 : " +"</td>" +"<td>" + email + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"카카오톡 아이디 : " +"</td>" +"<td>" + kakao + "</td>" + "</tr>");
		if(check != true)
			resp.getWriter().println("<tr>"+ "<td>" +"팀장여부" +"</td>" +"<td>" + "팀원입니다" + "</td>" + "</tr>");
		else
			resp.getWriter().println("<tr>"+ "<td>" +"팀장여부" +"</td>" +"<td>" + "팀장" + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td>" +"GitHub ID" +"</td>" +"<td>" + gitId + "</td>" + "</tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("<a href=" +"retrive" + ">" + "뒤로가기" + "</a>"+"</br>");
		resp.getWriter().println("<a href=" +"index.html" + ">" + "처음으로" + "</a>"+"</br>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}