package org.albertsanso.tabletennis.dataretrieval.scrapping.rtbtt.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Test {

    public static void main(String[] args) throws Throwable {

        String filePath = "c:/data/table-tennis/pdf/PREF_G1.pdf";

        PdfReader reader = new PdfReader(filePath);
        String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
        System.out.println(textFromPage);
        reader.close();
    }
}
