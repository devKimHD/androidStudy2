package com.kh.pratice12_5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    int xmlId;
    List<ProductVo> list;

    public MyAdapter(Context context, int xmlId, List<ProductVo> list) {
        this.context = context;
        this.xmlId = xmlId;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView ==null){
           convertView=View.inflate(context,xmlId,null);
       }
       TextView tvNumber=convertView.findViewById(R.id.tvNumber);
       TextView tvName=convertView.findViewById(R.id.tvName);
       TextView tvProduct=convertView.findViewById(R.id.tvProduct);
       TextView tvCount=convertView.findViewById(R.id.tvCount);

       tvNumber.setText(String.valueOf(list.get(position).getNumber()));
        tvName.setText(list.get(position).getName());
        tvProduct.setText(list.get(position).getProduct());
        tvCount.setText(String.valueOf(list.get(position).getCount()));
       return convertView;
    }
}
