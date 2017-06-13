# Luci-program

this program is used to code the individual frames from the Luci overnight runs.

all that is needed to run program is the jar file

this program is compiled with eclipse which I also use to create the jar file using the instructions: https://www.cs.utexas.edu/~scottm/cs307/handouts/Eclipse%20Help/jarInEclipse.htm

BUGS:

-issue with the program becoming non-responsive

TODO:

FEATURES:

Panel & Object Hierarchy:
+ControlPanel
+-->folderSpecificationPanel
+--+-->folderChooserButton
+--+-->folderPathTextField
+-->JumpPanel
+--+-->JumpPanelTop
+--+--+-->JLabel("Jump To Frame")
+--+--+-->frameSpecifierField
+-->eyeScalePanel
+--+-->EyeDisplayerLabel
+--+--+-->EyesOpenClosedListener
+--+-->EyeScaleControlpanel
+--+--+-->eyeScaleControlsInputPanel
+--+--+--+-->ScaleSpinner
+--+--+--+-->ScaleCheckBox
+--+--+-->JLabel("Scale for Eyes Closed")
+-->SavePanel
+--+-->nextPreviousButtonPanel
+--+--+-->previousButton
+--+--+-->nextButton
+--+-->saveButton
