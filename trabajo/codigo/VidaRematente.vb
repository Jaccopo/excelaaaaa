Sub vidaremanente()


    Sheets("larguillo").Cells(6, 65) = Sheets("Tr�nsito").a�os1.Text

    If Sheets("larguillo").Cells(4, 74) > CDbl(Sheets("Tr�nsito").a�os1.Text) Then
        Sheets("An�lisis Espectral").Cells(15, 12) = " > " & CDbl(Sheets("Tr�nsito").a�os1.Text)
    Else
        Sheets("An�lisis Espectral").Cells(15, 12) = Sheets("larguillo").Cells(4, 74)
    End If

    If Sheets("larguillo").Cells(4, 69) > CDbl(Sheets("Tr�nsito").a�os1.Text) Then
        Sheets("An�lisis Espectral").Cells(16, 12) = " > " & CDbl(Sheets("Tr�nsito").a�os1.Text)
    Else
        Sheets("An�lisis Espectral").Cells(16, 12) = Sheets("larguillo").Cells(4, 69)
    End If



End Sub
