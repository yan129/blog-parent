package com.xiong.article;

import org.junit.Test;

public class MPGenerator {

    @Test
    public void test1(){
        String name = "article";
        com.xiong.common.util.MPGenerator.generator(name, name);
    }
}
