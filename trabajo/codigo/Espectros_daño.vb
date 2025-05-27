Sub Espectros_daño()

Dim i, eje, NumMarcasClase As Integer
Dim Nd, Nf, DañoDef, DañoFat As Double
Dim RepEsperadas(1 To 4) As Double

  NumMarcasClase = 0
  i = 4
  
While Len(Sheets("Larguillo").Cells(i, 1)) > 0
   NumMarcasClase = NumMarcasClase + 1
   i = i + 1
Wend

For eje = 1 To 4
   
        For i = 1 To NumMarcasClase
             RepEsperadas(eje) = Sheets("Larguillo").Cells(3 + i, 36 + eje)
             Nd = Sheets("Larguillo").Cells(3 + i, 26 + 2 * eje)
             Nf = Sheets("Larguillo").Cells(3 + i, 27 + 2 * eje)
             DañoDef = RepEsperadas(eje) / Nd
             DañoFat = RepEsperadas(eje) / Nf
            Sheets("Larguillo").Cells(3 + i, 40 + 2 * eje) = DañoDef
            Sheets("Larguillo").Cells(3 + i, 41 + 2 * eje) = DañoFat
            
        Next i

Next eje

End Sub
