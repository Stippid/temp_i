package com.controller.LogBook;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
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
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class PDF_Logbook  extends AbstractPdfView {

	List<String> TH;
	String foot="";
	String username = "";
	final static String USER_PASSWORD = "user";
	String batchId;


	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public PDF_Logbook(List<String> TH,String foot,String username){

		this.TH = TH;this.foot=foot;this.username = username;
	}
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = batchId;

		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		
		response.setHeader("Content-Disposition", "inline; filename=\""+file_name+".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");


		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph p44 = new Paragraph() ;
		Paragraph p60 = new Paragraph() ;



		Paragraph pl;

		Paragraph plv;
		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);
		Font fontTableValue2 = FontFactory.getFont(FontFactory.TIMES, 9);

		////////////////////////////seal ////////

		PdfPTable table1 = new PdfPTable(11);
		table1.setWidths(new int[]{1,2,2,2,2,2,2,2,2,2,3});
		Paragraph p;
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table1.addCell(p);
		}

		table1.setHeaderRows(1);

		if(aList.size() > 0) {
			for(int i=0;i< aList.size();i++) {
				List<String> l = aList.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					PdfPCell cell = new PdfPCell(p); // Wrap paragraph in cell

					if (j == 10) {
						cell.setMinimumHeight(50);
					}

					table1.addCell(cell);
				}
			}
		}else {
			p = new Paragraph("No Data available");
			PdfPCell c4 = new PdfPCell(p);
			c4.setColspan(13);
			table1.addCell(c4);
		}


		///////// Remarks ///////////
		PdfPTable tableRemarks = new PdfPTable(4);
		tableRemarks.setWidthPercentage(100);
		tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		
		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));


		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table1);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(tableRemarks);
		table.addCell(cell1);



		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);
		PdfPTable table11 = new PdfPTable(1);
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell11;
		cell11 = new PdfPCell();
		cell11.setBorder(Rectangle.NO_BORDER);
		cell11.addElement(table);
		table11.addCell(cell11);
		table11.setTableEvent(new ImageBackgroundEvent(request,batchId));
//		document.add(table11);
		//document.add(table);

		PdfPTable tableR1 = new PdfPTable(3);
		tableR1.setWidths(new float[] { 0.3f, 1, 1 });

		tableRemarks.setWidthPercentage(100);
		tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		///----1
		PdfPCell cell08 = new PdfPCell(new Paragraph("1."));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell08.setMinimumHeight(35);
		cell08.setVerticalAlignment(Element.ALIGN_CENTER);
		cell08.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cell08);
		
		PdfPCell cell09 = new PdfPCell(new Paragraph("Nomenclature"));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell09.setMinimumHeight(40);
		cell09.setVerticalAlignment(Element.ALIGN_CENTER);
		cell09.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell09);
		
		PdfPCell cell10 = new PdfPCell(new Paragraph(aList.get(0).get(0).toString()));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell10.setMinimumHeight(40);
		cell10.setVerticalAlignment(Element.ALIGN_CENTER);
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell10);
		
		
		
		///----1
		
		PdfPCell cell081 = new PdfPCell(new Paragraph("2."));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell081.setMinimumHeight(40);
		cell081.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cell081);
		
		PdfPCell cell091 = new PdfPCell(new Paragraph("Make/ Model"));

		cell091.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell091.setMinimumHeight(40);
		tableR1.addCell(cell091);
		
		PdfPCell cell101 = new PdfPCell(new Paragraph(aList.get(0).get(1).toString()));

		cell101.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell101.setMinimumHeight(40);
		tableR1.addCell(cell101);
		
		///----1
		
		PdfPCell cell082 = new PdfPCell(new Paragraph("3."));

		cell082.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell082.setMinimumHeight(40);
		tableR1.addCell(cell082);
		
		PdfPCell cell092 = new PdfPCell(new Paragraph("Regd No"));
		cell092.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell081.setMinimumHeight(40);
		tableR1.addCell(cell092);
		
		PdfPCell cell102 = new PdfPCell(new Paragraph());//aList.get(0).get(2).toString()
		cell102.setMinimumHeight(40);
		cell102.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell102);
		
		///----1
		PdfPCell cell083 = new PdfPCell(new Paragraph("4."));

		cell083.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell083.setMinimumHeight(40);
		tableR1.addCell(cell083);
		
		PdfPCell cell093 = new PdfPCell(new Paragraph("Configuration"));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell093.setMinimumHeight(40);
		cell093.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell093);
		
		PdfPCell cell103 = new PdfPCell(new Paragraph());
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell103.setMinimumHeight(40);
		cell103.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell103);
		
		///----1
		
		PdfPCell cell084 = new PdfPCell(new Paragraph("5."));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell084.setMinimumHeight(40);
		cell084.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cell084);
		
		PdfPCell cell094 = new PdfPCell(new Paragraph("CRV No & dt(Att copy)"));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell094.setMinimumHeight(40);
		cell094.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell094);
		
		PdfPCell cell104 = new PdfPCell(new Paragraph());//aList.get(0).get(3).toString()
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell104.setMinimumHeight(40);
		cell104.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell104);
		
		
		///----1
		PdfPCell cell085 = new PdfPCell(new Paragraph("6."));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell085.setMinimumHeight(40);
		cell085.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cell085);
		
		PdfPCell cell095 = new PdfPCell(new Paragraph("Manufacturer"));
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell095.setMinimumHeight(40);
		cell095.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell095);
		
		PdfPCell cell105 = new PdfPCell(new Paragraph());
