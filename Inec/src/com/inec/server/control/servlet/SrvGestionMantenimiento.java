package com.inec.server.control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.process.GestionUsuario;
import com.inec.shared.UnknownException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SrvGestionMantenimiento  extends HttpServlet {
	
	private static final long serialVersionUID = 2556188587429050683L;
	private static final Logger log = Logger.getLogger(SrvGestionMantenimiento.class
			.getName());

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String url = request.getServletPath();
		 System.out.println( "la url es  "+url);
		if (url.equals("/inecservlet.html")) {
			response.setContentType("text/html;charset=ISO-8859-1");
			HttpSession session= request.getSession(true);			
			session.setAttribute("idsession", session.getId());		
			redirectAdmin(request, response);
		}else{
			generarPdf(request,response);
		}
		
	}

	private void redirectAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {			
			String urlRedirect=request.getRequestURL().toString().replaceAll(request.getRequestURI(), "/Inec.jsp");			
			log.warning(request.getRequestURI());
			log.warning(urlRedirect);
			PrintWriter out=response.getWriter();
			out.print(urlRedirect);
			out.flush();
			out.close();
		} catch (Exception ex) {
			log.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}	

	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void generarPdf(javax.servlet.http.HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
//		String fileName = "ejemplo.pdf";
		response.setContentType("application/pdf");
//		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		  try {
		   Document documento = new Document();
		
		   String dniUsuario= request.getParameter("codeusuario");
		   Long fechaInicial= Long.parseLong(request.getParameter("fechainicial"));
		   Long fechafinal= Long.parseLong(request.getParameter("fechafinal"));
		 
		   UsuarioFiscalizador beanUsuarioFiscalizador= GestionUsuario.verUsuario(dniUsuario);
		   List<PosicionFiscalizador> listaPosicionFiscalizador= GestionUsuario.listarPosicionByIntervalo(dniUsuario, fechaInicial, fechafinal);
		   PdfWriter.getInstance(documento, response.getOutputStream());
		   documento.open();
		   documento.add(new Paragraph("INEC REPORTE\n\n"));
		   documento.add(new Paragraph("FISCALIZADOR: "+ beanUsuarioFiscalizador.getApellido()+" "+beanUsuarioFiscalizador.getNombre()+"\n\n"));
		   documento.add(new Paragraph("LISTA DE RUTAS\n\n"));
		   
		   PdfPTable tabla = new PdfPTable(6);
		   
		   Font font = FontFactory.getFont(FontFactory.HELVETICA, 12,
                   Font.BOLD, BaseColor.BLACK);
		   
		   PdfPCell cellHeaderLatitud = new PdfPCell(new Phrase("LATITUD",font));		   	  
           tabla.addCell(cellHeaderLatitud);
           
           PdfPCell cellHeaderLongitud = new PdfPCell(new Phrase("LONGITUD",font));           	  
           tabla.addCell(cellHeaderLongitud);
		   
		   PdfPCell cellHeaderFecha = new PdfPCell(new Phrase("FECHA",font));
		   cellHeaderFecha.setColspan(2);		   
           tabla.addCell(cellHeaderFecha);		
           
           PdfPCell cellHeaderUbigeo = new PdfPCell(new Phrase("UBIGEO",font));
           cellHeaderUbigeo.setColspan(2);
           tabla.addCell(cellHeaderUbigeo);		

		   
           for (int i = 0; i < listaPosicionFiscalizador.size(); i++) {        	   
               PdfPCell cellLatitud = new PdfPCell(new Phrase(listaPosicionFiscalizador.get(i).getLatitud().toString()));  
//               cellLatitud.setBorder(PdfPCell.NO_BORDER);
               tabla.addCell(cellLatitud);
               
               PdfPCell cellLongitud = new PdfPCell(new Phrase(listaPosicionFiscalizador.get(i).getLongitud().toString()));                                    
               tabla.addCell(cellLongitud);
               
               PdfPCell cellFecha = new PdfPCell(new Phrase(new SimpleDateFormat().format(listaPosicionFiscalizador.get(i).getVersion())));
               cellFecha.setColspan(2);
               tabla.addCell(cellFecha);
               
               PdfPCell cellUbigeo = new PdfPCell(new Phrase(listaPosicionFiscalizador.get(i).getDepartamento()+" "+
               listaPosicionFiscalizador.get(i).getProvincia()+" "+ listaPosicionFiscalizador.get(i).getDistrito())); 
               cellUbigeo.setColspan(2);
               tabla.addCell(cellUbigeo);
           }          
           
           documento.add(tabla);
		   documento.close();
		   
		  } catch (DocumentException | UnknownException e) {
		   e.printStackTrace();
		  }
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);		  
	}	
}