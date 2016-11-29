package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.WatchIPService;
import service.WatchIPServiceImpl;


@WebServlet("/WatchIPServlet")
public class WatchIPServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private WatchIPService ws = new WatchIPServiceImpl();

    public WatchIPServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter pw = response.getWriter();
	    String district = request.getParameter("district");
	    List<String> ips = ws.getIPsByDistrict(district);
	    JSONArray ja = JSONArray.fromObject(ips);
	    pw.write(ja.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
