package main.java;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRun {

    // will take in the page that contain the table of content
    public static void main(String[] args) {
            pdfBookmarker bookmarker = new pdfBookmarker(3,5,"/home/kd/PDF_bookmarker/sample_pdf/progit.pdf");
            String pdfText = bookmarker.extractTableContent();
//            System.out.print(pdfText);
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
            String delimiter = ". ";


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
            String[] pdfTextLines = pdfText.split("\n");

            int i = 0;
            for(String line:pdfTextLines) {
                //{2,}[\\t\\n\\x0B\\f\\r]*[0-9]+
                String[] temp = line.split("[\\t\\n\\x0B\\f\\r]*" +  "(" + "\\" + delimiter +  ")+" + "[\\t\\n\\x0B\\f\\r]*");

                for(String tempString:temp){
                    System.out.print(tempString + "             ");
                }
                System.out.println("");

//                if(temp.length>1) {
//                    System.out.println("Title:" + temp[0]);
//
//                    temp[1] = temp[1].replace("[" + delimiter + "]* \\1 \\s", "");
//                    System.out.println("Location:" + temp[1]);
//                }


                ++ i;
                if (i > 6) {
                    while (true) {

                    }
                }
            }
            return pdfText;
        }
    }
