Sub EsfuerzoVerticalPuntual()

    Dim i As Integer
    Dim NumCapas, NumCapa As Integer
    Dim R1, R2, R3, R4, R5, R6 As Double
    Dim Ez1, Ez2, Ez3, Ez4, Ez5, Ez6 As Double
    Dim z, P As Double
    Dim KgAKpa As Double
    Dim Pi As Double

    Dim EzP As Double
    Dim PesoNeum As Double
    Dim tipoeje As String


    Pi = 3.14159265358979
    KgAKpa = 98.0665

    NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
    PesoNeum = Sheets("calculos").Cells(3, 4)
    tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)

    P = 1000 * PesoNeum

    For i = 1 To 2 'NumCapas - 1

        z = Sheets("calculos").Cells(2 + i, 7)

        R1 = Sheets("calculos").Cells(18, 4 + i)
        R2 = Sheets("calculos").Cells(19, 4 + i)
        R3 = Sheets("calculos").Cells(20, 4 + i)
        R4 = Sheets("calculos").Cells(21, 4 + i)
        R5 = Sheets("calculos").Cells(22, 4 + i)
        R6 = Sheets("calculos").Cells(23, 4 + i)

        Ez1 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R1 ^ 5)
        Ez2 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R2 ^ 5)
        Ez3 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R3 ^ 5)
        Ez4 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R4 ^ 5)
        Ez5 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R5 ^ 5)
        Ez6 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R6 ^ 5)

        Select Case tipoeje
            Case "Sencillo"
                EzP = Ez1
            Case "Sencillo Dual"
                EzP = Ez1 + Ez2
            Case "Tandem"
                EzP = Ez1 + Ez2 + Ez3 + Ez4
            Case "Tridem"
                EzP = Ez1 + Ez2 + Ez3 + Ez4 + Ez5 + Ez6
            Case "Medio Tridem"
                EzP = Ez1 + Ez3 + Ez5
        End Select

        Sheets("calculos").Cells(2 + i, 10) = EzP

    Next i

    For NumCapa = 1 To 2 'NumCapas - 1
        Call Esfuerzos_Damy(tipoeje, NumCapa)
    Next NumCapa


End Sub