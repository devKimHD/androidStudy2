package com.kh.pratice12_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button btnInsert, btnDelete;
    List<ProductVo> list = new ArrayList<>();
    HelperSqlProduct helper;
    MyAdapter adapter;
    ProductDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIs();
        setListeners();
        helper = new HelperSqlProduct(this, "ex12_5DB.db", null, 1);
        dao = ProductDao.getInstance();
        dao.setHelper(helper);
        //한번 실행해야 함
        SQLiteDatabase db = helper.getWritableDatabase();
        db.close();
        //setLists();
        selectData();
        setAdapter();


    }

    private void selectData() {
        list = dao.selectData();
    }

    private void setAdapter() {

        adapter = new MyAdapter(this, R.layout.view_cell, list);
        listView.setAdapter(adapter);

    }

    private void setLists() {
        for (int i = 1; i < 5; i++) {
            ProductVo vo = new ProductVo(i, "장동건", "제품", i * 10);
            list.add(vo);
        }
    }


    private void setUIs() {
        listView = findViewById(R.id.listView);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void setListeners() {
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductVo vo = list.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                View dialogView = View.inflate(MainActivity.this, R.layout.update_view, null);
                EditText edtNumber = dialogView.findViewById(R.id.edtNumber);
                EditText edtName = dialogView.findViewById(R.id.edtName);
                EditText edtProduct = dialogView.findViewById(R.id.edtProduct);
                EditText edtCount = dialogView.findViewById(R.id.edtCount);
                edtNumber.setText(String.valueOf(vo.getNumber()));
                dialog.setView(dialogView);
                dialog.setNegativeButton("닫기", null);
                dialog.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String uname=edtName.getText().toString();
                            String product=edtProduct.getText().toString();
                            int count=Integer.parseInt(edtCount.getText().toString());
                            int num=Integer.parseInt(edtNumber.getText().toString());
                            ProductVo vo=new ProductVo(num,uname,product,count);
                            boolean sucess=dao.updateData(vo);
                            if(sucess){
                                list.set(position,vo);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(),"수정성공",Toast.LENGTH_SHORT)
                                        .show();
                                return;
                            }
                        Toast.makeText(getApplicationContext(),"수정실패",Toast.LENGTH_SHORT)
                                .show();

                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnInsert) {
            int nextsize = list.size() + 1;
            View view = View.inflate(this, R.layout.update_view, null);
            EditText edtNumber = view.findViewById(R.id.edtNumber);
            EditText edtName = view.findViewById(R.id.edtName);
            EditText edtProduct = view.findViewById(R.id.edtProduct);
            EditText edtCount = view.findViewById(R.id.edtCount);
            edtNumber.setText(String.valueOf(nextsize));
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Log.d("TAG", "onClick: "+which);

                    String uname = edtName.getText().toString();
                    String product = edtProduct.getText().toString();
                    int count = Integer.parseInt(edtCount.getText().toString());
                    ProductVo vo = new ProductVo(nextsize, uname, product, count);
                    boolean sucess = dao.insertData(vo);
                    if (sucess) {
                        list.add(vo);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "입력성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            dialog.setNegativeButton("닫기", null);


            dialog.setView(view);
            dialog.show();

        } else if (v == btnDelete) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.delete_view, null);
            EditText edtDelNumber=view.findViewById(R.id.edtDelNumber);
            dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        int num=Integer.parseInt(edtDelNumber.getText().toString());
                        int position=num-1;
                        boolean sucess=dao.deleteData(num);
                        if(sucess){
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "삭제성공", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    Toast.makeText(getApplicationContext(), "삭제실패", Toast.LENGTH_SHORT).show();

                }
            });
            dialog.setNegativeButton("취소",null);
            dialog.setView(view);
            dialog.show();
        }
    }
}