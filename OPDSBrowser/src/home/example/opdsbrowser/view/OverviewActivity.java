package home.example.opdsbrowser.view;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.data.OpdsContext;
import home.example.opdsbrowser.utils.OpdsUtils;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OverviewActivity extends Activity {
	
	private ImageView cover;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Book b = OpdsContext.getContext().getThisBook();
		setContentView(R.layout.overview_screen);
		cover = (ImageView) findViewById(R.id.info_cover);
		TextView author = (TextView) findViewById(R.id.info_author);
		TextView title = (TextView) findViewById(R.id.info_title);
		cover.setImageBitmap(OpdsContext.getContext().getImage());
		author.setText(b.getAuthor());
		title.setText(b.getTitle());
		CoverAsynkTask imageTask = new CoverAsynkTask();
    	imageTask.execute(b.getCover());
	}
	
	private class CoverAsynkTask extends AsyncTask<String, Integer, Bitmap> {
		
		@Override
	    protected void onPostExecute(Bitmap result) {
			if (result != null){
				cover.setImageBitmap(result);
			}
		}

		@Override
		protected Bitmap doInBackground(String... args) {
			String imgUrl = args[0];
			if (imgUrl == null){
				return null;
			}
			return OpdsUtils.getImage(imgUrl, OpdsUtils.SCALE_ASIS);
		}
		
	}
	
	private void downloadBook(String url){
		/*DownloadManager.Request dr = new DownloadManager.Request(url);
		dr.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "fileName");
		dr.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		dm.enqueue(dr);*/
	}

}
