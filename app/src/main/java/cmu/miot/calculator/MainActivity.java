package cmu.miot.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import	net.objecthunter.exp4j.Expression;
import	net.objecthunter.exp4j.ExpressionBuilder;

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
        //Button deButton = (Button) findViewById(R.id.del);
        //deButton.setVisibility(View.GONE);
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
                if(Value.length()>0){
                Character lastChar = Value.charAt(Value.length()-1);
                if(Value.length()>0) {
                    display.setText(Value.substring(0, Value.length() - 1));
                    if(operands.contains(lastChar.toString())){
                        numPressed = true;
                        String[] numberTokens = display.getText().toString().split("[^0-9.]");
                        if((numberTokens[numberTokens.length-1]).contains(".")){
                            dotpressed = true;
                        }
                    }
                    if(lastChar.equals('.')){
                        dotpressed = false;
                    }
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
        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
             evaluate();
            }
        });


    }

    private void evaluate(){
        if(numPressed && !error){
            String exp = display.getText().toString();
            Expression Exp = new ExpressionBuilder(exp).build();
            try{
                double result = Exp.evaluate();
                display.setText(Double.toString(result));
                dotpressed = true;
            }
            catch (Exception e){
                display.setText("Error");
                numPressed = false;
                error = true;
            }
        }
    }
    //oldMethodforManualevaluation
    /*
    private String evaluateArithmetic(String exp){
        try{
            //String exp = display.getText().toString();
            String[] numbers = exp.split("[^0-9.]");
            String[] operators = exp.split("[0-9.]");
            String Value = "";
            if(!exp.equals("error")){
            int emptyStrings = 0;
            for (String op : operators){
                if(!op.equals("")){
                    emptyStrings++;
                }
            }
            String[] validOperations = new String[emptyStrings];
            emptyStrings = 0;
            for (String op : operators){
                if(!op.equals("")){
                   validOperations[emptyStrings] = op;
                    emptyStrings++;
                }
            }
            if(numbers.length ==validOperations.length+1){
                //Do all divisions first
                for(String operands:validOperations){
                    if(operands.equals("/"))
                    {

                    }
                }
            }}
            else{
                return "error";
            }
        }
        catch (Exception e){
            display.setText("Error");
            error = true;
            numPressed = false;
        }
        return "";
    }*/

}
