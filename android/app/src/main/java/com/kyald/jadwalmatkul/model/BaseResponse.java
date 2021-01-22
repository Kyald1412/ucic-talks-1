package com.kyald.jadwalmatkul.model;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse {
    public final int code;
    public final String s;

    public BaseResponse(int code, String s) {
        this.code = code;
        this.s = s;
    }
}