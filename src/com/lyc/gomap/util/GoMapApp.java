package com.lyc.gomap.util;

import java.util.HashMap;

import android.app.Application;

import com.baidu.mapapi.search.poi.PoiResult;

public class GoMapApp extends Application {
	public static double Latitude;//当前纬度
	public static double Longitude;//当前经度
	public static PoiResult mPoiResult;
	public static HashMap<Integer,PoiResult > mPoiResultHm;
}
