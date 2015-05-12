package game.ourball.util;

import game.ourball.mainview.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	 private Context mContext;
	 
	 public ImageAdapter(Context c)
	 {
	  mContext=c;
	 }
	 public int getCount() {
	  // TODO Auto-generated method stub
	  return mThumbIds.length;
	 }

	 public Object getItem(int position) {
	  // TODO Auto-generated method stub
	  return null;
	 }

	 public long getItemId(int position) {
	  // TODO Auto-generated method stub
	  return 0;
	 }

	 

	 public View getView(int position, View convertView, ViewGroup parent) {
	  // TODO Auto-generated method stub
	  
	  ImageView imageview;
	  if(convertView==null)
	  {
	   imageview=new ImageView(mContext);
	   imageview.setLayoutParams(new GridView.LayoutParams(85, 85));
	   imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
	   imageview.setPadding(10,10,10,10);
	 }
	  else
	  {
	   imageview=(ImageView) convertView;
	  }
	  imageview.setImageResource(mThumbIds[position]);
	  return imageview;
	  }

	 private Integer[] mThumbIds={//显示的图片数组
	  
	  R.drawable.l1,R.drawable.l2,
	  R.drawable.l3,R.drawable.l4,
	  R.drawable.l5,R.drawable.b_box,
	  R.drawable.b_box,R.drawable.b_box,
	  R.drawable.b_box,R.drawable.b_box,
	  R.drawable.b_box,R.drawable.b_box,
	  R.drawable.b_box,R.drawable.b_box,
	  R.drawable.b_box
	 };
	 

	}

