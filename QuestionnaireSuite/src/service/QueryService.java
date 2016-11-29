package service;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import com.lowagie.text.DocumentException;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

public interface QueryService {
	
	JSONArray query(String conditions);
	
	byte[] exportToExcel(JSONArray result);
	
	void exportToPDF(JSONArray result, OutputStream outputStream);
}
