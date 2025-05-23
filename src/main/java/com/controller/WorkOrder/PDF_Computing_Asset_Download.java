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
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;
public class PDF_Computing_Asset_Download extends AbstractPdfView {
	
	

	String Type = "";
	String foot = "";
	List<String> TH;
	ArrayList<ArrayList<String>>list1;
	String username = "";
	String wksp_unit_name;
	String wo_dt = "";
	String dt_eqpt_reqd_fwd_wksp = "";

	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_Computing_Asset_Download(String Type,List<String> TH,String username,
			ArrayList<ArrayList<String>>list1) {
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
			String dgis_logo =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"indianarmy_smrm5aaanew.png";
			logo = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(60);
		logo.scaleAbsoluteWidth(60);
		logo.scalePercent(30);
		Chunk chunk = new Chunk(logo, -5, 4);

		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String indian_Army =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"indianarmy_smrm5aaanew.png";
			logo2 = Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(60);
		logo2.scaleAbsoluteWidth(60);
		logo2.scalePercent(30);
		Chunk chunk2 = new Chunk(logo2, -5, 4);
		Chunk underline = new Chunk(" ", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("WorkOrder", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		///BISAG V1 220822///
		Chunk underline3 = new Chunk("UnitName:"+username, fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline2);
		p.add("\n");
		
		p.add(underline3);
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
		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);
		p90.setUnderline(0.1f, -2f);
		p1.add(p90);
		
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
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 


		Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		
		Paragraph p=new Paragraph();
		
		Paragraph pl;

		Paragraph plv;
		Paragraph pr;

		Paragraph prv;
		PdfPTable tableleftFM = new PdfPTable(2);
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		
		pl = new Paragraph("Wksp  Name. :", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(0).toString(), fontTableValue);

		tableleftFM.addCell(plv);

		pl = new Paragraph("W/O Dt :", fontTableHeading);

		tableleftFM.addCell(pl);

		plv = new Paragraph(list1.get(0).get(1).toString(), fontTableValue);

		tableleftFM.addCell(plv);

		PdfPTable tablerightFM = new PdfPTable(2);
		tablerightFM.setWidthPercentage(100);
		tablerightFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
		pr = new Paragraph("WO NO : ", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(3).toString(), fontTableValue);

		tablerightFM.addCell(prv);

		pr = new Paragraph("Dt fwd to Wksp :", fontTableHeading);

		tablerightFM.addCell(pr);

		prv = new Paragraph(list1.get(0).get(2).toString(), fontTableValue);

		tablerightFM.addCell(prv);

		PdfPTable tablecenterFM = new PdfPTable(2);

		tablecenterFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenterFM.setWidthPercentage(100);

		tablecenterFM.addCell(tableleftFM);

		tablecenterFM.addCell(tablerightFM);
		document.add(new Phrase("\n"));
		document.add(tablecenterFM);
		document.add(new Phrase("\n"));

		PdfPTable table3 = new PdfPTable(8);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}
		
		table3.setHeaderRows(1); // table first row will be repeated in all pages

		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}
		
	
		document.add(table3);
		document.add(new Phrase("\n"));
		
		Paragraph M34= new Paragraph("It is certified that:- \n\n",fontTableHeading);
		document.add(M34);
		
		Paragraph M35= new Paragraph("(a)    The computer/computer peripherals forward vide this work order are issued by IHQ(OS-11 "
				+ " and or is eligible for Pre-audit and upgradation at "+list1.get(0).get(0)+" Fd Wksp coy as per policy issued vide "
				+ " ADG system B&C letter No B/031015/ AFG sys (T&P) dt 24 Sep 1999. \n\n",fontTableHeading);
		document.add(M35);
		
		Paragraph M36= new Paragraph("(b)   Date introduced in service of the eqpt is as mentioned above onward & the eqpt is neither under "
				+ "warranty nor on AMC.\n\n",fontTableHeading);
		document.add(M36);
		
		Paragraph M37= new Paragraph("(c)  There is no clarified date in the computer systems "+list1.get(0).get(0)+" Fd Wksp Coy does not take any"
				+ " responsibility whatsoever the security and the loss of data during repair.\n\n",fontTableHeading);
		document.add(M37);
		
		Paragraph M38= new Paragraph("(d)  The eqpt was repaired in presence of unit rep no ___________________ Rank __________________             "
				+ "Name ____________________  Sign ___________________.\n\n",fontTableHeading);
		document.add(M38);
		
		Paragraph M39= new Paragraph("(e)  Eqpt is deposited to CRC along with issue vouchers and log books for necessary repair / "
				+ "replacment and Pre Audit."
				+ "Name ____________________  Sign ___________________.\n\n",fontTableHeading);
		document.add(M39);
		
		
		Chunk underline6 = new Chunk("Initiated By \n", Heading);
		underline6.setUnderline(0.1f, -2f);
		document.add(underline6);
		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));
		
		Chunk underline7 = new Chunk("ACCEPTED/NOT ACCEPTED \n", fontTableHeading2);
		underline7.setUnderline(0.1f, -2f);
		Paragraph p12 = new Paragraph(underline7);
		p12.setAlignment(Element.ALIGN_CENTER);
		document.add(p12);
		document.add(new Phrase("\n"));
		
		Paragraph M41= new Paragraph("Station C/o 56 APO \n",fontTableHeading);
		document.add(M41);
		
		
		 LocalDate currentdate = LocalDate.now();

	      int currentMonth = currentdate.getMonthValue();

	      int currentYear = currentdate.getYear();
		
		Paragraph M42= new Paragraph("Date :     /"+currentMonth+"/"+currentYear,fontTableHeading);
		document.add(M42);
		
	
		super.buildPdfMetadata(model, document, request);
	}

	
	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;
		
		ImageBackgroundEvent(HttpServletRequest request){
			this.request = request;
		}
		
		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,PdfContentByte[] canvases) {
			String ip = "";
			if (request != null) {
		        ip = request.getHeader("X-FORWARDED-FOR");
		        if (ip == null || "".equals(ip)) {
		            ip = request.getRemoteAddr();
		        }
		    }
			Date now = new Date();
			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
			String watermark = " Generated by "+username+" on "+dateString+" with IP " +ip ;
			
			Image img = null;
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.lightGray);
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
			graphics.drawString(watermark+watermark,0, 20);

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

				int loop = (int) table.getRowHeight(0);
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