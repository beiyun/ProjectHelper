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
    AutoCompleteTextView,
    MultiAutoCompleteTextView,
    CheckedTextView,
    ImageButton,
    RatingBar,
    SeekBar,
    All;

    public ArrayList<ViewType> getAll(){
        ArrayList<ViewType> viewTypes = new ArrayList<>();
        viewTypes.add(ImageView);
        viewTypes.add(EditText);
        viewTypes.add(TextView);
        viewTypes.add(Button);
        viewTypes.add(CheckBox);
        viewTypes.add(RadioButton);
        viewTypes.add(AutoCompleteTextView);
        viewTypes.add(MultiAutoCompleteTextView);
        viewTypes.add(CheckedTextView);
        viewTypes.add(ImageButton);
        viewTypes.add(RatingBar);
        viewTypes.add(SeekBar);
        return viewTypes;
    }

}
