package com.example.limmoor.candleburn.controller;

import android.content.res.Resources;
import android.widget.Spinner;

import com.example.limmoor.candleburn.R;
import com.example.limmoor.candleburn.model.candle.Material;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewBuilderTest {
    private static final String BEE = "Bienenwachs";
    private static final String PARA = "Paraffin";
    private static final String STEARIN = "Stearin";

    @Mock
    private MainActivity mMockActivity;

    @Mock
    private Resources mResources;

    @Test
    public void mockMeAmadeus() {
        ViewBuilder viewBuilder = new ViewBuilder();
        Spinner spinner = new Spinner(mMockActivity);
        when(mResources.getString(R.string.beewax)).thenReturn(BEE);
        when(mResources.getString(R.string.paraffin)).thenReturn(PARA);
        when(mResources.getString(R.string.stearin)).thenReturn(STEARIN);
        when(mMockActivity.getResources()).thenReturn(mResources);
        when(mMockActivity.findViewById(R.id.candleSpinner)).thenReturn(spinner);

        viewBuilder.buildSpinner(mMockActivity);

        assertEquals(3, viewBuilder.getMaterials().size());
        assertEquals(PARA, viewBuilder.getMaterials().get(0).toString());
        assertEquals(Material.PARAFIN.getDensity(), viewBuilder.getMaterials().get(0).getDensity(), 0);
    }
}