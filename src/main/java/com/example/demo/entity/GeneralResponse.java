package com.example.demo.entity;
import java.io.Serializable;
public class GeneralResponse implements Serializable {
	
	private int code;
	private String returnMsg;
	private String status;
	private Object data;
	public static final String SYS_SUCCESS="成功";
	public static final String SYS_ERROR="失败";
    public GeneralResponse(int code,String returnMsg,Object data) {
        super();
        this.code = code;
        this.returnMsg = returnMsg;
        this.status = code==-1?SYS_ERROR:SYS_SUCCESS;
        this.data=data;
    }

	public  static  GeneralResponse addSuccess(Object data){
        return new GeneralResponse(1,null,data);
    }
    public  static  GeneralResponse addError(String errMsg){
        return new GeneralResponse(-1,errMsg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public static String getSysSuccess() {
        return SYS_SUCCESS;
    }

    public static String getSysError() {
        return SYS_ERROR;
    }
}