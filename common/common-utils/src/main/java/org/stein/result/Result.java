package org.stein.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stein
 * @date 2024/3/6
 */
@Data
public final class Result {
    private boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private Result() {}

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(StatusCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public Result success(boolean success) {
        setSuccess(success);
        return this;
    }

    public Result code(Integer code) {
        setCode(code);
        return this;
    }

    public Result message(String message) {
        setMessage(message);
        return this;
    }

    public Result data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> data) {
        setData(data);
        return this;
    }
}
