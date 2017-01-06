package org.sample.ssm.web.form;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BasicForm
 *
 * @author Yang Cheng
 * @date 2016-09-07
 */
public class BasicForm implements Serializable {

    private static final long     serialVersionUID   = -488918622087057718L;

    /** 日期格式 */
    protected static final String dataFormat         = "yyyy.MM.dd HH:mm:ss.SSS";

    /** 每页大小,默认都为20 */
    protected int                 pageSize           = 20;

    /** 当前页码，没有则选中第一页 */
    protected int                 pageNum            = 1;

    /** 页面执行的操作 */
    protected String              action;

    /** 消息 */
    protected String              message;

    /** 消息状态 */
    protected String              messageStatus;

    /** 开始时间后缀*/
    protected final static String START_TIME_SUFFIX  = " 00:00";

    /** 结束时间后缀*/
    protected final static String END_TIME_SUFFIX    = " 23:59";

    /** 开始时间后缀*/
    protected final static String START_TIME_SUFFIX2 = ":00.000";

    /** 结束时间后缀*/
    protected final static String END_TIME_SUFFIX2   = ":59.999";

    /** 下载最大值 */
    protected final static Long   DOWNLOAD_MAXSIZE   = 5000L;

    /** 全部类型快照名字 */
    protected final static String ALL                = "all";

    /**
     * 日期字符转换类
     *
     * @param dateStr 日期字符
     * @return
     * @throws ParseException
     */
    protected Date parseToDate(String dateStr) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(dataFormat);
        return dateFormat.parse(dateStr);
    }

    /**
     * 设置分页参数
     *
     * @param pageQuery
     */
    protected void initPageConfig(PageQuery pageQuery) {
        pageQuery.setPage(pageNum);
        pageQuery.setPageSize(pageSize);
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     *
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     *
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>pageNum</tt>.
     *
     * @return property value of pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     *
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Getter method for property <tt>action</tt>.
     *
     * @return property value of action
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter method for property <tt>action</tt>.
     *
     * @param action value to be assigned to property action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property <tt>messageStatus</tt>.
     *
     * @return property value of messageStatus
     */
    public String getMessageStatus() {
        return messageStatus;
    }

    /**
     * Setter method for property <tt>messageStatus</tt>.
     *
     * @param messageStatus value to be assigned to property messageStatus
     */
    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * BasicForm
     *
     * @author Yang Cheng
     * @date 2016-09-02
     */
    public static class BasicForm {
    }
}
