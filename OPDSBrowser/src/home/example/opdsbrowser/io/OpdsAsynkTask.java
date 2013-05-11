package home.example.opdsbrowser.io;

import home.example.opdsbrowser.MainActivity;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;

public final class OpdsAsynkTask extends AsyncTask<String, Integer, String> {

	private Service context;

	public OpdsAsynkTask(Service context) {
		this.context = context;
	}

	@Override
     protected void onPostExecute(String opds) {
		Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
		intent.putExtra(MainActivity.ID_XML, opds);
		context.sendBroadcast(intent);
     }

	@Override
	protected String doInBackground(String... args) {
		String url = args[0];
		String opds = null;
		try {
			DefaultHttpClient http = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse resp = http.execute(get);
			HttpEntity entity = resp.getEntity();
			opds = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return opds;
	}

}
