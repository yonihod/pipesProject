package view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

class MyButton extends Button {
    MyButton(String str) {
        super(str);
        setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);


        Color color;

        switch (str.charAt(0)) {
            case 's':
                color = javafx.scene.paint.Color.rgb(0, 242, 148);
                break;
            case 'g':
                color = javafx.scene.paint.Color.rgb(255,58,59);
                break;

            default:
                color = Color.TRANSPARENT;
        }
        color = javafx.scene.paint.Color.rgb(193,200,209);
        setTextFill(color);
    }
}