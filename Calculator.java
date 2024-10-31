import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *계산기 프로그램 제작을 위한 클래스입니다.
 *
 * @version 1.0
 * @since 1.0
 *
 * @created
 */

public class Calculator extends JFrame {

    JTextField display;
    JButton[] btn;
    String[] key = {"←", "CE", "C", "", "=",
            "7", "8", "9", "%", "÷",
            "4", "5", "6", "x²", "×",
            "1", "2", "3", "√x", "-",
            "+/-", "0", ".", "1/x", "+"};

    private double num;
    private String operator;
    // private boolean isOperatorPressed = false;
    private double result;

    public Calculator() {
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());
        setTitle("계산기");
        setSize(400,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 50));
        display.setFont(new Font("SansSerif", Font.BOLD, 20));
        display.setBackground(Color.white);
        txtPanel.add(display, BorderLayout.CENTER);

        add(txtPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5,5, 1, 1));

        btn = new JButton[key.length];
        for (int i = 0; i < btn.length; i++) { // 안 쓰는 버튼을 아예 지우는 방법이 없나? 일단 컨티뉴 이거 안 됨
            btn[i] = new JButton(key[i]);
            btn[i].setBackground(Color.white);
            btn[i].setFont(new Font("SansSerif", Font.BOLD, 15));

            btn[i].setToolTipText(getTooltip(key[i]));

            btn[i].addActionListener(e -> {
                String btnStr = e.getActionCommand();
                if (btnStr.equals("0") || btnStr.equals("1") || btnStr.equals("2") ||
                btnStr.equals("3") || btnStr.equals("4") || btnStr.equals("5") ||
                btnStr.equals("6") || btnStr.equals("7") || btnStr.equals("8") ||
                btnStr.equals("9"))
                    display.setText(display.getText() + btnStr);

                if (btnStr.equals("."))
                    display.setText(display.getText() + ".");
                if (btnStr.equals("←"))
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                if (btnStr.equals("+/-")) {
                    if(display.getText().charAt(0) == ('+')) {
                        display.setText("-" + display.getText().substring(1,display.getText().length()));
                        } else if(display.getText().charAt(0) == '-') {
                            display.setText("+" + display.getText().substring(1,display.getText().length()));
                            } else {
                        display.setText("-" + display.getText());
                    }
                }

                if (btnStr.equals("+") || btnStr.equals("-") || btnStr.equals("×") ||
                        btnStr.equals("÷") || btnStr.equals("x²") || btnStr.equals("%") ||
                        btnStr.equals("1/x") || btnStr.equals("√x")) {
                    operator = btnStr;		// 연산 누르면 연산자 저장 + 입력된 숫자 저장 
                    switch (operator) {
                        case "+":
                            result = Double.parseDouble(display.getText());
                            display.setText("");
                            break;
                        case "-":
                            result = Double.parseDouble(display.getText());
                            display.setText("");
                            break;
                        case "×":
                            result = Double.parseDouble(display.getText());
                            display.setText("");
                            break;
                        case "÷":
                            result = Double.parseDouble(display.getText());
                            display.setText("");
                            break;
                        case "%":
                            result = Double.parseDouble(display.getText());
                            display.setText("");
                            break;
                        case "x²":
                            result = Math.pow(Double.parseDouble(display.getText()), 2);
                            display.setText(String.valueOf(result));
                            break;
                        case "1/x":
                            result = 1 / Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                        case "√x":
                            result = Math.sqrt(Double.parseDouble(display.getText()));
                            display.setText(String.valueOf(result));
                            break;
                    }
                }
                // '=' 을 누르면 기억하고 있는 연산과 숫자로 결과 계산
                if (btnStr.equals("=")) {
                    switch (operator) {
                        case "+":
                            result = result + Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                        case "-":
                            result = result - Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                        case "×":
                            result = result * Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                        case "÷":
                            result = result / Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                        case "%":
                            result = result % Double.parseDouble(display.getText());
                            display.setText(String.valueOf(result));
                            break;
                    }
                }
                if (btnStr.equals("C")) {
                    result = 0;
                    display.setText("");
                }
                if (btnStr.equals("CE")) {
                    display.setText("");
                }
            });

            if (i >= 0 && i <= 4) { // 이거 지피티한테 물어봄
                btn[i].setBackground(new Color(255,217,77));
            }
            else if (i % 5 == 3 || i % 5 == 4)
                btn[i].setBackground(new Color(255,232,128));

            btnPanel.add(btn[i]);
        }

        btnPanel.setSize(200,200);
        add(btnPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private String getTooltip(String label) {
        switch (label) {
            case "←": return "한 글자 삭제";
            case "CE": return "현재값 초기화";
            case "C": return "전체 초기화";
            case "=": return "계산하기";
            case "+": return "더하기";
            case "-": return "빼기";
            case "×": return "곱하기";
            case "÷": return "나누기";
            case "%": return "나머지 계산";
            case "x²": return "제곱";
            case "√x": return "제곱근";
            case "+/-": return "부호 변경";
            case "1/x": return "역수";
            case ".": return "소수점 입력";
            default: return "숫자";
        }
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}