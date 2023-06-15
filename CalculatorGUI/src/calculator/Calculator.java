package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator{
    public JTextField getTextField1() {
        return textField1;
    }
    private JTextField textField1;
    private JButton minusBtn;
    private JButton mulBtn;
    private JButton threeBtn;
    private JButton twoBtn;
    private JButton sixBtn;
    private JButton fiveBtn;
    private JButton nineBtn;
    private JButton percentageBtn;
    private JButton logBtn;
    private JButton eightBtn;
    private JButton signBtn;
    private JButton powerBtn;
    private JButton oneBtn;
    private JButton fourBtn;
    private JButton sevenBtn;
    private JButton clearBtn;
    private JButton squareBtn;
    private JButton zeroBtn;
    private JButton digitBtn;
    private JButton divideBtn;
    private JButton addBtn;
    private JButton equalBtn;
    private JButton sqrBtn;
    private JPanel calculator;

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    private double num1;

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    private double num2;

    public char getOperand() {
        return operand;
    }

    public void setOperand(char operand) {
        this.operand = operand;
    }

    private char operand;

    public boolean isClearField() {
        return clearField;
    }

    public void setClearField(boolean clearField) {
        this.clearField = clearField;
    }

    private boolean clearField = true;

    /**
     * Constructor of Calculator. Initializes all buttons to be listened to.
     */
    public Calculator(){
        this.textField1.setText("0");
        oneBtn.addActionListener(new oneBtnClicked(this));
        twoBtn.addActionListener(new twoBtnClicked(this));
        threeBtn.addActionListener(new threeBtnClicked(this));
        fourBtn.addActionListener(new fourBtnClicked(this));
        fiveBtn.addActionListener(new fiveBtnClicked(this));
        sixBtn.addActionListener(new sixBtnClicked(this));
        sevenBtn.addActionListener(new sevenBtnClicked(this));
        eightBtn.addActionListener(new eightBtnClicked(this));
        nineBtn.addActionListener(new nineBtnClicked(this));
        zeroBtn.addActionListener(new zeroBtnClicked(this));
        divideBtn.addActionListener(new divideBtnClicked(this));
        mulBtn.addActionListener(new mulBtnClicked(this));
        addBtn.addActionListener(new addBtnClicked(this));
        minusBtn.addActionListener(new minusBtnClicked(this));
        sqrBtn.addActionListener(new sqrBtnClicked(this));
        equalBtn.addActionListener(new equalBtnClicked(this));
        squareBtn.addActionListener(new squareBtnClicked(this));
        powerBtn.addActionListener(new powerBtnClicked(this));
        clearBtn.addActionListener(new clearBtnClicked(this));
        percentageBtn.addActionListener(new percentageBtnClicked(this));
        signBtn.addActionListener(new signBtnClicked(this));
        digitBtn.addActionListener(new digitBtnClicked(this));
        logBtn.addActionListener(new logBtnClicked(this));
    }

    /**
     * Initializes the Calculator instance and bind it to the form.
     * @param args Main function argument.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        Calculator myCal = new Calculator();
        frame.setContentPane(myCal.calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // test addition
        System.out.println("Testing addition");
        myCal.test("1");
        myCal.test("+");
        myCal.test("2");
        myCal.test("=");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 3");

        //test subtraction
        System.out.println("Testing subtraction");
        myCal.test("1");
        myCal.test(".");
        myCal.test("2");
        myCal.test("-");
        myCal.test("2");
        myCal.test("=");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: -0.8");

        //test multiplication
        System.out.println("Testing multiplication");
        myCal.test("1");
        myCal.test("*");
        myCal.test("2");
        myCal.test("=");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 2");

        //test division
        System.out.println("Testing multiplication");
        myCal.test("1");
        myCal.test(".");
        myCal.test("2");
        myCal.test("/");
        myCal.test("2");
        myCal.test("=");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 0.6");

        //test power
        System.out.println("Testing power");
        myCal.test("2");
        myCal.test("**");
        myCal.test("3");
        myCal.test("=");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 8");

        //test square
        System.out.println("Testing square");
        myCal.test("2");
        myCal.test("*2");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 4");

        //test percentage
        System.out.println("Testing percentage");
        myCal.test("1");
        myCal.test("%");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 0.01");

        //test square root
        System.out.println("Testing square root");
        myCal.test("4");
        myCal.test("sqr");
        myCal.test("txt");
        myCal.test("AC");
        System.out.println("The reference result is: 2");
    }
    public void test( String button){
        switch (button){
            case "0": zeroBtn.doClick();break;
            case "1": oneBtn.doClick();break;
            case "2": twoBtn.doClick();break;
            case "3": threeBtn.doClick();break;
            case "4": fourBtn.doClick();break;
            case "5": fiveBtn.doClick();break;
            case "6": sixBtn.doClick();break;
            case "7": sevenBtn.doClick();break;
            case "8": eightBtn.doClick();break;
            case "9": nineBtn.doClick();break;
            case "%": percentageBtn.doClick();break;
            case "-/+": signBtn.doClick();break;
            case "AC": clearBtn.doClick();break;
            case "*2": squareBtn.doClick();break;
            case "sqr": sqrBtn.doClick();break;
            case "log": logBtn.doClick();break;
            case ".": digitBtn.doClick();break;
            case "+": addBtn.doClick();break;
            case "-": minusBtn.doClick();break;
            case "*": mulBtn.doClick();break;
            case "/": divideBtn.doClick();break;
            case "**": powerBtn.doClick();break;
            case "=": equalBtn.doClick();break;
            case "txt": System.out.println("The result is: " + textField1.getText());break;
            default:System.out.println("invalid input");break;
        }
    }
}

abstract class numberBtnClicked implements ActionListener {
    JTextField tf;
    Calculator c;

    /**
     * Abstraction of the action button being clicked.
     * @param c: Calculator instance. For manipulation of some variables inside Calculator.
     */
    numberBtnClicked(Calculator c) {
        this.tf = c.getTextField1();
        this.c = c;
    }
    public abstract void actionPerformed(ActionEvent e);
}

