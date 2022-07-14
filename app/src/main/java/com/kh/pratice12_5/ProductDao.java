package com.kh.pratice12_5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    HelperSqlProduct helper;
    private static ProductDao instance=new ProductDao();
    private ProductDao(){}
    public static ProductDao getInstance() {
        return instance;
    }
    public void setHelper(HelperSqlProduct helper){
        this.helper=helper;
    }

    public List<ProductVo> selectData(){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from prodtable";
        Cursor cursor=db.rawQuery(sql,null);
        List<ProductVo> list=new ArrayList<>();
        while(cursor.moveToNext()){
            int num=cursor.getInt(0);
            String uname=cursor.getString(1);
            String product=cursor.getString(2);
            int count=cursor.getInt(3);
            ProductVo vo=new ProductVo(num,uname,product,count);
            list.add(vo);
        }
        cursor.close();
        db.close();
        return list;
    }

    
    public boolean insertData(ProductVo vo){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="insert into prodtable(num,uname,product,count) values(?,?,?,?)";
        SQLiteStatement stmt=db.compileStatement(sql);
        stmt.bindLong(1, vo.getNumber());
        stmt.bindString(2,vo.getName());
        stmt.bindString(3,vo.getProduct());
        stmt.bindLong(4,vo.getCount());
        long count=stmt.executeInsert();
        stmt.close();
        db.close();
        if(count>0){return  true;}
        return false;
    }
    public boolean updateData(ProductVo vo){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="update prodtable set  uname=?, product=?, count=? where num=?";
        SQLiteStatement stmt=db.compileStatement(sql);
        stmt.bindString(1,vo.getName());
        stmt.bindString(2,vo.getProduct());
        stmt.bindLong(3,vo.getCount());
        stmt.bindLong(4,vo.getNumber());
        long count=stmt.executeUpdateDelete();
        stmt.close();
        db.close();
        if(count>0){return  true;}
        return false;
    }
    public boolean deleteData(int num){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="delete from prodtable where num=?";
        SQLiteStatement stmt=db.compileStatement(sql);
        stmt.bindLong(1,num);
        long count=stmt.executeUpdateDelete();
        stmt.close();
        db.close();
        if(count>0){return  true;}
        return false;
    }
}
