package com.du.block.protocol.response;

import java.util.List;

import lombok.Data;

/**
 * 列表结果集
 * @author darwindu
 * @date 2019/3/7
 **/
@Data
public class ListResult<T> {

    /**记录总数**/
    private int total;
    /**列表**/
    private List<T>  list;
}
