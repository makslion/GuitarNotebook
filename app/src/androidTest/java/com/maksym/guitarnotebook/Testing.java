package com.maksym.guitarnotebook;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

//so far there is no methods, that could be tested, as everything or predefined or from databases.
//the same with Espresso for GUI test.
@RunWith(AndroidJUnit4.class)
public class Testing
{
    @org.junit.Test
    public void useAppContext()
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.maksym.guitarnotebook", appContext.getPackageName());
    }
}
