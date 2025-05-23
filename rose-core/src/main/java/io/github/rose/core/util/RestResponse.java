/*
 * Copyright © 2025 rosestack.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rose.core.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一封装 Restful 接口返回信息
 * <p>
 *
 * @author <a href="mailto:ichensoul@gmail.com">chensoul</a>
 * @since 0.0.1
 */
public class RestResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public RestResponse() {
    }

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return ok(data, HttpStatus.OK.name());
    }

    public static <T> RestResponse<T> ok(T data, String message) {
        return build(data, HttpStatus.OK.value(), message);
    }

    public static <T> RestResponse<T> error(int code, String message) {
        return build(null, code, message);
    }

    public static <T> RestResponse<T> error(String message) {
        return build(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> RestResponse<T> build(T data, int code, String message) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = code;
        restResponse.message = message;
        restResponse.data = data;
        return restResponse;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @JsonIgnore
    public Boolean isSuccess() {
        return (this.code == HttpStatus.OK.value());
    }

    @JsonIgnore
    public Map<String, Object> toMap() {
        return Maps.of("code", code, "message", message, "data", data);
    }
}
