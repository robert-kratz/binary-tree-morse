package us.rjks.gui;

import us.rjks.utils.MorseCodierung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyright Ⓒ Robert J. Kratz 2021
 * Created: 01.09.2021 / 17:50
 * Contact: https://link.rjks.us/support
 */

public class Formatter extends JFrame {

    private MorseCodierung morseCodierung;

    private JPanel main;

    private JTextArea output;
    private JButton submit;
    private JButton toggle;
    private JTextArea input;

    private JLabel title;

    private State state;

    public Formatter() {

        this.state = State.ENCODE;

        this.morseCodierung = new MorseCodierung();

        this.setTitle("Morse decoder/encoder by rjks.us");
        this.setSize(300, 488);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.main = new JPanel();
        this.main.setLayout(new FlowLayout());

        this.title = new JLabel("Encoding is enabled");

        this.output = new JTextArea("The output will be printed here", 10, 20);
        this.output.setMargin(new Insets(5, 5, 5, 5));
        this.output.setEditable(false);
        this.output.setLineWrap(true);

        JScrollPane out = new JScrollPane (this.output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.input = new JTextArea("", 10, 20);
        this.input.setToolTipText("Input your code here");
        this.input.setMargin(new Insets(5, 5, 5, 5));
        this.input.setLineWrap(true);

        JScrollPane in = new JScrollPane (this.input, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.submit = new JButton("Submit");

        this.toggle = new JButton("Decode");


        this.main.add(this.title);
        this.main.add(in);
        this.main.add(submit);
        this.main.add(out);
        this.main.add(toggle);
        this.main.add(new JLabel("Robert J. Kratz (rjks.us) © 2021"));

        addListeners();

        this.getContentPane().add(main);
    }

    private void addListeners() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input.getText() == null || input.getText().isEmpty()) {
                    output.setText("An error has occurred, no translations were made");
                    return;
                }
                String out;
                if (state.equals(State.ENCODE)) {
                    out = morseCodierung.encodeString(input.getText());

                } else {
                    out = morseCodierung.decodeString(input.getText());
                }
                output.setText(out);
            }
        });

        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state.equals(State.ENCODE)) {
                    title.setText("Decoding is enabled");
                    toggle.setText("Decode");
                    state = State.DECODE;
                } else {
                    title.setText("Encoding is enabled");
                    toggle.setText("Encode");
                    state = State.ENCODE;
                }
            }
        });
    }
}
