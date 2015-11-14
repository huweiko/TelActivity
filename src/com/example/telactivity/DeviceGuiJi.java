package com.example.telactivity;

public class DeviceGuiJi{
	private String mTime;
	private String mValue;
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	public String getmValue() {
		return mValue;
	}
	public void setmValue(String mValue) {
		this.mValue = mValue;
	}
	public DeviceGuiJi(String time,String value){
		this.mTime = time;
		this.mValue = value;
	}
}