# Pdf Bookmarker

## Introduction

Used for manipulating pdf and create bookmark with link to specific page for convenient, require that user took a look at the table of content of the book and fill in some info

Used pdfbox library from apache (https://pdfbox.apache.org/)

Built using Maven, IDE used is intelliJ

## Usage

Make sure to take a look at the pdf's table of content first before using the tool since it needs some info from your

You can just take the pdfBookmarker class and used it in your java code or use the PDF_bookmarker.jar inside the out/artifacts/PDF_bookmarker_jar folder, just run

```bash
cd ~/PDF_bookmarker/out/artifacts/PDF_bookmarker_jar/
java -jar PDF_bookmarker.jar
```

It will then print an introduction to the list of parameter needed, and then proceed to ask for them.

There is a sample input/output pdf inside the sample_pdf folder, the book used for example is progit(https://git-scm.com/book/en/v2)

## Reference

https://www.tutorialspoint.com/pdfbox/pdfbox_environment.htm

https://stackoverflow.com/questions/35260456/how-to-get-all-bookmarks-in-pdf-file-using-pdfbox-in-java (print bookmarks)

https://stackoverflow.com/questions/30695462/hierarchical-bookmark-generation-in-pdf-using-pdfbox-library (create bookmarks)