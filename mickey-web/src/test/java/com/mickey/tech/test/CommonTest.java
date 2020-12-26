package com.mickey.tech.test;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.mickey.tech.task.SyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Locale;
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

    @Test
    public void testLanguage () {
        Locale english = Locale.ENGLISH;
        Locale cn = Locale.CHINESE;

    }

    @Test
    public void testMD5 () {
        String testStr = "test中文";
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(testStr);//5393554e94bf0eb6436f240a4fd71282
        log.info("Str:{}, MD5:{}", testStr, digestHex);
    }
}
