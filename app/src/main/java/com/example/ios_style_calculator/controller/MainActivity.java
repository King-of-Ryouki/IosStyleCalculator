package com.example.ios_style_calculator.controller;

import com.example.ios_style_calculator.model.calculation.Calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // * 初始化

        InitFindView(); // findViewById
        InitView();

        // InitFont();  // IOS 字體，但是格式會跑掉

        InitOnClick();
        InitOnTouch();

        calc.InitCalculation();
    }

    Calculation calc = new Calculation();

    // * Init

    private LinearLayout layout_display_;

    private Button btn_num_0_;
    private Button btn_num_1_;
    private Button btn_num_2_;
    private Button btn_num_3_;
    private Button btn_num_4_;
    private Button btn_num_5_;
    private Button btn_num_6_;
    private Button btn_num_7_;
    private Button btn_num_8_;
    private Button btn_num_9_;

    private Button btn_add_;
    private Button btn_sub_;
    private Button btn_mul_;
    private Button btn_div_;
    private Button btn_mod_;

    private Button btn_equal_;
    private Button btn_minus_;
    private Button btn_dot_;

    private Button btn_clear_;

    private TextView txt_num_;     // 顯示數字
    private TextView txt_history_; // 顯示歷史紀錄

    private void InitFindView() {

        layout_display_ = (LinearLayout) findViewById(R.id.display);

        btn_num_0_ = (Button) findViewById(R.id.btn_num_0);
        btn_num_1_ = (Button) findViewById(R.id.btn_num_1);
        btn_num_2_ = (Button) findViewById(R.id.btn_num_2);
        btn_num_3_ = (Button) findViewById(R.id.btn_num_3);
        btn_num_4_ = (Button) findViewById(R.id.btn_num_4);
        btn_num_5_ = (Button) findViewById(R.id.btn_num_5);
        btn_num_6_ = (Button) findViewById(R.id.btn_num_6);
        btn_num_7_ = (Button) findViewById(R.id.btn_num_7);
        btn_num_8_ = (Button) findViewById(R.id.btn_num_8);
        btn_num_9_ = (Button) findViewById(R.id.btn_num_9);

        btn_add_ = (Button) findViewById(R.id.btn_add);
        btn_sub_ = (Button) findViewById(R.id.btn_sub);
        btn_mul_ = (Button) findViewById(R.id.btn_mul);
        btn_div_ = (Button) findViewById(R.id.btn_div);
        btn_mod_ = (Button) findViewById(R.id.btn_mod);

        btn_equal_ = (Button) findViewById(R.id.btn_equal);
        btn_minus_ = (Button) findViewById(R.id.btn_minus);
        btn_dot_   = (Button) findViewById(R.id.btn_dot);

        btn_clear_ = (Button) findViewById(R.id.btn_clear);

        txt_num_     = (TextView) findViewById(R.id.display_input);
        txt_history_ = (TextView) findViewById(R.id.display_history);
    }

    private void InitView() {
        btn_clear_.setText("AC");

        txt_num_.setText("0");
        txt_history_.setText("");
    }

    // * OnClick

    private void InitOnClick() {

        btn_num_0_.setOnClickListener(OnClickNum);
        btn_num_1_.setOnClickListener(OnClickNum);
        btn_num_2_.setOnClickListener(OnClickNum);
        btn_num_3_.setOnClickListener(OnClickNum);
        btn_num_4_.setOnClickListener(OnClickNum);
        btn_num_5_.setOnClickListener(OnClickNum);
        btn_num_6_.setOnClickListener(OnClickNum);
        btn_num_7_.setOnClickListener(OnClickNum);
        btn_num_8_.setOnClickListener(OnClickNum);
        btn_num_9_.setOnClickListener(OnClickNum);

        btn_add_.setOnClickListener(OnClickOper);
        btn_sub_.setOnClickListener(OnClickOper);
        btn_mul_.setOnClickListener(OnClickOper);
        btn_div_.setOnClickListener(OnClickOper);
        btn_mod_.setOnClickListener(OnClickOper);

        btn_equal_.setOnClickListener(OnClickOper);
        btn_minus_.setOnClickListener(OnClickOper);
        btn_dot_.setOnClickListener(OnClickOper);

        btn_clear_.setOnClickListener(OnClickOper);
    }

    // 點擊數字
    private Button.OnClickListener OnClickNum = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_num_0: calc.InputNum('0'); break;
                case R.id.btn_num_1: calc.InputNum('1'); break;
                case R.id.btn_num_2: calc.InputNum('2'); break;
                case R.id.btn_num_3: calc.InputNum('3'); break;
                case R.id.btn_num_4: calc.InputNum('4'); break;
                case R.id.btn_num_5: calc.InputNum('5'); break;
                case R.id.btn_num_6: calc.InputNum('6'); break;
                case R.id.btn_num_7: calc.InputNum('7'); break;
                case R.id.btn_num_8: calc.InputNum('8'); break;
                case R.id.btn_num_9: calc.InputNum('9'); break;
            }

            // 顯示
            txt_num_.setText(calc.get_input_num_());
            btn_clear_.setText("C");
        }
    };

    // 點擊運算子
    private Button.OnClickListener OnClickOper = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                // 運算
                case R.id.btn_add: calc.Cale(" + "); break;
                case R.id.btn_sub: calc.Cale(" - "); break;
                case R.id.btn_mul: calc.Cale(" * "); break;
                case R.id.btn_div: calc.Cale(" / "); break;
                case R.id.btn_mod: calc.Cale(" % "); break;

                // Other
                case R.id.btn_equal: calc.Equal(); break;
                case R.id.btn_minus: calc.Minus(); break;
                case R.id.btn_dot: calc.Dot(); break;

                case R.id.btn_clear:
                    calc.InitCalculation();
                    InitView();
                    break;
            }

            // 顯示
            txt_history_.setText(calc.get_history_() + calc.get_oper_());
            txt_num_.setText(calc.get_input_num_());
        }
    };

    // * OnTouch

    private void InitOnTouch() {
        layout_display_.setOnTouchListener(OnTouchDeleteNum);
    }

    private View.OnTouchListener OnTouchDeleteNum = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            float start_x = 0;

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    start_x = event.getX();
                    break;

                case MotionEvent.ACTION_UP:
                    if (event.getX() - start_x != 0) {
                        calc.DeleteNum();
                    }
                    break;
            }

            txt_num_.setText(calc.get_input_num_());

            if (txt_num_.getText().equals("0")) {
                btn_clear_.setText("AC");
            }

            return true;
        }
    };

    /*

    // IOS 字體，但是格式會跑掉
    private void InitFont() {

        Typeface type_face = Typeface.createFromAsset(getAssets(), "IOS 經典黑體繁.ttf");

        btn_num_0_.setTypeface(type_face);
        btn_num_1_.setTypeface(type_face);
        btn_num_2_.setTypeface(type_face);
        btn_num_3_.setTypeface(type_face);
        btn_num_4_.setTypeface(type_face);
        btn_num_5_.setTypeface(type_face);
        btn_num_6_.setTypeface(type_face);
        btn_num_7_.setTypeface(type_face);
        btn_num_8_.setTypeface(type_face);
        btn_num_9_.setTypeface(type_face);

        btn_add_.setTypeface(type_face);
        btn_sub_.setTypeface(type_face);
        btn_mul_.setTypeface(type_face);
        btn_div_.setTypeface(type_face);
        btn_mod_.setTypeface(type_face);
        btn_equal_.setTypeface(type_face);

        btn_minus_.setTypeface(type_face);
        btn_dot_.setTypeface(type_face);
        btn_clear_.setTypeface(type_face);

        txt_num_.setTypeface(type_face);
        txt_history_.setTypeface(type_face);
    }

    */
}