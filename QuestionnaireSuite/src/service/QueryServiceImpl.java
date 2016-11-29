package service;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import dao.QueryDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QueryServiceImpl implements QueryService {
	
	private QueryDao queryDao = new QueryDao();

	@Override
	public JSONArray query(String conditions) {
		JSONObject condition = JSONObject.fromObject(conditions);
		if (condition.containsKey("degree")) {
			int degree = condition.getInt("degree");
			condition.remove("degree");
			switch (degree) {
			case 0:
				condition.put("degree", "专科");
				break;
			case 1:
				condition.put("degree", "本科");
				break;
			case 2:
				condition.put("degree", "硕士");
				break;
			case 3:
				condition.put("degree", "博士");
				break;
			default:
				break;
			}
		}
		Iterator iterator = condition.keys();
		String key;
		StringBuilder conditionBuilder = new StringBuilder();
		int count=0;
		while(iterator.hasNext()){
			count++;
			key = (String) iterator.next();
			if (count == 1) {
				conditionBuilder.append(" where");
				conditionBuilder.append(" "+key+"="+condition.get(key));
			} else
				conditionBuilder.append(" and "+key+"="+condition.get(key));
		}
		String query = "select * from questionnaire" + conditionBuilder.toString();
	
		return queryDao.get(query);
	}

	@Override
	public byte[] exportToExcel(JSONArray result) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow title = sheet.createRow(0);
		String[] titles = {"Id", "姓名", "性别", "学校", "专业", "学历", "民族", "户口类型"};
		String key;
		int cellIndex = 0;
		//添加标题
		while(cellIndex<titles.length){
			HSSFCell cell = title.createCell(cellIndex);
			cell.setCellValue(titles[cellIndex]);
			cellIndex++;
		}
		int size = result.size();
		System.out.println("size:"+size);
		for(int index = 1; index <= size; index++){
			HSSFRow row = sheet.createRow(index);
			JSONObject object = result.getJSONObject(index-1);
			System.out.println("object:"+object);
			cellIndex = 0;
			Iterator<String> keyIterator = object.keys();
			while (keyIterator.hasNext()) {
				key = keyIterator.next();
				HSSFCell cell = row.createCell(cellIndex);
				cellIndex++;
				cell.setCellValue(object.getString(key));
				System.out.println("cellValue:"+cell.getStringCellValue());
			}
		}
		
		byte[] bytes = workbook.getBytes();
		return bytes;
	}

	@Override
	public void exportToPDF(JSONArray result, OutputStream outputStream){
		Document document = new Document();
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			BaseFont baseFontChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(baseFontChinese);
			
			document.open();
			
			for(int index=0; index<result.size(); index++){
				JSONObject object = result.getJSONObject(index);
				Iterator<String> iterator = object.keys();
				StringBuilder builder = new StringBuilder();
				while(iterator.hasNext()){
					String key = iterator.next();
					String info = key + ":" + object.getString(key);
					System.out.println(info);
					builder.append(info);
					builder.append("\n");
				}
				builder.append("\n");
				document.add(new Paragraph(builder.toString(), fontChinese));
			}
			document.close();
			outputStream.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
