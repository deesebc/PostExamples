package test.es.home.example.fop;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import es.home.example.fop.FoExample;

@RunWith(JUnitPlatform.class)
public class FoExampleTest {

    private final static String BASIC_PDF_REL_PATH = "src/test/resources/temp/foExample.pdf";

    @Test
    public void metodo() throws SAXException, IOException, TransformerException {
        String xmlRelativeFilePath = "src/main/resources/xsl/foExample.xsl";
        FoExample.getPDF(xmlRelativeFilePath, BASIC_PDF_REL_PATH);
        File exit = new File(BASIC_PDF_REL_PATH);
        exit.deleteOnExit(); // comment if you want to see the file
        Assertions.assertTrue(exit.exists() && exit.length() > 0);
    }

}
