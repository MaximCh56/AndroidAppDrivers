package com.example.pk1.driverstable.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.POJO.Driver;
import com.example.pk1.driverstable.model.POJO.ServerAnswer;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.model.network.NetworkAvailable;
import com.example.pk1.driverstable.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private APIInterface apiInterface;
    private Context context;

    public MainPresenterImpl(MainView mainView,  Context context,APIInterface apiInterface) {
        this.mainView = mainView;
        this.context = context;
        this.apiInterface = apiInterface;

    }

    @Override
    public void editDriver(Driver driver) {
        if (NetworkAvailable.isNetworkAvailable(context)) {
            apiInterface.editDriver(driver).enqueue(new Callback<ServerAnswer>() {
                @Override
                public void onResponse(@NonNull Call<ServerAnswer> call, @NonNull retrofit2.Response<ServerAnswer> response) {
                    if (response.body().getSuccess()) {
                        mainView.showMessage("Success");
                        mainView.updateDataDriverView();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ServerAnswer> call, @NonNull Throwable t) {
                    mainView.showMessage("An error occurred during networking");
                }
            });
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

    @Override
    public void getCategory() {
        if (NetworkAvailable.isNetworkAvailable(context)) {
            apiInterface.doGetListCategory().enqueue(new Callback<Category>() {
                @Override
                public void onResponse(@NonNull Call<Category> call, @NonNull retrofit2.Response<Category> response) {
                    mainView.showCategory(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                    mainView.showMessage("An error occurred during networking");
                }
            });
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

    @Override
    public void searchDrivers(String s) {
        if (NetworkAvailable.isNetworkAvailable(context)) {
            apiInterface.doGetListDrivers(s).enqueue(new Callback<List<Driver>>() {
                @Override
                public void onResponse(@NonNull Call<List<Driver>> call, @NonNull retrofit2.Response<List<Driver>> response) {
                    mainView.showSelectDrivers(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<List<Driver>> call, @NonNull Throwable t) {
                    mainView.showMessage("An error occurred during networking");
                }
            });
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

}
