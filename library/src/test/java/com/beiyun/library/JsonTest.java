package com.beiyun.library;


import com.beiyun.library.util.Jsons;

import org.junit.Test;

/**
 * Created by beiyun on 2017/12/8.
 */
public class JsonTest {

    @Test
    public void testJson(){

        String json = "{\"mtId\":\"370783001001001001\",\"medicalProperties\":\"2\"," +
                "\"doctorNo\":\"3707830010017\",\"mtName\":\"团队1\"," +
                "\"areaName\":\"山东省潍坊市寿光市圣城街道办事处城里居委会\"," +
                "\"unitNumber\":\"370783001001001\",\"name\":\"beiyun\"," +
                "\"departId\":\"0200\",\"userName\":\"北云\",\"phoneOne\":\"15653747488\"," +
                "\"medicalPName\":\"专科医院\",\"companyName\":\"寿光医院1\"," +
                "\"jobArea\":\"370783001001\",\"idNumber\":\"41022119890718481X\"}";


//        UserEntity entity = gson.fromJson(json,UserEntity.class);
//        UserEntity entity = gson.fromJson(json,new TypeToken<UserEntity>(){}.getType());

//        UserEntity entity = Jsons.parseJson(json, new TypeToken<UserEntity>() {
//        });

        UserEntity entity = Jsons.parseObject(json, UserEntity.class);


        System.out.print(entity);




    }
}
