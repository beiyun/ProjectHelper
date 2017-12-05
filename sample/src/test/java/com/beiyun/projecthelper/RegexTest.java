package com.beiyun.projecthelper;

import com.beiyun.library.constants.RegexConstants;
import com.beiyun.library.entity.TimeType;
import com.beiyun.library.util.Regexs;
import com.beiyun.library.util.Times;
import com.mifmif.common.regex.Generex;

import org.junit.Test;

/**
 * Created by beiyun on 2017/12/2.
 */
public class RegexTest {

    String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    @Test
    public void generateStringByRegex(){
        for (int i = 0; i < 10; i++) {
            String s = Regexs.generateStringByRegex(RegexConstants.REGEX_EMAIL);
            System.out.print(s + "\n");
        }

        System.out.print(Regexs.isEmail("alone169@163.com")+"-----"+Regexs.isEmail("alone.com"));


        Generex generex = new Generex(RegexConstants.REGEX_ID_CARD18);
        generex.setSeed(Times.timeMillis("1989-07-18", TimeType.yyyy_MM_dd));
        String random1 = generex.random();

        System.out.print(random1);

    }





}
