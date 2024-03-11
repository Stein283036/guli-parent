package org.stein.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author stein
 * @date 2024/3/7
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuliException extends RuntimeException {
    private Integer code;

    public GuliException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
