import java.awt.FlowLayout;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This example uses a "UI Designer" to demonstrate the idea of generative
 * programming.
 * 
 * @author RPL <>[]{}
 */
public class GenerativeUIDesigner {

	public static void main(String[] args) {
		String uiXML = "<?xml version=\"1.0\"?>"
			+ "<JPanel>"
			+ "	<JLabel text=\"UserID\" />"
			+ "	<JTextField length=\"5\" />"
			+ "	<JButton text=\"Print\" />"
			+ "</JPanel>";
		GenerativeUIDesigner guid = 
			new GenerativeUIDesigner(uiXML);
	}

	public GenerativeUIDesigner(String uiXML) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.getContentPane().setLayout(null);
		
		String javaCode = convertXMLtoJava(uiXML);
		System.out.println(javaCode);
		saveJavaCodeToFile("MyJPanel.java",javaCode);
		compile("MyJPanel.java");

		// note we must load MyJPanel through reflection
		// otherwise the class loader will attempt to load
		// it to early, before it had a chance to recompile it!
		//JPanel mjp = new MyJPanel();
		Object instance = null;
		try {
			Class<?> clazz = Class.forName("MyJPanel");
	        instance = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel mjp = (JPanel)instance; //= new MyJPanel();		
		f.getContentPane().add(mjp);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

    /**
     * Use the command line javac compiler to compile
     * @param fileName name of source code file
     * @return true if compile was successful
     */
	private boolean compile(String fileName) {
		int ret = 1;
		Process p;
		try {
			p = Runtime.getRuntime().exec(
					"javac " + fileName);
			p.waitFor();
			ret = p.exitValue();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return ret == 0;
	}


	private void saveJavaCodeToFile(String fileName, String javaCode) {
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(javaCode);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private String convertXMLtoJava(String uiXML) {
		System.out.println(uiXML);
		String javaCode = ""
			+"import java.awt.FlowLayout;"
			+"import javax.swing.*;"
			+"public class MyJPanel extends JPanel {"
			+"public MyJPanel() {"
			+"	setLayout(new FlowLayout());"
			+"	setSize(400, 300);";
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse( new ByteArrayInputStream(uiXML.getBytes()) );
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			if (doc.hasChildNodes()) {
				NodeList nodeList = doc.getChildNodes();
				Node jPanelNode = nodeList.item(0);
				NodeList uiElementsList = jPanelNode.getChildNodes();
				
				for (int i = 0; i < uiElementsList.getLength(); i++) {
					Node uiElement = uiElementsList.item(i);
					if (uiElement.getNodeType() == Node.ELEMENT_NODE) {
						String sUIElement = uiElement.getNodeName();
						String sParameter = null;
						if (uiElement.hasAttributes()) {
							NamedNodeMap nodeMap = uiElement.getAttributes();
							Node node = nodeMap.item(0);
							if ( node.getNodeName().equals("text")) {
								sParameter = "\""+node.getNodeValue()+"\"";
							} else if ( node.getNodeName().equals("length")) {
								sParameter = node.getNodeValue();
							}
						}
						String javaLine = "";
						if ( sParameter == null ) {
							javaLine = "add( new "+sUIElement+"() );";
						} else {
							javaLine = "add( new "+sUIElement+"("+sParameter+") );";
						}
						//System.out.println( javaLine );	
						javaCode += javaLine;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		javaCode += "}}";
		return javaCode;
	}
}
/*
class MyJPanel extends JPanel {
	public MyJPanel() {
		setLayout(new FlowLayout());
		setSize(400, 300);
		add( new JLabel("UserID") );
		//add( new JTextField(5) );
		//add( new JButton("Print") );
	}
}
//*/