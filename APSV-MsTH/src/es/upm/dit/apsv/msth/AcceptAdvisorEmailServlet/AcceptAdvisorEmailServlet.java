package es.upm.dit.apsv.msth.AcceptAdvisorEmailServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.dit.upm.apsv.msth.dao.MsTHDAO;
import es.dit.upm.apsv.msth.dao.MsTHDAOImpl;
import es.upm.dit.apsv.msth.model.MsTH;

@SuppressWarnings("serial")
public class AcceptAdvisorEmailServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties(), null), req.getInputStream());
			String tutor = new InternetAddress(message.getFrom()[0].toString()).getAddress();
			String autor = message.getSubject();
			
			MsTHDAO dao=MsTHDAOImpl.getInstance();
			//String user=req.getUserPrincipal().getName();
			//String autor=(String) req.getAttribute("autor");
			MsTH msth=dao.leer(autor).get(0);
			
			MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
			msg.setFrom(new InternetAddress("MsTH@apsv-149119.appspotmail.com", "Master Thesis management system"));
			msg.addRecipient(MimeMessage.RecipientType.TO,
			        new InternetAddress(msth.getAutor(), "Master Thesis "));
			msg.setSubject("Professor " + tutor + " accepts to be your tutor");
			msg.setText("Professor " + tutor + " accepts to be your tutor of the thesis proposed by " + msth.getAutor() + " with title " + msth.getTitulo());
			Transport.send(msg);
			
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
