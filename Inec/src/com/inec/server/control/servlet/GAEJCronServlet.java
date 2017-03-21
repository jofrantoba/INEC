package com.inec.server.control.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inec.server.model.process.GestionUsuario;

@SuppressWarnings("serial")
public class GAEJCronServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(GAEJCronServlet.class.getName());
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
	throws IOException {

		try {
		_logger.info("Ejecutando Tarea");
			GestionUsuario.prepareCronTask();
		}
		catch (Exception ex) {	
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doGet(req, resp);
	}
}
