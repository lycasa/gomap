package com.lyc.gomap.util;

import java.util.HashMap;

import android.app.Application;

import com.baidu.mapapi.search.poi.PoiResult;

public class GoMapApp extends Application {
	public static double Latitude;//��ǰγ��
	public static double Longitude;//��ǰ����
	public static PoiResult mPoiResult;
	public static HashMap<Integer,PoiResult > mPoiResultHm;
}
