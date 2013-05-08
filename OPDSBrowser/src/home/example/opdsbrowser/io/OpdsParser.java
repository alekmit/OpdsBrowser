package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class OpdsParser {

	public static List<Book> parse(InputStream is) {
        List<Book> books = null;
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            OpdsHandler handler = new OpdsHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(is));
            books = handler.getBooks();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

}
