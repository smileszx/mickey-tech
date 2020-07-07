package com.mickey.tech.task;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author suzhengxiao
 * @date 2020/4/26 4:00 下午
 */
public class SyncTask {

    private static AtomicLong staticCount = new AtomicLong(0);

    private AtomicLong nonStaticCount = new AtomicLong(0);

    public AtomicLong testStaticCount () {
        for (int i=0; i<100; i++) {
            staticCount.getAndIncrement();
        }
        return staticCount;
    }

    public AtomicLong testNonStaticCount () {
        for (int i=0; i<100; i++) {
            nonStaticCount.getAndIncrement();
        }
        return nonStaticCount;
    }

}
