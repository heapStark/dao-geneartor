package dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ItemDAOTest extends BaseDAOTest {
    @Autowired
    private ItemDAO itemDAO;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private POJOItem getPOJO() {

        POJOItem pojo = new POJOItem();
        return pojo;

    }

    @Test
    public void insertTest() {
        int result = itemDAO.insert(getPOJO());
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectAllTest() {
        insertTest();
        List result = itemDAO.selectAll();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void updateTest() {
        insertTest();
        List<POJOItem> result = itemDAO.selectAll();
        int i = itemDAO.updateById(result.get(0));
        Assert.assertEquals(1, i);
    }
    @Test
    public void deleteTest() {
        insertTest();
        List<POJOItem> result = itemDAO.selectAll();
        Assert.assertEquals(1, result.size());
        itemDAO.deleteById(result.get(0));
        Assert.assertEquals(0, itemDAO.selectAll().size());
    }


}