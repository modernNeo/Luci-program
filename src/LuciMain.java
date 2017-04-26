import javax.swing.JFrame;
/**
 * @author Jason Saadatmand, Student #: 100196234
 * @category CPSC 1181 - Assignment #10
 * @since July 23, 2012
 * Description: To Play the Loser Game
 */
public class LuciMain {

	/**
	 * Purpose: initiates the frame to play the loser game
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new LuciFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(LuciFrame.pictureMaxWidth,LuciFrame.pictureMaxHeight*2);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);    
		frame.setTitle("LUCI Program - SFU Psychology Department");
	}
}
