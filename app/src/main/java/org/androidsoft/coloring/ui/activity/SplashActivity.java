/* Copyright (c) 2010-2011 Pierre LEVY androidsoft.org
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.androidsoft.coloring.ui.activity;

import org.androidsoft.coloring.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Splash activity
 * 
 * @author Pierre Levy
 */
public class SplashActivity extends Activity  {

	private Button mButtonPlay, mButtonPlay2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);

		mButtonPlay = (Button) findViewById(R.id.button_go);
		//mButtonPlay.setOnClickListener(this);
		mButtonPlay2 = (Button) findViewById(R.id.button_go2);
		//mButtonPlay2.setOnClickListener(this);

		ImageView image = (ImageView) findViewById(R.id.image_splash);
		image.setImageResource(R.drawable.splach_icon);

		
		//int actionBarHeight;
				TypedValue tv = new TypedValue();
		    	if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
		    	{
		    		Globals.actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
		    		//Toast.makeText(getApplicationContext(), "tv  "+TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics()), 4000).show();
		    	}
		
		    	Globals.actionBarHeight-=getActionBar().getHeight();
		    	
	    		//Toast.makeText(getApplicationContext(), "getActionBar  "+getActionBar().getHeight(), 4000).show();
	
		    	
		    	
		    	 WindowManager w = getWindowManager();
		         Display d = w.getDefaultDisplay();
		         Point size = new Point();
		         d.getSize(size);
		         int width = size.x;
		         int height = size.y;
		         Globals.actionBarHeight = height-Globals.actionBarHeight;
		         Globals.actionBarHeight -=getSoftbuttonsbarHeight();
		         Globals.actionBarHeight -= getStatusBarHeight();
		         
		         
		
	}

	private int getSoftbuttonsbarHeight() {
	    // getRealMetrics is only available with API 17 and +
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
	        DisplayMetrics metrics = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        int usableHeight = metrics.heightPixels;
	        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
	        int realHeight = metrics.heightPixels;
	        if (realHeight > usableHeight)
	            return realHeight - usableHeight;
	        else
	            return 0;
	    }
	    return 0;
	}
	
	
	public int getStatusBarHeight() {
	    int result = 0;
	    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	    if (resourceId > 0) {
	        result = getResources().getDimensionPixelSize(resourceId);
	    }
	    return result;
	}
	
	
	
	/**
	 * {@inheritDoc }
	 */
	public void onClick(View v) {
		if (v == mButtonPlay) {
			Intent intent = new Intent(this, PaintActivity.class);
			startActivity(intent);
		} else if (v == mButtonPlay2) {
			Intent intent = new Intent(this, PaintActivity2.class);
			startActivity(intent);
		}
	}

	
}