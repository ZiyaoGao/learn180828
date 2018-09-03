package zhengyuan.com.learn;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.util.List;
import java.util.Stack;

public class SecondActivity extends AppCompatActivity {
    private ConstraintLayout cs1;
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        cs1 = findViewById(R.id.cons1);
        bt1 = findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changebackgrondcolor(cs1);
            }
        });
        bt2 = findViewById(R.id.bu2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changebackgrondcolor2(cs1);
            }
        });
    }
    public void changebackgrondcolor(ViewGroup v){
        Stack<ViewGroup> stack = new Stack<>();
        
            for(int i=0;i<v.getChildCount();i++)
                if(v.getChildAt(i) instanceof Button)
                    v.getChildAt(i).setBackgroundColor(Color.RED);
                else if(v.getChildAt(i) instanceof ViewGroup)
                    stack.push((ViewGroup) v.getChildAt(i));
            while (!stack.isEmpty()){
                ViewGroup vg = stack.pop();
                for(int i=0;i<vg.getChildCount();i++)
                    if(vg.getChildAt(i) instanceof Button)
                        vg.getChildAt(i).setBackgroundColor(Color.RED);
                    else if(vg.getChildAt(i) instanceof ViewGroup)
                        stack.push((ViewGroup) vg.getChildAt(i));
            }


    }
    public void changebackgrondcolor2(ViewGroup v){
        Stack<ViewGroup> stack = new Stack<>();
        for(int i=0;i<v.getChildCount();i++)
            if(v.getChildAt(i) instanceof Button)
                v.getChildAt(i).setBackgroundColor(Color.GRAY);
            else if(v.getChildAt(i) instanceof ViewGroup)
                stack.push((ViewGroup) v.getChildAt(i));
        while (!stack.isEmpty()){
            ViewGroup vg = stack.pop();
            for(int i=0;i<vg.getChildCount();i++)
                if(vg.getChildAt(i) instanceof Button)
                    vg.getChildAt(i).setBackgroundColor(Color.GRAY);
                else if(vg.getChildAt(i) instanceof ViewGroup)
                    stack.push((ViewGroup) vg.getChildAt(i));
        }
    }
}
