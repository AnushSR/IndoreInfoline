package com.indore.indoreinfoline.viewholder;

/**
 * Created by Harshit on 4/17/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indore.indoreinfoline.R;
import com.indore.indoreinfoline.model.DataBean;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyViewHolder> {

    private List<DataBean> busList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView source, destination, routeno, time;

        public MyViewHolder(View view) {
            super(view);
            source = (TextView) view.findViewById(R.id.source);
            destination = (TextView) view.findViewById(R.id.destination);
            routeno = (TextView) view.findViewById(R.id.routeno);
            time = (TextView) view.findViewById(R.id.time);
        }
    }


    public BusAdapter(List<DataBean> busList) {
        this.busList = busList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataBean dataBean = busList.get(position);
        holder.source.setText(dataBean.getSource());
        holder.destination.setText(dataBean.getDestination());
        holder.routeno.setText(String.valueOf(dataBean.getRouteno()));
        holder.time.setText(dataBean.getTime());
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }
}
