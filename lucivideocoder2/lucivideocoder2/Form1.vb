Public Class Form1
    Private EYESOPEN As Integer = 0
    Private EYESCLOSED As Integer = 1
    Private FPS = 11
    Private CurrentFrame As Integer = 0
    Private FrameCount As Integer = -1
    Private EyeStatus As Integer = 0 ' 0=open, 1=closed
    Private EyeScales As Integer = 100
    Structure sample
        Dim Frame As Integer
        Dim time As Double
        Dim eyestatus As Integer
        Dim scaleForEyesClosed As Integer
    End Structure
    Private data(1) As sample

    Private Sub btnOpenFile_Click(sender As System.Object, e As System.EventArgs) Handles btnOpenFile.Click

        FolderBrowserDialog1.ShowDialog()
        lblFileName.Text = FolderBrowserDialog1.SelectedPath
        ' if the csv file exists, open it:
        readdata()
        ' pad currentframe with 0's:
        LoadFrame()
    End Sub

    'Purpose: to read in the pictures from the selected folder
    Sub readdata()
        'Dim line As String
        'If My.Computer.FileSystem.FileExists(lblFileName.Text & "\Output.csv") Then
        '   Dim reader As New System.IO.StreamReader(lblFileName.Text & "\Output.csv")
        '   While Not reader.EndOfStream
        '      line = reader.ReadLine
        '      ' parse line: (frame,time,eyeclosed)

        '   End While
        'End If
        Dim numlines As Long
        If My.Computer.FileSystem.FileExists(lblFileName.Text & "\Output.csv") Then
            Using MyReader As New Microsoft.VisualBasic.FileIO.TextFieldParser(lblFileName.Text & "\Output.csv")
                MyReader.TextFieldType = FileIO.FieldType.Delimited
                MyReader.SetDelimiters(",")
                Dim currentRow As String()
                If Not MyReader.EndOfData Then
                    currentRow = MyReader.ReadFields() ' skip the first row
                End If
                numlines = 0
                FrameCount = 0
                ReDim data(0)
                While Not MyReader.EndOfData
                    Try
                        currentRow = MyReader.ReadFields()
                        numlines = numlines + 1
                        FrameCount = FrameCount + 1
                        ReDim Preserve data(numlines)
                        data(numlines).Frame = currentRow(0)
                        data(numlines).time = currentRow(1)
                        data(numlines).eyestatus = currentRow(2)
                        data(numlines).scaleForEyesClosed = currentRow(3)
                    Catch ex As Microsoft.VisualBasic.FileIO.MalformedLineException
                        MsgBox("Line " & ex.Message & "is not valid and will be skipped.")
                    End Try
                End While
            End Using
        End If
    End Sub

    'To load pictures onto the Frame in the GUI
    Private Sub LoadFrame()
        Dim filename As String
        Dim tmp As String
        Dim framename As String
        tmp = "000000"
        framename = Trim$(CurrentFrame)
        Mid(tmp, 7 - Len(framename), Len(framename)) = framename
        filename = lblFileName.Text & "\" & tmp & ".jpg"
        ' load first picture:
        lblFrame.Text = "Frame: " & tmp & vbCrLf & "Time: " & CurrentFrame * 0.1757 ' 5.69 fps
        Try
            picFrame.Image = Image.FromFile(filename)
        Catch
            MsgBox("no more files in that direction; please scroll the other way")
        End Try

    End Sub

    'Flips between pictures with the mouse wheel
    Private Sub Form1_MouseWheel(sender As Object, e As System.Windows.Forms.MouseEventArgs) Handles Me.MouseWheel
        If e.Delta > 0 Then
            ' up
            GoBackOne()
        Else
            ' down
            GoForwardOne()
        End If
    End Sub



    Private Sub GoBackOne()
        If CurrentFrame > FrameCount Then
            ' create new frame
            ReDim Preserve data(CurrentFrame)
            FrameCount = FrameCount + 1
            ' eye status is what it was before
        End If

        ' save current frame
        saveCurrentFrame()

        If CurrentFrame = 0 Then
            Exit Sub
        End If
        ' go back a frame
        If CurrentFrame > 0 Then
            CurrentFrame = CurrentFrame - 1
            If data(CurrentFrame).eyestatus = EYESCLOSED Then
                PicBoxEyesStates.Image = My.Resources.EyeClosed
                EyeStatus = EYESCLOSED
            Else
                PicBoxEyesStates.Image = My.Resources.EyeOpen
                EyeStatus = EYESOPEN
            End If ' TODO: should this have an else???
        End If
        LoadFrame()
    End Sub

    Sub saveCurrentFrame()
        With data(CurrentFrame)
            If PicBoxEyesStates.Enabled = False Then
                .eyestatus = 999
            Else
                .eyestatus = EyeStatus
            End If

            .Frame = CurrentFrame
            .time = CurrentFrame * 0.1757
            .scaleForEyesClosed = EyeScales
        End With
    End Sub

    Private Sub GoForwardOne()
        Dim created As Boolean
        If CurrentFrame > FrameCount Then
            ' create new frame
            ReDim Preserve data(CurrentFrame)
            FrameCount = FrameCount + 1
            ' eye status is what it was before
            created = True
        Else
            created = False
        End If

        ' save current frame
        saveCurrentFrame()

        ' go forward a frame
        CurrentFrame = CurrentFrame + 1

        ' load eye status
        If CurrentFrame <= FrameCount Then
            If data(CurrentFrame).eyestatus = EYESCLOSED Then
                PicBoxEyesStates.Image = My.Resources.EyeClosed
                EyeStatus = EYESCLOSED
            ElseIf data(CurrentFrame).eyestatus = EYESOPEN Then
                PicBoxEyesStates.Image = My.Resources.EyeOpen
                EyeStatus = EYESOPEN
            End If
        End If
        LoadFrame()
    End Sub

    'Switching the eye states pictures
    Private Sub PictureBox1_MouseUp(sender As Object, e As System.Windows.Forms.MouseEventArgs) Handles PicBoxEyesStates.MouseUp
        If EyeStatus = EYESOPEN Then
            EyeStatus = EYESCLOSED
            PicBoxEyesStates.Image = My.Resources.EyeClosed
        Else
            EyeStatus = EYESOPEN
            PicBoxEyesStates.Image = My.Resources.EyeOpen
        End If
        btnExit.Enabled = False
    End Sub

    Private Sub Button1_Click(sender As System.Object, e As System.EventArgs) Handles btnExit.Click
        Application.Exit()
    End Sub

    Sub savedata()
        Dim i As Integer
        Dim tmpstr As String
        tmpstr = "Frame,Time,EyeClosed, Scale For Eyes Closed" & vbCrLf
        My.Computer.FileSystem.WriteAllText(lblFileName.Text & "\Output.csv", tmpstr, False)
        For i = 1 To UBound(data)
            tmpstr = Str$(data(i).Frame) & "," & Str$(data(i).time) & "," & Str$(data(i).eyestatus) & "," & Str$(data(i).scaleForEyesClosed) & vbCrLf
            My.Computer.FileSystem.WriteAllText(FolderBrowserDialog1.SelectedPath & "\Output.csv", tmpstr, True)
        Next
    End Sub




    Private Sub BtnSave_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSave.Click
        savedata()
        btnExit.Enabled = True
    End Sub

    Private Sub btnJumpToFrame_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnJumpToFrame.Click
        CurrentFrame = Val(txtJumpToFrame.Text) - 1
        GoForwardOne()
        LoadFrame()
    End Sub

    Private Sub chkBoxIDK_CheckedChanged(sender As Object, e As EventArgs) Handles chkBoxIDK.CheckedChanged
        If (chkBoxIDK.Checked) Then
            PicBoxEyesStates.Enabled = False
        Else
            PicBoxEyesStates.Enabled = True
        End If
    End Sub

    Private Sub ScaleForEyesClosedChanged(sender As Object, e As EventArgs) Handles numFldUpDownSelector.ValueChanged
        EyeScales = numFldUpDownSelector.Value
    End Sub
End Class
