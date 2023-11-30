package com.tencent.wxcloudrun.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomHolder {
    public final static Random random=new Random();
    public final static Random secRandom=new SecureRandom();
}
