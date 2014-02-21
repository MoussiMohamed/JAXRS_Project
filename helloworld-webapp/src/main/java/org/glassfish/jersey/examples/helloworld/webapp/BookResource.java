package org.glassfish.jersey.examples.helloworld.webapp;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 * 
 * Date : January 2011
 */
//@Path("/contentbooks")
@Path("/helloworld")
public class BookResource {
    
     @GET
    @Produces("text/plain")
    public String getHello() {
        return "Hello World!";
    }
    
    
    
    
    
	
//	private byte[] readFromStream(InputStream stream) throws IOException {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1000];
//		int wasRead = 0;
//		do {
//			wasRead = stream.read(buffer);
//			if (wasRead > 0) {
//				baos.write(buffer, 0, wasRead);
//			}
//		} while (wasRead > -1);
//		return baos.toByteArray();
//	}
	
	/**
	 * 
	 * @param newBook
	 * @return
	 */
	@Consumes("application/xml")
	@POST
	@Path("uribuilder2")
	public Response createURIBooks(Book newBook, @Context UriInfo uri) {
		System.out.println("BookResource.createURIBooks()");
		
		URI build = UriBuilder
			.fromUri("http://www.mylocalhost")
			.path("path1")
			.path("path2")
			.build();
		return Response.created(build).build();
	}	
	
	/**
	 * 
	 * @param newBook
	 * @return
	 */
	@Consumes("application/xml")
	@POST
	@Path("uribuilder1")
	public Response createBooksFromURI(Book newBook, @Context UriInfo uri) {
		System.out.println("BookResource.createBooksFromURI()");
		
		URI absolutePath = uri
			.getAbsolutePathBuilder()
			.queryParam("param1", newBook.getName())
			.path(newBook.getIsbn())
			.build();
		return Response.created(absolutePath).build();
	}	
	
	/**
	 * 
	 * @param newBook
	 * @return
	 */
	@Consumes("application/xml")
	@POST
	@Path("response")
	public Response createBasicBooks(Book newBook, @Context UriInfo uri) {
		System.out.println("BookResource.createBasicBooks()");
		
		URI absolutePath = uri.getAbsolutePath();
		return Response.created(absolutePath).build();
	}

	
	/**
	 * URI: /response/maintenance/
	 * 
	 * @return
	 */
	@Path("response/maintenance")
	@GET
	public Response getMaintenanceBooks() {
		System.out.println("BookResource.getMaintenanceBooks()");
		
		return Response
			.serverError()
			.build();
	}
	
	/**
	 * URI: /contentbooks/response/
	 * 
	 * @return
	 */
	@Path("response")
	@GET
	public Response getBooks() {
		System.out.println("BookResource.getBooks()");
		
		return Response
			.status(Response.Status.OK)
			.header("param1", "Bonjour")
			.header("param2", "Hello")
			.entity("Ceci est le message du coprs de la r�ponse")
			.header("serverd", "keulkeul")
			.build();
	}
	
	/**
	 * URI: /contentbooks/jaxbxml/
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Path("jaxbxml")
	@Consumes("application/xml")
	@PUT
	public void updateContentBooksWithJAXBXML(Book current) throws IOException {
		System.out.println("BookResource.updateContentBooksWithJAXBXML()");
		
		System.out.println("Name: " + current.getName() + ", ISBN: " + current.getIsbn());
	}	
	
	/**
	 * URI: /contentbooks/jaxbjson/12
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Path("jaxbjson/{id}")
	@Produces("application/json")
	@GET
	public Book getContentBooksWithJAXBJSON(@PathParam("id") String id) throws IOException {
		System.out.println("BookResource.updateContentBooksWithJAXBJSON()");
		
		Book myBook = new Book();
		myBook.setIsbn("1112223");
		myBook.setName("Harry Potter");
		
		return myBook;
	}
	
	/**
	 * URI: /contentbooks/jaxbxmlwithjaxbelement/
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Path("jaxbxmlwithjaxbelement")
	@Consumes("application/xml")
	@PUT
	public void updateContentBooksWithJAXBElementXML(JAXBElement<Book> currentJAXBElemnt) throws IOException {
		System.out.println("BookResource.updateContentBooksWithJAXBElementXML()");

		Book current = currentJAXBElemnt.getValue();
		System.out.println("Name: " + current.getName() + ", ISBN: " + current.getIsbn());
	}		
	
	/**
	 * URI: /contentbooks/jaxbxml/
	 * 
	 * @return
	 */
	@Path("jaxbxml")
	@GET
	@Produces("application/xml")
	public Book getContentBooksWithJAXBXML() {
		System.out.println("BookResource.getContentBooksWithJAXBXML()");
		
		Book current = new Book();
		current.setIsbn("123-456-789");
		current.setName("Harry Toper");
		
		return current;		
	}	
	
	/**
	 * URI: /contentbooks/string/
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Path("string")
	@PUT
	public void updateContentBooksWithString(String current) throws IOException {
		System.out.println("BookResource.updateContentBooksWithString()");
		
		System.out.println(current);
	}

	/**
	 * URI: /contentbooks/string/
	 * 
	 * @return
	 */
	@Path("string")
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getContentBooksWithString() {
		System.out.println("BookResource.getContentBooksWithString()");
		
		return "<?xml version=\"1.0\"?>" + "<details>Ce livre est une introduction sur la vie" + "</details>";		
	}	
	
	/**
	 * URI: /contentbooks/inputstream/
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	@Path("inputstream")
	@GET
	@Produces(MediaType.TEXT_XML)
	public InputStream getContentBooksWithInputStream() throws FileNotFoundException {
		System.out.println("BookResource.getContentBooksWithInputStream()");
		
		FileInputStream fileInputStream = new FileInputStream("c:\\liaswebsite.xml");
		return fileInputStream;
	}
	
	/**
	 * URI: /contentbooks/file/
	 * 
	 * @return
	 * @throws IOException 
	 */
//	@Path("file")
//	@PUT
//	public void updateContentBooksWithFile(File file) throws IOException {
//		System.out.println("BookResource.updateContentBooksWithFile()");
//		
//		byte[] bytes = readFromStream(new FileInputStream(file));
//		String input = new String(bytes);
//		System.out.println(input);
//	}	
	
	/**
	 * URI: /contentbooks/inputstream/
	 * 
	 * @return
	 * @throws IOException 
	 */
//	@Path("inputstream")
//	@PUT
//	public void updateContentBooksWithInputStream(InputStream is) throws IOException {
//		System.out.println("BookResource.updateContentBooksWithInputStream()");
//		
//		byte[] bytes = readFromStream(is);
//		String input = new String(bytes);
//		System.out.println(input);
//	}
	
	/**
	 * URI: /contentbooks/file/
	 * 
	 * @return
	 */
        File fXmlFile;
        Element eElement;
	@Path("file")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getContentBooksWithFile() {
//		System.out.println("BookResource.getContentBooksWithFile()");
//		
//		File file = new File("c:\\liaswebsite.xml");
//		return file;
            
            try {
 
	fXmlFile = new File("/home/msi/Bureau/staff.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("staff");
 
	System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			eElement = (Element) nNode;
 
			System.out.println("Staff id : " + eElement.getAttribute("id"));
			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
 
		}
	}
        
    } catch (Exception e) {
	e.printStackTrace();
    }
        return "Staff id : " + eElement.getAttribute("id")+
			"\nFirst Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent()+
			"\nLast Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent()+
			"\nNick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent()+
			"\nSalary : " + eElement.getElementsByTagName("salary").item(0).getTextContent();
     
            
	}
}
