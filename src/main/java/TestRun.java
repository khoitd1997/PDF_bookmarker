package main.java;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

public class TestRun {

    public static void main(String[] args) {
            pdfBookmarker.printHelp();
            System.out.println("");

            Scanner userScanner = new Scanner(System.in);
            int startPage;
            int stopPage;
            String pdfPath;
            int firstContentPageNumber;
            String delimiter;

            System.out.println("Please Enter the start page");
            startPage = userScanner.nextInt();
            System.out.println("Please Enter the stop page");
            stopPage = userScanner.nextInt();
            System.out.println("Please enter pdf full path");
            userScanner.nextLine();
            pdfPath = userScanner.nextLine();
            System.out.println("Please enter first content page number");
            firstContentPageNumber = userScanner.nextInt();
            System.out.println("Please enter delimiter");
            userScanner.nextLine();
            delimiter = userScanner.nextLine();

            pdfBookmarker bookmarker = new pdfBookmarker(startPage,stopPage,
                    pdfPath, firstContentPageNumber, delimiter);
            bookmarker.createBookMark();
    }
}


