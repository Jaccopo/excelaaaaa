Sub AnalisisEspectral()

Dim NumMarcasClase As Integer
Dim i As Integer
Dim eje As Integer
Dim tipoeje As String
Dim damy As Boolean
 
 
Worksheets("Larguillo").Range("C4:BK300").ClearContents
damy = Sheets("Análisis Espectral").Cells(12, 13)

NumMarcasClase = 0
  i = 4
  While Len(Sheets("Larguillo").Cells(i, 1)) > 0
    NumMarcasClase = NumMarcasClase + 1
    i = i + 1
  Wend
Call espectros

For eje = 1 To 4
       
        Select Case eje
        Case 1
            tipoeje = "Sencillo"
        Case 2
            tipoeje = "Sencillo Dual"
        Case 3
           tipoeje = "Tandem"
        Case 4
            tipoeje = "Tridem"
        End Select
     
     Sheets("NuevoFormatoPav").Cells(8, 6) = tipoeje
    
    For i = 1 To NumMarcasClase
           'oprogress.Increase 50
        Sheets("NuevoFormatoPav").Cells(7, 6) = Sheets("Larguillo").Cells(3 + i, 2)
        Call CalculosDef

    Select Case damy

         Case False

            Sheets("Larguillo").Cells(3 + i, 2 + 5 * eje) = Sheets("calculos").Cells(3, 10)
            Sheets("Larguillo").Cells(3 + i, 3 + 5 * eje) = Sheets("calculos").Cells(4, 10)
            Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje) = Sheets("calculos").Cells(4, 29)
            Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje) = Sheets("calculos").Cells(3, 30)
            Sheets("Larguillo").Cells(3 + i, 6 + 5 * eje) = Sheets("calculos").Cells(3, 22)

        Case True
        
        'parte que toma en cuenta a damy
           Sheets("Larguillo").Cells(3 + i, 2 + 5 * eje) = Sheets("calculos").Cells(6, 10)
           Sheets("Larguillo").Cells(3 + i, 3 + 5 * eje) = Sheets("calculos").Cells(7, 10)
           Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje) = Sheets("calculos").Cells(7, 29)
           Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje) = Sheets("calculos").Cells(6, 30)
           Sheets("Larguillo").Cells(3 + i, 6 + 5 * eje) = Sheets("calculos").Cells(3, 22)
    End Select
    
    
    Next i
Next eje

'Call AsignacionTransito
Call VolumenTransito
Call RepeticionesAdmisibles
Call RepeticionesEsperadas
Call Espectros_daño
Call Sumas
Call vidaremanente


End Sub

Sub vidaremanente()


Sheets("larguillo").Cells(6, 65) = Sheets("Tránsito").años1.Text

If Sheets("larguillo").Cells(4, 74) > CDbl(Sheets("Tránsito").años1.Text) Then
Sheets("Análisis Espectral").Cells(15, 12) = " > " & CDbl(Sheets("Tránsito").años1.Text)
Else
Sheets("Análisis Espectral").Cells(15, 12) = Sheets("larguillo").Cells(4, 74)
End If

If Sheets("larguillo").Cells(4, 69) > CDbl(Sheets("Tránsito").años1.Text) Then
Sheets("Análisis Espectral").Cells(16, 12) = " > " & CDbl(Sheets("Tránsito").años1.Text)
Else
Sheets("Análisis Espectral").Cells(16, 12) = Sheets("larguillo").Cells(4, 69)
End If



End Sub
