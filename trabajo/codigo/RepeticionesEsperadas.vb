Sub RepeticionesEsperadas()

Dim i, eje, NumMarcasClase As Integer
Dim NumEjes(1 To 4) As Double
Dim RepEsperadas(1 To 4) As Double

  NumMarcasClase = 0
  i = 4
  
  While Len(Sheets("Larguillo").Cells(i, 1)) > 0
    NumMarcasClase = NumMarcasClase + 1
    i = i + 1
  Wend
For eje = 1 To 4
    NumEjes(eje) = Sheets("Tránsito").Cells(19 + eje, 7)
        For i = 1 To NumMarcasClase
            RepEsperadas(eje) = NumEjes(eje) * 0.01 * 0.5 * Sheets("Larguillo").Cells(3 + i, 2 + eje)
            Sheets("Larguillo").Cells(3 + i, 36 + eje) = RepEsperadas(eje)
        Next i
Next eje

End Sub
