package edu.jhu.teamundecided.clueless.client.dialogs;

import edu.jhu.teamundecided.clueless.client.ClientAppController;

import javax.swing.*;
import java.awt.event.*;

public class moveDialog extends JDialog
{
    private JPanel contentPane;
    private JButton _moveButton;
    private JButton _cancelButton;
    private JPanel _locationPanel;
    private JRadioButton radioButton1;

    private String _moveSelection = "";

    public moveDialog(ClientAppController cac)
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(_moveButton);
        _moveButton.setEnabled(false);

        _moveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onMove(cac);
            }
        });

        _cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Add Listeners
        for(JRadioButton button : DialogController.getInstance().getMoveList())
        {
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    _moveSelection = button.getName();
                    _moveButton.setEnabled(true);
                }
            });
        }
    }

    private void onMove(ClientAppController cac)
    {
        StringBuilder move = new StringBuilder("move:");
        move.append(_moveSelection);

        cac.writeToServer(move.toString());
        System.out.println(move.toString());

        cac.getClientApp().getMoveButton().setEnabled(false);

        if(_moveSelection.startsWith("hallway_"))
        {
            cac.getClientApp().getEndTurnButton().setEnabled(true);
            if(cac.getClientApp().getSuggestButton().isEnabled())
            {
                cac.getClientApp().getSuggestButton().setEnabled(false);
            }
        }
        else
        {
            cac.getClientApp().getSuggestButton().setEnabled(true);
        }

        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents()
    {
        _locationPanel = DialogController.getInstance().getMovePanel();
    }
}
