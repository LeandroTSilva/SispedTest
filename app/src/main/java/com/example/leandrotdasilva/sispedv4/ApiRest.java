package com.example.leandrotdasilva.sispedv4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leandrot.dasilva on 09/04/2018.
 */

public class ApiRest {

        public  static final String _BaseUrl = "http://192.168.57.1:8080/";

        public static Retrofit getRetrofit(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(_BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        }

}
