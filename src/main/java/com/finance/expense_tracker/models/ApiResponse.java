package com.finance.expense_tracker.models;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
