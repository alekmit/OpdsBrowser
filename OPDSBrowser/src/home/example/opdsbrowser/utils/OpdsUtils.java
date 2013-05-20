package home.example.opdsbrowser.utils;

import static home.example.opdsbrowser.utils.OpdsConstants.FLIBUSTA_URL;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class OpdsUtils {
	
	public static Bitmap getImage(String imgUrl){
		Bitmap bitmap = null;
		BitmapFactory.Options bmOpts = new BitmapFactory.Options();
		bmOpts.inSampleSize = 8;
		try {
			bitmap = BitmapFactory.decodeStream(
					new URL(FLIBUSTA_URL + imgUrl).openStream(), null, bmOpts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
