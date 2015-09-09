package com.lyc.gomap.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyc.gomap.R;


/**
 * 
 * @author liyunchao
 * @version 1.0
 * 左边是一个小图标，右边是一个Button
 *
 */

public class UIButton extends LinearLayout{
	//控件声明
	private ImageView iv_customview_uibutton_left;
	private TextView tv_customview_uibutton_right;
	
	//自定义属性声明
	private Drawable dra_customview_uibutton_left;
	private CharSequence str_customview_uibutton_right;

	//构造方法
	public UIButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		//找到布局和控件
		View view = LayoutInflater.from(context).inflate(R.layout.customview_uibutton, this, true);
		iv_customview_uibutton_left = (ImageView) view.findViewById(R.id.iv_customview_uibutton_left);
		tv_customview_uibutton_right = (TextView) view.findViewById(R.id.tv_customview_uibutton_right);
		
		//找到自定义属性
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.UIButton);
		dra_customview_uibutton_left = mTypedArray.getDrawable(R.styleable.UIButton_uibuttonleftimage);
		str_customview_uibutton_right = mTypedArray.getText(R.styleable.UIButton_uibuttonrighttext);
		
		//给控件赋值
		if(dra_customview_uibutton_left!=null){
			iv_customview_uibutton_left.setImageDrawable(dra_customview_uibutton_left);
			iv_customview_uibutton_left.setScaleType(ImageView.ScaleType.FIT_XY); 
		}
		if(str_customview_uibutton_right!=null){
			tv_customview_uibutton_right.setText(str_customview_uibutton_right);
		}
		
		mTypedArray.recycle();
	}
}










