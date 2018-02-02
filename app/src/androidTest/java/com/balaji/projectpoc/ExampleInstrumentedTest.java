package com.balaji.projectpoc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.balaji.projectpoc.model.Country;
import com.balaji.projectpoc.model.Row;
import com.balaji.projectpoc.utils.AppUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.balaji.projectpoc", appContext.getPackageName());
    }

    @Test
    public void removeEmptyDataWithAllNull() throws Exception {

        Country country = new Country();
        List<Row> rows = new ArrayList<>();

        Row row1 = new Row();
        row1.setTitle("title");
        row1.setDescription("desc");
        row1.setImageHref("image");
        rows.add(row1);

        Row row2 = new Row();
        row2.setTitle(null);
        row2.setDescription(null);
        row2.setImageHref(null);
        rows.add(row2);

        country.setRows(rows);
        assertEquals(1, AppUtils.removeEmptyData(country).getRows().size());

    }

    @Test
    public void removeEmptyDataWithSingleNull() throws Exception {

        Country country = new Country();
        List<Row> rows = new ArrayList<>();

        Row row1 = new Row();
        row1.setTitle("title");
        row1.setDescription("desc");
        row1.setImageHref("image");
        rows.add(row1);

        Row row2 = new Row();
        row2.setTitle("title");
        row2.setDescription(null);
        row2.setImageHref(null);
        rows.add(row2);

        country.setRows(rows);
        assertEquals(2, AppUtils.removeEmptyData(country).getRows().size());

    }
}
