package org.lino.work;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;

public class dd {


    @Test
    void test1(){
        String s = SecureUtil.md5("123456");
        System.out.println(s);
    }
}
