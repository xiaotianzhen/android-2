package com.qianwang.slidingmenudemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qianwang.slidingmenudemo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sky on 2017/3/13.
 */

public class menuAdapter extends RecyclerView.Adapter<menuAdapter.ViewHodler> {


    private Context context;
    private List<Map<Object,Object>> mList=new ArrayList<Map<Object,Object>>();

    public menuAdapter(Context context, List<Map<Object,Object>> mList) {

        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, null);
        ViewHodler viewHodler = new ViewHodler(view);

        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        Map<Object,Object> map=mList.get(position);
        holder.tv_show.setText(String.valueOf(map.get("key")));
        holder.img_menu.setImageDrawable((Drawable) map.get("img"));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {


        private TextView tv_show;
        private ImageView img_menu;

        public ViewHodler(View itemView) {
            super(itemView);

            tv_show = (TextView) itemView.findViewById(R.id.tv_show);
            img_menu= (ImageView) itemView.findViewById(R.id.img_menu);

        }
    }
}
