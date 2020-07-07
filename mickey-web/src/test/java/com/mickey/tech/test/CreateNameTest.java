package com.mickey.tech.test;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suzhengxiao
 * @date 2020/5/17 1:26 下午
 */
@Slf4j
public class CreateNameTest {

    /**
     * 数组初始化数量
     */
    private static final int NAME_CAPACITY = 10000;

    public static void main(String[] args) {
        String firstName = "Lee";
        List<String> firstWordList = Arrays.asList(
                "芃", "纪", "禹", "品", "宥"
        );
        List<String> secondWordList = Arrays.asList(
                "齐", "硕", "瑄", "祎"
        );

        List<String> nameWithDoubleWords = createNameWithDoubleWords(firstName, firstWordList, secondWordList);

        log.info("All names : {}", nameWithDoubleWords);
    }

    /**
     * 双字名
     * @param firstName
     * @param firstWordList
     * @param secondWordList
     */
    public static List<String> createNameWithDoubleWords(String firstName, List<String> firstWordList, List<String> secondWordList) {
        List<String> nameList = new ArrayList<>(NAME_CAPACITY);

        if (StringUtils.isBlank(firstName)) {
            throw new RuntimeException("姓氏不能为空！");
        }

        if (CollectionUtil.isEmpty(firstWordList)) {
            throw new RuntimeException("名字第一个字不能为空！");
        }

        if (CollectionUtil.isEmpty(secondWordList)) {
            throw new RuntimeException("名字第二个字不能为空！");
        }

        for (String first : firstWordList) {
            for (String second : secondWordList) {
                String name = String.format("%s%s%s", firstName, first, second);
                nameList.add(name);
            }
        }

        return nameList;
    }
}
