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
 * �����һ��Сͼ�꣬�ұ���һ��Button
 *
 */

public class UIButton extends LinearLayout{
	//�ؼ�����
	private ImageView iv_customview_uibutton_left;
	private TextView tv_customview_uibutton_right;
	
	//�Զ�����������
	private Drawable dra_customview_uibutton_left;
	private CharSequence str_customview_uibutton_right;

	//���췽��
	public UIButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		//�ҵ����ֺͿؼ�
		View view = LayoutInflater.from(context).inflate(R.layout.customview_uibutton, this, true);
		iv_customview_uibutton_left = (ImageView) view.findViewById(R.id.iv_customview_uibutton_left);
		tv_customview_uibutton_right = (TextView) view.findViewById(R.id.tv_customview_uibutton_right);
		
		//�ҵ��Զ�������
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.UIButton);
		dra_customview_uibutton_left = mTypedArray.getDrawable(R.styleable.UIButton_uibuttonleftimage);
		str_customview_uibutton_right = mTypedArray.getText(R.styleable.UIButton_uibuttonrighttext);
		
		//���ؼ���ֵ
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










