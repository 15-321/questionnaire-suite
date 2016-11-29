package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ServerConditions;
import net.sf.json.JSONObject;
import service.WatchServerService;
import service.WatchServerServiceImpl;


@WebServlet("/WatchServerServlet")
public class WatchServerServlet extends HttpServlet {
	private static final long serialVersionUID = 22L;
       
    public WatchServerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WatchServerService watchServerService = new WatchServerServiceImpl();
		ServerConditions serverConditions= watchServerService.getConditions();
		
		JSONObject jo = new JSONObject();
		jo.element("condition", serverConditions);	
		PrintWriter writer = response.getWriter();
		writer.print(jo.toString());
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
