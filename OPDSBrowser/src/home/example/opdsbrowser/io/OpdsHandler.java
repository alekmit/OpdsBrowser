package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.data.OpdsContext;
import home.example.opdsbrowser.utils.OpdsConstants;

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
		return tag.equalsIgnoreCase(name);
	}

	@Override
	public void endElement(String uri, String localName, String qname)
			throws SAXException {
		if (book == null && icon == null && is(qname, OpdsConstants.XML_ICON)){
			icon = value;
			OpdsContext.getContext().setIcon(icon);
			return;
		}
		if (book == null) return;
		if (is(qname, OpdsConstants.XML_ENTRY)){
			books.add(book);
			book = null;
		} else if (is(qname, OpdsConstants.XML_TITLE)){
			book.setTitle(value);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qname,
			Attributes attr) throws SAXException {
		value = null;
		if (is(qname, OpdsConstants.XML_ENTRY)){
			book = new Book();
		}
		if(is(qname, OpdsConstants.XML_LINK)){
			String lnk = attr.getValue(OpdsConstants.XML_HREF);
			if (book != null){
				if (attr.getValue("type").startsWith("application/atom+xml")){
					book.setLink(lnk);
				} else if (is(attr.getValue("type"), "image/jpeg") && attr.getValue("rel").endsWith("thumbnail")){
					book.setCover(lnk);
				}
				if (is(attr.getValue("rel"), "next")){
					OpdsContext.getContext().setPrev(OpdsContext.getContext().getNext());
					OpdsContext.getContext().setNext(lnk);
				}
			}
		}
		
	}

	public List<Book> getBooks() {
		return books;
	}
	
}