//		cell08.setBorder(Rectangle.NO_BORDER);
//		cell08.setColspan(3);
		cell105.setMinimumHeight(40);
		cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cell105);

		///----1
		PdfPCell cellNumber = new PdfPCell(new Paragraph("7."));
		cellNumber.setMinimumHeight(40);
		cellNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cellNumber);

		PdfPCell cellManufacturer = new PdfPCell(new Paragraph("Date Of Installation"));
		cellManufacturer.setMinimumHeight(40);
		cellManufacturer.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellManufacturer);

		PdfPCell cellEmpty = new PdfPCell(new Paragraph(""));
		cellEmpty.setMinimumHeight(40);
		cellEmpty.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellEmpty);

		///----1
		PdfPCell cellNumber2 = new PdfPCell(new Paragraph("8."));
		cellNumber2.setMinimumHeight(40);
		cellNumber2.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cellNumber2);

		PdfPCell cellManufacturer2 = new PdfPCell(new Paragraph("Warranty / AMC upto"));
		cellManufacturer2.setMinimumHeight(40);
		cellManufacturer2.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellManufacturer2);

		PdfPCell cellEmpty2 = new PdfPCell(new Paragraph(aList.get(0).get(4).toString()));
		cellEmpty2.setMinimumHeight(40);
		cellEmpty2.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellEmpty2);

		///----1
		PdfPCell cellNumber3 = new PdfPCell(new Paragraph("9."));
		cellNumber3.setMinimumHeight(40);
		cellNumber3.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cellNumber3);

		PdfPCell cellManufacturer3 = new PdfPCell(new Paragraph("Grant Procured From "));
		cellManufacturer3.setMinimumHeight(40);
		cellManufacturer3.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellManufacturer3);

		PdfPCell cellEmpty3 = new PdfPCell(new Paragraph(aList.get(0).get(5).toString()));
		cellEmpty3.setMinimumHeight(40);
		cellEmpty3.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellEmpty3);

		///----1
		PdfPCell cellNumber4 = new PdfPCell(new Paragraph("10."));
		cellNumber4.setMinimumHeight(40);
		cellNumber4.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cellNumber4);

		PdfPCell cellManufacturer4 = new PdfPCell();
		
		cellManufacturer4.addElement(new Paragraph("Maintenance"));
		cellManufacturer4.addElement(new Paragraph("(a) Agency "));
		cellManufacturer4.addElement(new Paragraph("(b) Period "));
		cellManufacturer4.addElement(new Paragraph("\n"));
		cellManufacturer4.setMinimumHeight(40);
		cellManufacturer4.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellManufacturer4);

		PdfPCell cellEmpty4 = new PdfPCell(new Paragraph(aList.get(0).get(6).toString()));
		cellEmpty4.setMinimumHeight(40);
		cellEmpty4.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellEmpty4);

		///----1
		PdfPCell cellNumber5 = new PdfPCell(new Paragraph("11."));
		cellNumber5.setMinimumHeight(40);
		cellNumber5.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR1.addCell(cellNumber5);

		PdfPCell cellManufacturer5 = new PdfPCell(new Paragraph("Sign of Vendor rep"));
		
		cellManufacturer5.setMinimumHeight(40);
		cellManufacturer5.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellManufacturer5);

		PdfPCell cellEmpty5 = new PdfPCell(new Paragraph(""));
		cellEmpty5.setMinimumHeight(40);
		cellEmpty5.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableR1.addCell(cellEmpty5);

		
		
		
		

	String typeasset="UNIT";
	Chunk underline3 = new Chunk(typeasset,FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
	underline3.setUnderline(0.1f, -2f);
	
	String typeasset1="O/o DGAFMS";
	Chunk underline31 = new Chunk(typeasset1, FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
	underline31.setUnderline(0.1f, -2f);

	Chunk underline32 = new Chunk("BRANCH/SECTION :                ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
	underline32.setUnderline(0.1f, -2f);
	
	Chunk underline33 = new Chunk("LOG BOOK", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
	underline33.setUnderline(0.1f, -2f);
	Chunk underline34 = new Chunk(" (         DESKTOP)", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
	underline34.setUnderline(0.1f, -2f);
	
	

	
	
	Paragraph p1 = new Paragraph();
	//p1.setSpacingAfter(10);
	p1.add("\n\n\n\n\n\n\n");
	
	p1.add(underline3);
	p1.add("\n\n\n");
	//p1.setSpacingAfter(10);
	p1.add(underline31);
	p1.add("\n\n\n\n\n");
	
	//p1.add(underline32);
	p1.add("\n\n\n\n\n");
	p1.add(underline33);
	p1.add("\n\n\n\n");
	p1.add(underline34);
	p1.add("\n\n\n\n");
	p1.setAlignment(Element.ALIGN_MIDDLE);
	p1.setAlignment(Element.ALIGN_CENTER); 

	Paragraph p2 = new Paragraph();
	Chunk underline221 = new Chunk("COMPUTER SYSTEM", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline221.setUnderline(0.1f, -2f);

	Chunk underline322 = new Chunk("HISTORY AND MAINTAINCE LOG BOOK ", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline322.setUnderline(0.1f, -2f);

	Chunk underline421 = new Chunk("PARTICULARS OF EQUIMENT", 
			FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline421.setUnderline(0.1f, -2f);
	p2.add("\n");
	p2.add(underline221);
	p2.add("\n\n");
	p2.add(underline322);
	p2.add("\n\n");
	p2.add(underline421);
	p2.add("\n\n\n");
	
	p2.setAlignment(Element.ALIGN_MIDDLE);
	p2.setAlignment(Element.ALIGN_CENTER); 
	
	
///page 3	
	Paragraph p3 = new Paragraph();
	Chunk underline321 = new Chunk("HARDWARE DETAILS", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline321.setUnderline(0.1f, -2f);
	p3.add(underline321);
	p3.add("\n\n");
	p3.setAlignment(Element.ALIGN_MIDDLE);
	p3.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable tableR2 = new PdfPTable(3);
	tableR2.setWidths(new float[] { 0.8f, 1.2f, 0.8f });

	tableRemarks.setWidthPercentage(100);
	tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	///----1
	PdfPCell cell3001 = new PdfPCell(new Paragraph("PERIPHARAL"));
	cell3001.setMinimumHeight(30);
	cell3001.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3001.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR2.addCell(cell3001);
	
	PdfPCell cell3002 = new PdfPCell(new Paragraph("TYPE OF SPECIFICATION"));
	cell3002.setMinimumHeight(30);
	cell3002.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3002.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR2.addCell(cell3002);
	
	PdfPCell cell3003 = new PdfPCell(new Paragraph("SERIAL"));
	cell3003.setMinimumHeight(30);
	cell3003.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3003.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR2.addCell(cell3003);
	
	
	PdfPCell cell3004 = new PdfPCell(new Paragraph("PROCESSOR"));
	cell3004.setMinimumHeight(20);
	cell3004.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3004.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3004);
	PdfPCell cell30041 = new PdfPCell(new Paragraph(""));
	cell30041.setMinimumHeight(20);
	cell30041.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30041.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30041);
	PdfPCell cell30042 = new PdfPCell(new Paragraph(""));
	cell30042.setMinimumHeight(20);
	cell30042.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30042.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30042);

	PdfPCell cell3005 = new PdfPCell(new Paragraph("MOTHER BD"));
	cell3005.setMinimumHeight(20);
	cell3005.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3005.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3005);
	PdfPCell cell30051 = new PdfPCell(new Paragraph(""));
	cell30051.setMinimumHeight(20);
	cell30051.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30051.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30051);
	PdfPCell cell30052 = new PdfPCell(new Paragraph(""));
	cell30052.setMinimumHeight(20);
	cell30052.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30052.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30052);
	

	PdfPCell cell3006 = new PdfPCell(new Paragraph("SMPS"));
	cell3006.setMinimumHeight(20);
	cell3006.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3006.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3006);
	PdfPCell cell30061 = new PdfPCell(new Paragraph(""));
	cell30061.setMinimumHeight(20);
	cell30061.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30061.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30061);
	PdfPCell cell30062 = new PdfPCell(new Paragraph(""));
	cell30062.setMinimumHeight(20);
	cell30062.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30062.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30062);
	

	PdfPCell cell3007 = new PdfPCell(new Paragraph("RAM"));
	cell3007.setMinimumHeight(20);
	cell3007.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3007.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3007);
	PdfPCell cell30071 = new PdfPCell(new Paragraph(""));
	cell30071.setMinimumHeight(20);
	cell30071.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30071.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30071);
	PdfPCell cell30072 = new PdfPCell(new Paragraph(""));
	cell30072.setMinimumHeight(20);
	cell30072.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30072.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30072);

	PdfPCell cell3008 = new PdfPCell(new Paragraph("HARD DISK DRIVE"));
	cell3008.setMinimumHeight(20);
	cell3008.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3008.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3008);
	PdfPCell cell30081 = new PdfPCell(new Paragraph(""));
	cell30081.setMinimumHeight(20);
	cell30081.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30081.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30081);
	PdfPCell cell30082 = new PdfPCell(new Paragraph(""));
	cell30082.setMinimumHeight(20);
	cell30082.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30082.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30082);
	
	PdfPCell cell3009 = new PdfPCell(new Paragraph("FLOPPY DRIVE"));
	cell3009.setMinimumHeight(20);
	cell3009.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3009.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3009);

	PdfPCell cell30091 = new PdfPCell(new Paragraph(""));
	cell30091.setMinimumHeight(20);
	cell30091.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30091.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30091);
	PdfPCell cell30092 = new PdfPCell(new Paragraph(""));
	cell30092.setMinimumHeight(20);
	cell30092.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30092.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30092);
	
	PdfPCell cell3010 = new PdfPCell(new Paragraph("CD/DVD/WRITER"));
	cell3010.setMinimumHeight(20);
	cell3010.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3010.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3010);
	PdfPCell cell30101 = new PdfPCell(new Paragraph(""));
	cell30101.setMinimumHeight(20);
	cell30101.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30101.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30101);
	PdfPCell cell30102 = new PdfPCell(new Paragraph(""));
	cell30102.setMinimumHeight(20);
	cell30102.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30102.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30102);

	PdfPCell cell3011 = new PdfPCell(new Paragraph("DISPLAY CARD"));
	cell3011.setMinimumHeight(20);
	cell3011.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3011.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3011);
	PdfPCell cell30111 = new PdfPCell(new Paragraph(""));
	cell30111.setMinimumHeight(20);
	cell30111.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30111.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30111);
	PdfPCell cell30112 = new PdfPCell(new Paragraph(""));
	cell30112.setMinimumHeight(20);
	cell30112.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30112.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30112);

	PdfPCell cell3012 = new PdfPCell(new Paragraph("LAN Controller Card"));
	cell3012.setMinimumHeight(20);
	cell3012.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3012.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3012);
	PdfPCell cell30121 = new PdfPCell(new Paragraph(""));
	cell30121.setMinimumHeight(20);
	cell30121.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30121.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30121);
	PdfPCell cell30122 = new PdfPCell(new Paragraph(""));
	cell30122.setMinimumHeight(20);
	cell30122.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30122.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30122);

	PdfPCell cell3013 = new PdfPCell(new Paragraph("SOUND CARD"));
	cell3013.setMinimumHeight(20);
	cell3013.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3013.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell3013);
	PdfPCell cell30131 = new PdfPCell(new Paragraph(""));
	cell30131.setMinimumHeight(20);
	cell30131.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30131.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30131);
	PdfPCell cell30142 = new PdfPCell(new Paragraph(""));
	cell30142.setMinimumHeight(20);
	cell30142.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30142.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30142);
	
	
	PdfPCell cell30143 = new PdfPCell(new Paragraph("Modem Int/External"));
	cell30143.setMinimumHeight(20);
	cell30143.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30143.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30143);

	PdfPCell cell30144 = new PdfPCell(new Paragraph(""));
	cell30144.setMinimumHeight(20);
	cell30144.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30144.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30144);

	PdfPCell cell30145 = new PdfPCell(new Paragraph(""));
	cell30145.setMinimumHeight(20);
	cell30145.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30145.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30145);

	PdfPCell cell30146 = new PdfPCell(new Paragraph("MONITOR/TFT"));
	cell30146.setMinimumHeight(20);
	cell30146.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30146.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30146);

	PdfPCell cell30147 = new PdfPCell(new Paragraph(""));
	cell30147.setMinimumHeight(20);
	cell30147.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30147.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30147);

	PdfPCell cell30148 = new PdfPCell(new Paragraph(""));
	cell30148.setMinimumHeight(20);
	cell30148.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30148.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30148);

	PdfPCell cell30149 = new PdfPCell(new Paragraph("KEY BOARD"));
	cell30149.setMinimumHeight(20);
	cell30149.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30149.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30149);

	PdfPCell cell30150 = new PdfPCell(new Paragraph(""));
	cell30150.setMinimumHeight(20);
	cell30150.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30150.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30150);
	
	
	PdfPCell cell30151 = new PdfPCell(new Paragraph(""));
	cell30151.setMinimumHeight(20);
	cell30151.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30151.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30151);

	
	
	
	PdfPCell cell30152 = new PdfPCell(new Paragraph("MOUSE"));
	cell30152.setMinimumHeight(20);
	cell30152.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30152.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30152);

	PdfPCell cell30153 = new PdfPCell(new Paragraph(""));
	cell30153.setMinimumHeight(20);
	cell30153.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30153.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30153);

	PdfPCell cell30154 = new PdfPCell(new Paragraph(""));
	cell30154.setMinimumHeight(20);
	cell30154.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30154.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30154);

	PdfPCell cell30155 = new PdfPCell(new Paragraph("PRINTER1"));
	cell30155.setMinimumHeight(20);
	cell30155.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30155.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30155);

	PdfPCell cell30156 = new PdfPCell(new Paragraph(""));
	cell30156.setMinimumHeight(20);
	cell30156.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30156.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30156);

	PdfPCell cell30157 = new PdfPCell(new Paragraph(""));
	cell30157.setMinimumHeight(20);
	cell30157.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30157.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30157);

	PdfPCell cell30158 = new PdfPCell(new Paragraph("PRINTER2"));
	cell30158.setMinimumHeight(20);
	cell30158.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30158.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30158);

	PdfPCell cell30159 = new PdfPCell(new Paragraph(""));
	cell30159.setMinimumHeight(20);
	cell30159.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30159.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30159);

	PdfPCell cell30160 = new PdfPCell(new Paragraph(""));
	cell30160.setMinimumHeight(20);
	cell30160.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30160.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30160);
	
	
	PdfPCell cell30161 = new PdfPCell(new Paragraph("SCANNER"));
	cell30161.setMinimumHeight(20);
	cell30161.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30161.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30161);

	PdfPCell cell30162 = new PdfPCell(new Paragraph(""));
	cell30162.setMinimumHeight(20);
	cell30162.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30162.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30162);

	PdfPCell cell30163 = new PdfPCell(new Paragraph(""));
	cell30163.setMinimumHeight(20);
	cell30163.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30163.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30163);

	PdfPCell cell30164 = new PdfPCell(new Paragraph("CVT/UPS"));
	cell30164.setMinimumHeight(20);
	cell30164.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30164.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30164);

	PdfPCell cell30165 = new PdfPCell(new Paragraph(""));
	cell30165.setMinimumHeight(20);
	cell30165.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30165.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30165);

	PdfPCell cell30166 = new PdfPCell(new Paragraph(""));
	cell30166.setMinimumHeight(20);
	cell30166.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30166.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30166);

	PdfPCell cell30167 = new PdfPCell(new Paragraph("OTHERS"));
	cell30167.setMinimumHeight(20);
	cell30167.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30167.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30167);

	PdfPCell cell30168 = new PdfPCell(new Paragraph(""));
	cell30168.setMinimumHeight(20);
	cell30168.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30168.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30168);

	PdfPCell cell30169 = new PdfPCell(new Paragraph(""));
	cell30169.setMinimumHeight(20);
	cell30169.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30169.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30169);

	
	PdfPCell cell30171 = new PdfPCell(new Paragraph(""));
	cell30171.setMinimumHeight(20);
	cell30171.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30171.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30171);

	PdfPCell cell30172 = new PdfPCell(new Paragraph(""));
	cell30172.setMinimumHeight(20);
	cell30172.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30172.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30172);

	PdfPCell cell30173 = new PdfPCell(new Paragraph(""));
	cell30173.setMinimumHeight(20);
	cell30173.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30173.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30173);
	
	
	

	PdfPCell cell30174 = new PdfPCell(new Paragraph(""));
	cell30174.setMinimumHeight(20);
	cell30174.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30174.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30174);

	PdfPCell cell30175 = new PdfPCell(new Paragraph(""));
	cell30175.setMinimumHeight(20);
	cell30175.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30175.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30175);

	PdfPCell cell30176 = new PdfPCell(new Paragraph(""));
	cell30176.setMinimumHeight(20);
	cell30176.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30176.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30176);
	
	
