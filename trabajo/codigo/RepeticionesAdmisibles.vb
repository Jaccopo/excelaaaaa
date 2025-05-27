Sub RepeticionesAdmisibles()

Dim i, eje, NumMarcasClase As Integer
Dim f1, f2, f3, f4, f5 As Double
Dim Nd, Nf As Double
Dim DefZ(1 To 4), DefT(1 To 4) As Double
Dim TipoModeloFatiga As String
Dim TipoModeloDeformacion As String
Dim Elastico As Double

Worksheets("Larguillo").Range("AB4:AI103").ClearContents

TipoModeloFatiga = Worksheets("Análisis Espectral").ComboBoxFatiga.Text
TipoModeloDeformacion = Worksheets("Análisis Espectral").ComboBoxDeformacion.Text
Elastico = Sheets("Análisis Espectral").Cells(13, 6)

Select Case TipoModeloFatiga
        Case "Asphalt Institute"
'            f1 = 0.0796
'            f2 = 3.291
            f1 = 0.0011358
            f2 = 3.291
            f3 = 0.854
        Case "Shell"
'            f1 = 0.0685
'            f2 = 5.671
             f1 = 0.0000005356
             f2 = 5.671
             f3 = 2.363
        Case "Illinois DOT"
            f1 = 0.000005
            f2 = 3
        Case "IMT"
            f1 = 0.000000000166
            f2 = 4.32
        Case "BRRC, BELGICA"
            f1 = 4.92E-14
            f2 = 4.76
        Case "Personalizado1"
            f1 = CDbl(Sheets("Análisis Espectral").Cells(26, 26))
            f2 = CDbl(Sheets("Análisis Espectral").Cells(27, 26))
        Case "Personalizado2"
            f1 = CDbl(Sheets("Análisis Espectral").Cells(30, 26))
            f2 = CDbl(Sheets("Análisis Espectral").Cells(31, 26))
            f3 = CDbl(Sheets("Análisis Espectral").Cells(32, 26))
End Select

Select Case TipoModeloDeformacion
        Case "Asphalt Institute"
            f4 = 0.000000001365
            f5 = 4.477
        Case "IMT"
            f4 = 0.0000000618
            f5 = 3.95
        Case "BRRC, BELGICA"
            f4 = 0.00000000305
            f5 = 4.35
        Case "Shell 50%"
            f4 = 0.000000615
            f5 = 4
        Case "Shell 85%"
            f4 = 0.000000194
            f5 = 4
        Case "Shell 95%"
            f4 = 0.000000105
            f5 = 4
        Case "Personalizado"
            f4 = CDbl(Sheets("Análisis Espectral").Cells(28, 26))
            f5 = CDbl(Sheets("Análisis Espectral").Cells(29, 26))
End Select

Call espectros
Call VolumenTransito

NumMarcasClase = 0
  i = 4
  While Len(Sheets("Larguillo").Cells(i, 1)) > 0
    NumMarcasClase = NumMarcasClase + 1
    i = i + 1
  Wend


For eje = 1 To 4
For i = 1 To NumMarcasClase

DefZ(eje) = Sheets("Larguillo").Cells(3 + i, 4 + 5 * eje)
DefT(eje) = Abs(Sheets("Larguillo").Cells(3 + i, 5 + 5 * eje))
   
        Select Case TipoModeloFatiga
                Case "Asphalt Institute"
                   Nf = f1 * (DefT(eje) ^ (-f2)) * (Elastico ^ (-f3))
                Case "Shell"
                   Nf = f1 * (DefT(eje) ^ (-f2)) * (Elastico ^ (-f3))
                Case "Personalizado2"
                   Nf = f1 * (DefT(eje) ^ (-f2)) * (Elastico ^ (-f3))
                Case Else
                   Nf = f1 * (DefT(eje) ^ (-f2))
        End Select

       Nd = f4 * (DefZ(eje) ^ (-f5))
   
       Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje) = Nd
       Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje) = Nf
        
Next i
Next eje


End Sub
