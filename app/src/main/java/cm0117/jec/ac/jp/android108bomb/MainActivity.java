package cm0117.jec.ac.jp.android108bomb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String fireStrA;
    String fireStrB;

    ImageView img;
    TextView msg;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //メッセージのインスタンス化
        msg = (TextView)findViewById(R.id.message);
        //リセットボタン
        resetBtn = (Button)findViewById(R.id.btnRst);
        resetBtn.setOnClickListener(new ResetBtnListerImpl());
        resetBtn.setEnabled(false);

        //画像
        img = (ImageView)findViewById(R.id.imgDynamite);


        //短いコード版、配列
        int[] btns = {R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
                R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,
                R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,
                R.id.btn14,R.id.btn15,R.id.btn16};

        for(int id : btns){
            Button b = (Button)findViewById(id);
            b.setOnClickListener(new BtnClickEvent());
        }
        //ランダムメソッドを呼ぶ
        getFireNumber();
    }
    private int nowState = 1;
    private int countSafe = 0;
    class BtnClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Button btn = (Button)v;
            if (btn.getText().toString().equals(fireStrA) || btn.getText().toString().equals(fireStrB)){

                switch ( nowState ){
                    case 1:
                        img.setImageResource(R.drawable.dynamite2);
                        Log.i("android108", "残り一回");
                        msg.setText("yabi");
                        break;
                    case 2:
                        img.setImageResource(R.drawable.dynamite3);
                        msg.setText("ゲームオーバー！");
                        resetBtn.setEnabled(true);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.dynamite1);
                        msg.setText("どれか押してみよう！");
                        nowState = 0;
                        break;
                }
                nowState++;
            }else{
                countSafe++;
                if (countSafe == 14){
                    msg.setText("You Win");
                    resetBtn.setEnabled(true);
                }
            }
            //押されたボタンを無効化する
            btn.setEnabled(false);
            Toast.makeText(MainActivity.this, btn.getText().toString() + "を押した",Toast.LENGTH_SHORT).show();
        }
    }
    //着火する番号を2つランダムで決めるメソッド
    void getFireNumber(){
        Random random = new Random();
        int fireA = random.nextInt(16) + 1;

        int fireB = -1;
        for (;;){
            fireB = random.nextInt(16) + 1;
            if (fireB != fireA){
                break;
            }
        }
        Log.i("android108","fireAは" + fireA + "fireBは" + fireB);
        fireStrA = String.valueOf(fireA);
        fireStrB = String.valueOf(fireB);
    }
    //リセットボタン、ハンドリング
    class ResetBtnListerImpl implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //リセットボタンが押された時の処理
            img.setImageResource(R.drawable.dynamite1);
            msg.setText("どれか押してみよう！");
            //状態保持用の変数を初期化
            nowState = 1;
            //乱数再設定
            getFireNumber();
            //リセットボタンを再び無効にする
            resetBtn.setEnabled(false);

            //
            int[] btns = {R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
                    R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,
                    R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,
                    R.id.btn14,R.id.btn15,R.id.btn16};
            for(int id : btns){
                Button b = (Button)findViewById(id);
                b.setEnabled(true);
            }

        }
    }

}
