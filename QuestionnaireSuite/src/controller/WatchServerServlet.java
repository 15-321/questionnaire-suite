package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IP;
import beans.ServerConditions;
import net.sf.json.JSONObject;
import service.WatchIPService;
import service.WatchIPServiceImpl;
import service.WatchServerService;
import service.WatchServerServiceImpl;


@WebServlet("/WatchServerServlet")
public class WatchServerServlet extends HttpServlet {
	private static final long serialVersionUID = 22L;
	WatchServerService watchServerService = new WatchServerServiceImpl();
	//WatchIPService watchIPService = new WatchIPServiceImpl();
       
    public WatchServerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServerConditions serverConditions= watchServerService.getConditions();		
		JSONObject jo = new JSONObject();
		jo.element("condition", serverConditions);	
		PrintWriter writer = response.getWriter();
		writer.write(jo.toString());
		writer.close();
		watchServerService.saveToDB(serverConditions);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
