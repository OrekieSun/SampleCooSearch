package com.orekie.searchlite.extended_search;

/**
 * 请不要修改此文件
 */
public class Model {
    /**
     * 这是一个通用的数据结构，用以把搜索结果数据统一封装
     * SearchList会将搜索结果全部封装如data数组中
     * data数组的顺序由content provider中cursor中的数据顺序确定
     * 是的，所有数据类型都会被转化成String
     */
    private String[] data;

    public Model(String[] data) {
        this.data = data;
    }

    public final String[] getData() {
        return data;
    }

}
