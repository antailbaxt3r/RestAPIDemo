package com.antailbaxt3r.restapidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.antailbaxt3r.restapidemo.models.RandomUser;
import com.antailbaxt3r.restapidemo.models.Result;
import com.antailbaxt3r.restapidemo.retrofit.RetrofitAPIInterface;
import com.antailbaxt3r.restapidemo.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Result> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.user_rv);
        RetrofitAPIInterface apiInterface = RetrofitClient.getClient();

        Map<String, Integer> map = new HashMap<>();
        map.put("results", 10);
        Call<RandomUser> call = apiInterface.getUser(map);

        call.enqueue(new Callback<RandomUser>() {
            @Override
            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                RandomUser obj = response.body();
                Log.i("RESPONSE", response.toString());
                userList = (ArrayList<Result>) obj.getResults();
                Log.i("RESPONSE", userList.get(0).getEmail());

                recyclerView.setAdapter(new UserAdapter(userList));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<RandomUser> call, Throwable t) {
                Log.e("RESPONSE ERROR", t.getMessage());
            }
        });

    }
}
