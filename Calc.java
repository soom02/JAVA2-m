import java.awt.*;
import javax.swing.*;

/**
 * 외형 구현 - 비교적 간단한 기능 구현(1 누르면 1 뜸 이런 거) - 계산 기능 구현 - 복잡한 기능 구현 - 세부기능 구현 - 디자인 기능 점검
 * 현 상황에서 고치고 싶은 거 : 텍스트필드 좀 늘리고 위에 첫줄도 배경색 바꾸기
 * 디자인적 요소 : 아이콘, 그냥 지금 디자인 자체가 ㅂㄹ임 5x5가 문제 같은데..
 * 기능 : 숫자 입력, 점(유리수? 어쨌든 소수 만들기), 사칙연산, 나머지계산, 제곱, 제곱근, 분수(이거 머라고 함? 분모화?), 하나만 지우기, (지금 입력한) 숫자만 지우기, 전부 지우기
 * 세부 기능 : + 버튼에 마우스 올리면 덧셈이에요~ 이런 안내문구, 키보드 입력도 가능!, 엥? 또 잇엇는데 머엿지
 * 고민할 거 : 기능을 좀 빼고 다른 부분 퀄리티 높이기.. (ex.사칙연산만 지원하는 대신 메모리얼 기능 추가, < 할 수 잇나?)
 **/


public class Calc extends JFrame{
    JTextField text;
    JButton[] button;
    String[] key = {"←", "CE", "C", "", "=",
            "7", "8", "9", "%", "÷",
            "4", "5", "6", "x²", "×",
            "1", "2", "3", "√x", "-",
            "+/-", "0", ".", "1/x", "+"};

    // 사용할 색상 - 꼭 이렇게 정할 필요가 잇나? 몰루
    Color white = new Color(242,242,242);
    Color lightgreen = new Color(204,240,221);
    Color green = new Color(106,168,148);
    Color orange = new Color(255,209,187);
    Color lavender = new Color(213,193,222);

    Calc() {
        text = new JTextField();
        getContentPane().add(text, BorderLayout.NORTH);
        text.setBackground(white);
        text.setText("0");
        text.setEditable(false); // 입력 불가 및 글자 색상 하얀색 고정 setEnabled(false), 입력 불가 및 글자 색상 변경 setEditable(false)
        text.setForeground(Color.GRAY);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 5, 2, 2));

        button = new JButton[25];
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(key[i]);
            button[i].setBackground(lightgreen);
            button[i].setFont(new Font("SansSerif", Font.BOLD, 15)); // 볼드체 지정을 위해서 글씨체랑 크기도 지정해야 함

            if (i % 5 == 3 || i % 5 == 4)
                 button[i].setBackground(green);

            panel.add(button[i]);
        }

        setTitle("계산기");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Calc();
    }
}