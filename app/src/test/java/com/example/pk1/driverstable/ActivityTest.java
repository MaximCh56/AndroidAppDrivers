package com.example.pk1.driverstable;

import com.example.pk1.driverstable.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {
    private MainActivity activity;
    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MainActivity.class )
                .create()
                .resume()
                .get();
    }
    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }
}
