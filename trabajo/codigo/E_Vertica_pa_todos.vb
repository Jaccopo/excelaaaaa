Sub E_VERTICAL_PTODOS_MA()

Dim RENGLON As Integer
Dim NCAPAS As Integer
Dim ra1, ra2, ra3, ra4, ra5, ra6 As Double
Dim R1, R2, R3, R4, R5, R6 As Double
Dim Ez1, Ez2, Ez3, Ez4, Ez5, Ez6 As Double
Dim z, P As Double
Dim KgAKpa As Double
Dim Pi As Double
Dim PESO_EJE As Double

Dim NumCapas As Integer
Dim PesoNeum As Double
Dim tipoeje As String
Dim EvZ As Double

Pi = 3.14159265358979
KgAKpa = 98.0665
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
PesoNeum = Sheets("calculos").Cells(3, 4)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)
z = Sheets("calculos").Cells(4, 7)
P = 1000 * PesoNeum

R1 = Sheets("calculos").Cells(18, 6)  'distancias R de cada llanta a Z
R2 = Sheets("calculos").Cells(19, 6)
R3 = Sheets("calculos").Cells(20, 6)
R4 = Sheets("calculos").Cells(21, 6)
R5 = Sheets("calculos").Cells(22, 6)
R6 = Sheets("calculos").Cells(23, 6)

Ez1 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R1 ^ 5)
Ez2 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R2 ^ 5)
Ez3 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R3 ^ 5)
Ez4 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R4 ^ 5)
Ez5 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R5 ^ 5)
Ez6 = KgAKpa * (3 * P * z ^ 3) / (2 * Pi * R6 ^ 5)

Select Case tipoeje
    Case "Sencillo"
        EvZ = Ez1
    Case "Sencillo Dual"
        EvZ = Ez1 + Ez2
    Case "Tandem"
        EvZ = Ez1 + Ez2 + Ez3 + Ez4
    Case "Tridem"
        EvZ = Ez1 + Ez2 + Ez3 + Ez4 + Ez5 + Ez6
    Case "Medio Tridem"
        EvZ = Ez1 + Ez3 + Ez5
End Select
Sheets("calculos").Cells(4, 10) = EvZ
End Sub
