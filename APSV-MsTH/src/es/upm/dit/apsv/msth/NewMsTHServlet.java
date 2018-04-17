package es.upm.dit.apsv.msth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import es.dit.upm.apsv.msth.dao.MsTHDAO;
import es.dit.upm.apsv.msth.dao.MsTHDAOImpl;
import es.upm.dit.apsv.msth.model.MsTH;

@SuppressWarnings("serial")
public class NewMsTHServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MsTHDAO dao=MsTHDAOImpl.getInstance();
		com.google.appengine.api.users.UserService userService= UserServiceFactory.getUserService();
		
		String titulo=req.getParameter("title");
		String tutor=req.getParameter("advisor");
		if (titulo == null && tutor ==null){
			RequestDispatcher view=req.getRequestDispatcher("jsp/form.jsp");
			try {
				view.forward(req,resp);
			} catch (ServletException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}else{
			//Solicitud correcta. Almacenamos la nueva solicitud y devolvemos a la pagina principal.
			String usuario=req.getUserPrincipal().getName();
			List<MsTH> mitrabajo=dao.crear(usuario, titulo, null, tutor, null, null);
			//req.getSession().setAttribute("user",usuario);
			//req.getSession().setAttribute("msths", new ArrayList<MsTH>(mitrabajo));
			req.getSession().setAttribute("msths", mitrabajo);
			
			String user=req.getUserPrincipal().getName();
			//String url=userService.createLogoutURL(req.getRequestURI());
			String urlLinktext="Logout";
			
			req.getSession().setAttribute("user",user);
			//req.getSession().setAttribute("url", url);
			req.getSession().setAttribute("urlLinktext", urlLinktext);
			
			RequestDispatcher view=req.getRequestDispatcher("jsp/ShowMsTHView.jsp");
			try {
				view.forward(req,resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
System.out.println("existo");
	}
}
