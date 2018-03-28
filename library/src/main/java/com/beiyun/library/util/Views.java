package com.beiyun.library.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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
     * 解决recyclerView外层嵌套ScrollView 滑动不流畅
     * @param recyclerView recyclerView
     */
    public static void nestedScroll(RecyclerView recyclerView){
        recyclerView.setNestedScrollingEnabled(false);
    }


    /**
     * 获取当前页面homeView
     * @return homeView
     */
    public static View getHomeView(){
        return getHomeView(Apps.getCurrentActivity());
    }

    /**
     * 获取指定页面homeView
     * @return homeView
     */
    private static View getHomeView(Activity activity) {
        return activity.findViewById(android.R.id.home);
    }


    /**
     * 获取当前页面绘制区域的最外层的View(不包含ActionBar和statusBar)
     * @return view
     */
    public static View getContentView(){
        return getContentView(Apps.getCurrentActivity());
    }


    /**
     * 获取指定页面绘制区域的最外层的View(不包含ActionBar和statusBar)
     * @return view
     */
    private static View getContentView(Activity activity) {
        return activity.findViewById(android.R.id.content);
    }


    /**
     * 清除当前页面所有的指定ViewType类型的内容(不清除actionBar和状态栏上的内容)
     */
    public static void clearContent(@NonNull ViewType... viewTypes){
        clearContent(getContentView(),viewTypes);
    }

    /**
     * 清除指定view中指定viewType的内容,期望view是ViewGroup但传入其他view 也不会有问题
     * @param view view 指定view
     * @param viewTypes viewTypes 指定type
     */
    public static void clearContent(@NonNull View view,@NonNull ViewType... viewTypes){
        for (ViewType viewType:viewTypes) {
            if(viewType == ViewType.All){
                for (ViewType vt:viewType.getAll()) {
                    clearChildContent(view,vt);
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
    public static void clearContent(@NonNull View view){
        Logs.e("clearContent : "+view.getClass().getName() );
        String className = getClassName(view.getClass());
        if(className.equals(AppCompatEditText.class.getName())){
            ((EditText) view).setText(null);
        }else if(className.equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setImageBitmap(null);
        }else if(className.equals(AppCompatTextView.class.getName())){
            ((TextView) view).setText(null);
        }else if(className.equals(AppCompatButton.class.getName()) ){
            ((Button) view).setText(null);
        }else if(className.equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setChecked(false);
        }else if(className.equals(AppCompatRadioButton.class.getName())){
            ViewParent parent = view.getParent();
            if(parent instanceof RadioGroup){
                ((RadioGroup) parent).check(-1);
            }
        }else if(className.equals(AppCompatAutoCompleteTextView.class.getName())){
            ((AppCompatAutoCompleteTextView) view).setText(null);
        }else if(className.equals(AppCompatMultiAutoCompleteTextView.class.getName())){
            ((AppCompatMultiAutoCompleteTextView) view).setText(null);
        }else if(className.equals(AppCompatCheckedTextView.class.getName())){
            ((AppCompatCheckedTextView) view).setText(null);
            ((AppCompatCheckedTextView) view).setChecked(false);
        }else if(className.equals(AppCompatImageButton.class.getName())){
            ((AppCompatImageButton) view).setImageBitmap(null);
        }else if(className.equals(AppCompatRatingBar.class.getName())){
            ((AppCompatRatingBar) view).setProgress(0);
        }else if(className.equals(AppCompatSeekBar.class.getName())){
            ((AppCompatSeekBar) view).setProgress(0);
        } else if(view instanceof ViewGroup){

            if(className.equals(ListView.class.getName())){
                ((ListView) view).setAdapter(null);
            }else if(className.equals(RecyclerView.class.getName())){
                ((RecyclerView) view).setAdapter(null);
            }else if(className.equals(ListViewCompat.class.getName())){
                ((ListViewCompat) view).setAdapter(null);
            }else if(className.equals(GridView.class.getName())){
                ((GridView) view).setAdapter(null);
            }else{

                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    clearContent(((ViewGroup) view).getChildAt(i));
                }
            }
        }
    }


    private static void clearChildContent(View child, ViewType viewType) {
        String className = getClassName(child.getClass());

        if(className.equals(AppCompatEditText.class.getName()) && viewType == ViewType.EditText){
            ((EditText) child).setText(null);
        }else if(className.equals(AppCompatImageView.class.getName()) && viewType == ViewType.ImageView){
            ((ImageView) child).setImageBitmap(null);
        }else if(className.equals(AppCompatTextView.class.getName()) && viewType == ViewType.TextView){
            ((TextView) child).setText(null);
        }else if(className.equals(AppCompatButton.class.getName()) && viewType == ViewType.Button){
            ((Button) child).setText(null);
        }else if(className.equals(AppCompatCheckBox.class.getName()) && viewType == ViewType.CheckBox){
            ((CheckBox) child).setChecked(false);
        }else if(className.equals(AppCompatRadioButton.class.getName()) && viewType == ViewType.RadioButton){
            ViewParent parent = child.getParent();
            if(parent instanceof RadioGroup){
                ((RadioGroup) parent).check(-1);
            }
        }else if(className.equals(AppCompatAutoCompleteTextView.class.getName()) && viewType == ViewType.AutoCompleteTextView){
            ((AppCompatAutoCompleteTextView) child).setText(null);
        }else if(className.equals(AppCompatMultiAutoCompleteTextView.class.getName()) && viewType == ViewType.MultiAutoCompleteTextView){
            ((AppCompatMultiAutoCompleteTextView) child).setText(null);
        }else if(className.equals(AppCompatCheckedTextView.class.getName()) && viewType == ViewType.CheckedTextView){
            ((AppCompatCheckedTextView) child).setText(null);
            ((AppCompatCheckedTextView) child).setChecked(false);
        }else if(className.equals(AppCompatImageButton.class.getName()) && viewType == ViewType.ImageButton){
            ((AppCompatImageButton) child).setImageBitmap(null);
        }else if(className.equals(AppCompatRatingBar.class.getName()) && viewType == ViewType.RatingBar){
            ((AppCompatRatingBar) child).setProgress(0);
        }else if(className.equals(AppCompatSeekBar.class.getName()) && viewType == ViewType.SeekBar){
            ((AppCompatSeekBar) child).setProgress(0);
        }else if(child instanceof ViewGroup){

            if(className.equals(ListView.class.getName())){
                ((ListView) child).setAdapter(null);
            }else if(className.equals(RecyclerView.class.getName())){
                ((RecyclerView) child).setAdapter(null);
            }else if(className.equals(ListViewCompat.class.getName())){
                ((ListViewCompat) child).setAdapter(null);
            }else if(className.equals(GridView.class.getName())){
                ((GridView) child).setAdapter(null);
            }else{

                for (int i = 0; i < ((ViewGroup) child).getChildCount(); i++) {
                    clearChildContent(((ViewGroup) child).getChildAt(i),viewType);
                }
            }

        }
    }


    /**
     * 禁用当前页面所有控件(actionBar和状态栏除外)
     */
    public static void disableControl(){
        disableControl(getContentView());
    }


    /**
     * 禁用view如果view是ViewGroup，那么该ViewGroup中的所有子view都将被禁用
     * @param view
     */
    public static void disableControl(View view) {
        String className = getClassName(view.getClass());;
        Logs.e("禁用控件xxxxxx ："+className);
        if(className.equals(AppCompatEditText.class.getName())){
            ((EditText) view).clearFocus();
            ((EditText) view).setFocusableInTouchMode(false);
        }else if(className.equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setClickable(false);
        }else if(className.equals(AppCompatTextView.class.getName())){
            ((TextView) view).setFocusable(false);
        }else if(className.equals(AppCompatButton.class.getName()) ){
            ((Button) view).setClickable(false);
        }else if(className.equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setClickable(false);
        }else if(className.equals(AppCompatRadioButton.class.getName())){
            ((RadioButton) view).setClickable(false);
        }else if(className.equals(AppCompatAutoCompleteTextView.class.getName())){
            ((AppCompatAutoCompleteTextView) view).clearFocus();
            ((AppCompatAutoCompleteTextView) view).setFocusableInTouchMode(false);
        }else if(className.equals(AppCompatMultiAutoCompleteTextView.class.getName())){
            ((AppCompatMultiAutoCompleteTextView) view).clearFocus();
            ((AppCompatMultiAutoCompleteTextView) view).setFocusableInTouchMode(false);
        }else if(className.equals(AppCompatCheckedTextView.class.getName())){
            ((AppCompatCheckedTextView) view).setFocusable(false);
            ((AppCompatCheckedTextView) view).setClickable(false);
        }else if(className.equals(AppCompatImageButton.class.getName())){
            ((AppCompatImageButton) view).setClickable(false);
        }else if(className.equals(AppCompatRatingBar.class.getName())){
            ((AppCompatRatingBar) view).setClickable(false);
        }else if(className.equals(AppCompatSeekBar.class.getName())){
            ((AppCompatSeekBar) view).setClickable(false);
        }else if(view instanceof ViewGroup){
            if(className.equals(ListView.class.getName())){
                view.setEnabled(false);
            }else if(className.equals(RecyclerView.class.getName())){
                view.setEnabled(false);
            }else if(className.equals(ListViewCompat.class.getName())){
                view.setEnabled(false);
            }else if(className.equals(GridView.class.getName())){
                view.setEnabled(false);
            }else{
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    disableControl(((ViewGroup) view).getChildAt(i));
                }
            }
        }
    }

    private static String getClassName(Class viewClass) {
        String className = viewClass.getName();
        Logs.e("getClassName = "+className);
        if(!className.startsWith("android")){
            className = getClassName(viewClass.getSuperclass());
        }

        return className;

    }


    /**
     * 解除禁用
     */
    public static void ableControl(){
        ableControl(getContentView());
    }


    public static void ableControl(View view) {
        String className = getClassName(view.getClass());
        Logs.e("解除禁用 ："+className);
        if(className.equals(AppCompatEditText.class.getName())){
            ((EditText) view).setFocusableInTouchMode(true);
        }else if(className.equals(AppCompatImageView.class.getName())){
            ((ImageView) view).setClickable(true);
        }else if(className.equals(AppCompatTextView.class.getName())){
            ((TextView) view).setFocusable(true);
        }else if(className.equals(AppCompatButton.class.getName()) ){
            ((Button) view).setClickable(true);
        }else if(className.equals(AppCompatCheckBox.class.getName())){
            ((CheckBox) view).setClickable(true);
        }else if(className.equals(AppCompatRadioButton.class.getName())){
            ((RadioButton) view).setClickable(true);
        }else if(className.equals(AppCompatAutoCompleteTextView.class.getName())){
            ((AppCompatAutoCompleteTextView) view).setFocusableInTouchMode(true);
        }else if(className.equals(AppCompatMultiAutoCompleteTextView.class.getName())){
            ((AppCompatMultiAutoCompleteTextView) view).setFocusableInTouchMode(true);
        }else if(className.equals(AppCompatCheckedTextView.class.getName())){
            ((AppCompatCheckedTextView) view).setFocusable(true);
            ((AppCompatCheckedTextView) view).setClickable(true);
        }else if(className.equals(AppCompatImageButton.class.getName())){
            ((AppCompatImageButton) view).setClickable(true);
        }else if(className.equals(AppCompatRatingBar.class.getName())){
            ((AppCompatRatingBar) view).setClickable(true);
        }else if(className.equals(AppCompatSeekBar.class.getName())){
            ((AppCompatSeekBar) view).setClickable(true);
        }else if(view instanceof ViewGroup){

            if(className.equals(ListView.class.getName())){
                Logs.e("className.equals(ListView.class.getName()");
                view.setEnabled(true);
            }else if(className.equals(RecyclerView.class.getName())){
                view.setEnabled(true);
            }else if(className.equals(ListViewCompat.class.getName())){
                view.setEnabled(true);
            }else if(className.equals(GridView.class.getName())){
                view.setEnabled(true);
            }else{
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    ableControl(((ViewGroup) view).getChildAt(i));
                }
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
