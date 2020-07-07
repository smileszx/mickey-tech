package com.mickey.tech.test;

import com.mickey.tech.task.SyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author suzhengxiao
 * @date 2020/3/11 15:31
 **/
@Slf4j
public class CommonTest {

    @Test
    public void testStringLength () {
        SyncTask syncTask = new SyncTask();
        SyncTask syncTask2 = new SyncTask();

        syncTask2.testStaticCount();
        AtomicLong atomicLong = syncTask.testStaticCount();
        log.info("类静态变量: {}", atomicLong);

        syncTask2.testNonStaticCount();
        AtomicLong nonAtomicLong = syncTask.testNonStaticCount();
        log.info("类非静态变量: {}", nonAtomicLong);
    }
}
