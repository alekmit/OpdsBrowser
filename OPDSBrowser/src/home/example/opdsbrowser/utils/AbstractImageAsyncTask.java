package home.example.opdsbrowser.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public abstract class AbstractImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {
	
	private int scale;
	
	public AbstractImageAsyncTask(int scale){
		super();
		this.scale = scale;
	}

	@Override
	protected Bitmap doInBackground(String... args) {
		String imgUrl = args[0];
		if (imgUrl == null){
			return null;
		}
		return OpdsUtils.getImage(imgUrl, scale);
	}

}
