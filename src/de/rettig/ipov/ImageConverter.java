package de.rettig.ipov;

import android.graphics.Bitmap;

public class ImageConverter {
	static final int H = 16;
	static final int W = 64;
	static final int BLACK = -16777216;
	static final int WHITE = -1;
 
	public static byte[] convert(Bitmap image){
		if(image.getHeight()!=H){
			throw new IllegalArgumentException("Imageheight must be 16");
		}
		byte[] buffer = new byte[2*W];
		int bufferPos = 0;
		for (int x=0;x<image.getWidth();x++){
			for (int y=0;y<image.getHeight();y+=8){
				for (int yB=0;yB<8;yB++){
					int rgb = image.getPixel(x, yB+y);
					if (rgb == BLACK){
						byte mask = (byte) (128 >> yB);
						buffer[bufferPos] |= mask;
					}
				}
				bufferPos++;
			}	
		}
		return buffer;
	}

}
