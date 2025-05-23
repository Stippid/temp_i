package com.controller.WorkOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.Locale;
import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;

public class PDF_Peripheral_Asset_Download extends AbstractPdfView {

	String Type = "";
	String foot = "";
	List<String> TH;
	ArrayList<ArrayList<String>> list1;
	String username = "";
	String wksp_unit_name;
	String wo_dt = "";
	String dt_eqpt_reqd_fwd_wksp = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_Peripheral_Asset_Download(String Type, List<String> TH, String username,
			ArrayList<ArrayList<String>> list1) {
		this.Type = Type;
		this.TH = TH;
		this.list1 = list1;
		this.username = username;
	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Image logo = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "indianarmy_smrm5aaanew.png";
			logo = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(40);
		logo.scaleAbsoluteWidth(30);
		logo.scalePercent(12);
		Chunk chunk = new Chunk(logo, -5, 10);

		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String indian_Army = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "indianarmy_smrm5aaanew.png";
			logo2 = Image.getInstance(indian_Army);// "d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(40);
		logo2.scaleAbsoluteWidth(30);
		logo2.scalePercent(12);
		Chunk chunk2 = new Chunk(logo2, -5, 10);
		Chunk underline = new Chunk(" ", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);

	
		
	
		
		

		Chunk underline2 = new Chunk("WORKORDER", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);

		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline2);
		p.add("\n");
		p.add("\n");

//		p.add(underline3);
		p.add("\n");
		p.add("\n");
		p.setFont(fontTableHeading1);
		Paragraph cell = new Paragraph(p);
		cell.setAlignment(Element.ALIGN_CENTER);
		float[] relativeWidths;
		int colunmSize = 3;
		relativeWidths = new float[colunmSize];
		Arrays.fill(relativeWidths, 0, colunmSize, 1);
		relativeWidths[1] = (float) 2.50;
		table3.addCell(new Phrase(chunk));
		table3.addCell(cell);
		table3.addCell(new Phrase(chunk2));
		Phrase p2 = new Phrase();
		try {
			table3.setWidths(relativeWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.setWidthPercentage(120);
		p2.add(table3);

		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		Phrase p1 = new Phrase();

		Chunk p89 = new Chunk(foot, fontTableHeadingMainHead);
		p89.setUnderline(0.1f, -2f);
		p1.add(p89);
		p1.add("\n");
//		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);
//		p90.setUnderline(0.1f, -2f);
//		p1.add(p90);

		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);

		document.setPageCount(1);

		document.setPageSize(PageSize.A4); // set document landscape

		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Font fontTableHeading2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");

		Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);

		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);

		Paragraph p = new Paragraph();

		Paragraph pl;

		Paragraph plv;
		Paragraph pr;

		Paragraph prv;
		PdfPTable tableleftFM = new PdfPTable(2);
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		pl = new Paragraph("Work Order No :-", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(3).toString(), fontTableValue);

		tableleftFM.addCell(plv);

		pl = new Paragraph("Date :", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(1).toString(), fontTableValue);

		tableleftFM.addCell(plv);

		pl = new Paragraph("Dte/Unit :", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(1).toString(), fontTableValue);

		tableleftFM.addCell("");

		pl = new Paragraph("Army Tele No :", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(1).toString(), fontTableValue);

		tableleftFM.addCell("");
		
		
		PdfPTable tablecenter = new PdfPTable(2);
		tablecenter.setWidthPercentage(100);
		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.addCell("");
		tablecenter.addCell("");
		tablecenter.addCell("");
		tablecenter.addCell("");
		tablecenter.addCell("");
		tablecenter.addCell("");
		tablecenter.addCell("");
		
		
		
		
		PdfPTable tablerightFM = new PdfPTable(2);
		tablerightFM.setWidthPercentage(100);
		tablerightFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		pr = new Paragraph("Main Wksp : ", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(0).toString(), fontTableValue);

		tablerightFM.addCell(prv);

		pr = new Paragraph("Station : ", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(0).toString(), fontTableValue);

		tablerightFM.addCell("");

		pr = new Paragraph("Password :", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(2).toString(), fontTableValue);

		tablerightFM.addCell("");

		pr = new Paragraph("Second Password :", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(2).toString(), fontTableValue);

		tablerightFM.addCell("");

		PdfPTable tablecenterFM = new PdfPTable(3);

		tablecenterFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenterFM.setWidthPercentage(100);
	
		PdfPTable maintenanceTable = new PdfPTable(1);
		maintenanceTable.setWidths(new float[] {1});
		maintenanceTable.setWidthPercentage(100);

		// Set the border for the table to NO_BORDER
		maintenanceTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		

		Chunk underline66 = new Chunk("Appx A", fontTableHeading);
		underline66.setUnderline(0.1f, -2f);
		// Header Cells
		Paragraph paragraph1 = new Paragraph(underline66);
		PdfPCell maintenanceDateHeaderCell = new PdfPCell(paragraph1);
