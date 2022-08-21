package de.lioker.passwordgenerator.gui;

import de.lioker.passwordgenerator.generator.Generator;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class WindowManager extends JFrame {

    private JSlider slider;
    private JButton generateButton;
    private JButton copyButton;
    private JTextField textField;
    private JLabel passwordLength;
    private JLabel lengthCheck;
    private JLabel numbersCheck;
    private JLabel upperCaseCheck;
    private JLabel lowerCaseCheck;
    private JLabel symbolsCheck;

    public void createWindow(){
        Generator generator = new Generator();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800,300);
        jFrame.setResizable(false);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        jFrame.setTitle("Password Generator");

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1270, 30));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(1270, 50));
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(180, 170));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        jFrame.getContentPane().setBackground(Color.decode("#050326"));
        topPanel.setBackground(Color.decode("#050326"));
        middlePanel.setBackground(Color.decode("#050326"));
        bottomPanel.setBackground(Color.decode("#070326"));

        passwordLength = new JLabel("Password length: 12");
        passwordLength.setForeground(Color.decode("#ffffff"));

        lengthCheck = new JLabel("8 or more characters");
        lengthCheck.setForeground(Color.decode("#ffffff"));

        numbersCheck = new JLabel("Contains numbers");
        numbersCheck.setForeground(Color.decode("#ffffff"));

        upperCaseCheck = new JLabel("Contains upper case letters");
        upperCaseCheck.setForeground(Color.decode("#ffffff"));

        lowerCaseCheck = new JLabel("Contains lower case letters");
        lowerCaseCheck.setForeground(Color.decode("#ffffff"));

        symbolsCheck = new JLabel("Contains symbols");
        symbolsCheck.setForeground(Color.decode("#ffffff"));

        textField = new JTextField("Password", 16);
        generateButton = new JButton("Generate");
        copyButton = new JButton("Copy");

        slider = new JSlider(JSlider.HORIZONTAL, 8, 50, 12);
        slider.setBackground(Color.decode("#050326"));
        slider.setForeground(Color.decode("#ffffff"));

        topPanel.add(slider);
        jFrame.add(topPanel);

        middlePanel.add(textField);
        middlePanel.add(copyButton);
        middlePanel.add(generateButton);
        jFrame.add(middlePanel);

        bottomPanel.add(passwordLength);
        bottomPanel.add(lengthCheck);
        bottomPanel.add(numbersCheck);
        bottomPanel.add(upperCaseCheck);
        bottomPanel.add(lowerCaseCheck);
        bottomPanel.add(symbolsCheck);
        jFrame.add(bottomPanel);

        jFrame.setVisible(true);

        slider.addChangeListener(e -> {
            passwordLength.setText("Password length: " + slider.getValue());
        });

        generateButton.addActionListener(e -> {
            textField.setText(generator.generatePassword(slider.getValue()));
            visualisePasswordCheck(generator);
        });

        copyButton.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(textField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                visualisePasswordCheck(generator);
            }
        });
    }

    public void visualisePasswordCheck(Generator generator) {
        passwordLength.setText("Password length: " + textField.getText().length());
        slider.setValue(textField.getText().length());

        if (textField.getText().length() >= 8) {
            lengthCheck.setForeground(Color.decode("#36a832"));
        } else {
            lengthCheck.setForeground(Color.decode("#a83232"));
        }

        if (generator.checkPasswordWithExtendedReturnData(textField.getText())[0]) {
            numbersCheck.setForeground(Color.decode("#36a832"));
        } else {
            numbersCheck.setForeground(Color.decode("#a83232"));
        }

        if(generator.checkPasswordWithExtendedReturnData(textField.getText())[1]) {
            upperCaseCheck.setForeground(Color.decode("#36a832"));
        } else {
            upperCaseCheck.setForeground(Color.decode("#a83232"));
        }

        if(generator.checkPasswordWithExtendedReturnData(textField.getText())[2]) {
            lowerCaseCheck.setForeground(Color.decode("#36a832"));
        } else {
            lowerCaseCheck.setForeground(Color.decode("#a83232"));
        }

        if(generator.checkPasswordWithExtendedReturnData(textField.getText())[3]) {
            symbolsCheck.setForeground(Color.decode("#36a832"));
        } else {
            symbolsCheck.setForeground(Color.decode("#a83232"));
        }
    }
}
