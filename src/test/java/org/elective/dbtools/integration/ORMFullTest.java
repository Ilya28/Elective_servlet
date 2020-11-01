package org.elective.dbtools.integration;

import org.elective.dbtools.Exporter;
import org.elective.dbtools.Importer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 Интеграционное тестирование с реальной базой данных. Создание, сохранение и выгрузка обьектов
 */

public class ORMFullTest {
    public static final String SQL_DROP = "DROP TABLE IF EXISTS `my_table`;";
    public static final String SQL_CREATE = "CREATE TABLE `my_table` (\n" +
            "  `id` INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "  `str` VARCHAR(60),\n" +
            "  `count` INT,\n" +
            "  `pass` BINARY(5),\n" +
            "  `flag` BOOL\n" +
            ") ENGINE=InnoDB;";
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC&user=meow&password=meow";

    @Test
    public void test() throws Exception {
        List<Entity> objects = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Entity entity = new Entity("meow", i*10, new byte[] {1,2,3,4,5}, i%2 == 0);
            objects.add(entity);
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(CONNECTION_URL)) {
            Statement statement = con.createStatement();
            statement.executeUpdate(SQL_DROP);
            statement.executeUpdate(SQL_CREATE);
        } catch (SQLException exception) {
            Assert.fail("Error with DROP/CREATE");
        }


        try (Connection con = DriverManager.getConnection(CONNECTION_URL)) {
            Exporter exporter = new Exporter(con);
            for (Entity entity: objects)
                exporter.save(entity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        List<Object> loaded;
        try (Connection con = DriverManager.getConnection(CONNECTION_URL)) {
            Importer importer = new Importer(con);
            loaded = importer.load(Entity.class, null);

            //objects.forEach(System.out::println);
            //System.out.println("==========");
            //loaded.forEach(System.out::println);
            Assert.assertEquals(objects.toString(), loaded.toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
