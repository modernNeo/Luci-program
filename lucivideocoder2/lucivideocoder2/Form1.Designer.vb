<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.picFrame = New System.Windows.Forms.PictureBox()
        Me.btnOpenFile = New System.Windows.Forms.Button()
        Me.lblFileName = New System.Windows.Forms.Label()
        Me.lblFrame = New System.Windows.Forms.Label()
        Me.FolderBrowserDialog1 = New System.Windows.Forms.FolderBrowserDialog()
        Me.PicBoxEyesStates = New System.Windows.Forms.PictureBox()
        Me.btnExit = New System.Windows.Forms.Button()
        Me.btnSave = New System.Windows.Forms.Button()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.txtJumpToFrame = New System.Windows.Forms.TextBox()
        Me.btnJumpToFrame = New System.Windows.Forms.Button()
        Me.lblScaleForEyesClosed = New System.Windows.Forms.Label()
        Me.numFldUpDownSelector = New System.Windows.Forms.NumericUpDown()
        Me.chkBoxIDK = New System.Windows.Forms.CheckBox()
        CType(Me.picFrame, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PicBoxEyesStates, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.numFldUpDownSelector, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'picFrame
        '
        Me.picFrame.Location = New System.Drawing.Point(18, 18)
        Me.picFrame.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.picFrame.Name = "picFrame"
        Me.picFrame.Size = New System.Drawing.Size(960, 738)
        Me.picFrame.TabIndex = 0
        Me.picFrame.TabStop = False
        '
        'btnOpenFile
        '
        Me.btnOpenFile.Location = New System.Drawing.Point(942, 775)
        Me.btnOpenFile.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.btnOpenFile.Name = "btnOpenFile"
        Me.btnOpenFile.Size = New System.Drawing.Size(36, 38)
        Me.btnOpenFile.TabIndex = 1
        Me.btnOpenFile.Text = "..."
        Me.btnOpenFile.UseVisualStyleBackColor = True
        '
        'lblFileName
        '
        Me.lblFileName.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.lblFileName.Location = New System.Drawing.Point(18, 775)
        Me.lblFileName.Margin = New System.Windows.Forms.Padding(4, 0, 4, 0)
        Me.lblFileName.Name = "lblFileName"
        Me.lblFileName.Size = New System.Drawing.Size(914, 34)
        Me.lblFileName.TabIndex = 2
        Me.lblFileName.Text = "Folder Name ..."
        Me.lblFileName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'lblFrame
        '
        Me.lblFrame.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.lblFrame.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblFrame.Location = New System.Drawing.Point(18, 869)
        Me.lblFrame.Margin = New System.Windows.Forms.Padding(4, 0, 4, 0)
        Me.lblFrame.Name = "lblFrame"
        Me.lblFrame.Size = New System.Drawing.Size(370, 48)
        Me.lblFrame.TabIndex = 3
        Me.lblFrame.Text = "Frame: X, Time: Y"
        Me.lblFrame.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'PicBoxEyesStates
        '
        Me.PicBoxEyesStates.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me.PicBoxEyesStates.Image = Global.LuciVideoCoder2.My.Resources.Resources.EyeOpen
        Me.PicBoxEyesStates.Location = New System.Drawing.Point(396, 828)
        Me.PicBoxEyesStates.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.PicBoxEyesStates.Name = "PicBoxEyesStates"
        Me.PicBoxEyesStates.Size = New System.Drawing.Size(90, 92)
        Me.PicBoxEyesStates.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.PicBoxEyesStates.TabIndex = 4
        Me.PicBoxEyesStates.TabStop = False
        '
        'btnExit
        '
        Me.btnExit.Location = New System.Drawing.Point(825, 828)
        Me.btnExit.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.btnExit.Name = "btnExit"
        Me.btnExit.Size = New System.Drawing.Size(147, 94)
        Me.btnExit.TabIndex = 5
        Me.btnExit.Text = "Exit"
        Me.btnExit.UseVisualStyleBackColor = True
        '
        'btnSave
        '
        Me.btnSave.Location = New System.Drawing.Point(670, 832)
        Me.btnSave.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.btnSave.Name = "btnSave"
        Me.btnSave.Size = New System.Drawing.Size(147, 94)
        Me.btnSave.TabIndex = 6
        Me.btnSave.Text = "Save"
        Me.btnSave.UseVisualStyleBackColor = True
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(18, 828)
        Me.Label1.Margin = New System.Windows.Forms.Padding(4, 0, 4, 0)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(120, 20)
        Me.Label1.TabIndex = 7
        Me.Label1.Text = "Jump to Frame:"
        '
        'txtJumpToFrame
        '
        Me.txtJumpToFrame.Location = New System.Drawing.Point(154, 828)
        Me.txtJumpToFrame.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.txtJumpToFrame.Name = "txtJumpToFrame"
        Me.txtJumpToFrame.Size = New System.Drawing.Size(110, 26)
        Me.txtJumpToFrame.TabIndex = 8
        '
        'btnJumpToFrame
        '
        Me.btnJumpToFrame.Location = New System.Drawing.Point(276, 829)
        Me.btnJumpToFrame.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.btnJumpToFrame.Name = "btnJumpToFrame"
        Me.btnJumpToFrame.Size = New System.Drawing.Size(112, 35)
        Me.btnJumpToFrame.TabIndex = 9
        Me.btnJumpToFrame.Text = "Jump"
        Me.btnJumpToFrame.UseVisualStyleBackColor = True
        '
        'lblScaleForEyesClosed
        '
        Me.lblScaleForEyesClosed.AutoSize = True
        Me.lblScaleForEyesClosed.Location = New System.Drawing.Point(494, 831)
        Me.lblScaleForEyesClosed.Margin = New System.Windows.Forms.Padding(4, 0, 4, 0)
        Me.lblScaleForEyesClosed.Name = "lblScaleForEyesClosed"
        Me.lblScaleForEyesClosed.Size = New System.Drawing.Size(164, 20)
        Me.lblScaleForEyesClosed.TabIndex = 11
        Me.lblScaleForEyesClosed.Text = "Scale for Eyes Closed"
        '
        'numFldUpDownSelector
        '
        Me.numFldUpDownSelector.Increment = New Decimal(New Integer() {20, 0, 0, 0})
        Me.numFldUpDownSelector.Location = New System.Drawing.Point(498, 856)
        Me.numFldUpDownSelector.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.numFldUpDownSelector.Name = "numFldUpDownSelector"
        Me.numFldUpDownSelector.Size = New System.Drawing.Size(98, 26)
        Me.numFldUpDownSelector.TabIndex = 12
        Me.numFldUpDownSelector.Value = New Decimal(New Integer() {100, 0, 0, 0})
        '
        'chkBoxIDK
        '
        Me.chkBoxIDK.AutoSize = True
        Me.chkBoxIDK.Location = New System.Drawing.Point(498, 890)
        Me.chkBoxIDK.Name = "chkBoxIDK"
        Me.chkBoxIDK.Size = New System.Drawing.Size(120, 24)
        Me.chkBoxIDK.TabIndex = 13
        Me.chkBoxIDK.Text = "I don't know"
        Me.chkBoxIDK.UseVisualStyleBackColor = True
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(9.0!, 20.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(990, 940)
        Me.Controls.Add(Me.chkBoxIDK)
        Me.Controls.Add(Me.numFldUpDownSelector)
        Me.Controls.Add(Me.lblScaleForEyesClosed)
        Me.Controls.Add(Me.btnJumpToFrame)
        Me.Controls.Add(Me.txtJumpToFrame)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.btnSave)
        Me.Controls.Add(Me.btnExit)
        Me.Controls.Add(Me.PicBoxEyesStates)
        Me.Controls.Add(Me.lblFrame)
        Me.Controls.Add(Me.lblFileName)
        Me.Controls.Add(Me.btnOpenFile)
        Me.Controls.Add(Me.picFrame)
        Me.Margin = New System.Windows.Forms.Padding(4, 5, 4, 5)
        Me.Name = "Form1"
        Me.Text = "Form1"
        CType(Me.picFrame, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PicBoxEyesStates, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.numFldUpDownSelector, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents picFrame As System.Windows.Forms.PictureBox
    Friend WithEvents btnOpenFile As System.Windows.Forms.Button
    Friend WithEvents lblFileName As System.Windows.Forms.Label
    Friend WithEvents lblFrame As System.Windows.Forms.Label
    Friend WithEvents FolderBrowserDialog1 As System.Windows.Forms.FolderBrowserDialog
    Friend WithEvents PicBoxEyesStates As System.Windows.Forms.PictureBox
   Friend WithEvents btnExit As System.Windows.Forms.Button
   Friend WithEvents btnSave As System.Windows.Forms.Button
   Friend WithEvents Label1 As System.Windows.Forms.Label
   Friend WithEvents txtJumpToFrame As System.Windows.Forms.TextBox
   Friend WithEvents btnJumpToFrame As System.Windows.Forms.Button
    Friend WithEvents lblScaleForEyesClosed As Label
    Friend WithEvents numFldUpDownSelector As NumericUpDown
    Friend WithEvents chkBoxIDK As CheckBox
End Class
