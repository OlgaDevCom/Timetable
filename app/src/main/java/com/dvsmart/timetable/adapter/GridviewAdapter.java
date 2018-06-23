package com.dvsmart.timetable.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dvsmart.timetable.R;
import com.dvsmart.timetable.konstr.KonstTable;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {
	private ArrayList<KonstTable> listData;
	private int lastid;
	private Activity activity;
	private String dir [];
    boolean status = false;
	private ArrayList<String> listColor;
	public GridviewAdapter(Activity activity, ArrayList<KonstTable> rout, String dir_[]) {
		super();
		this.listData = rout;
		this.dir = dir_;
		listColor = new ArrayList<>();
		this.activity = activity;
		lastid = rout.size();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//reloadColor();
		return listData.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position).direction_;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder {
		public TextView tvBack;
		public TextView tvBack2;
		public TextView txtViewTitleDirection;
		public TextView txtViewTitleTimePath;
		public TextView txtViewTitleTimeDirect;
		public TextView txtMarker;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();

		if (convertView == null) {
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.gridview_row_info, null);

			view.txtViewTitleTimePath =   convertView
					.findViewById(R.id.textViewtimecellinfo_);
			view.txtViewTitleTimeDirect =   convertView
					.findViewById(R.id.textView_stinfo_);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}


		//view.txtViewTitleTimeDirect.setText(listData.get(position).direction_);




		String d= "";

		if(position == listData.size()-1)
		{
			 d = listData.get(position).getTimeOff_();
			Log.i("txtViewTitleTimePath4", d);
		}else
		{
			 d = listData.get(position).getTimeDirection_();
			Log.i("txtViewTitleTimePath3", d);
		}
        String  s = " -> "+listData.get(position).direction_;

		SpannableString ss3=  new SpannableString(d+s);
		ss3.setSpan(new StyleSpan(Typeface.NORMAL),
				d.length(),ss3.length(), 0);
		ss3.setSpan(new ForegroundColorSpan(Color.GRAY),d.length(),ss3.length(), 0);// set color



		view.txtViewTitleTimePath.setText(ss3);



		return convertView;
	}
	
	private void reloadColor()
	{
		boolean res = false;

		for (int i = 0; i < listData.size(); i++) {

			if(listData.get(i).number_.equals(dir[0]))
			{
				res = true;
			}
//			if(listData.get(i).number_.equals(dir[1]))
//			{
//				res = false;
//			}


			if(i != 0 && listData.get(i-1).number_.equals(dir[1]))
			{
				res = false;
			}



			if(res)
			{
              listColor.add("#79b921");
			}else
			{
				listColor.add("#ffffff");
			}


		}
	}
}
