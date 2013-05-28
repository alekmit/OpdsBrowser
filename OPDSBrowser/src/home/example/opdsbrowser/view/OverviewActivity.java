package home.example.opdsbrowser.view;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.data.OpdsContext;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OverviewActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Book b = OpdsContext.getContext().getThisBook();
		setContentView(R.layout.overview_screen);
		ImageView cover = (ImageView) findViewById(R.id.info_cover);
		TextView author = (TextView) findViewById(R.id.info_author);
		TextView title = (TextView) findViewById(R.id.info_title);
		cover.setImageBitmap(OpdsContext.getContext().getImage());
		author.setText(b.getAuthor());
		title.setText(b.getTitle());
		CoverAsynkTask imageTask = new CoverAsynkTask(cover);
    	imageTask.execute(b.getCover());
	}
	
	private void downloadBook(String url){
		/*DownloadManager.Request dr = new DownloadManager.Request(url);
		dr.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "fileName");
		dr.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		dm.enqueue(dr);*/
	}

}
