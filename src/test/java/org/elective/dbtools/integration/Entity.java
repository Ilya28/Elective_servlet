package org.elective.dbtools.integration;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

import java.util.Arrays;

@Table(name = "my_table")
public class Entity {
    @TableField(name = "id", type = TableField.TF_GENERATED)
    int id;
    @TableField(name = "str")
    String str;
    @TableField(name = "count")
    int count;
    @TableField
    byte[] pass;
    @TableField
    boolean flag;

    @Override
    public String toString() {
        return "Entity{" +
                ", str='" + str + '\'' +
                ", count=" + count +
                ", pass=" + Arrays.toString(pass) +
                ", flag=" + flag +
                '}';
    }

    public Entity() {
    }

    public Entity(String str, int count, byte[] pass, boolean flag) {
        this.str = str;
        this.count = count;
        this.pass = pass;
        this.flag = flag;
    }
}