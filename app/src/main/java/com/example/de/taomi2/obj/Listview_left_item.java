package com.example.de.taomi2.obj;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Listview_left_item {
    String name;
    int select; //0:没选中 1: 选中
    int num;

    public Listview_left_item(String name, int select, int num) {
        this.name = name;
        this.select = select;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
