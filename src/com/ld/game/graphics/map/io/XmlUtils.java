package com.ld.game.graphics.map.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.badlogic.gdx.files.FileHandle;

public class XmlUtils {

	public static Document newDocument() throws ParserConfigurationException{
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}
	
	public static Document getDocument(FileHandle file) throws SAXException, IOException, ParserConfigurationException{
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(file.readString())));
	}

	public static void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError, FileNotFoundException, TransformerException{
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.setOutputProperty(OutputKeys.METHOD, "xml");
        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        // send DOM to file
        tr.transform(new DOMSource(document), 
                             new StreamResult(new FileOutputStream(path)));
	}
	
}