package cmu.miot.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private Boolean numPressed,dotpressed,error;
    private Float numberValue;
    private String operands = "/*+-";
    private int[] numButtons = {R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,R.id.eight,R.id.nine,R.id.zero};
    private int[] opButtons = {R.id.sub,R.id.mul,R.id.div,R.id.sum};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.display = (TextView) findViewById(R.id.display);
        error = false;numPressed=false;dotpressed=false;
        setNumberClickListeners();
        setOperatorClickListeners();

    }
    private void setNumberClickListeners(){
        View.OnClickListener numListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)	{
                if(error){
                    display.setText("");
                    error = false;
                }
                else {
                    Button button = (Button) v;
                    display.append(button.getText());
                    numPressed = true;
                }
            }
        };
        for	(int	id	:	numButtons)	{
            findViewById(id).setOnClickListener(numListener);
        }

    }
    private void setOperatorClickListeners(){
        View.OnClickListener oprListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)	{
                if(numPressed && !error){

                Button button = (Button) v;
                display.append(button.getText());
                    numPressed = false;
                    dotpressed = false;
                }

            }
        };
        for	(int id	: opButtons)	{
            findViewById(id).setOnClickListener(oprListener);
        }

        //clearButton
        findViewById(R.id.clear).setOnClickListener( new View.OnClickListener(){
            @Override
            public void	onClick(View v){

                display.setText("");
                numPressed = false;
                error = false;
                dotpressed = false;
            }
        });

        //clearButton
        findViewById(R.id.del).setOnClickListener( new View.OnClickListener(){
            @Override
            public void	onClick(View v){
                String Value = display.getText().toString();
                Character lastChar = Value.charAt(Value.length()-1);
                if(Value.length()>0) {
                    display.setText(Value.substring(0, Value.length() - 1));
                    if(operands.contains(lastChar.toString())){
                        numPressed = true;
                        display.getText().toString().split("[+-/*]]");
                        if(Value.substring(0, Value.length() - 1).lastIndexOf('.')>0){

                        }
                    }
                    if(lastChar.equals('.')){
                        dotpressed = false;
                    }
                }
            }
        });

        findViewById(R.id.dot).setOnClickListener( new View.OnClickListener(){
            @Override
            public void	onClick(View v){
                if(!dotpressed && numPressed && !error){
                display.append(".");
                    dotpressed = true;
                    numPressed = false;
            }
            }
        });

    }

}
