package com.example.pk1.driverstable;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.network.APIClient;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.presenter.MainPresenter;
import com.example.pk1.driverstable.presenter.MainPresenterImpl;
import com.example.pk1.driverstable.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {
    private MainActivity activity;
    private MainPresenter mainPresenter;
    @Mock private APIInterface apiInterface;
    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity( MainActivity.class )
                .create()
                .resume()
                .get();
        mainPresenter = new MainPresenterImpl(activity, activity, apiInterface);
    }
    @Test
    public void serverAnswerTest() throws Exception
    {
        Category category=new Category();
        List<String> strings=new ArrayList<>();
        strings.add("dfs");
        strings.add("dfs");
        strings.add("dfs");
        category.setCategory(new ArrayList<String>());
      //  when(apiInterface.doGetListCategory()).thenReturn(category);

    }


}