class oneBtnClicked extends numberBtnClicked {
    oneBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("1"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(1)));
    }
}

class twoBtnClicked extends numberBtnClicked {
    twoBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("2"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(2)));
        }
}

class threeBtnClicked extends numberBtnClicked {
    threeBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("3"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(3)));
    }
}

class fourBtnClicked extends numberBtnClicked {
    fourBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("4"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(4)));
    }
}

class fiveBtnClicked extends numberBtnClicked {
    fiveBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("5"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(5)));
    }
}

class sixBtnClicked extends numberBtnClicked {
    sixBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("6"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(6)));
    }
}

class sevenBtnClicked extends numberBtnClicked {
    sevenBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("7"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(7)));
    }
}

class eightBtnClicked extends numberBtnClicked {
    eightBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("8"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(8)));
    }
}

class nineBtnClicked extends numberBtnClicked {
    nineBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("9"); c.setClearField(false);}
        else tf.setText(tf.getText().concat(String.valueOf(9)));
    }
}

class zeroBtnClicked extends numberBtnClicked {
    zeroBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(c.isClearField()){ tf.setText("0"); c.setClearField(true);}
        else if(!tf.getText().equals("0")) tf.setText(tf.getText().concat(String.valueOf(0)));
    }
}

class divideBtnClicked extends numberBtnClicked {
    divideBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e){
        if(tf.getText().equals("Error.")) return;
        c.setNum1(Double.parseDouble(tf.getText()));
        c.setOperand('/');
        c.setClearField(true);
    }
}

class mulBtnClicked extends numberBtnClicked {
    mulBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        c.setNum1(Double.parseDouble(tf.getText()));
        c.setOperand('*');
        c.setClearField(true);
    }
}

class minusBtnClicked extends numberBtnClicked {
    minusBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        c.setNum1(Double.parseDouble(tf.getText()));
        c.setOperand('-');
        c.setClearField(true);
    }
}

