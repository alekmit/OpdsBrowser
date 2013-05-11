package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.view.BookArrayAdapter;
import home.example.opdsbrowser.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
			books = OpdsParser.parse(is);
		}
		for (Book book : books) {
			if (book.getCover() == null)
				continue;
			String imageURL = book.getCover();
			Bitmap bitmap = null;
			BitmapFactory.Options bmOpts = new BitmapFactory.Options();
			bmOpts.inSampleSize = 1;
			try {
				bitmap = BitmapFactory.decodeStream(
						new URL(imageURL).openStream(), null, bmOpts);
			} catch (IOException e) {
				e.printStackTrace();
			}
			book.setImage(bitmap);
		}
		return books;
	}

}
