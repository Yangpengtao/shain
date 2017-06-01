package com.ypt.love.wss.shain.util;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageHelper {

	/**
	 * 底片效果 B.r=255-B.r; B.g=255-B.g; B.b=255-B.b;
	 * 
	 * @param bm
	 * @return
	 */
	public static Bitmap handleImageNegative(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		int color;
		int r, g, b, a;
		// 不能在原图上操作，需新创建一个
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Bitmap.Config.ARGB_8888);

		int[] oldPx = new int[width * height];
		int[] newPx = new int[width * height];
		bm.getPixels(oldPx, 0, width, 0, 0, width, height);

		for (int i = 0; i < oldPx.length; i++) {
			color = oldPx[i];
			r = Color.red(color);
			g = Color.green(color);
			b = Color.blue(color);
			a = Color.alpha(color);
			r = 255 - r;
			g = 255 - g;
			b = 255 - b;

			if (r > 255)
				r = 255;
			if (r < 0)
				r = 0;
			if (g > 255)
				g = 255;
			if (g < 0)
				g = 0;
			if (b > 255)
				b = 255;
			if (b < 0)
				b = 0;
			// 合成新的颜色
			newPx[i] = Color.argb(a, r, g, b);
		}

		bmp.setPixels(newPx, 0, width, 0, 0, width, height);
		return bmp;
	}

	/**
	 * 怀旧效果
	 * 
	 * @param bm
	 * @return
	 */
	public static Bitmap handleImageOldDraw(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		int color;
		int r, g, b, a;
		int r1, g1, b1, a1;
		// 不能在原图上操作，需新创建一个
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Bitmap.Config.ARGB_8888);

		int[] oldPx = new int[width * height];
		int[] newPx = new int[width * height];
		bm.getPixels(oldPx, 0, width, 0, 0, width, height);

		for (int i = 0; i < oldPx.length; i++) {
			color = oldPx[i];
			r = Color.red(color);
			g = Color.green(color);
			b = Color.blue(color);
			a = Color.alpha(color);
			r1 = (int) (0.393 * r + 0.760 * g + 0.189 * b);
			g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
			b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

			if (r1 > 255)
				r1 = 255;
			if (r1 < 0)
				r1 = 0;
			if (g1 > 255)
				g1 = 255;
			if (g1 < 0)
				g1 = 0;
			if (b1 > 255)
				b1 = 255;
			if (b1 < 0)
				b1 = 0;
			// 合成新的颜色
			newPx[i] = Color.argb(a, r1, g1, b1);
		}

		bmp.setPixels(newPx, 0, width, 0, 0, width, height);
		return bmp;
	}

	/**
	 * 浮雕效果
	 * 
	 * @param bm
	 * @return
	 */
	public static Bitmap handleImagePixelRelief(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		int color;
		int r, g, b, a;
		int r1, g1, b1, a1;
		// 不能在原图上操作，需新创建一个
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Bitmap.Config.ARGB_8888);

		int[] oldPx = new int[width * height];
		int[] newPx = new int[width * height];
		bm.getPixels(oldPx, 0, width, 0, 0, width, height);

		for (int i = 1; i < oldPx.length; i++) {
			color = oldPx[i - 1];
			r1 = Color.red(color);
			g1 = Color.green(color);
			b1 = Color.blue(color);
			a1 = Color.alpha(color);

			color = oldPx[i];
			r = Color.red(color);
			g = Color.green(color);
			b = Color.blue(color);
			a = Color.alpha(color);

			r = (r - r1 + 127);
			g = (g - g1 + 127);
			b = (b - b1 + 127);

			if (r > 255)
				r = 255;
			if (r < 0)
				r = 0;
			if (g > 255)
				g = 255;
			if (g < 0)
				g = 0;
			if (b > 255)
				b = 255;
			if (b < 0)
				b = 0;
			// 合成新的颜色
			newPx[i] = Color.argb(a, r, g, b);
		}

		bmp.setPixels(newPx, 0, width, 0, 0, width, height);
		return bmp;
	}

}
