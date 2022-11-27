package com.ntigo.junit5.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.concurrent.TimeUnit;

@ExtendWith( ExtensionModel.class )
public class ExtensionTest {

//    @RegisterExtension
//    static ExtensionModel extensionModel = new ExtensionModel();

    @Test
    void slowTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep( 2 );
    }

    @Test
    @Slow
    void markedSlowTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep( 2 );
    }
}
