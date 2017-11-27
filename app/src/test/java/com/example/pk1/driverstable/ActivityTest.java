package com.example.pk1.driverstable;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.network.APIInterface;
import com.example.pk1.driverstable.presenter.MainPresenterImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static io.reactivex.Single.just;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock APIInterface apiInterface;
    @InjectMocks MainPresenterImpl mainPresenter;



    @Test public void emptyTest() {
        Category category=new Category();
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        category.setCategory(list);
        when(apiInterface.doGetListCategory()).thenReturn(just(category));
        mainPresenter.getCategory();
    }

}
