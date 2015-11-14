package com.example.telactivity;

import com.example.telactivity.DevGuiJiEditListViewAdapter.OnGuiJiItemClickClass;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends BaseActivity implements OnItemClickListener,OnClickListener{
	private DBtableGuiJiName mDBtableGuiJiName;
	private Context appContext;
	private Cursor myCursor;
	private DevGuiJiListViewAdapter mDevGuiJiListViewAdapter;
	private DevGuiJiEditListViewAdapter mDevGuiJiEditListViewAdapter;
	private TextView mTextViewGuiJiTitle;
	private Button mButtonGuiJiActivityBack;
	private Button mButtonGuiJiActivityEdit;
	private Button mButtonGuiJiActivityFinish;
	private EditText mEditTextPhoneNumber;
	private Button mButtonAddPhoneNumber;
	private static final int CLICK_GUIJI_CANCEL = 0; 
	private static final int CLICK_GUIJI_EDIT = 1; 
	private static final int CLICK_GUIJI_FINISH = 2; 
	private static final int CLICK_PHONENUM_ADD = 3;//添加号码 
	private ListView mGuiJiListView;
	private String [] mDataGuiJi;
	
	public static final String GUIJI_TITLE = "GuiJiTitle";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_white_list);
		appContext = getApplicationContext();
		
		mDBtableGuiJiName = new DBtableGuiJiName(appContext);
		mDBtableGuiJiName.createDBtable();
		select_DB();
		mDevGuiJiListViewAdapter = new DevGuiJiListViewAdapter(appContext, mDataGuiJi, R.layout.guiji_listview);
		mGuiJiListView = (ListView) findViewById(R.id.ListViewGuiJi);
		mTextViewGuiJiTitle = (TextView) findViewById(R.id.TextViewGuiJiTitle);
		mButtonGuiJiActivityEdit = (Button) findViewById(R.id.ButtonGuiJiActivityEdit);
		mButtonGuiJiActivityBack = (Button) findViewById(R.id.ButtonGuiJiActivityBack);
		mButtonGuiJiActivityFinish = (Button) findViewById(R.id.ButtonGuiJiActivityFinish);
		mEditTextPhoneNumber = (EditText) findViewById(R.id.EditTextPhoneNumber);
		mButtonAddPhoneNumber = (Button) findViewById(R.id.ButtonAddPhoneNumber);
		mButtonGuiJiActivityEdit.setOnClickListener(this);
		mButtonGuiJiActivityBack.setOnClickListener(this);
		mButtonGuiJiActivityFinish.setOnClickListener(this);
		mButtonAddPhoneNumber.setOnClickListener(this);
		mButtonGuiJiActivityEdit.setTag(CLICK_GUIJI_EDIT);
		mButtonGuiJiActivityBack.setTag(CLICK_GUIJI_CANCEL);
		mButtonGuiJiActivityFinish.setTag(CLICK_GUIJI_FINISH);
		mButtonAddPhoneNumber.setTag(CLICK_PHONENUM_ADD);
		
		mGuiJiListView.setAdapter(mDevGuiJiListViewAdapter);
		mDevGuiJiListViewAdapter.notifyDataSetChanged();
		mGuiJiListView.setOnItemClickListener(this);
	}
	//查询数据库
	private void select_DB() {
		myCursor=mDBtableGuiJiName.select();
		mDataGuiJi = new String[myCursor.getCount()];
//		把从数据库中获取的数据放入数组列表
		for(int i = 0;i < myCursor.getCount();i++){
			myCursor.moveToPosition(i);
			mDataGuiJi[i] = myCursor.getString(0);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mDBtableGuiJiName != null){
			mDBtableGuiJiName.close();
		}
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int tag = (Integer) v.getTag();
		switch (tag) {
		case CLICK_GUIJI_CANCEL:
			finish();
			break;
		case CLICK_GUIJI_EDIT:
			mButtonGuiJiActivityFinish.setVisibility(View.VISIBLE);
			mButtonGuiJiActivityBack.setVisibility(View.GONE);
			mButtonGuiJiActivityEdit.setVisibility(View.GONE);
			mTextViewGuiJiTitle.setText("删除白名单中的数据");
			mDevGuiJiEditListViewAdapter = new DevGuiJiEditListViewAdapter(appContext, mDataGuiJi, R.layout.item_guiji_listview, onItemClickClass);
			mGuiJiListView.setAdapter(mDevGuiJiEditListViewAdapter);
			mGuiJiListView.setOnItemClickListener(null);
			mDevGuiJiEditListViewAdapter.notifyDataSetChanged();
			break;
		case CLICK_GUIJI_FINISH:
			mButtonGuiJiActivityFinish.setVisibility(View.GONE);
			mButtonGuiJiActivityBack.setVisibility(View.VISIBLE);
			mButtonGuiJiActivityEdit.setVisibility(View.VISIBLE);
			mTextViewGuiJiTitle.setText("白名单");
			select_DB();
			mDevGuiJiListViewAdapter.setListItems(mDataGuiJi);
			mGuiJiListView.setAdapter(mDevGuiJiListViewAdapter);
			mDevGuiJiListViewAdapter.notifyDataSetChanged();
			mGuiJiListView.setOnItemClickListener(this);
			break;
		case CLICK_PHONENUM_ADD:
			if(!mEditTextPhoneNumber.getText().equals("")){
				mDBtableGuiJiName.insert(mEditTextPhoneNumber.getText().toString());
				select_DB();
				mDevGuiJiListViewAdapter.setListItems(mDataGuiJi);
				mDevGuiJiListViewAdapter.notifyDataSetChanged();
			}
			break;
		default:
			break;
		}
	}

	DevGuiJiEditListViewAdapter.OnGuiJiItemClickClass onItemClickClass=new OnGuiJiItemClickClass() {

		@Override
		public void OnItemClick(View v, int Position) {
			// TODO Auto-generated method stub
			mDBtableGuiJiName.delete(mDataGuiJi[Position]);
			select_DB();
			mDevGuiJiEditListViewAdapter.setListItems(mDataGuiJi);
			mDevGuiJiEditListViewAdapter.notifyDataSetChanged();
		}
		
	};
}
