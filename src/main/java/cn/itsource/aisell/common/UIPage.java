package cn.itsource.aisell.common;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUi专属Page
 * @param <T>
 */
public class UIPage<T> {

    private Long total;
    private List<T> rows = new ArrayList<>();

    //在构造的时候把page对象变成前端需要的结果
    public UIPage(Page page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
