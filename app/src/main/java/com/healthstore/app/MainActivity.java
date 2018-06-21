package com.healthstore.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.btn_test_1) Button btnTest1;
//    @BindView(R.id.btn_test_2) Button btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_test_1, R.id.btn_test_2})
    void onClickBtn(Button btn){
        btn.setText(btn.getId()+"");
    }

}
