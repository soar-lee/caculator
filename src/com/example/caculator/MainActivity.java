package com.example.caculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	Button btn_zero; //数字按钮
	Button btn_one; //数字按钮
	Button btn_two; //数字按钮
	Button btn_three; //数字按钮
	Button btn_four; //数字按钮
	Button btn_five; //数字按钮
	Button btn_six; //数字按钮
	Button btn_seven; //数字按钮
	Button btn_eight; //数字按钮
	Button btn_nine; //数字按钮
	Button btn_point; //数字按钮
	Button btn_clear; //数字按钮
	Button btn_del; //数字按钮
	Button btn_add; //数字按钮
	Button btn_substract; //数字按钮
	Button btn_multiply; //数字按钮
	Button btn_divide; //数字按钮
	Button btn_equal; //数字按钮
	EditText et_input; //显示屏
	
	Boolean clear_flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//给按钮设置点击事件
		Button[] btn ={btn_zero,btn_one,btn_two,btn_three,btn_four,btn_five,
				btn_six,btn_seven,btn_eight,btn_nine,btn_point,btn_clear,btn_del,
				btn_add,btn_substract,btn_multiply,btn_divide,btn_equal};
		int[] btnId ={R.id.btn_zero,R.id.btn_one,R.id.btn_two,R.id.btn_three,R.id.btn_four,R.id.btn_five,
				R.id.btn_six,R.id.btn_seven,R.id.btn_eight,R.id.btn_nine,R.id.btn_point,R.id.btn_clear,R.id.btn_del,
				R.id.btn_add,R.id.btn_substract,R.id.btn_multiply,R.id.btn_divide,R.id.btn_equal};
		
		for(int i=0; i<btn.length; i++){
			btn[i] = (Button) findViewById(btnId[i]);
			btn[i].setOnClickListener(this);
		}
		et_input = (EditText) findViewById(R.id.et_input);
		et_input.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String str = et_input.getText().toString();
		switch(v.getId()){
			case R.id.btn_zero:
			case R.id.btn_one:
			case R.id.btn_two:
			case R.id.btn_three:
			case R.id.btn_four:
			case R.id.btn_five:
			case R.id.btn_six:
			case R.id.btn_seven:
			case R.id.btn_eight:
			case R.id.btn_nine:
			case R.id.btn_point:
				if(str.equals("0")){
					et_input.setText(((Button)v).getText());
				}else if(clear_flag && !str.contains(" ")){
					et_input.setText(((Button)v).getText());
					clear_flag = false;
				}else{
					et_input.setText(str + ((Button)v).getText());
				}
				break;
			case R.id.btn_add:
			case R.id.btn_substract:
			case R.id.btn_multiply:
			case R.id.btn_divide:
				if(str.contains(" ")){
					setResult();
				}
				str = et_input.getText().toString();
				et_input.setText(str+" "+((Button)v).getText()+" ");
				break;
			case R.id.btn_del:
				if(str!=null && !str.equals("") ){
					et_input.setText(str.substring(0, str.length()-1));
				}
				break;
			case R.id.btn_clear:
				et_input.setText("");
				break;
			case R.id.btn_equal:
				setResult();
				break;
			default:break;
		}
	}

	//运算结果
	private void setResult() {
		// TODO Auto-generated method stub
		String exp = et_input.getText().toString();
		if(exp!=null && !exp.equals("") && exp.contains(" ")){
			String s1 = exp.substring(0, exp.indexOf(" "));	//前面的字符串
			String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2);
			String s2 = exp.substring(exp.indexOf(" ")+3);
			
			if(s1!=null&&!s1.equals("")&&op!=null&&!op.equals("")&&s2!=null&&!s2.equals("")){
				double d1 = Double.parseDouble(s1);
				double d2 = Double.parseDouble(s2);
				
				double result = 0;
				if(op.equals("+")){
					result = d1 + d2;
				}else if(op.equals("-")){
					result = d1 - d2;
				}else if(op.equals("×")){
					result = d1 * d2;
				}else if(op.equals("÷")){
					if(d2 != 0){
						result = d1 / d2;
					}
				}
				clear_flag = true;
				if((int)result == result){
					et_input.setText((int)result+"");
				}else{
					et_input.setText(result+"");
				}
			}
		}
	}

}
