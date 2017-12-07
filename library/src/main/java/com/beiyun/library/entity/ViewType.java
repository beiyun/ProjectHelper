package com.beiyun.library.entity;

import java.util.ArrayList;

/**
 * Created by beiyun on 2017/12/6.
 *
 */
public enum ViewType {

    ImageView,
    EditText,
    TextView,
    Button,
    CheckBox,
    RadioButton,
    All;

    public ArrayList<ViewType> getAll(){
        ArrayList<ViewType> viewTypes = new ArrayList<>();
        viewTypes.add(ImageView);
        viewTypes.add(EditText);
        viewTypes.add(TextView);
        viewTypes.add(Button);
        viewTypes.add(CheckBox);
        viewTypes.add(RadioButton);
        return viewTypes;
    }

}
