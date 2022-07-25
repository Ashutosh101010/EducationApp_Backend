package com.aurd.Student.Model.Request;

public class FunBoxRequest {

    private Integer page;
    private Integer pageSize;
    private Integer instId;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }
}
