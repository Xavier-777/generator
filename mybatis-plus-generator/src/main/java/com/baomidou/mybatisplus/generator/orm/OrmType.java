package com.baomidou.mybatisplus.generator.orm;

/**
 * @Author: meizhaowei
 * @Create: 2022/7/8 23:17
 * @Description: ORM框架类型
 */
public enum OrmType {

    /**
     * Mybatis_plus框架，默认为Mybatis-plus
     */
    Mybatis_plus("Mybatis-plus"),

    /**
     * Hand框架，该框架只有我们公司再用，而且非常垃圾！
     */
    Hand("Hand");

    private String name;

    OrmType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
