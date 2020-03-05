package com.pdf.convert;

import com.aspose.pdf.*;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfToImage {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        File file = new File("G:\\applicat-tip-paper.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                ImageIO.write(image, "PNG", new File("G:\\test"+i+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("(end-start) = " + (end-start));
    }

    public static void loadLicense() throws Exception{
        InputStream is = SwingPDF.class.getResourceAsStream("/license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);
    }
}
