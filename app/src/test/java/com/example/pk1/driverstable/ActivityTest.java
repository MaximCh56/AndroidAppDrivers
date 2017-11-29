package com.example.pk1.driverstable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.presenter.MainPresenter;
import com.example.pk1.driverstable.presenter.MainPresenterImpl;
import com.example.pk1.driverstable.view.MainActivity;
import com.example.pk1.driverstable.view.MainView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static io.reactivex.Single.just;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock APIInterface apiInterface;
    @InjectMocks MainPresenterImpl mainPresenter;
    @Mock Context context;

    @Test public void emptyTest() {
        Category category=new Category();
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        category.setCategory(list);
        when(apiInterface.doGetListCategory()).thenReturn(just(category));
        apiInterface.doGetListCategory()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe(new DisposableSingleObserver<Category>() {
                    @Override
                    public void onSuccess(@android.support.annotation.NonNull Category category) {
                        System.out.println(category.getCategory().size());
                    }

                    @Override
                    public void onError(@android.support.annotation.NonNull Throwable e) {
                        System.out.println("An error occurred during networking "+ e);
                    }
                });
        mainPresenter.getCategory();
    }
}
