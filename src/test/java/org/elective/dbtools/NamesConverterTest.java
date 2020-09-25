package org.elective.dbtools;

import org.junit.Assert;
import org.junit.Test;

public class NamesConverterTest {
    @Test
    public void abbreviationWithWordTest() {
        String javaName = "SQLName";
        String expected = "sql_name";
        Assert.assertEquals(expected, NamesConverter.toSQLName(javaName));
    }

    @Test
    public void abbreviationTest() {
        String javaName = "JDBC";
        String expected = "jdbc";
        Assert.assertEquals(expected, NamesConverter.toSQLName(javaName));
    }

    @Test
    public void twoWordNameTest() {
        String javaName = "newName";
        String expected = "new_name";
        Assert.assertEquals(expected, NamesConverter.toSQLName(javaName));
    }

    @Test
    public void wordWithLocaleTest() {
        String javaName = "newNameEN";
        String expected = "new_name_en";
        Assert.assertEquals(expected, NamesConverter.toSQLName(javaName));
    }
}