//
//	PdfPCell cell30177 = new PdfPCell(new Paragraph(""));
//	cell30177.setMinimumHeight(20);
//	cell30177.setVerticalAlignment(Element.ALIGN_CENTER);
//	cell30177.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell("");

	
	PdfPCell cell30178 = new PdfPCell(new Paragraph(""));
	cell30178.setMinimumHeight(20);
	cell30178.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30178.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30178);

	PdfPCell cell30179 = new PdfPCell(new Paragraph(""));
	cell30179.setMinimumHeight(20);
	cell30179.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30179.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30179);
	
	
	
	

	PdfPCell cell30180 = new PdfPCell(new Paragraph(""));
	cell30180.setMinimumHeight(20);
	cell30180.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30180.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30180);

	PdfPCell cell30181 = new PdfPCell(new Paragraph(""));
	cell30181.setMinimumHeight(20);
	cell30181.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30181.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30181);

	PdfPCell cell30182 = new PdfPCell(new Paragraph(""));
	cell30182.setMinimumHeight(20);
	cell30182.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30182.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30182);

	
	
	
	PdfPCell cell30183 = new PdfPCell(new Paragraph(""));
	cell30183.setMinimumHeight(20);
	cell30183.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30183.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30183);

	PdfPCell cell30184 = new PdfPCell(new Paragraph(""));
	cell30184.setMinimumHeight(20);
	cell30184.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30184.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30184);

	PdfPCell cell30185 = new PdfPCell(new Paragraph(""));
	cell30185.setMinimumHeight(20);
	cell30185.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30185.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30185);
	
	
	

	PdfPCell cell30186 = new PdfPCell(new Paragraph(""));
	cell30186.setMinimumHeight(20);
	cell30186.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30186.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30186);

	PdfPCell cell30187 = new PdfPCell(new Paragraph(""));
	cell30187.setMinimumHeight(20);
	cell30187.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30187.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30187);

	PdfPCell cell30188 = new PdfPCell(new Paragraph(""));
	cell30188.setMinimumHeight(20);
	cell30188.setVerticalAlignment(Element.ALIGN_CENTER);
	cell30188.setHorizontalAlignment(Element.ALIGN_LEFT);
	tableR2.addCell(cell30188);
	
	

	
	
	Paragraph p4 = new Paragraph();
	Chunk underline4021 = new Chunk("EQUIMENT DOWN TIME", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline4021.setUnderline(0.1f, -2f);
	p4.add(underline4021);
	p4.add("\n\n");
	p4.setAlignment(Element.ALIGN_MIDDLE);
	p4.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable tableR3 = new PdfPTable(6);
	tableR3.setWidths(new float[] { 1, 1,1,1,1,1 });

	tableRemarks.setWidthPercentage(100);
	tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	///----1
	PdfPCell cell4001 = new PdfPCell(new Paragraph("Date"));
	cell4001.setMinimumHeight(30);
	cell4001.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4001.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4001);
	
	PdfPCell cell4002 = new PdfPCell(new Paragraph("Sub System"));
	cell4002.setMinimumHeight(30);
	cell4002.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4002.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4002);
	
	PdfPCell cell4003 = new PdfPCell(new Paragraph("Symptom"));
	cell4003.setMinimumHeight(30);
	cell4003.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4003.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4003);

	PdfPCell cell4004 = new PdfPCell(new Paragraph("Date of Reporting"));
	cell4004.setMinimumHeight(30);
	cell4004.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4004.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4004);
	
	PdfPCell cell4005 = new PdfPCell(new Paragraph("Date of Rectified"));
	cell4005.setMinimumHeight(30);
	cell4005.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4005.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4005);
	PdfPCell cell4006 = new PdfPCell(new Paragraph("Sign of OC"));
	cell4006.setMinimumHeight(30);
	cell4006.setVerticalAlignment(Element.ALIGN_CENTER);
	cell4006.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR3.addCell(cell4006);
	
	for(int i=0;i<120;i++)
	{
		
		PdfPCell cell4006a = new PdfPCell(new Paragraph(""));
		cell4006a.setMinimumHeight(20);
		cell4006a.setVerticalAlignment(Element.ALIGN_CENTER);
		cell4006a.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableR3.addCell(cell4006a);
	
	}
	
	
	Paragraph p5 = new Paragraph();
	Chunk underline5021 = new Chunk("DETAILS OF REPAIRS", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	underline5021.setUnderline(0.1f, -2f);
	p5.add(underline5021);
	p5.add("\n\n");
	p5.setAlignment(Element.ALIGN_MIDDLE);
	p5.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable tableR4 = new PdfPTable(6);
	tableR4.setWidths(new float[] { 1, 1, 1, 1, 1, 1 });

	tableR4.setWidthPercentage(100);
	tableR4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	// Header Cells
	PdfPCell headerCell1 = new PdfPCell(new Paragraph("Date"));
	headerCell1.setMinimumHeight(30);
	headerCell1.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell1);

	PdfPCell headerCell2 = new PdfPCell(new Paragraph("Sub System Name"));
	headerCell2.setMinimumHeight(30);
	headerCell2.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell2);

	PdfPCell headerCell3 = new PdfPCell(new Paragraph("Details of Repair"));
	headerCell3.setMinimumHeight(30);
	headerCell3.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell3);

	PdfPCell headerCell5 = new PdfPCell(new Paragraph("Date of Reporting")); // Changed from headerCell4 to headerCell5
	headerCell5.setMinimumHeight(30);
	headerCell5.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell5);

	PdfPCell headerCell6 = new PdfPCell(new Paragraph("Sign of Tech (Cmptr) "));
	headerCell6.setMinimumHeight(30);
	headerCell6.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell6);

	PdfPCell headerCell7 = new PdfPCell(new Paragraph("Remark of OIC")); // Changed from headerCell6 to headerCell7
	headerCell7.setMinimumHeight(30);
	headerCell7.setVerticalAlignment(Element.ALIGN_CENTER);
	headerCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
	tableR4.addCell(headerCell7);

	// Empty Cells
	for (int i = 0; i < 150; i++) {
	    PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
	    emptyCell.setMinimumHeight(20);
	    emptyCell.setVerticalAlignment(Element.ALIGN_CENTER);
	    emptyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    tableR4.addCell(emptyCell);
	}
	
	
	Paragraph repairsDetailsParagraph = new Paragraph();
	Chunk repairsDetailsChunk = new Chunk("DETAILS OF PASSWORDS", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	repairsDetailsChunk.setUnderline(0.1f, -2f);
	repairsDetailsParagraph.add(repairsDetailsChunk);
	repairsDetailsParagraph.add("\n\n");
	repairsDetailsParagraph.setAlignment(Element.ALIGN_MIDDLE);
	repairsDetailsParagraph.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable repairsTable = new PdfPTable(5);
	repairsTable.setWidths(new float[] { 1, 1, 1, 1, 1 });

	repairsTable.setWidthPercentage(100);
	repairsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	// Header Cells
	PdfPCell dateHeaderCell = new PdfPCell(new Paragraph("Date"));
	dateHeaderCell.setMinimumHeight(30);
	dateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	dateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	repairsTable.addCell(dateHeaderCell);

	PdfPCell subSystemHeaderCell = new PdfPCell(new Paragraph("Bios PWD"));
	subSystemHeaderCell.setMinimumHeight(30);
	subSystemHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	subSystemHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	repairsTable.addCell(subSystemHeaderCell);

	PdfPCell repairDetailsHeaderCell = new PdfPCell(new Paragraph("User Pwd"));
	repairDetailsHeaderCell.setMinimumHeight(30);
	repairDetailsHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	repairDetailsHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	repairsTable.addCell(repairDetailsHeaderCell);

	PdfPCell reportingDateHeaderCell = new PdfPCell(new Paragraph("Sign of User")); // Changed from headerCell4 to reportingDateHeaderCell
	reportingDateHeaderCell.setMinimumHeight(30);
	reportingDateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	reportingDateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	repairsTable.addCell(reportingDateHeaderCell);

	PdfPCell techSignatureHeaderCell = new PdfPCell(new Paragraph("Remarks of OIC"));
	techSignatureHeaderCell.setMinimumHeight(30);
	techSignatureHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	techSignatureHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	repairsTable.addCell(techSignatureHeaderCell);

	
	// Empty Cells
	for (int i = 0; i < 140; i++) {
	    PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
	    emptyCell.setMinimumHeight(20);
	    emptyCell.setVerticalAlignment(Element.ALIGN_CENTER);
	    emptyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    repairsTable.addCell(emptyCell);
	}
	
	
	
	
	Paragraph transferDetailsParagraph = new Paragraph();
	Chunk transferDetailsChunk = new Chunk("RECORD OF INTER UNIT TRANSFER OF EQUIMENT", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	transferDetailsChunk.setUnderline(0.1f, -2f);
	transferDetailsParagraph.add(transferDetailsChunk);
	transferDetailsParagraph.add("\n\n");
	transferDetailsParagraph.setAlignment(Element.ALIGN_MIDDLE);
	transferDetailsParagraph.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable transferTable = new PdfPTable(5);
	transferTable.setWidths(new float[] { 1, 1, 1, 1, 1});

	transferTable.setWidthPercentage(100);
	transferTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	// Header Cells
	PdfPCell transferDateHeaderCell = new PdfPCell(new Paragraph("EQT"));
	transferDateHeaderCell.setMinimumHeight(30);
	transferDateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	transferDateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	transferTable.addCell(transferDateHeaderCell);

	PdfPCell transferSubSystemHeaderCell = new PdfPCell(new Paragraph("FROM"));
	transferSubSystemHeaderCell.setMinimumHeight(30);
	transferSubSystemHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	transferSubSystemHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	transferTable.addCell(transferSubSystemHeaderCell);

	PdfPCell transferDetailsHeaderCell = new PdfPCell(new Paragraph("TO"));
	transferDetailsHeaderCell.setMinimumHeight(30);
	transferDetailsHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	transferDetailsHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	transferTable.addCell(transferDetailsHeaderCell);

	PdfPCell transferReportingDateHeaderCell = new PdfPCell(new Paragraph("DATE")); // Changed from headerCell4 to transferReportingDateHeaderCell
	transferReportingDateHeaderCell.setMinimumHeight(30);
	transferReportingDateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	transferReportingDateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	transferTable.addCell(transferReportingDateHeaderCell);

	PdfPCell transferTechSignatureHeaderCell = new PdfPCell(new Paragraph("SIGNATURE "));
	transferTechSignatureHeaderCell.setMinimumHeight(30);
	transferTechSignatureHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	transferTechSignatureHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	transferTable.addCell(transferTechSignatureHeaderCell);


	// Empty Cells
	for (int i = 0; i < 140; i++) {
	    PdfPCell emptyTransferCell = new PdfPCell(new Paragraph(""));
	    emptyTransferCell.setMinimumHeight(20);
	    emptyTransferCell.setVerticalAlignment(Element.ALIGN_CENTER);
	    emptyTransferCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    transferTable.addCell(emptyTransferCell);
	}
	
	
	
	
	Paragraph maintenanceReportParagraph = new Paragraph();
	Chunk maintenanceReportChunk = new Chunk("RECORD OF CONFIGURATION CHANGES/UPGRADATION", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	maintenanceReportChunk.setUnderline(0.1f, -2f);
	maintenanceReportParagraph.add(maintenanceReportChunk);
	maintenanceReportParagraph.add("\n\n");
	maintenanceReportParagraph.setAlignment(Element.ALIGN_MIDDLE);
	maintenanceReportParagraph.setAlignment(Element.ALIGN_CENTER); 

	PdfPTable maintenanceTable = new PdfPTable(5);
	maintenanceTable.setWidths(new float[] { 1, 1, 1, 1, 1 });

	maintenanceTable.setWidthPercentage(100);
	maintenanceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	// Header Cells
	PdfPCell maintenanceDateHeaderCell = new PdfPCell(new Paragraph("Name of sub System"));
	maintenanceDateHeaderCell.setMinimumHeight(30);
	maintenanceDateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	maintenanceDateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	maintenanceTable.addCell(maintenanceDateHeaderCell);

	PdfPCell maintenanceSubSystemHeaderCell = new PdfPCell(new Paragraph("Card No"));
	maintenanceSubSystemHeaderCell.setMinimumHeight(30);
	maintenanceSubSystemHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	maintenanceSubSystemHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	maintenanceTable.addCell(maintenanceSubSystemHeaderCell);

	PdfPCell maintenanceDetailsHeaderCell = new PdfPCell(new Paragraph("Date of Change"));
	maintenanceDetailsHeaderCell.setMinimumHeight(30);
	maintenanceDetailsHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	maintenanceDetailsHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	maintenanceTable.addCell(maintenanceDetailsHeaderCell);

	PdfPCell maintenanceReportingDateHeaderCell = new PdfPCell(new Paragraph("Kind of Change")); // Changed from headerCell4 to maintenanceReportingDateHeaderCell
	maintenanceReportingDateHeaderCell.setMinimumHeight(30);
	maintenanceReportingDateHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	maintenanceReportingDateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	maintenanceTable.addCell(maintenanceReportingDateHeaderCell);

	PdfPCell maintenanceTechSignatureHeaderCell = new PdfPCell(new Paragraph("Auth and Sign of OIC"));
	maintenanceTechSignatureHeaderCell.setMinimumHeight(30);
	maintenanceTechSignatureHeaderCell.setVerticalAlignment(Element.ALIGN_CENTER);
	maintenanceTechSignatureHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	maintenanceTable.addCell(maintenanceTechSignatureHeaderCell);

	
	// Empty Cells
	for (int i = 0; i < 140; i++) {
	    PdfPCell emptyMaintenanceCell = new PdfPCell(new Paragraph(""));
	    emptyMaintenanceCell.setMinimumHeight(20);
	    emptyMaintenanceCell.setVerticalAlignment(Element.ALIGN_CENTER);
	    emptyMaintenanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    maintenanceTable.addCell(emptyMaintenanceCell);
	}
	
	// Paragraph dosTitle = new Paragraph("Do's:");

	
	
	
		document.add(p1);
		document.newPage();
		document.add(p2);
		document.add(tableR1);
		document.newPage();
		document.add(p3); 
		document.add(tableR2);
		document.newPage();
		document.add(p4); 
		document.add(tableR3);
		document.newPage();
		document.add(p5); 
		document.add(tableR4);
		document.newPage();
		document.add(repairsDetailsParagraph); 
		document.add(repairsTable);
		document.newPage();
		document.add(transferDetailsParagraph); 
		document.add(transferTable);
		document.newPage();
		document.add(maintenanceReportParagraph); 
		document.add(maintenanceTable);
		
		document.newPage();

		Paragraph dosTitle = new Paragraph();
		Chunk dosTitlechunk = new Chunk("Do's", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
		dosTitlechunk.setUnderline(0.11f, -2f);
		dosTitle.add("\n");
		dosTitle.add(dosTitlechunk);
		dosTitle.add("\n");
		dosTitle.setAlignment(Element.ALIGN_LEFT);

     document.add(dosTitle);
	 
     Paragraph space = new Paragraph("");
     space.setSpacingAfter(2f); // Set the space after this paragraph

     document.add(new Paragraph("1.    Ensure that the power is switched off before connecting any device to the computer"));
     document.add(space);
     document.add(new Paragraph("2.    Secure the connectors and cables properly with fastening screws and accessories."));
     document.add(space); 
     document.add(new Paragraph("3.    Use a power strip for the distribution. Plug the computer and all peripherals into a switch-controlled strip."));
     document.add(space);
     document.add(new Paragraph("4.    In the event of lightning storms, unplug your entire system and peripherals."));
     document.add(space);
     document.add(new Paragraph("5.    Keep cables clear and away from power cords, especially coiled power cords."));
     document.add(space); 
     document.add(new Paragraph("6.    When cleaning, make sure that the power is off and that all plugs are pulled out of the power sockets use a damp cloth."));
     document.add(space);
     document.add(new Paragraph("7.    Log all the relevant maintenance and repair actions in the log book to develop a history of the maintenance conducted on computer systems."));
     document.add(space);
     document.add(new Paragraph("8.    Always unplug your computer system when a blackout occurs."));
     document.add(space);
     document.add(new Paragraph("9.    To Extend Floppy Disk Life:"));
     document.add(space);

     // Create the first table for alphabet
     PdfPTable alphabetTable = new PdfPTable(2); // 1 column
     alphabetTable.setWidthPercentage(100); 
     alphabetTable.setWidths(new float[] { 0.1f, 1});

     // Set width to 100%
     alphabetTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // No border

     // Add alphabet points
     String[] alphabets = {"(a)", "(b)", "(c)", "(d)", "(e)", "(f)", "(g)", "(h)", "(i)"};
     // Add corresponding points
     String[] points = {
         "Buy standard and brand disks.",
         "Never touch the disk surface.",
         "Store disks in their protective jackets.",
         "Never write on a label that's already on a disk.",
         "Store disk in a cool, clean place.",
         "Backup all data disks.",
         "Store working and data disks in different places.",
         "Never set disks near monitors or televisions.",
         "Don't bend or fold disks."
     };
     for (int k=0;k<alphabets.length;k++) {
         PdfPCell cell = new PdfPCell(new Paragraph(alphabets[k]));
         cell.setBorder(PdfPCell.NO_BORDER); // No border for each cell
         cell.setPaddingLeft(30); 
         cell.setMinimumHeight(20);
         alphabetTable.addCell(cell);
         
         PdfPCell cell2 = new PdfPCell(new Paragraph(points[k]));
         cell2.setBorder(PdfPCell.NO_BORDER);
         cell2.setMinimumHeight(20);// No border for each cell
         cell.setPaddingLeft(5f); 
         alphabetTable.addCell(cell2);
         
     }

     document.add(alphabetTable); // Add alphabet table to document

     document.add(space);
     document.add(new Paragraph("10.    Clean the read/write head of floppy drives after every 40 hours of disk operation\n"
     		+ "using head cleaning kit."));
     document.add(space);
     document.add(new Paragraph("11.    While cleaning disk, make such that the solvent evaporates before your operate the drive."));
     document.add(space);
     document.add(new Paragraph("12.    Use the following method for screen and cabinet cleaning:-"));
     document.add(space);
  // Create the first table for best practices
     PdfPTable bestPracticesTable = new PdfPTable(2); // 2 columns
     bestPracticesTable.setWidthPercentage(100); 
     bestPracticesTable.setWidths(new float[] { 0.1f, 1 }); // Adjusted widths

     // Set no border for the default cell
     bestPracticesTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // No border

     // Add best practice points
     String[] practiceLabels = {"(a)", "(b)"};
     // Add corresponding best practice descriptions
     String[] practiceDescriptions = {
         "Use one part of fabric softener to three parts water to clean your screen.",
         "Use mild soap and water, use a soft cloth for drying.",
     };

     for (int index = 0; index < practiceLabels.length; index++) {
         PdfPCell labelCell = new PdfPCell(new Paragraph(practiceLabels[index]));
         labelCell.setBorder(PdfPCell.NO_BORDER); // No border for each cell
         labelCell.setPaddingLeft(30); 
         labelCell.setMinimumHeight(20);
         bestPracticesTable.addCell(labelCell);
         
         PdfPCell descriptionCell = new PdfPCell(new Paragraph(practiceDescriptions[index]));
         descriptionCell.setBorder(PdfPCell.NO_BORDER); // No border for each cell
         descriptionCell.setMinimumHeight(20);
         bestPracticesTable.addCell(descriptionCell);
     }

     document.add(bestPracticesTable); // Add best practices table to document
     document.add(new Paragraph("13.    Install a static free carpet or install antistatic floor mat beneath your computer chair"));
     document.add(space);
     document.add(new Paragraph("14.    Mop hard floors with an antistatic solution. The antistatic floor finish works well most antistatic work for up to 6 months."));
     document.add(space);
 	 document.add(new Paragraph("15.    Locate your computer system at least 6 feet away from any television set."));
 	 document.add(space);
 	 document.add(new Paragraph("16.    Keep your computer system away from direct sunlight."));
 	 document.add(space);
 	Paragraph dosTitle2 = new Paragraph();
	Chunk dosTitlechunk2 = new Chunk("Don'ts", FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1));
	dosTitlechunk2.setUnderline(0.1f, -2f);
	dosTitle2.add("\n\n");
	dosTitle2.add(dosTitlechunk);
	dosTitle2.add("\n\n");
	dosTitle2.setAlignment(Element.ALIGN_LEFT);
	
	 document.add(dosTitle2);
	 document.add(space);
	 
	 
     document.add(new Paragraph("1.    Don't inset/remove a peripheral connector without first tuning of the power to the computer."));
     document.add(space);
     document.add(new Paragraph("2.    Don't keep any establish of liquids nearby the computer."));
     document.add(space); 
     document.add(new Paragraph("3.    Don't use power tool near your computer while it's operating."));
     document.add(space);
     document.add(new Paragraph("4.    Never touch contacts with your fingers."));
     document.add(space);
     document.add(new Paragraph("5.    Do not attach the cable and connectors incorrectly by using force, because it may cause damage to peripherals."));
     document.add(space); 
     document.add(new Paragraph("6.    Do not leave the disk lying around. Keen the disks in protective jackets. Don't touch the disk surface with your fingers. Keep the disks out of hot sun."));
     document.add(space); 
 	 
 	 
 	 
		super.buildPdfMetadata(model, document, request);
	}
	
	
	
	private String get(Integer sum_auth2) {
		// TODO Auto-generated method stub
		return null;
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;
		HttpServletRequest request;
		private String batchId; // Add field for batch ID

		// Modified constructor to accept batchId
		ImageBackgroundEvent(HttpServletRequest request, String batchId) {
			this.request = request;
			this.batchId = batchId;
		}

		int page = 1;

		@Override
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
			String watermark = "Generated by " + username + " on " + dateString + " with IP " + ip;
			String batchWatermark = "Batch Id = " + batchId;

			try {
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
				cb.saveState();

				// Set font and color for watermark
				BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				cb.setFontAndSize(bf, 25); // Size for main watermark

				// Set color with transparency (light gray with 20% opacity)
				cb.setColorFill(new Color(192, 192, 192, 51));

				// Get page dimensions
				Rectangle pageSize = cb.getPdfDocument().getPageSize();
				float width = pageSize.getWidth();
				float height = pageSize.getHeight();

				// Calculate center points for landscape orientation
				float centerX = width / 2;
				float centerY = height / 2;

				// Add first watermark (existing text)
				cb.beginText();
				cb.showTextAligned(Element.ALIGN_CENTER, watermark, centerX - 50, centerY + 40, 30);
				cb.endText();

				// Add second watermark (batch ID)
				// Using slightly smaller font for batch ID
				cb.setFontAndSize(bf, 22);
				cb.beginText();
				cb.showTextAligned(Element.ALIGN_CENTER, batchWatermark, centerX - 50, centerY - 40, 30);
				cb.endText();

				cb.restoreState();
			} catch (Exception e) {
				throw new ExceptionConverter(e);
			}
		}
	}

	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		PdfTemplate total1;
		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(30, 15); // Increased width to 30
				total1 = writer.getDirectContent().createTemplate(30, 15); // Increased width to 30
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			
			 total = writer.getDirectContent().createTemplate(50, 20);
			 total1 = writer.getDirectContent().createTemplate(50, 20);
		  
		}
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
		    try {
		        Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		        
		        // Create a new table for the footer
		        PdfPTable table = new PdfPTable(2);
		        table.setWidths(new float[] { (float)1.0, (float)1.0 });
		        table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
		        table.setLockedWidth(true);
		        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		        table.addCell("");
		        table.addCell("");
		        table.addCell(new Phrase(String.format("%d OF ", writer.getPageNumber()), fontTableHeading1));
		        PdfPCell cell = new PdfPCell(Image.getInstance(total));
		        cell.setPaddingLeft(-225);
		        cell.setPaddingTop(0.95f);
		        cell.setBorder(Rectangle.NO_BORDER);
		        table.addCell(cell);
		        table.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
		 

		    } catch (DocumentException de) {
		        throw new ExceptionConverter(de);
		    }
		}
		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {
			Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
			ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
			ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
		}
	}
	
	
	
	
	
	
	
	
protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 10, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
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
		logo.scaleAbsoluteHeight(30);
		logo.scaleAbsoluteWidth(40);
		logo.scalePercent(12);
		Chunk chunk = new Chunk(logo, -5, 10);

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
		logo2.scaleAbsoluteHeight(30);
		logo2.scaleAbsoluteWidth(40);
		logo2.scalePercent(12);
		Chunk chunk2 = new Chunk(logo2, 5, 10);
	
	
		String typeasset="RESTRICTED";
		Chunk underline2 = new Chunk(typeasset, fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		
		
		
	
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase p = new Phrase(underline2);
		p.add("\n");
		p.add("\n");
		p.add("\n");
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

	
	
	

}
