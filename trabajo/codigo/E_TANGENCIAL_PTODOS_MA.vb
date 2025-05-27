Sub E_TANGENCIAL_PTODOS_MA() 'ESFUERZO

Dim RENGLON As Integer
Dim NCAPAS As Integer
Dim A As Double
Dim B1, B2, B3, B4, B5, B6 As Double
Dim C1, C2, C3, C4, C5, C6 As Double
Dim ra1, ra2, ra3, ra4, ra5, ra6 As Double
Dim R1, R2, R3, R4, R5, R6 As Double
Dim Et1, Et2, Et3, Et4, Et5, Et6 As Double
Dim z, P As Double
Dim KgAKpa As Double
Dim Pi As Double
Dim PESO_EJE As Double

Dim NumCapas As Integer
Dim PesoNeum As Double
Dim tipoeje As String
Dim EtZ As Double
Dim Poisson As Double

Pi = 3.14159265358979
KgAKpa = 98.0665

NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
z = Sheets("calculos").Cells(4, 7)
PesoNeum = Sheets("calculos").Cells(3, 4)
P = 1000 * PesoNeum
Poisson = Sheets("calculos").Cells(3, 5)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)
A = (1 - 2 * Poisson) * (P / (2 * Pi))

R1 = Sheets("calculos").Cells(18, 6)
R2 = Sheets("calculos").Cells(19, 6)
R3 = Sheets("calculos").Cells(20, 6)
R4 = Sheets("calculos").Cells(21, 6)
R5 = Sheets("calculos").Cells(22, 6)
R6 = Sheets("calculos").Cells(23, 6)

Et1 = KgAKpa * (A * ((1 / (R1 * (R1 + z))) - (z / R1 ^ 3))) 'ecuacion 3.14
Et2 = KgAKpa * (A * ((1 / (R2 * (R2 + z))) - (z / R2 ^ 3)))
Et3 = KgAKpa * (A * ((1 / (R3 * (R3 + z))) - (z / R3 ^ 3)))
Et4 = KgAKpa * (A * ((1 / (R4 * (R4 + z))) - (z / R4 ^ 3)))
Et5 = KgAKpa * (A * ((1 / (R5 * (R5 + z))) - (z / R5 ^ 3)))
Et6 = KgAKpa * (A * ((1 / (R6 * (R6 + z))) - (z / R6 ^ 3)))

Select Case tipoeje
    Case "Sencillo"
        EtZ = Et1
    Case "Sencillo Dual"
        EtZ = Et1 + Et2
    Case "Tandem"
        EtZ = Et1 + Et2 + Et3 + Et4
    Case "Tridem"
        EtZ = Et1 + Et2 + Et3 + Et4 + Et5 + Et6
    Case "Medio Tridem"
        EtZ = Et1 + Et3 + Et5
End Select
Sheets("calculos").Cells(4, 12) = EtZ

Sheets("calculos").Cells(41, 6) = Et1 ' ojo con estas anotaciones aun no se desechan
Sheets("calculos").Cells(42, 6) = Et2
Sheets("calculos").Cells(43, 6) = Et3
Sheets("calculos").Cells(44, 6) = Et4
Sheets("calculos").Cells(45, 6) = Et5
Sheets("calculos").Cells(46, 6) = Et6

End Sub
