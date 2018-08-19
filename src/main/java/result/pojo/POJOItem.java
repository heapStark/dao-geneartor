package result.pojo;

import java.util.Date;

public class POJOItem {
    /**
     * 
     */
    private int id;
    /**
     * 名称
     */
    private String itemName;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String image;
    /**
     * 跳转信息
     */
    private String entry;
    /**
     * 列数
     */
    private int widthCount;
    /**
     * 行数
     */
    private int heightCount;
    /**
     * 默认优先级
     */
    private int priority;
    /**
     * 动态信息
     */
    private String summary;
    /**
     * 动态处理器,空则采用默认显示
     */
    private String itemHandler;
    /**
     * 是否有效
     */
    private int valid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}