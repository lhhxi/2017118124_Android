package cn.edu.hstc.cs.ljc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits(); //初始化水果数据
        //①创建recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView); //获取recyclerView布局
        //②创建布局管理器，这里是线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //
        //③将定义好的布局管理器交给recycleView用
        recyclerView.setLayoutManager(layoutManager);
        //④创建adapter,将水果数据传入到FruitAdapter的构造函数中
        final FruitAdapter adapter = new FruitAdapter(fruitList);
        //调用RecyclerView的setAdapter()方法来完成适配器设置（在recycleView中装入Adapter
        recyclerView.setAdapter(adapter);
        addButton=findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adapter.addData(fruitList.size());
            }
            });
    }

    private void initFruits(){
        for (int i = 0;i < 2;i++){
            Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit orange = new Fruit("Orange",R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit banana = new Fruit("Banana",R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
}
