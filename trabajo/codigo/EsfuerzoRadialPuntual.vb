Sub EsfuerzoRadialPuntual()

Dim i As Integer
Dim NumCapas As Integer

Dim ra1, ra2, ra3, ra4, ra5, ra6 As Double
Dim R1, R2, R3, R4, R5, R6 As Double
Dim Er1, Er2, Er3, Er4, Er5, Er6 As Double
Dim z, P As Double
Dim KgAKpa As Double
Dim Pi As Double

Dim Poisson As Double
Dim PesoNeum As Double
Dim tipoeje As String
Dim ErP As Double


Pi = 3.14159265358979
KgAKpa = 98.0665

NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)
Poisson = Sheets("calculos").Cells(3, 5)
PesoNeum = Sheets("calculos").Cells(3, 4)
P = 1000 * PesoNeum

ra1 = Sheets("calculos").Cells(18, 4)
ra2 = Sheets("calculos").Cells(19, 4)
ra3 = Sheets("calculos").Cells(20, 4)
ra4 = Sheets("calculos").Cells(21, 4)
ra5 = Sheets("calculos").Cells(22, 4)
ra6 = Sheets("calculos").Cells(23, 4)

For i = 1 To 2 'NumCapas - 1

z = Sheets("calculos").Cells(2 + i, 7)

R1 = Sheets("calculos").Cells(18, 4 + i)
R2 = Sheets("calculos").Cells(19, 4 + i)
R3 = Sheets("calculos").Cells(20, 4 + i)
R4 = Sheets("calculos").Cells(21, 4 + i)
R5 = Sheets("calculos").Cells(22, 4 + i)
R6 = Sheets("calculos").Cells(23, 4 + i)

Er1 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra1 ^ 2) / R1 ^ 5) - ((1 - 2 * Poisson) / (R1 * (R1 + z)))))
Er2 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra2 ^ 2) / R2 ^ 5) - ((1 - 2 * Poisson) / (R2 * (R2 + z)))))
Er3 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra3 ^ 2) / R3 ^ 5) - ((1 - 2 * Poisson) / (R3 * (R3 + z)))))
Er4 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra4 ^ 2) / R4 ^ 5) - ((1 - 2 * Poisson) / (R4 * (R4 + z)))))
Er5 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra5 ^ 2) / R5 ^ 5) - ((1 - 2 * Poisson) / (R5 * (R5 + z)))))
Er6 = KgAKpa * ((P / (2 * Pi)) * (((3 * z * ra6 ^ 2) / R6 ^ 5) - ((1 - 2 * Poisson) / (R6 * (R6 + z)))))

Select Case tipoeje
    Case "Sencillo"
        ErP = Er1
    Case "Sencillo Dual"
        ErP = Er1 + Er2
    Case "Tandem"
       ErP = Er1 + Er2 + Er3 + Er4
    Case "Tridem"
        ErP = Er1 + Er2 + Er3 + Er4 + Er5 + Er6
    Case "Medio Tridem"
        ErP = Er1 + Er3 + Er5

End Select

    Sheets("calculos").Cells(2 + i, 11) = ErP

    Sheets("calculos").Cells(41, 2 + i) = Er1
    Sheets("calculos").Cells(42, 2 + i) = Er2
    Sheets("calculos").Cells(43, 2 + i) = Er3
    Sheets("calculos").Cells(44, 2 + i) = Er4
    Sheets("calculos").Cells(45, 2 + i) = Er5
    Sheets("calculos").Cells(46, 2 + i) = Er6
Next i

    
End Sub
