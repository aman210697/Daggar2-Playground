package com.aman.dagger2playground;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aman.dagger2example.R;
import com.aman.dagger2playground.MainActivityFeature.DaggerMainActivityComponent;
import com.aman.dagger2playground.MainActivityFeature.MainActivityComponent;
import com.aman.dagger2playground.MainActivityFeature.MainActivityModule;
import com.aman.dagger2playground.adapter.RandomUserAdapter;
import com.aman.dagger2playground.application.RandomUserApplication;
import com.aman.dagger2playground.interfaces.RandomUsersApi;
import com.aman.dagger2playground.model.RandomUsers;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Inject
    RandomUsersApi randomUsersApi;

    @Inject
    RandomUserAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        afterActivityLevelComponent();
        populateUsers();

    }

    private void afterActivityLevelComponent() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserApplicationComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
    }



    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = getRandomUserService().getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if(response.isSuccessful()) {
                    mAdapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    public RandomUsersApi getRandomUserService(){
        return randomUsersApi;
    }


}
