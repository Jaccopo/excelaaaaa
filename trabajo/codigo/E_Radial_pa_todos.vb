Sub E_RADIAL_PTODOS_MA()

Dim RENGLON As Integer
Dim NCAPAS As Integer
Dim A As Double
Dim B1, B2, B3, B4, B5, B6 As Double
Dim C1, C2, C3, C4, C5, C6 As Double
Dim ra1, ra2, ra3, ra4, ra5, ra6 As Double
Dim R1, R2, R3, R4, R5, R6 As Double
Dim Er1, Er2, Er3, Er4, Er5, Er6 As Double
Dim z, P As Double
Dim KgAKpa As Double
Dim Pi As Double
Dim PESO_EJE As Double

Dim NumCapas As Integer
Dim PesoNeum As Double
Dim tipoeje As String
Dim ErZ As Double
Dim Poisson As Double


Pi = 3.14159265358979
KgAKpa = 98.0665
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
z = Sheets("calculos").Cells(4, 7)
PesoNeum = Sheets("calculos").Cells(3, 4)
P = 1000 * PesoNeum
A = P / (2 * Pi)
Poisson = Sheets("calculos").Cells(3, 5)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)

ra1 = Sheets("calculos").Cells(18, 4)
ra2 = Sheets("calculos").Cells(19, 4)
ra3 = Sheets("calculos").Cells(20, 4)
ra4 = Sheets("calculos").Cells(21, 4)
ra5 = Sheets("calculos").Cells(22, 4)
ra6 = Sheets("calculos").Cells(23, 4)

R1 = Sheets("calculos").Cells(18, 6)
R2 = Sheets("calculos").Cells(19, 6)
R3 = Sheets("calculos").Cells(20, 6)
R4 = Sheets("calculos").Cells(21, 6)
R5 = Sheets("calculos").Cells(22, 6)
R6 = Sheets("calculos").Cells(23, 6)

Er1 = KgAKpa * (A * (((3 * z * ra1 ^ 2) / R1 ^ 5) - ((1 - 2 * Poisson) / (R1 * (R1 + z))))) 'ecuacion 3.13
Er2 = KgAKpa * (A * (((3 * z * ra2 ^ 2) / R2 ^ 5) - ((1 - 2 * Poisson) / (R2 * (R2 + z)))))
Er3 = KgAKpa * (A * (((3 * z * ra3 ^ 2) / R3 ^ 5) - ((1 - 2 * Poisson) / (R3 * (R3 + z)))))
Er4 = KgAKpa * (A * (((3 * z * ra4 ^ 2) / R4 ^ 5) - ((1 - 2 * Poisson) / (R4 * (R4 + z)))))
Er5 = KgAKpa * (A * (((3 * z * ra5 ^ 2) / R5 ^ 5) - ((1 - 2 * Poisson) / (R5 * (R5 + z)))))
Er6 = KgAKpa * (A * (((3 * z * ra6 ^ 2) / R6 ^ 5) - ((1 - 2 * Poisson) / (R6 * (R6 + z)))))


Select Case tipoeje
    Case "Sencillo"
        ErZ = Er1
    Case "Sencillo Dual"
        ErZ = Er1 + Er2
    Case "Tandem"
        ErZ = Er1 + Er2 + Er3 + Er4
    Case "Tridem"
        ErZ = Er1 + Er2 + Er3 + Er4 + Er5 + Er6
    Case "Medio Tridem"
        ErZ = Er1 + Er3 + Er5

End Select
Sheets("calculos").Cells(4, 11) = ErZ

Sheets("calculos").Cells(41, 4) = Er1  'ojo con esta parte no desechar aun
Sheets("calculos").Cells(42, 4) = Er2
Sheets("calculos").Cells(43, 4) = Er3
Sheets("calculos").Cells(44, 4) = Er4
Sheets("calculos").Cells(45, 4) = Er5
Sheets("calculos").Cells(46, 4) = Er6

End Sub
