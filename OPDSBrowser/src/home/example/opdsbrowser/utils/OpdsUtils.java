package home.example.opdsbrowser.utils;

import static home.example.opdsbrowser.utils.OpdsConstants.FLIBUSTA_URL;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class OpdsUtils {
	
	public static final int SCALE_ICON = 8;
	
	public static final int SCALE_THUMP = 4;
	
	public static final int SCALE_ASIS = 0;
	
	public static Bitmap getImage(String imgUrl, int scale){
		Bitmap bitmap = null;
		BitmapFactory.Options bmOpts = new BitmapFactory.Options();
		bmOpts.inSampleSize = scale;
		try {
			bitmap = BitmapFactory.decodeStream(
					new URL(FLIBUSTA_URL + imgUrl).openStream(), null, bmOpts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
