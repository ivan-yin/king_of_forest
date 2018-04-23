package com.ifzer.common;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Objects;

/**
 * Created by nelson on 2018-04-23.
 */
public class JquiPage {

    public JquiPage(){
    }

    private JquiPage(Page page){
        if (Objects.nonNull(page)){
            this.total = page.getTotal();
            this.rows = page.getRecords();
        }
    }

    public JquiPage(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    private Long total;
    private List rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public static JquiPage fromMyBatisPage(Page page){
        return new JquiPage(page);
    }
}
