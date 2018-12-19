package es.home.example.fop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.xml.sax.SAXException;

public class FoExample {

    public static void getPDF(final String foRelativeFilePath, final String outputFileRelPath)
            throws SAXException, IOException, TransformerException {
        // Step 1: Construct a FopFactory without configuration
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // Step 2: Construct fop with desired output format associated to the output
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outputFileRelPath)))) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            // Step 3: Setup JAXP using identity transformer with a XSL
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            // Step 4: Setup input and output for transformation
            Source src = new StreamSource(new FileInputStream(new File(foRelativeFilePath)));
            Result res = new SAXResult(fop.getDefaultHandler());
            // Step 5: Start transformation and FOP processing
            transformer.transform(src, res);
        }
    }
}