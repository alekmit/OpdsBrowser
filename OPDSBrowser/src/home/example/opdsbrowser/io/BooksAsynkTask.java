package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.data.OpdsContext;
import home.example.opdsbrowser.utils.IOpdsConstants;
import home.example.opdsbrowser.view.BookArrayAdapter;
import home.example.opdsbrowser.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public final class BooksAsynkTask extends AsyncTask<String, Integer, List<Book>> {

	private Activity activity;

	private BookArrayAdapter listAdapter;

	public BooksAsynkTask(Activity activity) {
		this.activity = activity;
	}

	@Override
     protected void onPostExecute(List<Book> books) {
		listAdapter = new BookArrayAdapter(activity, books);
        ((MainActivity) activity).getListView().setAdapter(listAdapter);
     }

	@Override
	protected List<Book> doInBackground(String... args) {
		List<Book> books = null;
		String opds = args[0];
		if (opds != null) {
			InputStream is = new ByteArrayInputStream(opds.getBytes());
			books = parse(is);
		}
		String icon = OpdsContext.getContext().getIcon();
		if (OpdsContext.getContext().getImage() == null && icon != null){
			OpdsContext.getContext().setImage(getImage(icon));
		}
		for (Book book : books) {
			String imgUrl = book.getCover();
			if (imgUrl == null)
				continue;
			book.setImage(getImage(imgUrl));
		}
		return books;
	}
	
	public List<Book> parse(InputStream is) {
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
	
	private Bitmap getImage(String imgUrl){
		Bitmap bitmap = null;
		BitmapFactory.Options bmOpts = new BitmapFactory.Options();
		bmOpts.inSampleSize = 16;
		try {
			bitmap = BitmapFactory.decodeStream(
					new URL(IOpdsConstants.FLIBUSTA_URL + imgUrl).openStream(), null, bmOpts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
