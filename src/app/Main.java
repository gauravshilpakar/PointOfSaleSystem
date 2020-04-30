package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.AccountException;
import javax.swing.*;

class MainFrame extends JFrame // default serial version was added
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPasswordField password;
    JPanel panel;
    JButton login;

    MainFrame() {
        panel = new JPanel();
        JPanel heading = new JPanel();
        heading.add(new JLabel("ABC STORE"));
        panel.add(heading);
        password = new JPasswordField();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(password);
        login = new JButton("Redeem Rewards");
        login.addActionListener(new Login());
        JPanel footer = new JPanel();
        footer.add(login);
        panel.add(footer);
        this.add(panel);
        this.setTitle("Store");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(180, 150);
    }

    class Login implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            @SuppressWarnings("deprecation")
            String passwd = password.getText().toString();
            int pin = 0;
            try {
                pin = Integer.parseInt(passwd);
                if (passwd.length() == 6) {
                    if (pin < 1 || pin > 999999) {
                        JOptionPane.showMessageDialog(null, "Create your unique code");
                    } else {
                        // our next part
                        new AccountException();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Password length must be at least 6");
				}
			}
			catch(NumberFormatException e){System.out.println("Thank you!!");}
		}
		
	}
}

public class Main {
public static void main(String[] args) {
	new MainFrame();
}
}