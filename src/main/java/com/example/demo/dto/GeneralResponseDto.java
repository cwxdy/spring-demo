package com.example.demo.dto;
import java.io.Serializable;
public class GeneralResponseDto implements Serializable {
	
	private int code;
	private String returnMsg;
	private String status;
	private Object data;
	public static final String SYS_SUCCESS="success";
	public static final String SYS_ERROR="error";
    public GeneralResponseDto(int code, String returnMsg, Object data) {
        super();
        this.code = code;
        this.returnMsg = returnMsg;
        this.status = code==-1?SYS_ERROR:SYS_SUCCESS;
        this.data=data;
    }

	public  static GeneralResponseDto addSuccess(Object data){
        return new GeneralResponseDto(1,null,data);
    }
    public  static GeneralResponseDto addError(String errMsg){
        return new GeneralResponseDto(-1,errMsg,null);
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
