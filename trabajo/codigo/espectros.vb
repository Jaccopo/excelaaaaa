Sub espectros()

Dim M(1 To 3) As Double
Dim SD(1 To 3) As Double
Dim X As Double
Dim W(1 To 3) As Double
Dim TipoCarga As String
Dim eje As Integer
Dim parametros As Integer
Dim celda As Integer
Dim i As Integer
Dim altura As Double

Dim celdaSeleccionada As Range
 

'TipoCarga = Sheets("Espectros de Carga").Cells(10, 12)
TipoCarga = Sheets("Espectros de Carga").ComboBox2.Text

Select Case TipoCarga
    Case "Legal"
       celda = 0
    Case "Ligera Sobrecarga"
       celda = 5
    Case "Alta Sobrecarga"
       celda = 10
    Case "Muy Alta Sobrecarga"
       celda = 15
    Case "Avanzado"
       celda = 20
End Select

For eje = 1 To 4
    For parametros = 1 To 3
        W(parametros) = Sheets("Espectros de Carga").Cells(29 + celda + eje, 2 + parametros)


        M(parametros) = Sheets("Espectros de Carga").Cells(29 + celda + eje, 5 + parametros)
        SD(parametros) = Sheets("Espectros de Carga").Cells(29 + celda + eje, 8 + parametros)
                If SD(parametros) = 0 Then
                SD(parametros) = 0.000000001
                End If
    Next parametros
    
    For i = 1 To 100
           If Sheets("avanzado").Cells(2, 4) = "ESPECTRAL" Then
            X = i * 0.5
            
            altura = W(1) / (2.506628274631 * X * SD(1)) * Exp(-0.5 * ((Log(X) - M(1)) / SD(1)) ^ 2) _
                    + W(2) / (2.506628274631 * X * SD(2)) * Exp(-0.5 * ((Log(X) - M(2)) / SD(2)) ^ 2) _
                    + W(3) / (2.506628274631 * X * SD(3)) * Exp(-0.5 * ((Log(X) - M(3)) / SD(3)) ^ 2)
                    
        Sheets("Larguillo").Cells(3 + i, 1) = i
        Sheets("Larguillo").Cells(3 + i, 2) = i * 0.5
        Sheets("Larguillo").Cells(3 + i, 2 + eje) = altura * 100
    Next i
Next eje

End Sub
