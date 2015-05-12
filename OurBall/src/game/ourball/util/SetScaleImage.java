package game.ourball.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class SetScaleImage {
	
	public Bitmap getScaleImg(Bitmap bm, int newWidth, int newHeight) {
		
		int width = bm.getWidth();
		int height = bm.getHeight();
		
		int newWidth1 = newWidth;
		int newHeight1 = newHeight;
		
		float scaleWidth = ((float) newWidth1) / width;
		float scaleHeight = ((float) newHeight1) / height;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
				true);
		return newbm;
	}
}
