import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 * @author Jason Saadatmand, Student #: 100196234
 * @category CPSC 1181 - Assignment #10
 * @since July 23, 2012
 * Description: To Play the Loser Game
 */
public class LuciFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	String folderHierarchy;

	JPanel pictureDisplayPanel;
	ImageIcon pictureDisplayerIcon;
	JLabel pictureDisplayerLabel;

	int currentPicIndex=0;
	int totalPicIndex=0;
	String [][]pictureNames;
	boolean []pictures;
	JLabel framePositionLabel;
	double time=0;
	JPanel jumpPanel;
	JPanel controlPanel;
	JPanel eyeScalePanel;
	JPanel savePanel;
	JLabel EyeDisplayerLabel;
	boolean eyeOpen;
	JTextField frameSpecifierField;
	JCheckBox scaleCheckBox;
	JSpinner scaleSpinner;
	JPanel folderSpecificationPanel;
	JTextField folderPathTextField;
	String eyeOpenFileName="EyeOpen.jpg";
	String eyeClosedFileName="EyeClosed.jpg";
	String directory="\\";
	/**
	 * Purpose: to show the initial frame that asks if the user wants to play the game
	 */
	public LuciFrame(){

		createSaveAndClosePanel();
		createEyeScalePanel();
		createtimeFramePanel();
		createfolderSpecificationPanel();
		createControlPanel();
		createPictureDisplayPanel();
		RegisterMouseWheelAction();

		add(pictureDisplayPanel, BorderLayout.NORTH);		

	}

	public void createSaveAndClosePanel(){
		savePanel = new JPanel(); 
		createSaveButton();
	}

	public void createSaveButton(){
		class createSaveButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				try{
					PrintWriter writer = new PrintWriter("output.csv", "UTF-8");
					writer.println( "Frame,Time,EyeClosed, Scale For Eyes Closed");
					for (int i = 0 ; i < totalPicIndex ; i++){
						writer.println(pictureNames[i][0]+","+pictureNames[i][1]+","+pictureNames[i][2]+","+pictureNames[i][3]+",");
					}
					writer.close();
				} catch (IOException e) {
					// do something
				}
			}
		}
		ActionListener SaveButtonListener = new createSaveButtonListener();
		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(SaveButtonListener);
		savePanel.add(SaveButton, BorderLayout.NORTH);
	}

	public void createEyeScalePanel(){
		scaleSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 20) );
		scaleCheckBox = new JCheckBox("I don't know");
		eyeScalePanel = new JPanel(); 
		EyeDisplayerLabel = new JLabel();
		eyeOpen = true;
		System.out.println(System.getProperty("user.dir"));
		ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeOpenFileName); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back

		EyeDisplayerLabel.setIcon(imageIcon);
		switchEyesOpenClosed();

		reAddComponents(false, false, true, false, false);
	}

	public void switchEyesOpenClosed(){
		class detectEyesOpenClosedListener implements MouseListener
		{
			public void mousePressed (MouseEvent event){} 
			public void mouseReleased(MouseEvent event) {
				ImageIcon imageIcon;
				if (eyeOpen == true){
					imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeClosedFileName); // load the image to a imageIcon
					Image image = imageIcon.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					imageIcon = new ImageIcon(newimg);  // transform it back
				}else{

					imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeOpenFileName); // load the image to a imageIcon
					Image image = imageIcon.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					imageIcon = new ImageIcon(newimg);  // transform it back
				}
				eyeOpen=!eyeOpen;



				EyeDisplayerLabel.setIcon(imageIcon);

				removeComponents(false, false, true, false, false);
				reAddComponents(false, false, true, false, false);
				redrawComponent(false, false, true, false, false);
				removeComponents(false, false, false, false, true);
				reAddComponents(false, false, false, false, true);
				redrawComponent(false, false, false, false, true);
			}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		} 
		MouseListener EyesOpenClosedListener = new detectEyesOpenClosedListener();
		EyeDisplayerLabel.addMouseListener(EyesOpenClosedListener);
	}

	public void createtimeFramePanel(){
		jumpPanel = new JPanel(); 
		frameSpecifierField = new JTextField("          ");
		framePositionLabel = new JLabel("Frame: X, Time: Y");
		jumpPanel.add(new JLabel("Jump to Frame: "));
		jumpPanel.add(frameSpecifierField);
		JumpFrameButtonMaker(jumpPanel);
		jumpPanel.add(framePositionLabel);
	}	

	public void JumpFrameButtonMaker(JPanel panel){
		class JumpFrameButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				if (totalPicIndex >= 1){
					if (Integer.parseInt(frameSpecifierField.getText().trim()) >=0 ||  Integer.parseInt(frameSpecifierField.getText().trim()) <totalPicIndex){
						currentPicIndex=Integer.parseInt(frameSpecifierField.getText().trim());
						if (pictures[currentPicIndex]){
							//reload from array

							if (pictureNames[currentPicIndex][2].equals("1")){
								eyeOpen = true;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeOpenFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}else{
								eyeOpen = false;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeClosedFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}
							if (Integer.parseInt(pictureNames[currentPicIndex][3]) != 999){
								scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
								scaleCheckBox.setSelected(false);
							}else{
								scaleCheckBox.setSelected(true);
							}
						}
						time=currentPicIndex*.1757;
						removeComponents(false, true, false, false, false);
						framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
						reAddComponents(false, true, false, false, false);
						redrawComponent(false, true, false, false, false);	


						pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

						if (pictureDisplayerIcon.getIconHeight() >= 1000 || pictureDisplayerIcon.getIconHeight() >= 500){
							Image image = pictureDisplayerIcon.getImage(); // transform it 
							Image newimg = image.getScaledInstance(1000, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
							pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
						}

						reAddComponents(true, false, false, false, false);
						reAddComponents(true, false, false, false, false);
						redrawComponent(true, false, false, false, false);
						reAddComponents(false, false, false, false, true);
						reAddComponents(false, false, false, false, true);
						redrawComponent(false, false, false, false, true);
					}
				}
			}
		}
		ActionListener JumpButtonListener = new JumpFrameButtonListener();
		JButton frameJumpButton = new JButton("Jump");
		frameJumpButton.addActionListener(JumpButtonListener);//actionlistener that initiates the game for the user
		panel.add(frameJumpButton, BorderLayout.WEST);
	}	

	public void createfolderSpecificationPanel(){
		folderSpecificationPanel = new JPanel(new BorderLayout());
		folderPathTextField = new JTextField("Folder Name...");
		folderPathTextField.setEditable(false);
		folderSpecificationPanel.add(folderPathTextField, BorderLayout.PAGE_START);
		FolderChooserButtonMaker(folderSpecificationPanel);
	}

	public void FolderChooserButtonMaker(JPanel panel){
		class FolderChooserButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				JDialog dialogbox = new JDialog();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(dialogbox);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					folderPathTextField.setText(selectedFile.getAbsolutePath());
					folderHierarchy = selectedFile.getAbsolutePath();
					try {
						loadImagesFromFolder();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		ActionListener FolderChooserButtonListener = new FolderChooserButtonListener();
		JButton  folderChooserButton = new JButton("folderChooserButton");
		folderChooserButton.addActionListener(FolderChooserButtonListener);//actionlistener that initiates the game for the user
		panel.add(folderChooserButton, BorderLayout.WEST);
	}

	public void loadImagesFromFolder() throws IOException{
		File folder = new File(folderHierarchy);
		File[] listOfFiles = folder.listFiles();

		pictureNames = new String[listOfFiles.length][4];

		int numberOfPhotos=0;
		for (int i = 0 ; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				File file = new File(folderHierarchy+directory+listOfFiles[i].getName());
				if (ImageIO.read(file) == null){
				}else{
					pictureNames[numberOfPhotos++][0]=listOfFiles[i].getName();
				}
			}
		}
		removeComponents(false, true, false, false, false);
		framePositionLabel = new JLabel("Frame: 0, Time: 0");
		reAddComponents(false, true, false, false, false);
		redrawComponent(false, true, false, false, false);

		removeComponents(false, false, false, false, true);
		reAddComponents(false, false, false, false, true);
		redrawComponent(false, false, false, false, true);

		currentPicIndex=0;
		totalPicIndex=numberOfPhotos;
		pictures = new boolean[totalPicIndex];
		for (int i = 0 ; i < totalPicIndex ; i++){
			pictures[i]=false;
		}
		pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

		if (pictureDisplayerIcon.getIconHeight() >= 1000 || pictureDisplayerIcon.getIconHeight() >= 500){
			Image image = pictureDisplayerIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(1000, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
		}

		reAddComponents(true, false, false, false, false);

		reAddComponents(true, false, false, false, false);
		redrawComponent(true, false, false, false, false);
	}

	public void createControlPanel(){
		controlPanel = new JPanel(); 
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setPreferredSize(new Dimension(200, 200));
		reAddComponents(false, false, false, false, true);
	}

	public void createPictureDisplayPanel(){
		pictureDisplayPanel = new JPanel();
		pictureDisplayerIcon = new ImageIcon();
		pictureDisplayerLabel = new JLabel(pictureDisplayerIcon);
		reAddComponents(true, false, false, false, false);
	}	

	public void RegisterMouseWheelAction(){
		class MouseWheelChanged implements MouseWheelListener{
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				savedataToArray();
				if (totalPicIndex >= 1){
					if (notches < 0) {
						if (currentPicIndex+1 == totalPicIndex){
							currentPicIndex=0;
						}else{
							currentPicIndex++;
						}
						if (pictures[currentPicIndex]){
							//reload from array

							if (pictureNames[currentPicIndex][2].equals("1")){
								eyeOpen = true;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeOpenFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}else{
								eyeOpen = false;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeClosedFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}
							if (Integer.parseInt(pictureNames[currentPicIndex][3]) != 999){
								scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
								scaleCheckBox.setSelected(false);
							}else{
								scaleCheckBox.setSelected(true);
							}
						}
						time=currentPicIndex*.1757;
						framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
						removeComponents(false, true, false, false, false);
						reAddComponents(false, true, false, false, false);
						redrawComponent(false, true, false, false, false);

						pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

						if (pictureDisplayerIcon.getIconHeight() >= 1000 || pictureDisplayerIcon.getIconHeight() >= 500){
							Image image = pictureDisplayerIcon.getImage(); // transform it 
							Image newimg = image.getScaledInstance(1000, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
							pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
						}


						removeComponents(true,false , false, false, false);
						reAddComponents(true, false, false, false, false);
						redrawComponent(true, false, false, false, false);
						removeComponents(false,false , false, false, true);
						reAddComponents(false, false, false, false, true);
						redrawComponent(false, false, false, false, true);

					} else {
						if (currentPicIndex-1 < 0){
							currentPicIndex=totalPicIndex-1;
						}else{
							currentPicIndex--;
						}
						if (pictures[currentPicIndex]){
							//reload from array
							if (pictureNames[currentPicIndex][2].equals("1")){
								eyeOpen = true;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeOpenFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}else{
								eyeOpen = false;
								ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+directory+eyeClosedFileName); // load the image to a imageIcon
								Image image = imageIcon.getImage(); // transform it 
								Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
								imageIcon = new ImageIcon(newimg);  // transform it back
								EyeDisplayerLabel.setIcon(imageIcon);
								removeComponents(false, false, true, false, false);
								reAddComponents(false, false, true, false, false);
								redrawComponent(false, false, true, false, false);
								removeComponents(false, false, false, false, true);
								reAddComponents(false, false, false, false, true);
								redrawComponent(false, false, false, false, true);
							}
							if (Integer.parseInt(pictureNames[currentPicIndex][3]) != 999){
								scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
								scaleCheckBox.setSelected(false);
							}else{
								scaleCheckBox.setSelected(true);
							}
						}
						time=currentPicIndex*.1757;
						framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
						removeComponents(false, true, false, false, false);
						reAddComponents(false, true, false, false, false);
						redrawComponent(false, true, false, false, false);

						pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

						if (pictureDisplayerIcon.getIconHeight() >= 1000 || pictureDisplayerIcon.getIconHeight() >= 500){
							Image image = pictureDisplayerIcon.getImage(); // transform it 
							Image newimg = image.getScaledInstance(1000, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
							pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
						}


						removeComponents(true,false , false, false, false);
						reAddComponents(true, false, false, false, false);
						redrawComponent(true, false, false, false, false);
						removeComponents(false,false , false, false, true);
						reAddComponents(false, false, false, false, true);
						redrawComponent(false, false, false, false, true);
					}
				}
			}
		}
		MouseWheelListener mouseWheelListener = new MouseWheelChanged();
		addMouseWheelListener(mouseWheelListener);
	}

	public void savedataToArray(){
		//save frame
		if (totalPicIndex >= 1){
			pictureNames[currentPicIndex][1]=Double.toString(time);
			if (eyeOpen == true){
				pictureNames[currentPicIndex][2]="1";
			}else{
				pictureNames[currentPicIndex][2]="0";
			}
			if (scaleCheckBox.isSelected()){
				pictureNames[currentPicIndex][3]=Integer.toString(999);
			}else{
				pictureNames[currentPicIndex][3]= String.valueOf(scaleSpinner.getValue());
			}
			pictures[currentPicIndex]=true;	
		}

	}

	public void removeComponents(boolean boolPictureDisplayPanel, boolean boolFrameJumpPanel, boolean boolEyeScalePanel, boolean boolFolderSpecificationPanel, boolean boolControlPanel){
		if (boolPictureDisplayPanel){
			remove(pictureDisplayPanel);
			pictureDisplayPanel.removeAll();
			pictureDisplayerLabel.removeAll();
		}
		if (boolFrameJumpPanel){
			remove(controlPanel);
			controlPanel.removeAll();
			jumpPanel.removeAll();
		}
		if (boolEyeScalePanel){
			remove(controlPanel);
			controlPanel.removeAll();
			eyeScalePanel.removeAll();
		}
		if (boolFolderSpecificationPanel){
			remove(controlPanel);
			controlPanel.removeAll();
			folderSpecificationPanel.removeAll();

		}
		if (boolControlPanel){
			remove(controlPanel);
			controlPanel.removeAll();
		}
	}

	public void reAddComponents(boolean boolPictureDisplayPanel, boolean boolFrameJumpPanel, boolean boolEyeScalePanel, boolean boolFolderSpecificationPanel, boolean boolControlPanel){

		if (boolEyeScalePanel){
			eyeScalePanel.add(EyeDisplayerLabel);
			eyeScalePanel.add(new JLabel("Scale for Eyes Closed"));
			eyeScalePanel.add(scaleSpinner);
			eyeScalePanel.add(scaleCheckBox);
		}


		if (boolFrameJumpPanel){
			jumpPanel.add(new JLabel("Jump to Frame: "));
			jumpPanel.add(frameSpecifierField);
			JumpFrameButtonMaker(jumpPanel);
			jumpPanel.add(framePositionLabel);
		}
		if (boolControlPanel){
			controlPanel.add(folderSpecificationPanel, BorderLayout.PAGE_START);
			controlPanel.add(jumpPanel, BorderLayout.LINE_START);
			controlPanel.add(eyeScalePanel, BorderLayout.CENTER);
			controlPanel.add(savePanel, BorderLayout.LINE_END);
			add(controlPanel, BorderLayout.SOUTH);	
		}
		if (boolPictureDisplayPanel){
			pictureDisplayerLabel.setIcon(pictureDisplayerIcon);
			pictureDisplayPanel.add(pictureDisplayerLabel);
			add(pictureDisplayPanel, BorderLayout.NORTH);

		}
	}

	public void redrawComponent(boolean boolPictureDisplayPanel, boolean boolFrameJumpPanel, boolean boolEyeScalePanel, boolean boolFolderSpecificationPanel, boolean boolControlPanel){

		if (boolPictureDisplayPanel){
			pictureDisplayPanel.validate();
			pictureDisplayPanel.repaint();
			validate();
			repaint();
		}
		if (boolFrameJumpPanel){
			jumpPanel.validate();
			jumpPanel.repaint();
			controlPanel.validate();
			controlPanel.repaint();
			validate();
			repaint();
		}
		if (boolEyeScalePanel){
			eyeScalePanel.validate();
			eyeScalePanel.repaint();
			controlPanel.validate();
			controlPanel.repaint();
			validate();
			repaint();
		}
		if (boolFolderSpecificationPanel){
			controlPanel.add(folderSpecificationPanel);
			controlPanel.add(jumpPanel);
			controlPanel.add(eyeScalePanel);
			controlPanel.add(savePanel);
			validate();
			repaint();
		}
		if (boolControlPanel){
			controlPanel.validate();
			controlPanel.repaint();
			validate();
			repaint();
		}
	}

}