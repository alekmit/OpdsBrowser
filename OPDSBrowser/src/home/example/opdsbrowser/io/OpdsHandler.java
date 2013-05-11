package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.utils.IOpdsService;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class OpdsHandler extends DefaultHandler {
	
	private List<Book> books;
	
	private Book book;
	
	private String value;
	
	private String icon;
	
	public OpdsHandler(){
		books = new ArrayList<Book>();
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = new String(ch, start, length);
	}
	
	private static boolean is(String name,  String tag){
		return name.equalsIgnoreCase(tag);
	}

	@Override
	public void endElement(String uri, String localName, String qname)
			throws SAXException {
		if (book == null && is(qname, IOpdsService.XML_ICON)){
			icon = IOpdsService.FLIBUSTA_URL + value;
			return;
		}
		if (book == null) return;
		if (is(qname, IOpdsService.XML_ENTRY)){
			books.add(book);
			book = null;
		} else if (is(qname, IOpdsService.XML_TITLE)){
			book.setTitle(value);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qname,
			Attributes attr) throws SAXException {
		value = null;
		if (is(qname, IOpdsService.XML_ENTRY)){
			book = new Book();
			book.setCover(icon);
		}
		if(book != null && is(qname, IOpdsService.XML_LINK)){
			if (attr.getValue("type").startsWith("application/atom+xml")){
				book.setLink(IOpdsService.FLIBUSTA_URL + attr.getValue(IOpdsService.XML_HREF));
			} else if (is(attr.getValue("type"), "image/jpeg") && attr.getValue("rel").endsWith("thumbnail")){
				book.setCover(IOpdsService.FLIBUSTA_URL + attr.getValue(IOpdsService.XML_HREF));
			}
		}
		
	}

	public List<Book> getBooks() {
		return books;
	}
	
}
