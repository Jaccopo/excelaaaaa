Sub Sumas()

Dim i, eje, NumMarcasClase As Integer
Dim Nd, Nf, DañoDef, DañoFat As Double
Dim RepEsperadas(1 To 4) As Double
Dim suma1, suma2, suma3, suma4, suma5, suma6, suma7, suma8 As Double


  NumMarcasClase = 0
  i = 4
  suma1 = 0: suma2 = 0: suma3 = 0: suma4 = 0: suma5 = 0: suma6 = 0: suma7 = 0: suma8 = 0

While Len(Sheets("Larguillo").Cells(i, 1)) > 0
   NumMarcasClase = NumMarcasClase + 1
   i = i + 1
Wend
    For i = 1 To NumMarcasClase

                Sheets("Larguillo").Cells(3 + i, 51) = Sheets("Larguillo").Cells(3 + i, 42) + _
                                                       Sheets("Larguillo").Cells(3 + i, 44) + _
                                                       Sheets("Larguillo").Cells(3 + i, 46) + _
                                                       Sheets("Larguillo").Cells(3 + i, 48)
                Sheets("Larguillo").Cells(3 + i, 52) = Sheets("Larguillo").Cells(3 + i, 43) + _
                                                       Sheets("Larguillo").Cells(3 + i, 45) + _
                                                       Sheets("Larguillo").Cells(3 + i, 47) + _
                                                       Sheets("Larguillo").Cells(3 + i, 49)
                                
                suma1 = Sheets("Larguillo").Cells(3 + i, 42) + suma1
                Sheets("Larguillo").Cells(3 + i, 54) = suma1
                                
                suma2 = Sheets("Larguillo").Cells(3 + i, 44) + suma2
                Sheets("Larguillo").Cells(3 + i, 55) = suma2
                
                suma3 = Sheets("Larguillo").Cells(3 + i, 46) + suma3
                Sheets("Larguillo").Cells(3 + i, 56) = suma3
                
                suma4 = Sheets("Larguillo").Cells(3 + i, 48) + suma4
                Sheets("Larguillo").Cells(3 + i, 57) = suma4
                
                Sheets("Larguillo").Cells(3 + i, 58) = Sheets("Larguillo").Cells(3 + i, 54) + _
                                                       Sheets("Larguillo").Cells(3 + i, 55) + _
                                                       Sheets("Larguillo").Cells(3 + i, 56) + _
                                                       Sheets("Larguillo").Cells(3 + i, 57)
                
                suma5 = Sheets("Larguillo").Cells(3 + i, 43) + suma5
                Sheets("Larguillo").Cells(3 + i, 59) = suma5
                
                suma6 = Sheets("Larguillo").Cells(3 + i, 45) + suma6
                Sheets("Larguillo").Cells(3 + i, 60) = suma6
                
                suma7 = Sheets("Larguillo").Cells(3 + i, 47) + suma7
                Sheets("Larguillo").Cells(3 + i, 61) = suma7
                
                suma8 = Sheets("Larguillo").Cells(3 + i, 49) + suma8
                Sheets("Larguillo").Cells(3 + i, 62) = suma8
                
                Sheets("Larguillo").Cells(3 + i, 63) = Sheets("Larguillo").Cells(3 + i, 59) + _
                                                       Sheets("Larguillo").Cells(3 + i, 60) + _
                                                       Sheets("Larguillo").Cells(3 + i, 61) + _
                                                       Sheets("Larguillo").Cells(3 + i, 62)
    Next i
End Sub
