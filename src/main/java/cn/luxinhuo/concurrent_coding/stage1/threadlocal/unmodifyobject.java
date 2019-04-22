package cn.luxinhuo.concurrent_coding.stage1.threadlocal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class unmodifyobject {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(20);

        final Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);

        final ImmutableList<Integer> list = ImmutableList.of(1,2,3,4,5);
        final ImmutableMap<String,String> iMap = ImmutableMap.of("key1","value1","key2","value2");
        final ImmutableMap<String,String> iMap2 = ImmutableMap.<String,String>builder()
                .put("key1","value2").put("key2","value2").build();

        //这里会抛异常
        list.add(10);
    }
}
