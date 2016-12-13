package com.ld.game.graphics.map.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.badlogic.gdx.files.FileHandle;

public class XmlMapReader {

	public XmlMapReader(){
		
	}
	
	public List<LoadedTileData> getTilesFromFile(FileHandle file) throws SAXException, IOException, ParserConfigurationException{
		List<LoadedTileData> loadedTileMap = new ArrayList<LoadedTileData>();
		
		Document document = (XmlUtils.getDocument(file));
		
		Node tilesParentNode = document.getDocumentElement().getElementsByTagName("tiles").item(0);
		
		NodeList tilesNode = tilesParentNode.getChildNodes();
		
		System.out.println("Tile nodes: " + tilesNode.getLength());
		
		for(int tileIndex = 0; tileIndex < tilesNode.getLength(); tileIndex++){
			Node tile = tilesNode.item(tileIndex);
			if(tile.getNodeName().equals("tile")){
				LoadedTileData tileData = new LoadedTileData(new HashMap<String, String>());
				
				NamedNodeMap tileAttributes = (tile.getAttributes());
				
				for(int tileAttributeIndex = 0; tileAttributeIndex < tileAttributes.getLength(); tileAttributeIndex++){
					Node tileAttribute = tileAttributes.item(tileAttributeIndex);
					tileData.addAttribute(tileAttribute.getNodeName(), tileAttribute.getNodeValue());
				}
				
				loadedTileMap.add(tileData);
		    }
		}
		
		System.out.println("Returning " + loadedTileMap.size() + " tiles");
		
		return loadedTileMap;
	}
	
}
