package cc.landfill.crowd.util;

import java.sql.ResultSet;

/**
 * @title: ResultEntity 统一整个项目中Ajax 请求返回的结果，也可以用于分布式架构各个模块间调用时返回统一类型
 * @Author Landfill
 * @Date: 2020/7/24 16:41
 * @Version 1.0
 */

//规范Ajax请求的返回结果
public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    private String result; //封装当前请求处理的结果是成功还是失败
    private String message;//请求处理失败时返回的错误消息
    private T data;        //要返回的数据。不一定是返回给浏览器，如在分布式架构中，可能是返给其他的服务消费方

    /**
    * @Description:请求处理成功且不需要返回数据时使用的工具方法  增删改
    * @Param:
    * @return:
    * @Author: Landfill
    * @Date:
    */
    public static <E> ResultEntity<E> successWithoutData(){  //泛型方法中声明一个泛型，因为不能再静态方法中调非静态的属性
        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
    * @Description:请求处理成功，且返回数据的方法   查询
    * @Param:
    * @return:
    * @Author: Landfill
    * @Date:
    */
     public static <E> ResultEntity<E> successWithData(E data){  //泛型方法中声明一个泛型，因为不能再静态方法中调非静态的属性
            return new ResultEntity<E>(SUCCESS,null,data);
        }
    /**
    * @Description:请求处理失败时返回消息  所有失败的
    * @Param:  msg  失败的错误消息
    * @return:
    * @Author: Landfill
    * @Date:
    */
     public static <E> ResultEntity<E> failed(String msg){  //泛型方法中声明一个泛型，因为不能再静态方法中调非静态的属性
            return new ResultEntity<E>(FAILED,msg,null);
        }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}