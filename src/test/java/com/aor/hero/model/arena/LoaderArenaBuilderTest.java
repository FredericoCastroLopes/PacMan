package com.aor.hero.model.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderArenaBuilderTest {

    @Test
    void getWidth() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        Assertions.assertEquals(21, loaderArenaBuilder.getWidth());
    }

    @Test
    void getHeight() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        Assertions.assertEquals(21, loaderArenaBuilder.getHeight());
    }



}
