package ru.innopolis.stc9.saturn.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ru.innopolis.stc9.saturn.db.entities.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Victor Bugaenko
 * @since 08.07.2018
 */

public class PDF
{
    public static Document createPDF (User user) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));
        document.open();
        document.add(new Paragraph("table"));
        document.add(new Paragraph(new Date().toString()));
        PdfPTable table=new PdfPTable(2);

        PdfPCell cell = new PdfPCell(new Paragraph ("table"));

        cell.setColspan (2);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (10.0f);
        cell.setBackgroundColor (new BaseColor(140, 221, 8));

        table.addCell(cell);
        ArrayList<String[]> row=new ArrayList<String[]>();
        String[] data=new String[2];
        data[0]="1";
        data[1]="2";
        String[] data1=new String[2];
        data1[0]="3";
        data1[1]="4";
        row.add(data);
        row.add(data1);

        for(int i=0;i<row.size();i++) {
            String[] cols=row.get(i);
            for(int j=0;j<cols.length;j++){
                table.addCell(cols[j]);
            }
        }

        document.add(table);
        document.close();

        return document;
    }
}
