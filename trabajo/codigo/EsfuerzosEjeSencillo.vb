Sub EsfuerzosEjeSencillo()

    Dim i As Integer
    Dim NumCapas As Integer
    Dim EvZ, Er1, Et1 As Double
    Dim he() As Double
    Dim PresionInflado, RadioContacto, Poisson As Double
    Dim tipoeje As String
    Dim NumCapa As Integer


    NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
    PresionInflado = Sheets("NuevoFormatoPav").Cells(9, 6)
    RadioContacto = Sheets("calculos").Cells(3, 6)
    Poisson = Sheets("calculos").Cells(3, 5)
    tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)


    ReDim he(NumCapas)

    'Esfuerzo Vertical, Radial y Tangencial
    For i = 1 To 2 'NumCapas - 1

        he(i) = Sheets("calculos").Cells(2 + i, 7)

        NumCapa = i
        Call Esfuerzos_Damy(tipoeje, NumCapa)

        EvZ = 6.894757 * (PresionInflado * (1 - ((he(i) ^ 3) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 1.5))))
        Er1 = 6.894757 * (PresionInflado / 2) * (1 + (2 * Poisson) - (((2 * he(i) * (1 + Poisson)) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 0.5))) + (((he(i) ^ 3) / ((RadioContacto ^ 2 + he(i) ^ 2) ^ 1.5))))
        Et1 = Er1

        Sheets("calculos").Cells(2 + i, 10) = EvZ
        Sheets("calculos").Cells(2 + i, 11) = Er1
        Sheets("calculos").Cells(2 + i, 12) = Et1
        Sheets("calculos").Cells(41, 2 + i) = Er1
        Sheets("calculos").Cells(41, 4 + i) = Et1

    Next i
End Sub