package org.example.util;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class ConvertClass {

    public static <T> T packClassObj(Class<T> clazz,String[] queryFields,Object[] queryValues) throws IllegalAccessException, InstantiationException, SQLException {
        T t=clazz.newInstance();
        //获取所有的私有属性
        Field[] fields=clazz.getDeclaredFields();
        for(Field f :fields){
            //设置属性值
            f.setAccessible(true);
            String fieldName=f.getName();
            int i=0;
            while (i<queryFields.length){
                if(queryFields[i].equals(fieldName)) {
                    f.set(t, queryValues[i]);
                }
                i++;
            }
        }
        return t;
    }
}
