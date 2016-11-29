package util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfWriter;

public class Utils {
    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://139.199.192.64:3306/questionnaire";
    private static String user = "root";
    private static String password = "root";

    public static Connection getConnection() {
        try {
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
	/**
	 * Զ�� ִ��������ؽ�����ù��� ��ͬ���ģ�ִ����Ż᷵�أ�
	 * 
	 * @param host
	 *            ������
	 * @param user
	 *            �û���
	 * @param psw
	 *            ����
	 * @param port
	 *            �˿�
	 * @param command
	 *            ����
	 * @return
	 */
	public static String exec(String host, String user, String psw, int port, String command) {
		String result = "";
		Session session = null;
		ChannelExec openChannel = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			//int exitStatus = openChannel.getExitStatus();
			// System.out.println(exitStatus);
			openChannel.connect();
			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				result += new String(buf.getBytes("gbk"), "UTF-8") + "\n";
			}
		} catch (JSchException | IOException e) {
			result += e.getMessage();
		} finally {
			if (openChannel != null && !openChannel.isClosed()) {
				openChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		return result;
	}

	public static void main(String args[]) {
		// String exec = exec("139.199.192.64", "root", "z1s9j9m4!", 22, "vmstat
		// 2 1");
		// System.out.println(exec);

		// ���õ�һ����������C������һ������ΪITextTest.pdf ���ļ�
		try {
			writeSimplePdf();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ���õڶ�����������C������ΪITextTest.pdf���ļ�������½ڡ�
		try {
			writeCharpter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeSimplePdf() throws Exception {

		// 1.�½�document����
		// ��һ��������ҳ���С���������Ĳ����ֱ������ҡ��Ϻ���ҳ�߾ࡣ
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		// 2.����һ����д��(Writer)��document���������ͨ����д��(Writer)���Խ��ĵ�д�뵽�����С�
		// ���� PdfWriter ���� ��һ�������Ƕ��ĵ���������ã��ڶ����������ļ���ʵ�����ƣ��ڸ������л�����������·����
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\ITextTest.pdf"));

		// 3.���ĵ�
		document.open();

		// 4.���ĵ����������
		// ͨ�� com.lowagie.text.Paragraph ������ı����������ı�����Ĭ�ϵ����塢��ɫ����С�ȵ�����������һ��Ĭ�϶���
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("Some more text on the  first page with different color and font type.",
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 150, 200))));

		// 5.�ر��ĵ�
		document.close();
	}

	/**
	 * ��Ӻ����½ڵ�pdf�ļ�
	 * 
	 * @throws Exception
	 */
	public static void writeCharpter() throws Exception {

		// �½�document���� ��һ��������ҳ���С���������Ĳ����ֱ������ҡ��Ϻ���ҳ�߾ࡣ
		Document document = new Document(PageSize.A4, 20, 20, 20, 20);

		// ����һ����д��(Writer)��document���������ͨ����д��(Writer)���Խ��ĵ�д�뵽�����С�
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("c:\\ITextTest.pdf"));

		// ���ļ�
		document.open();

		// ����
		document.addTitle("Hello mingri example");

		// ����
		document.addAuthor("wolf");

		// ����
		document.addSubject("This example explains how to add metadata.");
		document.addKeywords("iText, Hello mingri");
		document.addCreator("My program using iText");

		// document.newPage();
		// ���ĵ����������
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("Some more text on the first page with different color and font type.",
				FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
		Paragraph title1 = new Paragraph("Chapter 1",
				FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));

		// �½��½�
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);
		Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
				FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
		Section section1 = chapter1.addSection(title11);
		Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
		section1.add(someSectionText);
		someSectionText = new Paragraph("Following is a 3 X 2 table.");
		section1.add(someSectionText);
		document.add(chapter1);

		// �ر��ĵ�
		document.close();
	}

}
