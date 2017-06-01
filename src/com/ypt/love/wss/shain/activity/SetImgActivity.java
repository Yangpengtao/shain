package com.ypt.love.wss.shain.activity;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ypt.love.wss.shain.R;
import com.ypt.love.wss.shain.imageFilters.ComicFilter;
import com.ypt.love.wss.shain.imageFilters.IceFilter;
import com.ypt.love.wss.shain.imageFilters.MoltenFilter;
import com.ypt.love.wss.shain.imageFilters.SoftGlowFilter;
import com.ypt.love.wss.shain.util.ImageCache;
import com.ypt.love.wss.shain.util.ImageHelper;
import com.ypt.love.wss.shain.util.ImageUtil;
import com.ypt.love.wss.shain.util.Tools;

public class SetImgActivity extends Activity {

	private final String CURR_IMG = "curr_img";
	private final String ICE_fILTER = "IceFilter";
	private final String MOLTEN_FILTER = "MoltenFilter";
	private final String COMIC_FILTER = "ComicFilter";
	private final String SOFTGLOW_FILTER = "SoftGlowFilter";
	private final String NEGATIVE_FILTER = "Negative_Filter";
	private final String PIXELRELIEF_FILTER = "PixelRelief_Filter";
	private final String OLODRAW_FILTER = "OldDraw_Filter";

	private ImageView mImg;
	private LinearLayout mLinear;
	private Bitmap mBtm;

	private HashMap<String, Bitmap> mBtms = new HashMap<String, Bitmap>();
	private String mTag;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_img);
		Tools.setStateBar(findViewById(R.id.fff), this);
		mImg = (ImageView) findViewById(R.id.img);
		mLinear = (LinearLayout) findViewById(R.id.ll_content);
		// Bitmap bm = BitmapFactory.decodeFile("/storage/emulated/0/aa.jpg");
		// Log.e("aaaa", "----" + bm);

		mBtm = ImageCache.get(CURR_IMG);
		mTag = CURR_IMG;
		mImg.setImageBitmap(mBtm);

		Bitmap btmBitmap = ImageUtil.resizeBitmap(mBtm, 80, 100);

		View demo = null;
		mLinear.removeAllViews();

		Bitmap tmpBitmap = btmBitmap;
		for (int i = 0; i < 8; i++) {
			demo = LayoutInflater.from(this).inflate(R.layout.img_item, null);

			ImageView img = (ImageView) demo.findViewById(R.id.img_poker);

			switch (i) {
			case 0:
				tmpBitmap = btmBitmap;
				mBtms.put(CURR_IMG, tmpBitmap);
				mTag = CURR_IMG;
				break;
			case 1:
				tmpBitmap = new IceFilter(btmBitmap).imageProcess()
						.getDstBitmap();
				mBtms.put(ICE_fILTER, tmpBitmap);
				mTag = ICE_fILTER;
				break;
			case 2:
				tmpBitmap = new MoltenFilter(btmBitmap).imageProcess()
						.getDstBitmap();
				mBtms.put(MOLTEN_FILTER, tmpBitmap);
				mTag = MOLTEN_FILTER;
				break;
			case 3:
				tmpBitmap = new ComicFilter(btmBitmap).imageProcess()
						.getDstBitmap();
				mBtms.put(COMIC_FILTER, tmpBitmap);
				mTag = COMIC_FILTER;
				break;
			case 4:
				tmpBitmap = new SoftGlowFilter(btmBitmap).imageProcess()
						.getDstBitmap();
				mBtms.put(SOFTGLOW_FILTER, tmpBitmap);
				mTag = SOFTGLOW_FILTER;
				break;
			case 5:
				tmpBitmap = ImageHelper.handleImageNegative(btmBitmap);
				mBtms.put(NEGATIVE_FILTER, tmpBitmap);
				mTag = NEGATIVE_FILTER;
				break;
			case 6:
				tmpBitmap = ImageHelper.handleImageOldDraw(btmBitmap);
				mBtms.put(OLODRAW_FILTER, tmpBitmap);
				mTag = OLODRAW_FILTER;
				break;
			case 7:
				tmpBitmap = ImageHelper.handleImagePixelRelief(btmBitmap);
				mBtms.put(PIXELRELIEF_FILTER, tmpBitmap);
				mTag = PIXELRELIEF_FILTER;
				break;
			default:
				tmpBitmap = btmBitmap;
				mTag = CURR_IMG;
				break;
			}
			img.setImageBitmap(tmpBitmap);
			img.setTag(mTag);
			img.setOnClickListener(clickLis);
			mLinear.addView(demo);
		}
	}

	private OnClickListener clickLis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.img_poker:
				String tag = (String) v.getTag();
				if (tag == null || tag.equals("")) {
					tag = CURR_IMG;
				}
				mTag = tag;
				setBitmap();
				break;

			default:
				break;
			}
		}
	};

	ProgressDialog progress;

	/**
	 * ÉèÖÃµ±Ç°Í¼Æ¬
	 * 
	 * @param tag
	 */
	private void setBitmap() {
		progress = new ProgressDialog(this);
		progress.show();
		new Thread() {
			public void run() {
				if (ImageCache.get(mTag) == null) {
					Bitmap tmpBitmap = null;
					if (mTag.equals(ICE_fILTER)) {
						tmpBitmap = new IceFilter(mBtm).imageProcess()
								.getDstBitmap();
						ImageCache.put("IceFilter", tmpBitmap);
					} else if (mTag.equals(MOLTEN_FILTER)) {
						tmpBitmap = new MoltenFilter(mBtm).imageProcess()
								.getDstBitmap();
						ImageCache.put("MoltenFilter", tmpBitmap);
					} else if (mTag.equals(COMIC_FILTER)) {
						tmpBitmap = new ComicFilter(mBtm).imageProcess()
								.getDstBitmap();
						ImageCache.put("ComicFilter", tmpBitmap);
					} else if (mTag.equals(SOFTGLOW_FILTER)) {
						tmpBitmap = new SoftGlowFilter(mBtm, 10, 0.1f, 0.1f)
								.imageProcess().getDstBitmap();
						ImageCache.put("SoftGlowFilter", tmpBitmap);
					} else if (mTag.equals(NEGATIVE_FILTER)) {
						tmpBitmap = ImageHelper.handleImageNegative(mBtm);
						ImageCache.put(NEGATIVE_FILTER, tmpBitmap);
					} else if (mTag.equals(OLODRAW_FILTER)) {
						tmpBitmap = ImageHelper.handleImageOldDraw(mBtm);
						ImageCache.put(OLODRAW_FILTER, tmpBitmap);
					} else if (mTag.equals(PIXELRELIEF_FILTER)) {
						tmpBitmap = ImageHelper.handleImagePixelRelief(mBtm);
						ImageCache.put(PIXELRELIEF_FILTER, tmpBitmap);
					} else {
						tmpBitmap = mBtm;
					}
				}
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			};
		}.start();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				mImg.setImageBitmap(ImageCache.get(mTag));
				progress.dismiss();
			}
		};
	};

}