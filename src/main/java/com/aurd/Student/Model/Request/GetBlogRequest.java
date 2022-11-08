package com.aurd.Student.Model.Request;

public class GetBlogRequest {
    long inst_id;
    String  date;
//    String lastId;
    Integer page;
    Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
//
//    public String getLastId() {
//        return lastId;
//    }
//
//    public void setLastId(String lastId) {
//        this.lastId = lastId;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }
}
