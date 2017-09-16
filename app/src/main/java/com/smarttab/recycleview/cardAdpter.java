package com.smarttab.recycleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AshwinChauhan on 9/15/2017.
 */

public class cardAdpter extends RecyclerView.Adapter<cardAdpter.viewHolder>{

    ArrayList<getUserDetails> arrayList=new ArrayList<getUserDetails>();

    Activity activity;
    Context context;

    getUserDetails user;
    public cardAdpter(Context context, ArrayList<getUserDetails> arrayList)
    {

        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);

        return new viewHolder(view);


    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.email.setText(arrayList.get(position).getEmail());
        Picasso.with(context).load(Constant.ImagePath+arrayList.get(position).getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name,email;
        ImageView imageView;
        public viewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.name);
            email= (TextView) itemView.findViewById(R.id.email);
            imageView= (ImageView) itemView.findViewById(R.id.cardimage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,ShowActivity.class);
            intent.putExtra("name",name.getText().toString());
            intent.putExtra("email",email.getText().toString());
            intent.putExtra("image",arrayList.get(getAdapterPosition()).getImage());
            context.startActivity(intent);
        }
    }


}
