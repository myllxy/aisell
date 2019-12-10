package cn.itsource.aisell.common;

/**
 * 返回结果
 */
public class JsonResult {
    //默认是成功的
    private boolean success = true;
    //失败后返回的原因
    private String msg;

    public JsonResult(){}
    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
