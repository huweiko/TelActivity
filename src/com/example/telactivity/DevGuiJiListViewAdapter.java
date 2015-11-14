package com.example.telactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DevGuiJiListViewAdapter extends BaseAdapter {
	
	private Context 					context;//运行上下文
	private String []					listItems;//数据集合
	private LayoutInflater 				listContainer;//视图容器
	private int 						itemViewResource;//自定义项视图源 

	public class ListItemView{				//自定义控件集合  
	    public TextView name;
	}  
	
	public DevGuiJiListViewAdapter(Context context, String[] data,int resource) {
		this.context = context;			
		this.listContainer = LayoutInflater.from(context);	//创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	}
	
	public void setListItems(String[] data){
		listItems = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView  listItemView = null;
		
		if (convertView == null) {
			//获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);
			
			listItemView = new ListItemView();
			//获取控件对象
			listItemView.name= (TextView)convertView.findViewById(R.id.TextViewGuiJiName);
			
			
			//设置控件集到convertView
			convertView.setTag(listItemView);
		}else {
			listItemView = (ListItemView)convertView.getTag();
		}	
		
		//设置文字和图片
		String name = listItems[position];
		listItemView.name.setText(name);
		return convertView;
	}

}
