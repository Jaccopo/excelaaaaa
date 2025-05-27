Option Explicit
Public oprogress As New frm_lcf_ProgressBar
Public endrow As Long

Public Sub Avance2()
    
    Sheets("avanzado").Cells(2, 4) = "ESPECTRAL"
    
    
    'Dim oprogress As New frm_lcf_ProgressBar
    Dim style As Integer
    Dim windowCaption As String
'    Dim endrow As Long
    Dim i As Long
    style = 2                 ' Progress bar style (1 / 2).
    windowCaption = "Calculando Espectro de Daño..." ' Progress bar window caption.
    endrow = 22100           ' Max value
    ' Progress bar initialization
    oprogress.Initialize endrow, style, windowCaption
  On Error Resume Next
  
    oprogress.Show 0          ' Shows the progress bar window
'probabilista.Show
Call AnalisisEspectral

'    For i = 0 To endrow - 4     ' Dummy loop for this example
'                                ' <<Do something, put here your code>>
'          oprogress.Increase    ' Increases 1 unit the progress bar
'    Next
    oprogress.Increase 4      ' Increases 4 units the progress bar
    Unload oprogress          ' Unload progress bar window
   
    Sheets("avanzado").Cells(2, 4) = ""
Set oprogress = Nothing

End Sub
