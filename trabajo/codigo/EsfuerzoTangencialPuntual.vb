Sub EsfuerzoTangencialPuntual()

Dim i As Integer
Dim NumCapas As Integer
Dim R1, R2, R3, R4, R5, R6 As Double
Dim Et1, Et2, Et3, Et4, Et5, Et6 As Double
Dim z, P As Double
Dim KgAKpa As Double
Dim Pi As Double

Dim PesoNeum As Double
Dim tipoeje As String
Dim Poisson As Double
Dim EtP As Double

Pi = 3.14159265358979
KgAKpa = 98.0665

NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)
Poisson = Sheets("calculos").Cells(3, 5)
PesoNeum = Sheets("calculos").Cells(3, 4)
P = 1000 * PesoNeum

For i = 1 To 2 'NumCapas - 1

z = Sheets("calculos").Cells(2 + i, 7)

R1 = Sheets("calculos").Cells(18, 4 + i)
R2 = Sheets("calculos").Cells(19, 4 + i)
R3 = Sheets("calculos").Cells(20, 4 + i)
R4 = Sheets("calculos").Cells(21, 4 + i)
R5 = Sheets("calculos").Cells(22, 4 + i)
R6 = Sheets("calculos").Cells(23, 4 + i)

Et1 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R1 * (R1 + z))) - (z / R1 ^ 3)))
Et2 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R2 * (R2 + z))) - (z / R2 ^ 3)))
Et3 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R3 * (R3 + z))) - (z / R3 ^ 3)))
Et4 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R4 * (R4 + z))) - (z / R4 ^ 3)))
Et5 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R5 * (R5 + z))) - (z / R5 ^ 3)))
Et6 = KgAKpa * (((1 - 2 * Poisson) * (P / (2 * Pi))) * ((1 / (R6 * (R6 + z))) - (z / R6 ^ 3)))


Select Case tipoeje
    Case "Sencillo"
        EtP = Et1
    Case "Sencillo Dual"
        EtP = Et1 + Et2
    Case "Tandem"
       EtP = Et1 + Et2 + Et3 + Et4
    Case "Tridem"
        EtP = Et1 + Et2 + Et3 + Et4 + Et5 + Et6
    Case "Medio Tridem"
        EtP = Et1 + Et3 + Et5
End Select

    Sheets("calculos").Cells(2 + i, 12) = EtP

    Sheets("calculos").Cells(41, 4 + i) = Et1
    Sheets("calculos").Cells(42, 4 + i) = Et2
    Sheets("calculos").Cells(43, 4 + i) = Et3
    Sheets("calculos").Cells(44, 4 + i) = Et4
    Sheets("calculos").Cells(45, 4 + i) = Et5
    Sheets("calculos").Cells(46, 4 + i) = Et6

Next i

End Sub
