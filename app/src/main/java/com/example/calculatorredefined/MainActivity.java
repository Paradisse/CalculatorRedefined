package com.example.calculatorredefined;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private TextView tvExpressionResult;
    private TextView tvExpression;
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvExpressionResult = (TextView) findViewById(R.id.tvExpressionResult);
        tvExpression = (TextView) findViewById(R.id.tvExpression);

        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

        String expressionText = calculatorViewModel.getExpression();
        String expressionResultText = calculatorViewModel.getExpressionResult();

        if (expressionText != null) {
            tvExpression.setText(expressionText);
        }
        if (expressionResultText != null)
            tvExpressionResult.setText(expressionResultText);
    }

    public void numberClicked(View view) {
        Button btnView = (Button) view;
        String numberEntered = btnView.getText().toString();

        tvExpression.setText(calculatorViewModel.changeExpression(numberEntered));
        tvExpressionResult.setText(calculatorViewModel.changeExpressionResult());
    }

    public void dotClicked(View view) {
        tvExpression.setText(calculatorViewModel.makeExpressionFractional());
    }

    public void eraseClicked(View view) {
        String expressionText = tvExpression.getText().toString();
        tvExpression.setText(calculatorViewModel.eraseOneCharFromExpression(expressionText));
        tvExpressionResult.setText(calculatorViewModel.changeExpressionResult());
    }

    public void acClicked(View view) {
        tvExpression.setText(calculatorViewModel.clearEntireExpression());
        tvExpressionResult.setText(calculatorViewModel.clearResult());
    }

    public void negateClicked(View view) {
        tvExpression.setText(calculatorViewModel.negateExpression());
        tvExpressionResult.setText(calculatorViewModel.changeExpressionResult());
    }

    public void addClicked(View view) {
        String updatedExpressionText = calculatorViewModel.getAdditionResult();
        String updatedExpressionResultText = calculatorViewModel.changeExpressionResult();

        if (updatedExpressionText != null)
            tvExpression.setText(updatedExpressionText);
        if (updatedExpressionResultText != null)
            tvExpressionResult.setText(updatedExpressionResultText);
    }

    public void subtractClicked(View view) {
        String updatedExpressionText = calculatorViewModel.getSubtractionResult();
        String updatedExpressionResultText = calculatorViewModel.changeExpressionResult();

        if (updatedExpressionText != null)
            tvExpression.setText(updatedExpressionText);
        if (updatedExpressionResultText != null)
            tvExpressionResult.setText(updatedExpressionResultText);
    }

    public void remainderClicked(View view) {
        String updatedExpressionText = calculatorViewModel.getRemainderResult();
        String updatedExpressionResultText = calculatorViewModel.changeExpressionResult();

        if (updatedExpressionText != null)
            tvExpression.setText(updatedExpressionText);
        if (updatedExpressionResultText != null)
            tvExpressionResult.setText(updatedExpressionResultText);
    }

    public void divideClicked(View view) {
        String updatedExpressionText = calculatorViewModel.getDivisionResult();
        String updatedExpressionResultText = calculatorViewModel.changeExpressionResult();

        if (updatedExpressionText != null)
            tvExpression.setText(updatedExpressionText);
        if (updatedExpressionResultText != null)
            tvExpressionResult.setText(updatedExpressionResultText);
    }

    public void multiplyClicked(View view) {
        String updatedExpressionText = calculatorViewModel.getMultiplyResult();
        String updatedExpressionResultText = calculatorViewModel.changeExpressionResult();

        if (updatedExpressionText != null)
            tvExpression.setText(updatedExpressionText);
        if (updatedExpressionResultText != null)
            tvExpressionResult.setText(updatedExpressionResultText);
    }

    public void evaluateClicked(View view) {
        String updatedExpressionText = calculatorViewModel.evaluateExpression();

        if (updatedExpressionText != null) {
            tvExpression.setText(updatedExpressionText);
            tvExpressionResult.setText(calculatorViewModel.clearResult());
        }
    }
}
