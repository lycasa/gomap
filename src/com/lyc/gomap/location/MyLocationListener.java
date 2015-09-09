package com.lyc.gomap.location;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.lyc.gomap.R;
import com.lyc.gomap.util.GoMapApp;

public class MyLocationListener implements BDLocationListener {
	
	private MapView mMapView;
	public MyLocationListener(MapView mMapView){
		this.mMapView = mMapView;
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null)
			return;
		StringBuffer sb = new StringBuffer(256);
		sb.append("time : ");
		sb.append(location.getTime());
		sb.append("\nerror code : ");
		sb.append(location.getLocType());
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());
		if (location.getLocType() == BDLocation.TypeGpsLocation) {
			sb.append("\nspeed : ");
			sb.append(location.getSpeed());
			sb.append("\nsatellite : ");
			sb.append(location.getSatelliteNumber());
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
			sb.append("\naddr : ");
			sb.append(location.getAddrStr());
		}
		Log.i("MyLocationListener", "====sb=====" + sb.toString());
		// ���춨λ����  
		if(location!=null){
			MyLocationData locData = new MyLocationData.Builder()  
		    .accuracy(location.getRadius())  
		    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360  
		    .direction(100).latitude(location.getLatitude())  
		    .longitude(location.getLongitude()).build();  
		// ���ö�λ����  
		mMapView.getMap().setMyLocationData(locData);  
		// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory  
		    .fromResource(R.drawable.ccp);  
		MyLocationConfiguration config = new MyLocationConfiguration(LocationMode.FOLLOWING, true, mCurrentMarker);  
		mMapView.getMap().setMyLocationConfigeration(config);
		
		GoMapApp.Latitude = location.getLatitude();
		GoMapApp.Longitude = location.getLongitude();
		
		}
	}
}