class addBtnClicked extends numberBtnClicked {
    addBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        c.setNum1(Double.parseDouble(tf.getText()));
        c.setOperand('+');
        c.setClearField(true);
    }
}

class equalBtnClicked extends numberBtnClicked {
    equalBtnClicked(Calculator c){
        super(c);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        c.setNum2(Double.parseDouble(tf.getText()));
        double result;
        switch(c.getOperand()){
            case '^':
                result = Math.pow(c.getNum1(), c.getNum2());
                if((int)result == result) tf.setText(String.valueOf((int)result));
                else tf.setText(String.valueOf(result));
                break;
            case '*':
                result = c.getNum1()*c.getNum2();
                if((int)result == result) tf.setText(String.valueOf((int)result));
                else tf.setText(String.valueOf(result));
                break;
            case '-':
                result = c.getNum1()-c.getNum2();
                if((int)result == result) tf.setText(String.valueOf((int)result));
                else tf.setText(String.valueOf(result));
                break;
            case '+':
                result = c.getNum1()+c.getNum2();
                if((int)result == result) tf.setText(String.valueOf((int)result));
                else tf.setText(String.valueOf(result));
                break;
            case '/':
                if(c.getNum2() == 0){
                    tf.setText("Error.");
                }else{
                    result = c.getNum1() / c.getNum2();
                    if ((int) result == result) tf.setText(String.valueOf((int) result));
                    else tf.setText(String.valueOf(result));
                }
                break;
        }
        c.setOperand('0');
        c.setClearField(true);
    }
}

class sqrBtnClicked extends numberBtnClicked {
    sqrBtnClicked(Calculator c) {super(c);}
    @Override
    public void actionPerformed(ActionEvent e){
        if(tf.getText().equals("Error.")) return;
        double num1 = Double.parseDouble(tf.getText());
        c.setClearField(true);
        if(num1 < 0){
            tf.setText("Error.");
        }else{
            double result = Math.sqrt(num1);
            tf.setText((int) result == result? String.valueOf((int)result): String.valueOf(result));
        }
    }
}

class squareBtnClicked extends numberBtnClicked {
    squareBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e){
        if(tf.getText().equals("Error.")) return;
        double num1 = Double.parseDouble(tf.getText());
        c.setClearField(true);
        double result = num1*num1;
        tf.setText((int) result == result? String.valueOf((int)result): String.valueOf(result));
    }
}

class powerBtnClicked extends numberBtnClicked {
    powerBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        c.setNum1(Double.parseDouble(tf.getText()));
        c.setOperand('^');
        c.setClearField(true);
    }
}

class percentageBtnClicked extends numberBtnClicked {
    percentageBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        double num1 = Double.parseDouble(tf.getText());
        c.setClearField(true);
        double result = num1*0.01;
        tf.setText((int) result == result? String.valueOf((int)result): String.valueOf(result));
    }
}

class logBtnClicked extends numberBtnClicked {
    logBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        double num1 = Double.parseDouble(tf.getText());
        c.setClearField(true);
        double result = Math.log(num1);
        if(result != result || 1/result == 0) tf.setText("Error."); // Test if result is NaN (Not a number) or infinity
        else tf.setText((int) result == result? String.valueOf((int)result): String.valueOf(result));
    }
}

class signBtnClicked extends numberBtnClicked {
    signBtnClicked(Calculator c) {super(c);}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("0") || tf.getText().equals("Error.")) return;
        if(tf.getText().charAt(0) == '-') tf.setText(tf.getText().substring(1));
        else tf.setText("-" + tf.getText());

    }
}

class clearBtnClicked extends numberBtnClicked {
    clearBtnClicked(Calculator c) {
        super(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tf.setText("0");
        c.setClearField(true);
    }
}

class digitBtnClicked extends numberBtnClicked {
    digitBtnClicked(Calculator c) {
        super(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tf.getText().equals("Error.")) return;
        if(c.isClearField()){ tf.setText("0."); c.setClearField(false);}
        else if(!tf.getText().contains(".")) tf.setText(tf.getText().concat("."));
    }
}



