package com.lyc.gomap.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.lyc.gomap.R;
import com.lyc.gomap.location.MyLocationListener;
import com.lyc.gomap.util.GoMapApp;

public class MainActivity extends Activity implements OnClickListener {
	public static MapView mMapView = null;
	private ImageButton main_icon_roadcondition_on, main_icon_maplayers,
			notification_icon,map_layer_hot;
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener;
	private LocationMode tempMode = LocationMode.Battery_Saving;

	private String tempcoor = "bd09ll";
	private final String TAG = getClass().getSimpleName();
	public static MainActivity mMainActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = this;
		Log.i(TAG, "==onCreate==");
		// ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.main_activity_main);
		initView();
		// ��ȡ��ͼ�ؼ�����
		mMapView = (MapView) findViewById(R.id.bmapView);
		myListener = new MyLocationListener(mMapView);
		mMapView.getMap().setMyLocationEnabled(true);

		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��
		mLocationClient.registerLocationListener(myListener); // ע���������

		InitLocation();

		mLocationClient.start();

	}

	private void initView() {
		main_icon_roadcondition_on = (ImageButton) findViewById(R.id.main_icon_roadcondition_on);
		main_icon_roadcondition_on.setOnClickListener(this);
		main_icon_maplayers = (ImageButton) findViewById(R.id.main_icon_maplayers);
		main_icon_maplayers.setOnClickListener(this);
		notification_icon = (ImageButton) findViewById(R.id.notification_icon);
		notification_icon.setOnClickListener(this);
		map_layer_hot = (ImageButton) findViewById(R.id.map_layer_hot);
		map_layer_hot.setOnClickListener(this);
	}

	private void InitLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);// ���ö�λģʽ
		option.setCoorType(tempcoor);// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		int span = 5000;

		option.setScanSpan(span);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		mLocationClient.setLocOption(option);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		mLocationClient.stop();
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Log.i(TAG, "==onResume==");
		// ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onPause();
	}
	//test github
	//test github 2
	//test github 3
	int i = 1;
	int j = 1;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ·��
		case R.id.main_icon_roadcondition_on:
			i++;
			mMapView.getMap().setTrafficEnabled(i % 2 == 0 ? true : false);
			break;
		// ����ͼ
		case R.id.main_icon_maplayers:
			j++;
			mMapView.getMap().setMapType(j%2==0?BaiduMap.MAP_TYPE_SATELLITE : BaiduMap.MAP_TYPE_NORMAL);
			break;
		// ��ͨͼ
		case R.id.notification_icon:
			mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		//����ͼ
		case R.id.map_layer_hot:
			i++;
			mMapView.getMap().setBaiduHeatMapEnabled(i % 2 == 0 ? true : false);
			break;

		default:
			break;
		}

	}
	
	/*
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(TAG, "==onActivityResult1=="+requestCode);
		Log.i(TAG, "==onActivityResult2=="+resultCode);
		if(requestCode == 1){
			if(resultCode == 2){
				Log.i(TAG, "==PoiOverlay==");
				mMapView.getMap().clear();
				PoiOverlay overlay = new MyPoiOverlay(mMapView.getMap());
				mMapView.getMap().setOnMarkerClickListener(overlay);
				overlay.setData(GoMapApp.mPoiResult);
				overlay.addToMap();
				overlay.zoomToSpan();
			}
		}
		
		mPoiSearch.searchInCity((new PoiCitySearchOption())
				.city(editCity.getText().toString())
				.keyword(editSearchKey.getText().toString())
				.pageNum(load_Index));
		
		
	}*/
	
	private class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int index) {
			super.onPoiClick(index);
			PoiInfo poi = getPoiResult().getAllPoi().get(index);
			// if (poi.hasCaterDetails) {
				/*mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
						.poiUid(poi.uid));*/
			// }
			return true;
		}
	}
}
