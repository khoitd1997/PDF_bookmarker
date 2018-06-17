package main.java;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

class pdfBookmarker {
    private PDDocument document;
    private String pdfFullPath;
    private int startPage; // start page of the table of content in term of pdf index
    private int stopPage; // stop page of table of content in term of pdf index

    /**
     * the page index of the first item in the table of content, we need this because the page noted by the
     * table of content isn't the same as the page number in the pdf, having this allows us to get the correct
     * page that the table of content actually means
     */
    private int firstContentPage;
    private String delimiter; // the string that separates the title and the page number

    public pdfBookmarker(int startPage, int stopPage, String pdfFullPath, int firstContentPage, String delimiter) {
        try {
            this.delimiter = delimiter;
            this.firstContentPage = firstContentPage;
            this.document = PDDocument.load(new File(pdfFullPath));
            this.pdfFullPath = pdfFullPath;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.startPage = startPage;
        this.stopPage = stopPage;
    }

    private String[] extractTableContent() {
        String pdfText;

        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(startPage);
            pdfStripper.setEndPage(stopPage);
            pdfText = pdfStripper.getText(document);
        } catch (IOException ioException) {
            pdfText = new String("");
            ioException.printStackTrace();
        }
        return pdfText.split("\n");
    }

    public void createBookMark() {

        String[] pdfTextLines = this.extractTableContent();

        // create the outline for bookmark in the pdf docs, all bookmark entries go under this
        PDDocumentOutline outline =  new PDDocumentOutline();
        document.getDocumentCatalog().setDocumentOutline(outline);
        PDOutlineItem pagesOutline = new PDOutlineItem();
        pagesOutline.setTitle( "Bookmark" );
        outline.addLast(pagesOutline);

        for(String line:pdfTextLines) {

            // get a line in the table of content and separates it into the title part and the page number part
            String[] temp = line.split("[\\t\\n\\x0B\\f\\r]*" +  "(" + "\\" + delimiter +  ")+" + "[\\t\\n\\x0B\\f\\r]*");

            if(temp.length>=2) {
                temp[temp.length - 1] = temp[temp.length - 1].replaceAll("\\D","");

                // create a bookmark entry by creating a link to the page specified in the table of content
                PDPage page = document.getPage(firstContentPage - 2 + Integer.parseInt(temp[temp.length - 1]));
                PDPageFitWidthDestination dest = new PDPageFitWidthDestination();
                dest.setPage(page);
                PDOutlineItem bookmark = new PDOutlineItem();
                bookmark.setDestination(dest);
                bookmark.setTitle(temp[temp.length - 2]);
                pagesOutline.addLast(bookmark);
            }
        }

        try {
            document.save(pdfFullPath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    static public void printHelp() {
        System.out.print("This is the pdf bookmarker, it's used for creating pdf bookmark based on the pdf's table of content" +
                ", please run the executable and enter the correct info, note that" +
                "the page number on the pdf text may be misleading, used the number given by the pdf reader(ie page index)" +
                " not on the fine print" +
                "\nStart Page is the page index of the first page of the table of content" +
                "\nStop Page is the last page index of the table of content" +
                "\nFull path includes the name of the pdf, for ex, /home/dur/rand.pdf" +
                "\nFirst content page is the page index of the first item mentioned in the table of content" +
                "\nDelimiter is the pattern that separates the title and the page number, if the pattern repeats, just enter once" +
                "\nThe pdf will be modified and saved where it was");
    }

}
