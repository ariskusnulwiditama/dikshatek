package com.example.lawrecontest.controller;

import java.io.Serializable;

public class GeneralBodyResponseV2<T> implements Serializable {

    private MetaInfo meta;
    private int code;
    private String status;
    private String message;


    private Object data;


    public GeneralBodyResponseV2(int code, String status, String message, int page, int size, int totalPage, long totalItem, Object data) {
        MetaInfo meta = new MetaInfo(page, size, totalPage, totalItem);
        this.meta = meta;
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }


}

class MetaInfo {
    private int currentPage;
    private int perPage;
    private int totalPage;
    private long totalItem;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public MetaInfo(int page, int size, int totalPage, long totalItem){
        this.currentPage = page;
        this.perPage = size;
        this.totalPage = totalPage;
        this.totalItem = totalItem;
    }
}
