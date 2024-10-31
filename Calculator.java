import javax.swing.*;
import java.awt.*;

/**
 *계산기 기능을 제공하는 Calculator 클래스입니다.
 *
 * @author Jeon Soomin
 * @version 1.0
 * @since 1.0
 */

public class Calculator extends JFrame {

    /** 입력 받은 값을 표시하는 텍스트필드 */
    JTextField display;
    /** 계산기 버튼을 저장하는 배열 */
    JButton[] btn;
    /** 각 버튼에 넣을 키 값을 저장하는 배열 */
    String[] key = {"←", "CE", "C", "", "=",
            "7", "8", "9", "%", "÷",
            "4", "5", "6", "x²", "×",
            "1", "2", "3", "√x", "-",
            "+/-", "0", ".", "1/x", "+"};

    /**
     * @param operator 사용자가 선택한 연산자를 저장하는 변수
     * @param result 계산 결과를 저장하는 변수
     */
    private String operator;
    private double result;

    /** 기본 생성자
     * 외형 및 기능 초기화와 구성
     */
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
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton(key[i]);
            btn[i].setBackground(Color.white);
            btn[i].setFont(new Font("SansSerif", Font.BOLD, 15));

            /** 마우스 커서 올릴 때 툴팁 표기 */
            btn[i].setToolTipText(getTooltip(key[i]));

            /**
             * 버튼 클릭 이벤트 리스너
             * - 일반 숫자 입력
             * - 소수점 찍기, 백스페이스(하나 지우기), 부호 변경
             * - 연산자 입력, 제곱, 역수, 제곱근 계산
             * - 계산 결과 출력
             * - 초기화(텍스트 필드)
             * - 전체 초기화
             *
             * @param btnStr 클릭한 버튼의 레이블
             */
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
                    operator = btnStr;
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

            /**
             * 버튼 색상 지정
             * - 1열 : 연산 초기화 및 계산 버튼(진한 노란색)
             * -2~5열
             *  - 1~3행 : 숫자 버튼(하얀색)
             *  - 4~5행 : 연산자 버튼(연한 노란색)
             */
            if (i >= 0 && i <= 4) { // 이거 지피티한테 물어봄
                btn[i].setBackground(new Color(255,217,77));
            }
            else if (i % 5 == 3 || i % 5 == 4)
                btn[i].setBackground(new Color(255,232,128));

            btnPanel.add(btn[i]);
        }

        add(btnPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * 툴팁 내용 지정
     * @param label 버튼 레이블
     * @return 레이블 별 툴팁 내용
     */
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

    /** 메인 메서드
     * 계산기 프로그램 실행
     *
     * @param args 실행 인수
     */

    public static void main(String[] args) {
        new Calculator();
    }
}