package com.beiyun.library.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.beiyun.library.entity.ViewType;

/**
 * Created by beiyun on 2017/11/17.
 * view
 */
public class Views {



    public static View create(){
        return new View(Apps.getCurrentActivity());
    }


    /**
     * 清除当前页面所有的指定ViewType类型的内容(不清除actionBar和状态栏上的内容)
     */
    public static void clearContent(@NonNull ViewType... viewTypes){
        View view = Apps.getCurrentActivity().findViewById(android.R.id.content);
        clearContent(view,viewTypes);
    }

    /**
     * 清除指定view中指定viewType的内容,期望view是ViewGroup但传入其他view 也不会有问题
     * @param view view 指定view
     * @param viewTypes viewTypes 指定type
     */
    public static void clearContent(@NonNull View view,@NonNull ViewType... viewTypes){
        for (ViewType viewType:viewTypes) {
            if(view instanceof ViewGroup){
                for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                    View child = ((ViewGroup)view).getChildAt(i);
                    if(viewType == ViewType.All){
                        for (ViewType vt:viewType.getAll()) {
                            clearChildContent(child,vt);
                        }
                    }else{
                        clearChildContent(child,viewType);
                    }
                }
            }else{
                clearChildContent(view,viewType);
            }
        }
    }


    /**
     * 清除指定view的内容如果view是viewGroup 那么会将他的子控件都清空
     * @param view
     */
    public static void clearContent(View view){
        if(view.getClass().getName().equals(AppCompatEditText.class.getName())){
            ((EditText) view).setText(null);
        }else if(view.getClass().getName().equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setImageBitmap(null);
        }else if(view.getClass().getName().equals(AppCompatTextView.class.getName())){
            ((TextView) view).setText(null);
        }else if(view.getClass().getName().equals(AppCompatButton.class.getName()) ){
            ((Button) view).setText(null);
        }else if(view.getClass().getName().equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setChecked(false);
        }else if(view.getClass().getName().equals(AppCompatRadioButton.class.getName())){
            ViewParent parent = view.getParent();
            if(parent instanceof RadioGroup){
                ((RadioGroup) parent).check(-1);
            }
        }else if(view instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                clearContent(((ViewGroup) view).getChildAt(i));
            }
        }
    }


    private static void clearChildContent(View child, ViewType viewType) {

        if(child.getClass().getName().equals(AppCompatEditText.class.getName()) && viewType == ViewType.EditText){
            ((EditText) child).setText(null);
        }else if(child.getClass().getName().equals(AppCompatImageView.class.getName()) && viewType == ViewType.ImageView){
            ((ImageView) child).setImageBitmap(null);
        }else if(child.getClass().getName().equals(AppCompatTextView.class.getName()) && viewType == ViewType.TextView){
            ((TextView) child).setText(null);
        }else if(child.getClass().getName().equals(AppCompatButton.class.getName()) && viewType == ViewType.Button){
            ((Button) child).setText(null);
        }else if(child.getClass().getName().equals(AppCompatCheckBox.class.getName()) && viewType == ViewType.CheckBox){
            ((CheckBox) child).setChecked(false);
        }else if(child.getClass().getName().equals(AppCompatRadioButton.class.getName()) && viewType == ViewType.RadioButton){
            ViewParent parent = child.getParent();
            if(parent instanceof RadioGroup){
                ((RadioGroup) parent).check(-1);
            }
        }else if(child instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup) child).getChildCount(); i++) {
                clearChildContent(((ViewGroup) child).getChildAt(i),viewType);
            }
        }
    }


    /**
     * 禁用当前页面所有控件(actionBar和状态栏除外)
     */
    public static void disableControl(){
        View view = Apps.getCurrentActivity().findViewById(android.R.id.content);
        disableControl(view);
    }


    /**
     * 禁用view如果view是ViewGroup，那么该ViewGroup中的所有子view都将被禁用
     * @param view
     */
    public static void disableControl(View view) {
        if(view == null) return;
        if(view.getClass().getName().equals(AppCompatEditText.class.getName())){
            ((EditText) view).clearFocus();
            ((EditText) view).setFocusableInTouchMode(false);
        }else if(view.getClass().getName().equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setClickable(false);
        }else if(view.getClass().getName().equals(AppCompatTextView.class.getName())){
            ((TextView) view).setFocusable(false);
        }else if(view.getClass().getName().equals(AppCompatButton.class.getName()) ){
            ((Button) view).setClickable(false);
        }else if(view.getClass().getName().equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setClickable(false);
        }else if(view.getClass().getName().equals(AppCompatRadioButton.class.getName())){
            ((RadioButton) view).setClickable(false);
        }else if(view instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                disableControl(((ViewGroup) view).getChildAt(i));
            }
        }
    }


    /**
     * 解除禁用
     */
    public static void ableControl(){
        View view = Apps.getCurrentActivity().findViewById(android.R.id.content);
        ableControl(view);
    }


    public static void ableControl(View view) {
        if(view == null)return;
        if(view.getClass().getName().equals(AppCompatEditText.class.getName())){
            ((EditText) view).setFocusableInTouchMode(true);
        }else if(view.getClass().getName().equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setClickable(true);
        }else if(view.getClass().getName().equals(AppCompatTextView.class.getName())){
            ((TextView) view).setFocusable(true);
        }else if(view.getClass().getName().equals(AppCompatButton.class.getName()) ){
            ((Button) view).setClickable(true);
        }else if(view.getClass().getName().equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setClickable(true);
        }else if(view.getClass().getName().equals(AppCompatRadioButton.class.getName())){
            ((RadioButton) view).setClickable(true);
        }else if(view instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                ableControl(((ViewGroup) view).getChildAt(i));
            }
        }
    }


    /**
     * 测量视图尺寸
     *
     * @param view 视图
     * @return arr[0]: 视图宽度, arr[1]: 视图高度
     */
    public static int[] measureView(final View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int lpHeight = lp.height;
        int heightSpec;
        if (lpHeight > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(widthSpec, heightSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    /**
     * 获取测量视图宽度
     *
     * @param view 视图
     * @return 视图宽度
     */
    public static int getMeasuredWidth(final View view) {
        return measureView(view)[0];
    }

    /**
     * 获取测量视图高度
     *
     * @param view 视图
     * @return 视图高度
     */
    public static int getMeasuredHeight(final View view) {
        return measureView(view)[1];
    }
}
