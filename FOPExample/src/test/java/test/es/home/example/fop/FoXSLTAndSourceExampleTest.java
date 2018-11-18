package test.es.home.example.fop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import es.home.example.fop.FoXSLTAndSourceExample;

@RunWith(JUnitPlatform.class)
public class FoXSLTAndSourceExampleTest {

    private final static String PDF_REL_PATH = "src/test/resources/temp/foXSLTAndSourceExample.pdf";
    private final static String XML_REL_PATH = "src/main/resources/xml/foXSLTAndSourceExample.xml";

    @Test
    public void metodo() throws SAXException, IOException, TransformerException {
        String xmlRelativeFilePath = "src/main/resources/xsl/foXSLTAndSourceExample.xsl";
        String xmlText = IOUtils.toString(new FileInputStream(new File(XML_REL_PATH)));
        FoXSLTAndSourceExample.getPDF(xmlText, xmlRelativeFilePath, PDF_REL_PATH);
        File exit = new File(PDF_REL_PATH);
        exit.deleteOnExit(); // comment if you want to see the file
        Assertions.assertTrue(exit.exists() && exit.length() > 0);
    }

}
