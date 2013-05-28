package home.example.opdsbrowser.view;

import home.example.opdsbrowser.utils.AbstractImageAsyncTask;
import home.example.opdsbrowser.utils.OpdsUtils;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class CoverAsynkTask extends AbstractImageAsyncTask {
	
	private ImageView cover;
	
	public CoverAsynkTask(ImageView cover) {
		super(OpdsUtils.SCALE_ASIS);
		this.cover = cover;
	}

	@Override
    protected void onPostExecute(Bitmap result) {
		if (result != null){
			cover.setImageBitmap(result);
		}
	}
	
}