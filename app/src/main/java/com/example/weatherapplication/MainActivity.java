package com.example.weatherapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.weatherapplication.databinding.ActivityMainBinding;
import com.example.weatherapplication.seasonData.interfaceApi;
import com.example.weatherapplication.seasonData.main;
import com.example.weatherapplication.seasonData.mausamData;
import com.example.weatherapplication.seasonData.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currdate = format.format(new Date());


        binding.date.setText(currdate);
        binding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(binding.searchbar.getText().toString())){
                    binding.searchbar.setError("Please Enter City Name");
                }
                else{
                    String cityname  = binding.cityname.getText().toString();
                    fatchWeather(cityname);
                }

            }
        });
    }

    void fatchWeather(String cityname){

        retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            interfaceApi interfaceApi =  retrofit.create(com.example.weatherapplication.seasonData.interfaceApi.class);

            Call<mausamData> call = interfaceApi.getData(cityname,"22e02e220487b7a820920ba11a1400a3","metric");
            call.enqueue(new Callback<mausamData>() {
                @Override
                public void onResponse(@NonNull Call<mausamData> call, @NonNull Response<mausamData> response) {
                    if(response.isSuccessful()){
                        mausamData mausamData = response.body();
                        assert mausamData != null;
                        main newmain = mausamData.getMain();


//                        binding.temperature.setText(newmain.getTemp() +"");
                        binding.temperature.setText(String.valueOf(newmain.getTemp()));
                        Log.d("VaishnaviPawar",newmain.getTemp()+" ");
//                        binding.maxtemp.setText(String.valueOf(newmain.getTemp_max())+"\u2103");
//                        binding.mintemp.setText(String.valueOf(newmain.getTemp_min())+"\u2103");
//                        binding.pressure.setText(String.valueOf(newmain.getPressure())+"\u2103");
                        binding.cityname.setText(mausamData.getName());

                        List<weather> descrition = mausamData.getWeather();

                        for(weather data : descrition){
                            binding.haze.setText(data.getDescription());
                        }



                    }
                }

                @Override
                public void onFailure(Call<mausamData> call, Throwable t) {

                }
            });



    }
}