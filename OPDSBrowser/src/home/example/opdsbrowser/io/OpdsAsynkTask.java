package home.example.opdsbrowser.io;

import home.example.opdsbrowser.data.Book;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public final class OpdsAsynkTask extends AsyncTask<String, Integer, List<Book>> {

	@Override
	protected List<Book> doInBackground(String... args) {
		List<Book> books = null;
	    String url = args[0];
	    if (url != null) {
	    	InputStream is = new ByteArrayInputStream(getXML(url).getBytes());
	        OpdsParser.parse(is);
	    }
	    return books;
	}
	
	private String getXML(String url){
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
        return xml;
	}
	

}
