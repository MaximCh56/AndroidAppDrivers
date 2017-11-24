package com.example.pk1.driverstable;

import android.content.Context;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.presenter.MainPresenterImpl;
import com.example.pk1.driverstable.view.MainActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static io.reactivex.Single.just;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock APIInterface apiInterface;
    @InjectMocks MainPresenterImpl mainPresenter;
    @InjectMocks MainActivity activity;

//    @Before
//    public void setUp() throws Exception
//    {
//        activity = Robolectric.buildActivity( MainActivity.class )
//                .create()
//                .resume()
//                .get();
//    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test public void emptyTest() {
        Category category=new Category();
        List<String> list=new ArrayList<>();
        list.add("dgf");
        list.add("dgf");
        list.add("dgf");
        category.setCategory(list);
        when(apiInterface.doGetListCategory()).thenReturn(just(category));
        mainPresenter.getCategory();
        System.out.println(activity.category);

//        apiInterface.doGetListCategory()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<Category>() {
//                    @Override
//                    public void onSuccess(@android.support.annotation.NonNull Category category) {
//                        System.out.println("fghf");
//                    }
//
//                    @Override
//                    public void onError(@android.support.annotation.NonNull Throwable e) {
//                        System.out.println("An error occurred during networking "+ e);
//                    }
//                });
//        apiInterface.doGetListCategory();

    }

}
