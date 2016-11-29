package util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	/**
	 * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
	 * 
	 * @param host
	 *            主机名
	 * @param user
	 *            用户名
	 * @param psw
	 *            密码
	 * @param port
	 *            端口
	 * @param command
	 *            命令
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

		// 调用第一个方法，向C盘生成一个名字为ITextTest.pdf 的文件
		try {
			writeSimplePdf();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 调用第二个方法，向C盘名字为ITextTest.pdf的文件，添加章节。
		try {
			writeCharpter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeSimplePdf() throws Exception {

		// 1.新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\ITextTest.pdf"));

		// 3.打开文档
		document.open();

		// 4.向文档中添加内容
		// 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph("Some more text on the  first page with different color and font type.",
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 150, 200))));

		// 5.关闭文档
		document.close();
	}

	/**
	 * 添加含有章节的pdf文件
	 * 
	 * @throws Exception
	 */
	public static void writeCharpter() throws Exception {

		// 新建document对象 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 20, 20, 20, 20);

		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("c:\\ITextTest.pdf"));

		// 打开文件
		document.open();

		// 标题
		document.addTitle("Hello mingri example");

		// 作者
		document.addAuthor("wolf");

		// 主题
		document.addSubject("This example explains how to add metadata.");
		document.addKeywords("iText, Hello mingri");
		document.addCreator("My program using iText");

		// document.newPage();
		// 向文档中添加内容
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

		// 新建章节
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

		// 关闭文档
		document.close();
	}

}
