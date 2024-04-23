package com.ebay.test.managersystem.common;

import lombok.Getter;

@Getter
public enum ReturnCode {
    RC200(200, "ok"),
    RC400(400, "请求失败，参数错误，请检查后重试。"),
    RC401(401, "未登录。"),
    RC403(403, "用户无权限。"),
    RC404(404, "未找到您请求的资源。"),
    RC405(405, "请求方式错误。"),
    RC500(500, "操作失败，服务器繁忙或内部错误，请稍后再试。");

    private final int code;

    private final String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
