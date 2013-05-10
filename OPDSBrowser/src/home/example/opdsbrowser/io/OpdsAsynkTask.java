package home.example.opdsbrowser.io;

import home.example.opdsbrowser.MainActivity;
import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.view.BookArrayAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public final class OpdsAsynkTask extends AsyncTask<String, Integer, List<Book>> {

	private Context context;

	// private BookArrayAdapter listAdapter;

	public OpdsAsynkTask(Context context) {
		this.context = context;
	}

	@Override
     protected void onPostExecute(List<Book> books) {

     }

	@Override
	protected List<Book> doInBackground(String... args) {
		List<Book> books = null;
		String url = args[0];
		if (url != null) {
			InputStream is = new ByteArrayInputStream(getXML(url).getBytes());
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

	private String getXML(String url) {
		String xml = null;
		try {
			DefaultHttpClient http = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse resp = http.execute(get);
			HttpEntity entity = resp.getEntity();
			xml = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(xml);
		return xml;
	}

}
