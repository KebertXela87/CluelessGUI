package edu.jhu.teamundecided.clueless.client;

import edu.jhu.teamundecided.clueless.client.dialogs.DialogController;
import edu.jhu.teamundecided.clueless.client.dialogs.moveDialog;
import edu.jhu.teamundecided.clueless.client.gameboard.ClientGameBoard;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;

public class ClientApp
{
    private JTextArea _messageCenter;
    private JTextField _messageField;
    private JButton _sendButton;
    private JPanel _chatPanel;
    private JPanel _playerHand;
    private JPanel _gameBoard;
    private JPanel _controls;
    private JButton _moveButton;
    private JButton _suggestButton;
    private JButton _accusationButton;
    private JButton _notebookButton;
    private JPanel _mainPanel;
    private JButton _endTurnButton;
    private JButton _logoutButton;

    public ClientApp(ClientAppController controller)
    {
        _sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.writeToServer("chat:" + controller.getUserName() + " > " + _messageField.getText());
                _messageField.setText(""); // clear text field
            }
        });

        _messageField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    _sendButton.doClick();
                }
            }
        });

        // MOVE BUTTON
        _moveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // send message to server to get possible room locations
                controller.writeToServer("getmoves:" + controller.getUserName());
            }
        });

        _logoutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.getFrame().dispatchEvent(new WindowEvent(controller.getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public void writeToScreen(String message)
    {
        _messageCenter.setText(_messageCenter.getText().trim() + "\n" + message);
        DefaultCaret caret = (DefaultCaret)_messageCenter.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public JPanel returnMainPanel() { return _mainPanel; }

    public Component[] getControlButtons() { return _controls.getComponents(); }

    private void createUIComponents()
    {
        _gameBoard = new ClientGameBoard();
    }
}
