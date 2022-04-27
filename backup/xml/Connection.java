package com.is_web.repository.xml;

import com.is_web.repository.RepositoryException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

/**
 * Provides loading and saving XML data files (tables)
 */
public class Connection {

    private static final String xmlPath = "C:\\Users\\David a Veronika\\IdeaProjects\\is_foj0105_web\\xmlDatabase\\";

    public static Document getDocument(String tableName) throws RepositoryException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(xmlPath + tableName + ".xml");
        } catch (Exception e) {
            throw new RepositoryException(e, "Could not open XML database");
        }
    }

    public static void saveDocument(String tableName, Document document) throws RepositoryException {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "no");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(xmlPath + tableName + ".xml")));
        } catch (Exception e) {
            throw new RepositoryException(e, "Could not save XML database");
        }
    }
}