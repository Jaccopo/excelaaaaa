Sub PrintResIndividual()
Dim i As Integer
Dim TipoAnalisis As String
Dim NumCapas As Integer
Dim X As Integer


TipoAnalisis = Sheets("Avanzado").Cells(3, 4)
NumCapas = Sheets("Análisis Individual").Cells(10, 6)

If TipoAnalisis = "Individual" Then

            Sheets("Análisis Individual").Cells(15, 25) = Sheets("calculos").Cells(3, 22)
            Sheets("Análisis Individual").Cells(17, 13) = Sheets("calculos").Cells(3, 30)
            Sheets("Análisis Individual").Cells(17, 28) = Sheets("calculos").Cells(6, 10)
            
    Select Case NumCapas
     Case Is = 2
                X = 19
     Case Is = 3
                X = 19
     Case Is = 4
                X = 21
     Case Is = 5
                X = 23
     Case Is >= 6
                X = 25
     Case Else
      
    End Select
            Sheets("Análisis Individual").Cells(X, 13) = Sheets("calculos").Cells(4, 29)
            Sheets("Análisis Individual").Cells(X, 28) = Sheets("calculos").Cells(7, 10)
Else
End If

End Sub
