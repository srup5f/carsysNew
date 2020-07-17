package org.example.util;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.bean.Car;
import org.example.bean.CarAccessories;
import org.postgresql.ds.PGSimpleDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Connection conn=null;
    private PreparedStatement pstm = null;
    private ResultSet rs=null;

    //单例模式的实现分为三步,第一步:创建私有对象
    private static DBManager db=null;

    //第二步：构造器私有化
    public DBManager(){}


    //第三步：提供供外部的访问方法：getInstace()
    public static DBManager getInstance(){
        if(db==null){
            synchronized (DBManager.class){
                if(db==null){
                    return new DBManager();
                }
            }
        }
        return db;
    }

    public void conn_init(String dataSource,String url,String username,String password){
        try{
            if("mysql".equals(dataSource)){
                MysqlDataSource mysqlDataSource=new MysqlDataSource();
                mysqlDataSource.setUrl(url);
                conn=mysqlDataSource.getConnection(username,password);
            }else if("postgresql".equals(dataSource)){
                PGSimpleDataSource pgSimpleDataSource=new PGSimpleDataSource();
                pgSimpleDataSource.setUrl(url);
                conn=pgSimpleDataSource.getConnection(username,password);
            }
        }catch (SQLException e){
            System.out.println("connection init failed");
            System.err.println(e.getMessage());
        }
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object[] params) {
        try{
            pstm=conn.prepareStatement(sql);
            if(params!=null){
                //封装参数
                for(int i=0;i<params.length;i++){
                    pstm.setObject(i+1,params[i]);
                }
            }
            //执行查询
            rs=pstm.executeQuery();
            List<T> list=new ArrayList<T>();
            if(clazz== Car.class){
                while(rs.next()){
                    //封装Car对象
                    T t = packCarObj(clazz);
                    list.add(t);
                }
            }else if(clazz== CarAccessories.class){
                while(rs.next()){
                    //封装CarAccessories对象
                    T t = packCarAccessoriesObj(clazz);
                    list.add(t);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println("prepare statement init failed");
            System.err.println(e.getMessage());
        }
        return null;
    }
    private <T> T packCarAccessoriesObj(Class<T> clazz) throws IllegalAccessException, InstantiationException, SQLException {
        T t=clazz.newInstance();
        //获取所有的私有属性
        Field[] fields=clazz.getDeclaredFields();
        for(Field f :fields){
            //设置属性值
            f.setAccessible(true);
            String fieldName=f.getName();
            if("id".equals(fieldName)){
                f.set(t,rs.getInt("id"));
            }else if("brand".equals(fieldName)){
                f.set(t,rs.getString(fieldName));
            }else if("accessoriesName".equals(fieldName)) {
                f.set(t,rs.getString("accessories_name"));
            }else if("availability".equals(fieldName)){
                f.set(t,new Boolean(rs.getBoolean(fieldName)));
            }else if("price".equals(fieldName)){
                f.set(t,new Float(rs.getFloat(f.getName())));
            }
        }
        return t;
    }


    private <T> T packCarObj(Class<T> clazz) throws InstantiationException, IllegalAccessException, SQLException {
        T t=clazz.newInstance();
        //获取所有的私有属性
        Field[] fields=clazz.getDeclaredFields();
        for(Field f :fields){
            //设置属性值
            String fieldName=f.getName();
            f.setAccessible(true);
            if("id".equals(fieldName)){
                f.set(t,rs.getInt("id"));
            }else if("brand".equals(fieldName) || "type".equals(fieldName)){
                f.set(t,rs.getString(fieldName));
            }else if("price".equals(fieldName)){
                f.set(t,rs.getFloat(fieldName));
            }
        }
        return t;
    }
    public void close(){
        try{
            if(rs!=null){
                rs.close();
            }
            if(pstm!=null){
                pstm.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            rs=null;
            pstm=null;
            conn=null;
            System.out.println("close connection failed");
            System.err.println(e.getMessage());
        }
    }
}