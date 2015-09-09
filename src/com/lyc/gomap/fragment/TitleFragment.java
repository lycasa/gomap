package com.lyc.gomap.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.lyc.gomap.R;
import com.lyc.gomap.main.MainActivity;
import com.lyc.gomap.main.SearchPoiActivity;
import com.lyc.gomap.util.GoMapApp;

public class TitleFragment extends Fragment implements OnClickListener {

	private TextView tv_search_tip;
	private View mview;
	private final String TAG = getClass().getSimpleName();
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mview = inflater.inflate(R.layout.fragment_titlefragment, container, false);  
		 initView();
		return mview;
	}

	private void initView() {
		tv_search_tip = (TextView) mview.findViewById(R.id.tv_search_tip);
		tv_search_tip.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//Ö÷½çÃæËÑË÷textview
		case R.id.tv_search_tip:
			//startActivity(new Intent(getActivity(), SearchPoiActivity.class));
			startActivityForResult(new Intent(getActivity(), SearchPoiActivity.class), 1);
			break;

		default:
			break;
		}
		
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(TAG, "==onActivityResult1=="+requestCode);
		Log.i(TAG, "==onActivityResult2=="+resultCode);
		if(requestCode == 1){
			if(resultCode == 2){
				
				MainActivity.mMapView.getMap().clear();
				PoiOverlay overlay = new MyPoiOverlay(MainActivity.mMapView.getMap());
				MainActivity.mMapView.getMap().setOnMarkerClickListener(overlay);
				overlay.setData(GoMapApp.mPoiResult);
				overlay.addToMap();
				overlay.zoomToSpan();
			}
		}
		
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
