package entity;

import lombok.Data;

import java.util.List;

/**
 * 分页结果实体类
 *
 * @author MuYang
 * @date 2020/1/6
 */
@Data
public class PageResult<T> {

    private long total;
    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
