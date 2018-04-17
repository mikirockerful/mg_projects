package es.upm.dit.apsv.msth;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.dit.upm.apsv.msth.dao.MsTHDAO;
import es.dit.upm.apsv.msth.dao.MsTHDAOImpl;
import es.upm.dit.apsv.msth.model.MsTH;

public class ProcessfileServlet extends HttpServlet{
	public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Object usuario=req.getSession().getAttribute("user");
		MsTHDAO dao=MsTHDAOImpl.getInstance();
		
		Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
		List<BlobKey> blobKeys = blobs.get("file");
		if (blobKeys == null || blobKeys.isEmpty() || blobKeys.get(0) == null) {
		    resp.sendError(1200);
		}
		
		MsTH msth=dao.leer(req.getParameter("author")).get(0);
		
		msth.setFichero(blobKeys.get(0).getKeyString());
		msth.setEstado(3);
		dao.actualizar(msth);
		
		RequestDispatcher view=req.getRequestDispatcher("jsp/ShowMsTHView.jsp");
		try {
			view.forward(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
