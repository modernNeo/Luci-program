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
	public static int pictureMaxHeight=520;
	public static int pictureMaxWidth=1280;
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
	JPanel jumpPanelTop;
	JPanel jumpPanelBottom;
	JPanel controlPanel;
	JPanel eyeScalePanel;
	JPanel eyeScaleControlsPanel;
	JPanel eyeScaleControlsInputPanel;
	JPanel savePanel;
	JLabel EyeDisplayerLabel;
	boolean eyeOpen;
	JTextField frameSpecifierField;
	JCheckBox scaleCheckBox;
	JSpinner scaleSpinner;
	JPanel folderSpecificationPanel;
	JTextField folderPathTextField;
	ImageIcon imageIcon;
	String eyeOpenFileName="EyeOpen.jpg";
	String eyeClosedFileName="EyeClosed.jpg";
	String directory;
	String windows="\\";
	String LinuxUNIX="/";
	JPanel nextPreviousButonsPanel;
	/**
	 * Purpose: to show the initial frame that asks if the user wants to play the game
	 */
	public LuciFrame(){

		if( System.getProperty("os.name").trim().equals("Mac OS X") ){
			directory = LinuxUNIX;
		}else if (System.getProperty("os.name").trim().equals("Linux")){
			directory=LinuxUNIX;
		}else{
			directory = windows;
		}
		createSaveNextAndPreviousButtonPanel();
		createEyeScalePanel();
		createtimeFramePanel();
		createfolderSpecificationPanel();
		createControlPanel();
		createPictureDisplayPanel();
		RegisterMouseWheelAction();
		add(pictureDisplayPanel, BorderLayout.NORTH);	

	}

	public void createSaveNextAndPreviousButtonPanel(){
		savePanel = new JPanel(new BorderLayout()); 
		nextPreviousButonsPanel = new JPanel();
		createPreviousButton();
		createNextButton();
		savePanel.add(nextPreviousButonsPanel, BorderLayout.NORTH);
		createSaveButton();
	}

	public void createSaveButton(){
		class createSaveButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				try{
					savedataToArray();//makes sure the info about the current pic is also saved
					PrintWriter writer = new PrintWriter(folderHierarchy+directory+"output.csv", "UTF-8");
					writer.println( "Frame,Time,EyeOpen, Scale For Eyes Closed");
					for (int i = 0 ; i < totalPicIndex ; i++){
						writer.print(pictureNames[i][0]+","+pictureNames[i][1]+",");
						if (pictureNames[i][2]=="1"){//saves boolean as true and false just for the sake of easy reading
							writer.print("true");
						}else{
							writer.print("false");
						}
						writer.println(","+pictureNames[i][3]+",");
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
		savePanel.add(SaveButton, BorderLayout.CENTER);
	}

	public void createNextButton(){
		class createNextButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				if (totalPicIndex >= 1){
					goToNextPic();
				}
			}
		}
		ActionListener NextButtonListener = new createNextButtonListener();
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(NextButtonListener);
		nextPreviousButonsPanel.add(NextButton);
	}

	public void createPreviousButton(){
		class createPreviousButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				if (totalPicIndex >= 1){
					goToPrevPic();
				}
			}
		}
		ActionListener PreviousButtonListener = new createPreviousButtonListener();
		JButton PreviousButton = new JButton("Previous");
		PreviousButton.addActionListener(PreviousButtonListener);
		nextPreviousButonsPanel.add(PreviousButton);
	}
	public void resizeEyeScaleIcon(String iconName){
		System.out.println("switching eye state");
		imageIcon = new ImageIcon(this.getClass().getResource("img/"+iconName+".jpg"));//System.getProperty("user.dir")+directory+eyeClosedFileName); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
	}
	public void createEyeScalePanel(){
		scaleSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 20) );

		eyeScalePanel = new JPanel(); 
		EyeDisplayerLabel = new JLabel();
		eyeOpen = true;
		IDKCheckBoxToggled();
		resizeEyeScaleIcon("EyeOpen");

		EyeDisplayerLabel.setIcon(imageIcon);
		switchEyesOpenClosed();

		reAddEyeScalePanel();
	}

	public void IDKCheckBoxToggled(){
		class idkCheckBoxListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				if (scaleCheckBox.isSelected()){
					EyeDisplayerLabel.setEnabled(false);
					scaleSpinner.setEnabled(false);
				}else{
					EyeDisplayerLabel.setEnabled(true);
					scaleSpinner.setEnabled(true);
					scaleSpinner.setValue(0);
				}
			}
		}
		ActionListener idkCheckListener = new idkCheckBoxListener();
		scaleCheckBox = new JCheckBox("I don't know");
		scaleCheckBox.addActionListener(idkCheckListener);
	}
	public void switchEyesOpenClosed(){
		class detectEyesOpenClosedListener implements MouseListener
		{
			public void mousePressed (MouseEvent event){} 
			public void mouseReleased(MouseEvent event) {
				System.out.println("switching eye mouse function");
				if (eyeOpen == true){
					resizeEyeScaleIcon("EyeClosed");
				}else{
					resizeEyeScaleIcon("EyeOpen");
				}
				eyeOpen=!eyeOpen;

				//removeEyeScalePanel();

				EyeDisplayerLabel.setIcon(imageIcon);


				reAddEyeScalePanel();
				reDrawEyeScalePanel();
				removeControlPanel();
				reAddControlPanel();
				reDrawControlPanel();
			}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		} 
		MouseListener EyesOpenClosedListener = new detectEyesOpenClosedListener();
		EyeDisplayerLabel.addMouseListener(EyesOpenClosedListener);
	}

	public void createtimeFramePanel(){
		jumpPanel = new JPanel(new BorderLayout()); 
		jumpPanelTop = new JPanel();

		frameSpecifierField = new JTextField("          ");
		framePositionLabel = new JLabel("Frame: X, Time: Y");

		jumpPanelTop.add(new JLabel("Jump to Frame: "));
		jumpPanelTop.add(frameSpecifierField);
		jumpPanelBottom = new JPanel();
		JumpFrameButtonMaker(jumpPanelBottom);
		jumpPanelBottom.add(framePositionLabel);

		jumpPanel.add(jumpPanelBottom, BorderLayout.CENTER);
		jumpPanel.add(jumpPanelTop, BorderLayout.NORTH);
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
								resizeEyeScaleIcon("EyeOpen");
								EyeDisplayerLabel.setIcon(imageIcon);

								reAddEyeScalePanel();
								reDrawEyeScalePanel();
								removeControlPanel();
								reAddControlPanel();
								reDrawControlPanel();
							}else{
								eyeOpen = false;
								//removeEyeScalePanel();
								resizeEyeScaleIcon("EyeClosed");
								EyeDisplayerLabel.setIcon(imageIcon);

								reAddEyeScalePanel();
								reDrawEyeScalePanel();
								removeControlPanel();
								reAddControlPanel();
								reDrawControlPanel();
							}
							if (Integer.parseInt(pictureNames[currentPicIndex][3]) != 999){
								scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
								scaleCheckBox.setSelected(false);
							}else{
								scaleCheckBox.setSelected(true);
							}
						}
						time=currentPicIndex*.1757;
						removeFrameJumpPanel();
						framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
						reAddFrameJumpPanel();
						reDrawFrameJumpPanel();


						pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

						resizeImage();

						reAddPictureDisplayPanel();
						reDrawPictureDisplayPanel();
						reAddControlPanel();
						reDrawControlPanel();
					}
				}
			}
		}
		ActionListener JumpButtonListener = new JumpFrameButtonListener();
		JButton frameJumpButton = new JButton("Jump");
		frameJumpButton.addActionListener(JumpButtonListener);
		panel.add(frameJumpButton);
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
		JButton  folderChooserButton = new JButton("Choose Picture Folder");
		folderChooserButton.addActionListener(FolderChooserButtonListener);
		panel.add(folderChooserButton, BorderLayout.WEST);
	}

	public void loadImagesFromFolder() throws IOException{
		JOptionPane.showMessageDialog(null,"Press OK to Load Pictures", "Loadng pictures....", JOptionPane.OK_OPTION);
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
		removeFrameJumpPanel();
		framePositionLabel = new JLabel("Frame: 0, Time: 0");
		reAddFrameJumpPanel();
		reDrawFrameJumpPanel();

		removeControlPanel();
		reAddControlPanel();
		reDrawControlPanel();

		currentPicIndex=0;
		totalPicIndex=numberOfPhotos;
		pictures = new boolean[totalPicIndex];
		for (int i = 0 ; i < totalPicIndex ; i++){
			pictures[i]=false;
		}
		pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

		resizeImage();

		reAddPictureDisplayPanel();

		reDrawPictureDisplayPanel();
	}

	public void createControlPanel(){
		controlPanel = new JPanel(); 
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setPreferredSize(new Dimension(200, 200));
		reAddControlPanel();
	}

	public void createPictureDisplayPanel(){
		pictureDisplayPanel = new JPanel();
		pictureDisplayerIcon = new ImageIcon();
		pictureDisplayerLabel = new JLabel(pictureDisplayerIcon);
		reAddPictureDisplayPanel();
	}	

	public void RegisterMouseWheelAction(){
		class MouseWheelChanged implements MouseWheelListener{
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				if (totalPicIndex >= 1){
					if (notches < 0) {
						goToNextPic();
					} else {
						goToPrevPic();
					}
				}
			}
		}
		MouseWheelListener mouseWheelListener = new MouseWheelChanged();
		addMouseWheelListener(mouseWheelListener);
	}

	public void goToNextPic(){
		savedataToArray();
		if (currentPicIndex+1 == totalPicIndex){
			JOptionPane.showMessageDialog(null,null, "Looped back to first Picture", JOptionPane.OK_OPTION);
			currentPicIndex=0;
		}else{
			currentPicIndex++;
		}
		if (pictures[currentPicIndex]){
			//reload from array
			if (Integer.parseInt(pictureNames[currentPicIndex][3]) == 999){
				scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
				scaleCheckBox.setSelected(true);
				EyeDisplayerLabel.setEnabled(false);
				scaleSpinner.setEnabled(false);
			}else{
				scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
				scaleCheckBox.setSelected(false);
				EyeDisplayerLabel.setEnabled(true);
				scaleSpinner.setEnabled(true);
				if (pictureNames[currentPicIndex][2].equals("1")){
					eyeOpen = true;
					//removeEyeScalePanel();
					resizeEyeScaleIcon("EyeOpen");
					EyeDisplayerLabel.setIcon(imageIcon);

					reAddEyeScalePanel();
					reDrawEyeScalePanel();
					removeControlPanel();
					reAddControlPanel();
					reDrawControlPanel();
				}else{
					eyeOpen = false;
					//removeEyeScalePanel();
					resizeEyeScaleIcon("EyeClosed");
					EyeDisplayerLabel.setIcon(imageIcon);

					reAddEyeScalePanel();
					reDrawEyeScalePanel();
					removeControlPanel();
					reAddControlPanel();
					reDrawControlPanel();
				}
			}

		}
		time=currentPicIndex*.1757;
		framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
		removeFrameJumpPanel();
		reAddFrameJumpPanel();
		reDrawFrameJumpPanel();

		pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon


		resizeImage();


		removePictureDisplayPanel();
		reAddPictureDisplayPanel();
		reDrawPictureDisplayPanel();
		removeControlPanel();
		reAddControlPanel();
		reDrawControlPanel();
	}

	public void goToPrevPic(){
		savedataToArray();
		if (currentPicIndex-1 < 0){
			currentPicIndex=totalPicIndex-1;
			JOptionPane.showMessageDialog(null,null, "Looped back to first Picture", JOptionPane.OK_OPTION);
		}else{
			currentPicIndex--;
		}
		if (pictures[currentPicIndex]){
			//reload from array
			if (Integer.parseInt(pictureNames[currentPicIndex][3]) == 999){
				scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
				scaleCheckBox.setSelected(true);
				EyeDisplayerLabel.setEnabled(false);
				scaleSpinner.setEnabled(false);
			}else{
				scaleSpinner.setValue(Integer.parseInt(pictureNames[currentPicIndex][3]));
				scaleCheckBox.setSelected(false);
				EyeDisplayerLabel.setEnabled(true);
				scaleSpinner.setEnabled(true);
				if (pictureNames[currentPicIndex][2].equals("1")){
					eyeOpen = true;
					//removeEyeScalePanel();
					resizeEyeScaleIcon("EyeOpen");
					EyeDisplayerLabel.setIcon(imageIcon);

					reAddEyeScalePanel();
					reDrawEyeScalePanel();
					removeControlPanel();
					reAddControlPanel();
					reDrawControlPanel();
				}else{
					eyeOpen = false;
					//removeEyeScalePanel();
					resizeEyeScaleIcon("EyeClosed");
					EyeDisplayerLabel.setIcon(imageIcon);

					reAddEyeScalePanel();
					reDrawEyeScalePanel();
					removeControlPanel();
					reAddControlPanel();
					reDrawControlPanel();
				}
			}
		}
		time=currentPicIndex*.1757;
		framePositionLabel = new JLabel("Frame: "+currentPicIndex+", Time: "+time);
		removeFrameJumpPanel();
		reAddFrameJumpPanel();
		reDrawFrameJumpPanel();

		pictureDisplayerIcon = new ImageIcon(folderHierarchy+directory+pictureNames[currentPicIndex][0]); // load the image to a imageIcon

		resizeImage();

		removePictureDisplayPanel();
		reAddPictureDisplayPanel();
		reDrawPictureDisplayPanel();
		removeControlPanel();
		reAddControlPanel();
		reDrawControlPanel();
	}
	public void resizeImage(){
		if (pictureDisplayerIcon.getIconHeight() >= pictureMaxHeight){
			Image image = pictureDisplayerIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(pictureDisplayerIcon.getIconWidth() - ( pictureDisplayerIcon.getIconHeight() - pictureMaxHeight), pictureDisplayerIcon.getIconHeight() - ( pictureDisplayerIcon.getIconHeight() - pictureMaxHeight ) ,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
		}
		if (pictureDisplayerIcon.getIconWidth() >= pictureMaxWidth ){
			Image image = pictureDisplayerIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(pictureDisplayerIcon.getIconWidth() - (pictureDisplayerIcon.getIconWidth() - pictureMaxWidth ) , pictureDisplayerIcon.getIconHeight() - (pictureDisplayerIcon.getIconWidth()  - pictureMaxWidth),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			pictureDisplayerIcon = new ImageIcon(newimg);  // transform it back
		}
	}

	public void savedataToArray(){
		//save frame
		if (totalPicIndex >= 1){
			System.out.println("currentPicIndex: "+currentPicIndex+" eyeOpen: "+eyeOpen+" ScaleSpinner: "+scaleSpinner.getValue());
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
	public void removePictureDisplayPanel(){
		remove(pictureDisplayPanel);
		pictureDisplayPanel.removeAll();
		pictureDisplayerLabel.removeAll();		
	}
	public void removeFrameJumpPanel(){
		remove(controlPanel);
		controlPanel.removeAll();
		jumpPanel.removeAll();
		jumpPanelTop.removeAll();
		jumpPanelBottom.removeAll();			
	}
	/*public void removeEyeScalePanel(){
		remove(controlPanel);
		controlPanel.removeAll();
		EyeDisplayerLabel.removeAll();
		eyeScalePanel.removeAll();
		eyeScaleControlsPanel.removeAll();
		eyeScaleControlsInputPanel.removeAll();
	}*/
	public void removeControlPanel(){
		remove(controlPanel);
		controlPanel.removeAll();		
	}
	public void reAddPictureDisplayPanel(){
		pictureDisplayerLabel.setIcon(pictureDisplayerIcon);
		pictureDisplayPanel.add(pictureDisplayerLabel);
		add(pictureDisplayPanel, BorderLayout.NORTH);		
	}
	public void reAddFrameJumpPanel(){
		jumpPanelTop.add(new JLabel("Jump to Frame: "));
		jumpPanelTop.add(frameSpecifierField);
		JumpFrameButtonMaker(jumpPanelBottom);
		jumpPanelBottom.add(framePositionLabel);
		jumpPanel.add(jumpPanelBottom, BorderLayout.CENTER);
		jumpPanel.add(jumpPanelTop, BorderLayout.NORTH);		
	}
	public void reAddEyeScalePanel(){
		eyeScaleControlsInputPanel = new JPanel(new BorderLayout());
		eyeScaleControlsInputPanel.add(scaleSpinner, BorderLayout.NORTH);
		eyeScaleControlsInputPanel.add(scaleCheckBox, BorderLayout.CENTER);
		eyeScaleControlsPanel = new JPanel(new BorderLayout());
		eyeScaleControlsPanel.add(new JLabel("Scale for Eyes Closed"), BorderLayout.NORTH);
		eyeScaleControlsPanel.add(eyeScaleControlsInputPanel, BorderLayout.CENTER);
		eyeScalePanel = new JPanel(new BorderLayout());
		eyeScalePanel.add(EyeDisplayerLabel, BorderLayout.WEST);
		eyeScalePanel.add(eyeScaleControlsPanel, BorderLayout.EAST);		
	}
	public void reAddControlPanel(){
		controlPanel.add(folderSpecificationPanel, BorderLayout.PAGE_START);
		controlPanel.add(jumpPanel, BorderLayout.LINE_START);
		controlPanel.add(eyeScalePanel, BorderLayout.CENTER);
		controlPanel.add(savePanel, BorderLayout.LINE_END);
		add(controlPanel, BorderLayout.SOUTH);			
	}
	public void reDrawPictureDisplayPanel(){
		pictureDisplayPanel.validate();
		pictureDisplayPanel.repaint();
		validate();
		repaint();
	}
	public void reDrawFrameJumpPanel(){
		jumpPanel.validate();
		jumpPanel.repaint();
		controlPanel.validate();
		controlPanel.repaint();
		validate();
		repaint();
	}
	public void reDrawEyeScalePanel(){
		eyeScaleControlsInputPanel.validate();
		eyeScaleControlsInputPanel.repaint();
		eyeScaleControlsPanel.validate();
		eyeScaleControlsPanel.repaint();
		EyeDisplayerLabel.validate();
		EyeDisplayerLabel.repaint();
		eyeScalePanel.validate();
		eyeScalePanel.repaint();
		controlPanel.validate();
		controlPanel.repaint();
		validate();
		repaint();		
	}
	public void reDrawControlPanel(){
		controlPanel.validate();
		controlPanel.repaint();
		validate();
		repaint();		
	}
}
