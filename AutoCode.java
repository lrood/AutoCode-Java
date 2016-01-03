import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// The public class AutoCode has 3 private fields: The controlPanel which
// contains the three buttons, the statusLabel which holds the Text and the
// mainFrame which is the JFrame.  
public class AutoCode 
{

	private JFrame mainFrame;
	private JLabel statusLabel;
	private JPanel controlPanel;

	
	// The constructor calls the prepare GUI method
	public AutoCode()
	{
		prepareGUI();
	}

	
	// The main method uses the Autocode class constructor to create an
	// instance of AutoCode.  The showEvent method is called
	public static void main(String[] args)
	{
		AutoCode scantool = new AutoCode();  
		scantool.showEvent();       
	}
      
	// The class constructor calls this method to create the frame, add the
	// statusLabel and controlPanel and use a grid for the mainFrame, 
	// statusLabel, and controlPanel.  Flow Layout is used for the buttons
	// on the control panel.
	private void prepareGUI()
	{
		  mainFrame = new JFrame("Auto Codes");
		  mainFrame.setSize(400,200);
		  mainFrame.setResizable(false);
		  mainFrame.setLayout(new GridLayout(3, 1));
		  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		  statusLabel = new JLabel("",JLabel.CENTER);        

		  statusLabel.setSize(350,100);
		  controlPanel = new JPanel();
		  controlPanel.setLayout(new FlowLayout());

		  
		  mainFrame.add(controlPanel);
		  mainFrame.add(statusLabel);
		  mainFrame.setVisible(true);  
	}

	// This method is called from the main method.  It adds the buttons to
	// the controlPanel and sets the ActionListeners with Lamda Expressions
	private void showEvent()
	{
      
      JButton codeButton = new JButton("Pull Codes");
      JButton clearButton = new JButton("Clear Codes");
      JButton informationButton = new JButton("Information");

      codeButton.addActionListener((ActionEvent e)-> statusLabel.setText(code()));
      clearButton.addActionListener((ActionEvent e)-> statusLabel.setText(clear()));
      informationButton.addActionListener((ActionEvent e)-> information());

      controlPanel.add(codeButton);
      controlPanel.add(clearButton);
      controlPanel.add(informationButton);       

      mainFrame.setVisible(true);  
	}
	
	// This method creates an instance of the serial class and 
	// parses the returned string.  
	private String code()
	{
		   String message = "";
		   SerialConnection serial = new SerialConnection();
		   message = serial.connect("03");
		   if (message.contains("NO") || message.contains("00 00 00 00 00"))
		   {
				message = "NO CODES";
		   }
		   
		   else if (message.contains("Check"))
		   {
				message = "Check your Connections. Turn the Ignition On";
		   }
		   else if (message.contains("UNABLE"))
		   {
				message = "UNABLE TO CONNECT";
		   }
		   
		   // If codes are returned from the serial Class then they are
		   // processed with the following code.  
		   else 
		   {
				int n = 0;
				int x = 0;
				int num = 0;
				int codes = 0;
				int y = 0;
				
				// This part of the code will take the spaces out of the
				// returned string and shorten it to only the codes
				String changeCode = "";
				String displayCode = "";
				String[] allCodes = new String[6];
				String tcode = "";
				String strippedString = "";
				String shortString = "";
				int index = message.indexOf("43"); 
				index = index + 3;	
				while (index < message.length())
					{
							shortString = shortString + message.charAt(index);
							index +=1;
					}
				strippedString = shortString.replaceAll(" ","");
				// This algorithm will determine the number of codes that were pulled
				if ((strippedString.length()/4) < 3)
				{
					codes = 2;
				}
				if ((strippedString.length()/4) >= 3 && strippedString.length()/4 <4)
				{
					codes = 3;
				}
				if ((strippedString.length()/4) >= 4 && strippedString.length()/4 <5)
				{
					codes = 4;
				}
				if ((strippedString.length()/4) >= 5 && strippedString.length()/4 <6)
				{
					codes = 5;
				}
				if ((strippedString.length()/4) >=6 )
				{
					codes = 6;
				}
				
				// This while loop assigns the codes to an array named allCodes
				while (num < codes && n < (strippedString.length()) - 4)
				{
					for (x = 0; x < 4; x++)
					{
						tcode = tcode + strippedString.charAt(n);
						n += 1;
						allCodes[num] = tcode;
					}
					tcode = "";
					num += 1;
				}
				// This while loop makes the Powertrain, Body, Chassis, and Network(U) codes.  This is where the code
				// is put in standard form
				while (y < codes)
				{
					changeCode = allCodes[y];
					if (changeCode.charAt(0) == '3' || changeCode.charAt(0) == '2' || changeCode.charAt(0) == '1' || changeCode.charAt(0) == '0')
						{
							displayCode = "P" + changeCode.charAt(0) + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3); 
						}
					if (changeCode.charAt(0) == '4')
						{
							displayCode = "C" + "0" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='5')
						{
							displayCode = "C" + "1" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='6')
						{
							displayCode = "C" + "2" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='7')
						{
							displayCode = "C" + "3" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='8')
						{
							displayCode = "B" + "0" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='9')
						{
							displayCode = "B" + "1" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='A')
						{
							displayCode = "B" + "2" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='B')
						{
							displayCode = "B" + "3" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='C')
						{
							displayCode = "U" + "0" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='D')
						{
							displayCode = "U" + "1" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='E')
						{
							displayCode = "U" + "2" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					if (changeCode.charAt(0) =='F')
						{
							displayCode = "U" + "3" + changeCode.charAt(1) + changeCode.charAt(2) + changeCode.charAt(3);
						}
					
					// This if statement gets rid of "000" Codes
					if (displayCode.charAt(1) == '0' && displayCode.charAt(2) == '0' && displayCode.charAt(3) == '0')
						{
							displayCode = "";
						}
					allCodes[y] = displayCode;
					y +=1;
				}
				
				// This for statement puts all codes in the message statement to be returned to the showEvent method.
				message = "";
				for (int i = 0; i < codes; i++)
				{
					message = message + allCodes[i] + " ";
				}
		   }
		   return message;
	}
	
	// This method clears the codes
	private String clear()
	{
		String message = "Canceled";
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?");
		if (dialogResult == 0)
		{
			SerialConnection serial = new SerialConnection();
			serial.connect("04");
			message = "Codes Cleared";
		}
		return message;
	}
   
   // This method shows the user program information
   private void information()
	{
		JOptionPane.showMessageDialog(null, "Auto Code\nVersion 1.0\nAuthor: Lauren Rood\n\nUse with ELM327 USB Interface");
	}
}
