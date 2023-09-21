package com.example.demo.message;

import com.example.demo.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ResponseMessage {
    private String message;
    private Object data;
    private int code;

    public ResponseMessage(String message, Object data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }
}
