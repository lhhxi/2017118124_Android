package cn.edu.hstc.cs.ljc;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;

    //行视图，⑥Adapter创建空的itemView
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView; //容器元素（看不见的）
        ImageView fruitImage;
        EditText fruitName;
        TextView fruitId;
        Button deteleButton;
        Button updateButton;
        Button button2;

        public ViewHolder(View view){
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruitImage);
            fruitName = (EditText)view.findViewById(R.id.fruitName);
            fruitId=(TextView) view.findViewById(R.id.fruitId);
            deteleButton=view.findViewById(R.id.deleteButton);
            updateButton=view.findViewById(R.id.updateButton);
            button2=view.findViewById(R.id.button2);
        }
    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList = fruitList;  //水果列表
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){ //创建ViewHolder实例
        //将fruit_item布局加载进来
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.deteleButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                //Fruit fruit = mFruitList.get(position);
                mFruitList.remove(position);
                notifyItemRemoved(position);//刷新被删除的地方
                notifyItemRangeChanged(position,getItemCount()); //刷新被删除数据，以及其后面的数据
            }
        });
        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.fruitName.setFocusableInTouchMode(true);
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                fruit.setName(holder.fruitName.getText().toString());
               // mFruitList.get(position).setName(holder.fruitName.getText().toString());
                mFruitList.set(position,fruit);
                holder.button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        int position = holder.getAdapterPosition();
                        notifyItemChanged(position);
                        Log.v("1",mFruitList.get(position).getName());
                    }
                });


                //fruit.setName();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){  //绑定对RecyclerView子项的数据进行赋值
        Fruit fruit = mFruitList.get(position); //position指水果队列中的当前项的下标，用来得到当前项的Fruit实例
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitId.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount(){
        return mFruitList.size();  //⑤Adapter告诉RecycleView有多少数据要显示
    }

    // 添加数据
    public void addData(int position) {
    //在list中添加数据，并通知条目加入一条
        Fruit fruit = new Fruit("banana1",R.drawable.apple_pic);
        mFruitList.add(position,fruit);
        //添加动画
        notifyItemInserted(position);
    }

}




