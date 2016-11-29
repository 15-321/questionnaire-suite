package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.QueryService;
import service.QueryServiceImpl;


@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

    public QueryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		QueryService queryService = new QueryServiceImpl();
		String condition = request.getParameter("condition");
		
		JSONArray result = queryService.query(condition);
		
		if (operation.equals("querry")) {
			PrintWriter writer = response.getWriter();
			writer.write(result.toString());
			writer.close();
		} else if (operation.equals("exportToExcel")) {
			byte[] bytes = queryService.exportToExcel(result);
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.close();
		} else if (operation.equals("exportToPDF")) {
			OutputStream outputStream = response.getOutputStream();
			queryService.exportToPDF(result, outputStream);
		}
	}

}
