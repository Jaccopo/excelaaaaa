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
