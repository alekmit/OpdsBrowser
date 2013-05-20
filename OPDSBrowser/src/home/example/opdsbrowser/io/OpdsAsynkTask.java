package home.example.opdsbrowser.io;

import static home.example.opdsbrowser.utils.OpdsConstants.*;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;

public final class OpdsAsynkTask extends AsyncTask<String, Integer, byte[]> {

	private Service context;
	private int action;

	public OpdsAsynkTask(Service context, int action) {
		this.context = context;
		this.action = action;
	}

	@Override
     protected void onPostExecute(byte[] result) {
		Intent intent = new Intent(BROADCAST_ACTION);
		intent.putExtra(ACTION_ID, action);
		intent.putExtra(ID_DATA, result);
		context.sendBroadcast(intent);
     }

	@Override
	protected byte[] doInBackground(String... args) {
		String url = args[0];
		byte[] result = null;
		try {
			DefaultHttpClient http = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse resp = http.execute(get);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toByteArray(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
