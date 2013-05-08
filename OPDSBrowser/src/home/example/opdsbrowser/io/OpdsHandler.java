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
		if (is(qname, IOpdsService.XML_ENTRY)){
			books.add(book);
		} else if (is(qname, IOpdsService.XML_TITLE)){
			book.setTitle(value);
		} else if(is(qname, IOpdsService.XML_LINK)){
			book.setLink(value);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qname,
			Attributes attributes) throws SAXException {
		value = null;
		if (is(qname, IOpdsService.XML_ENTRY)){
			book = new Book();
		}
	}

	public List<Book> getBooks() {
		return books;
	}
	
}
