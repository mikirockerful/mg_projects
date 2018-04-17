package es.upm.dit.apsv.msth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.apphosting.api.UserServicePb.UserService;

import es.dit.upm.apsv.msth.dao.MsTHDAO;
import es.dit.upm.apsv.msth.dao.MsTHDAOImpl;
import es.upm.dit.apsv.msth.model.MsTH;

@SuppressWarnings("serial")
public class APSV_MsTHServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MsTHDAO dao=MsTHDAOImpl.getInstance();

		com.google.appengine.api.users.UserService userService= UserServiceFactory.getUserService();
		String url=userService.createLoginURL(req.getRequestURI());
		String urlLinktext= "Login";
		String user="";
		
		
		if (req.getUserPrincipal() != null){
			//Estoy autenticado
			user=req.getUserPrincipal().getName();
			url=userService.createLogoutURL(req.getRequestURI());
			urlLinktext="Logout";
			//resp.getWriter().println("<p>Hello "+user+"<p>");
			List<MsTH> mitrabajo=dao.leer(user);
			List<MsTH> lista_secr=dao.listar_de_secretario(user);
			List<MsTH> lista_tut=dao.listar_de_tutor(user);
			if(mitrabajo.isEmpty() && lista_secr.isEmpty() && lista_tut.isEmpty()){
				//Soy un profesor o un alumno sin trabajos, doy de alta uno (cuidado, siempre como alumno).
				RequestDispatcher view=req.getRequestDispatcher("jsp/form.jsp");
				try {
					view.forward(req,resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(!mitrabajo.isEmpty() && mitrabajo.get(0).getEstado()==2 && mitrabajo.get(0).getAutor()==user){
				//TO DO, tengo que subir la memoria
				req.getSession().setAttribute("msth",mitrabajo.get(0));
				RequestDispatcher view=req.getRequestDispatcher("jsp/subida.jsp");
				try {
					view.forward(req,resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				//Soy un usuario que tiene algun trabajo asignado mio o del que soy tutor.
				//System.out.println(user);
				req.getSession().setAttribute("user",user);
				req.getSession().setAttribute("url", url);
				req.getSession().setAttribute("urlLinktext", urlLinktext);
				if (mitrabajo.isEmpty()){
					ArrayList<MsTH> listado_completo=new ArrayList<MsTH>();
					for (int i=0;i<lista_tut.size();i++){
					listado_completo.add(lista_tut.get(i));
					}
					for (int i=0;i<lista_secr.size();i++){
					listado_completo.add(lista_secr.get(i));
					}
					req.getSession().setAttribute("msths",listado_completo);
				}else{
					req.getSession().setAttribute("msths", new ArrayList<MsTH>(mitrabajo));
				}
				RequestDispatcher view=req.getRequestDispatcher("jsp/ShowMsTHView.jsp");
				try {
					view.forward(req,resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else{
			List<MsTH> resultado=dao.listar_todos();
			
			//System.out.println(url);
			//resp.getWriter().println("<p>Press <a href=\""+url+"\">"+urlLinktext+"</a>.</p>");
			req.getSession().setAttribute("user",user);
			req.getSession().setAttribute("url", url);
			req.getSession().setAttribute("urlLinktext", urlLinktext);
			req.getSession().setAttribute("msths", new ArrayList<MsTH>(resultado));
		
			RequestDispatcher view=req.getRequestDispatcher("jsp/ShowMsTHView.jsp");
			try {
				view.forward(req,resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
			public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				System.out.println("Yendo aqui");
			}
}
