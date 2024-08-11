/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTable;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author David Rodriguez,THEJAVABANDITS DCCO-ESPE
 */
public class PdfExporter {
    public static boolean exportTableToPdf(JTable table, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // Adding table headers
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // Adding table rows
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pdfTable.addCell(table.getValueAt(i, j).toString());
                }
            }

            document.add(new Paragraph("Informacion de la Tabla\n"));
            document.add(pdfTable);
            
            // Successfully saved
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Failed to save
            return false;
        } finally {
            document.close();
        }
    }
    
    public static  void savingThePdfFile(String fileName, JTable tblTable, JFrame parentFrame) {
        if (PdfExporter.exportTableToPdf(tblTable, fileName)) {
            Validation.showInfoMessage(parentFrame, "El PDF fue guardado con éxito como " + fileName);
        } else {
            Validation.showErrorMessage(parentFrame, "No se pudo guardar el PDF");
        }
    }
}
