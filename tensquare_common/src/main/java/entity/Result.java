package entity;

import lombok.Data;

/**
 * 返回结果实体类
 *
 * @author MuYang
 * @date 2020/1/6
 */
@Data
public class Result {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

}
