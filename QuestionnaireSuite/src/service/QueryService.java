package service;

import java.io.OutputStream;

import net.sf.json.JSONArray;

public interface QueryService {
	
	JSONArray query(String conditions);
	
	void exportToExcel(JSONArray result, OutputStream out);
	
	void exportToPDF(JSONArray result, OutputStream outputStream);
}
