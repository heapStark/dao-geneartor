package result.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

@DAOpublic interface ItemDAO {

    String TABLE = "item";

    String FIELDS = "item_name,title,image,entry,width_count,height_count,priority,summary,item_handler,valid,create_time,update_time";

    @SQL("INSERT INTO " + TABLE + " (" + FIELDS + ") VALUES " +
            "(:p.itemName,:p.title,:p.image,:p.entry,:p.widthCount,:p.heightCount,:p.priority,:p.summary,:p.itemHandler,:p.valid,:p.createTime,:p.updateTime)")
    int insert(@SQLParam("p") POJOItem pOJOItem);

    @SQL("SELECT id," + FIELDS + " FROM " + TABLE)
    List<POJOItem> selectAll();

    @SQL("UPDATE " + TABLE + " SET item_name=:p.itemName, title=:p.title, image=:p.image, entry=:p.entry, width_count=:p.widthCount, height_count=:p.heightCount, priority=:p.priority, summary=:p.summary, item_handler=:p.itemHandler, valid=:p.valid, create_time=:p.createTime, update_time=:p.updateTime WHERE id=:p.id")
    int updateById(@SQLParam("p") POJOItem pOJOItem);


    @SQL("DELETE  FROM " + TABLE +" WHERE id=:p.id")
    void deleteById(@SQLParam("p") POJOItem pOJOItem);

}