//		maintenanceDateHeaderCell.setMinimumHeight(30);
		maintenanceDateHeaderCell.setPaddingRight(135f); 
		maintenanceDateHeaderCell.setVerticalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell.setBorder(PdfPCell.NO_BORDER); // No border for this cell
		
		maintenanceTable.addCell(maintenanceDateHeaderCell);

		PdfPCell maintenanceSubSystemHeaderCell = new PdfPCell(new Paragraph("Refer to Para 10(b) of info \n0security Instrs (NSC) \nIn lieu of IAFO: 1370"));
		maintenanceSubSystemHeaderCell.setMinimumHeight(30);
		maintenanceSubSystemHeaderCell.setPaddingLeft(350f); 
		maintenanceSubSystemHeaderCell.setVerticalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell.setBorder(PdfPCell.NO_BORDER); // No border for this cell
		maintenanceTable.addCell(maintenanceSubSystemHeaderCell);
		maintenanceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// Add the table to the document
		document.add(maintenanceTable);
		
		tablecenterFM.addCell(tableleftFM);
		tablecenterFM.addCell(tablecenter);
		tablecenterFM.addCell(tablerightFM);
		document.add(new Phrase("\n"));
		document.add(tablecenterFM);
		document.add(new Phrase("\n"));

		PdfPTable table3 = new PdfPTable(6);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for (int h = 0; h < TH.size(); h++) {
			p = new Paragraph(TH.get(h), fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}

		table3.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			for (int j = 0; j < l.size(); j++) {
				p = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}

		// table.addCell(table3);
		document.add(table3);
		document.add(new Phrase("\n"));

		Paragraph certi = new Paragraph("Certificate:- \n\n", fontTableHeading);
		document.add(certi);

		Paragraph M34 = new Paragraph("It is certified that:- \n\n", fontTableHeading);
		document.add(M34);

		Paragraph M35 = new Paragraph(
				"(a)   The computer/computer peripherals forwarded vide the work order are issued by ADG IT "
						+ " and is eligible for repair and up gradation at Army HQ NSC (HQ TG EME) as per policy issued vide "
						+ "ADG system (S & C) letter No. B/04015/ADS Sys (T&P) dated 24 Sep 99. \n\n",
				fontTableHeading);
		document.add(M35);

		Paragraph M36 = new Paragraph(
				"(b)   Date introduced in service of the eqpt is _________________________________  and the eqpt is neither under "
						+ "warranty nor on AMC.\n\n",
				fontTableHeading);
		document.add(M36);

		Paragraph M37 = new Paragraph(
				"(c)   There is no Classified data in the computer sys. AHQ NSC (HQ TG EME) does not take any"
						+ " responsibility. What so ever security and the loss of data during repair.\n\n",
				fontTableHeading);
		document.add(M37);

		Paragraph M38 = new Paragraph(
				"(d)   The eqpt was repaired in presence of unit rep No________________Rank ______________\nName: __________________ Sign ___________________.\n\n",
				fontTableHeading);
		document.add(M38);

		Paragraph M39 = new Paragraph(
				"(e)   Computers will be deposited to the NSC along with original voucher and log book for necessary repair/replacement of parts.\n\n",
				fontTableHeading);
		document.add(M39);

		Chunk underline6 = new Chunk("Initiated By \n", Heading);
		underline6.setUnderline(0.1f, -2f);
		Paragraph paragraph = new Paragraph(underline6);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
//		document.add(new Phrase("\n"));
		Chunk officestamp = new Chunk("(Office Stamp) \n", fontTableHeadingNonBoldData);
		underline6.setUnderline(0.1f, -2f);
		document.add(new Phrase("\n"));
		document.add(officestamp);
		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));
		Chunk underline7 = new Chunk("ACCEPTED/NOT ACCEPTED \n", fontTableHeading2);
		underline7.setUnderline(0.1f, -2f);
		Paragraph p12 = new Paragraph(underline7);
		p12.setAlignment(Element.ALIGN_CENTER);
		document.add(p12);

		LocalDate currentdate = LocalDate.now();

		int currentMonth = currentdate.getMonthValue();

		int currentYear = currentdate.getYear();

//		Paragraph M42= new Paragraph("Date :     /"+currentMonth+"/"+currentYear,fontTableHeading);
//		document.add(M42);

		super.buildPdfMetadata(model, document, request);
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {
			this.request = request;
		}

		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
				PdfContentByte[] canvases) {
			String ip = "";
			if (request != null) {
				ip = request.getHeader("X-FORWARDED-FOR");
				if (ip == null || "".equals(ip)) {
					ip = request.getRemoteAddr();
				}
			}
			Date now = new Date();
			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
			String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;

			Image img = null;
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
					BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.lightGray);
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
			graphics.drawString(watermark + watermark, 0, 20);

			try {
				try {
					img = com.lowagie.text.Image.getInstance(bufferedImage, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (BadElementException e) {
				e.printStackTrace();
			}
			this.image = img;

			try {

				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];

				int tableWidth = (int) table.getTotalWidth();
				int first = 0;
				if (tableWidth == 523) {
					first = 750;
				}
				if (tableWidth == 770) {
					first = 500;
				}

				int last = first - (int) table.getRowHeight(0);
				while (first > last) {
					image.setAbsolutePosition(30, first);
					cb.addImage(image, false);
					first -= 30;
				}
			} catch (DocumentException e) {
				throw new ExceptionConverter(e);
			}
		}
	}

}