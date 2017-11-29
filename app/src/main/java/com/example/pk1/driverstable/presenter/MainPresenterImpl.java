package com.example.pk1.driverstable.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.POJO.Driver;
import com.example.pk1.driverstable.model.POJO.ServerAnswer;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.view.MainView;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private APIInterface apiInterface;
    private Context context;

    public MainPresenterImpl(Context context,APIInterface apiInterface) {
        this.context = context;
        this.apiInterface = apiInterface;

    }

    @Override
    public void editDriver(Driver driver) {
        if (isNetworkAvailable(context)) {
            if (isAttached()) {
                apiInterface.editDriver(driver)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<ServerAnswer>() {
                            @Override
                            public void onSuccess(@NonNull ServerAnswer message) {
                                if (message.getSuccess()) {
                                    mainView.showMessage("Success");
                                    mainView.updateDataDriverView();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mainView.showMessage("An error occurred during networking " + e);
                            }
                        });
            }
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

    @Override
    public void getCategory() {
        if (isNetworkAvailable(context)) {
            if (isAttached()) {
                apiInterface.doGetListCategory()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<Category>() {
                            @Override
                            public void onSuccess(@NonNull Category category) {
                                mainView.showCategory(category);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mainView.showMessage("An error occurred during networking " + e);
                            }
                        });
            }
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

    @Override
    public void searchDrivers(String s) {
        if (isNetworkAvailable(context)) {
            if (isAttached()) {
                apiInterface.doGetListDrivers(s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<List<Driver>>() {
                            @Override
                            public void onSuccess(@NonNull List<Driver> drivers) {
                                mainView.showSelectDrivers(drivers);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mainView.showMessage("An error occurred during networking " + e);
                            }
                        });
            }
        } else {
            mainView.showMessage("An error occurred during networking");
        }
    }

    @Override
    public void attach(MainView view) {
        mainView = view;
    }

    @Override
    public void detach() {
        mainView=null;

    }

    @Override
    public boolean isAttached() {
        return mainView != null;
    }

    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
