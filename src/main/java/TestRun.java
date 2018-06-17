package main.java;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class TestRun {

    // will take in the page that contain the table of content
    public static void main(String[] args) {

            pdfBookmarker bookmarker = new pdfBookmarker(3,5,"/home/kd/PDF_bookmarker/sample_pdf/progit.pdf");
            String pdfText = bookmarker.extractTableContent();
            System.out.print(pdfText);
    }
}
    class pdfBookmarker {
        PDDocument document;
        private int startPage;
        private int stopPage;

        public pdfBookmarker(int startPage, int stopPage, String pdfFullPath) {
            try {
                this.document = PDDocument.load(new File(pdfFullPath));

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.startPage = startPage;
            this.stopPage = stopPage;
        }

        public String extractTableContent() {
            String pdfText;
            try {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                pdfStripper.setStartPage(startPage);
                pdfStripper.setEndPage(stopPage);
                pdfText = pdfStripper.getText(document);
//                document.close();
            } catch (IOException ioException) {
                pdfText = new String("");
                ioException.printStackTrace();
            }
            return pdfText;
        }
    }
