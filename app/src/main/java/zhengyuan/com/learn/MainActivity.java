package zhengyuan.com.learn;

import android.animation.Animator;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private MyButton bt2;
    private ConstraintLayout cs1;
    private Handler hhandler;
    private Animator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hhandler = new Handler();
        setContentView(R.layout.activity_main);
        bt1 =  findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt2.startAnim();

                hhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNew();
                    }
                },3000);
            }
        });

        cs1 = findViewById(R.id.cons1);
        cs1.getBackground().setAlpha(0);
    }
    private void gotoNew() {
        bt2.gotoNew();

        final Intent intent=new Intent(this,ThirdActivityActivity.class);

        int xc=(bt2.getLeft()+bt2.getRight())/2;
        int yc=(bt2.getTop()+bt2.getBottom())/2;
        animator= ViewAnimationUtils.createCircularReveal(cs1,xc,yc,0,1111);
        animator.setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                hhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

                    }
                },200);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        cs1.getBackground().setAlpha(255);
    }

    @Override
    protected void onStop() {
        super.onStop();
        animator.cancel();
        cs1.getBackground().setAlpha(0);
        bt2.regainBackground();
    }
}
