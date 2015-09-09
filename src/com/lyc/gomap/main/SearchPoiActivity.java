package com.lyc.gomap.main;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.lyc.gomap.R;
import com.lyc.gomap.util.GoMapApp;

public class SearchPoiActivity extends Activity implements OnClickListener,OnGetPoiSearchResultListener, OnItemClickListener{
	private Button btn_searchpoi;
	private PoiSearch mPoiSearch = null;
	private ArrayAdapter<String> poiResultAdapter = null;
	private final String TAG = getClass().getSimpleName();
	private ListView lv_searchpoi_result;
	private EditText et_inputkeyword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchpoi_mainlayout);
		initView();
		
	}

	private void initView() {
		 mPoiSearch = PoiSearch.newInstance();
		 mPoiSearch.setOnGetPoiSearchResultListener(this);
		
		btn_searchpoi = (Button) findViewById(R.id.btn_searchpoi);
		btn_searchpoi.setOnClickListener(this);
		et_inputkeyword = (EditText) findViewById(R.id.et_inputkeyword);
		
		lv_searchpoi_result = (ListView) findViewById(R.id.lv_searchpoi_result);
		lv_searchpoi_result.setOnItemClickListener(this);
		
		poiResultAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line);
		lv_searchpoi_result.setAdapter(poiResultAdapter);
		
	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	//POI¼ìË÷
	case R.id.btn_searchpoi:
		Toast.makeText(this, "¿ª·¢ÖÐ...", Toast.LENGTH_LONG).show();
	      PoiNearbySearchOption mPoiNearbySearchOption = new PoiNearbySearchOption();
	      mPoiNearbySearchOption.keyword(et_inputkeyword.getText().toString());
	      mPoiNearbySearchOption.location(new LatLng(GoMapApp.Latitude, GoMapApp.Longitude));
		  mPoiSearch.searchNearby(mPoiNearbySearchOption);
	
		
		break;

	default:
		break;
	}
		
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult arg0) {
		Log.i(TAG, "==Name=="+arg0.getName());
		Log.i(TAG, "==Address=="+arg0.getAddress());
		
	}

	List<PoiInfo> mPoiInfoList = new ArrayList<PoiInfo>();

	@Override
	public void onGetPoiResult(PoiResult arg0) {
		Log.i(TAG, "==onGetPoiResult==");
		if (arg0 == null || arg0.getAllPoi() == null) {
			return;
		}
		GoMapApp.mPoiResult = arg0;
		poiResultAdapter.clear();
		for (PoiInfo mPoiInfo : arg0.getAllPoi()) {
			if (mPoiInfo.name!= null)
				poiResultAdapter.add(mPoiInfo.name);
		}
		poiResultAdapter.notifyDataSetChanged();
		
		
		Log.i(TAG, "==getCurrentPageNum=="+arg0.getCurrentPageNum());
		Log.i(TAG, "==getTotalPageNum=="+arg0.getTotalPageNum());
		mPoiInfoList = arg0.getAllPoi();
		for(PoiInfo mPoiInfo : mPoiInfoList){
			Log.i(TAG, "==getAllPoi=="+mPoiInfo.name);
			Log.i(TAG, "==getAllPoi=="+mPoiInfo.address);
			
		}
		
		/*MainActivity.mMapView.getMap().clear();
		PoiOverlay overlay = new MyPoiOverlay(MainActivity.mMapView.getMap());
		MainActivity.mMapView.getMap().setOnMarkerClickListener(overlay);
		overlay.setData(GoMapApp.mPoiResult);
		overlay.addToMap();
		overlay.zoomToSpan();*/
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Intent mintent=new Intent();
		setResult(2, mintent);
		finish();
		
	}
	
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
