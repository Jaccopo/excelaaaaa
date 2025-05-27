Sub DeflexionPuntual()
'Este módulo está pendiente de terminar por las consideraciones para cada capa

'Ecuaciones para determinar la deflexión debida a una carga puntual(UDLITZ)


Dim ra1, ra2, ra3, ra4, ra5, ra6 As Double
Dim R1, R2, R3, R4, R5, R6 As Double
Dim R1b, R2b, R3b, R4b, R5b, R6b As Double
Dim A1, A2, A3, A4, A5, A6 As Double
Dim B1, B2, B3, B4, B5, B6 As Double
Dim C1, C2, C3, C4, C5, C6 As Double
Dim D1, D2, D3, D4, D5, D6 As Double
Dim E1, E2, E3, E4, E5, E6 As Double
Dim Poisson As Double
Dim Z1, Z2 As Double
Dim Pi As Double
Dim PesoNeum As Double
Dim KpaAKg As Double
Dim ModElastico() As Double
Dim tipoeje As String
Dim NumCapas As Integer
Dim i As Integer
Dim Espesor() As Double
Dim Fcorreccion() As Double
Dim he() As Double


tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
Pi = 3.14159265358979
Poisson = Sheets("NuevoFormatoPav").Cells(11, 6)
KpaAKg = 0.01019716
PesoNeum = Sheets("calculos").Cells(3, 4)

ReDim ModElastico(NumCapas)
ReDim Espesor(NumCapas)
ReDim Fcorreccion(NumCapas)
ReDim he(NumCapas)

For i = 1 To NumCapas
    Espesor(i) = Sheets("NuevoFormatoPav").Cells(13 + i, 5)
    ModElastico(i) = Sheets("NuevoFormatoPav").Cells(13 + i, 6)
       If NumCapas = 2 Then
            Fcorreccion(i) = 0.9
        Else
           If i = 1 Then
            Fcorreccion(i) = 1
           Else
            Fcorreccion(i) = 0.8
           End If
        End If
Next i

For i = 1 To NumCapas - 1
    he(i) = Fcorreccion(i) * (Espesor(i) + he(i - 1)) * (ModElastico(i) / ModElastico(i + 1)) ^ (1 / 3)
    Sheets("calculos").Cells(48 + i, 3) = he(i)
Next i

For i = 1 To NumCapas - 1

    If i = 1 Then
        Sheets("calculos").Cells(49, 4) = Sheets("NuevoFormatoPav").Cells(14, 5)
    Else
        Sheets("calculos").Cells(48 + i, 4) = Sheets("calculos").Cells(48 + i, 3)
    End If
Next i

ModElastico(i) = Sheets("calculos").Cells(i + 2, 3)


For i = 1 To NumCapas

ra1 = Sheets("calculos").Cells(18, 4)
ra2 = Sheets("calculos").Cells(19, 4)
ra3 = Sheets("calculos").Cells(20, 4)
ra4 = Sheets("calculos").Cells(21, 4)
ra5 = Sheets("calculos").Cells(22, 4)
ra6 = Sheets("calculos").Cells(23, 4)

R1 = Sheets("calculos").Cells(18, 4 + i)
R2 = Sheets("calculos").Cells(19, 4 + i)
R3 = Sheets("calculos").Cells(20, 4 + i)
R4 = Sheets("calculos").Cells(21, 4 + i)
R5 = Sheets("calculos").Cells(22, 4 + i)
R6 = Sheets("calculos").Cells(23, 4 + i)

R1b = Sheets("calculos").Cells(18, 5 + i)
R2b = Sheets("calculos").Cells(19, 5 + i)
R3b = Sheets("calculos").Cells(20, 5 + i)
R4b = Sheets("calculos").Cells(21, 5 + i)
R5b = Sheets("calculos").Cells(22, 5 + i)
R6b = Sheets("calculos").Cells(23, 5 + i)


A1 = ((1 + Poisson) * (1000 * PesoNeum)) / (2 * Pi * ModElastico(i) * KpaAKg)

B1 = (2 * (1 - Poisson)) / R1
B2 = (2 * (1 - Poisson)) / R2
B3 = (2 * (1 - Poisson)) / R3
B4 = (2 * (1 - Poisson)) / R4
B5 = (2 * (1 - Poisson)) / R5
B6 = (2 * (1 - Poisson)) / R6

C1 = Z1 ^ 2 / R1 ^ 3
C2 = Z1 ^ 2 / R2 ^ 3
C3 = Z1 ^ 2 / R3 ^ 3
C4 = Z1 ^ 2 / R4 ^ 3
C5 = Z1 ^ 2 / R5 ^ 3
C6 = Z1 ^ 2 / R6 ^ 3

D1 = (2 * (1 - Poisson)) / R1b
D2 = (2 * (1 - Poisson)) / R2b
D3 = (2 * (1 - Poisson)) / R3b
D4 = (2 * (1 - Poisson)) / R4b
D5 = (2 * (1 - Poisson)) / R5b
D6 = (2 * (1 - Poisson)) / R6b

E1 = Z2 ^ 2 / R1b ^ 3
E2 = Z2 ^ 2 / R2b ^ 3
E3 = Z2 ^ 2 / R3b ^ 3
E4 = Z2 ^ 2 / R4b ^ 3
E5 = Z2 ^ 2 / R5b ^ 3
E6 = Z2 ^ 2 / R6b ^ 3


Select Case tipoeje
        Case "Sencillo"
        
        Case "Sencillo Dual"
            Sheets("calculos").Cells(2 + i, 16) = A1 + A2
            Sheets("calculos").Cells(2 + i, 17) = B1 + B2
            Sheets("calculos").Cells(2 + i, 18) = C1 + C2
            Sheets("calculos").Cells(2 + i, 19) = D1 + D2
            Sheets("calculos").Cells(2 + i, 20) = E1 + E2
        Case "Tandem"
            Sheets("calculos").Cells(2 + i, 16) = A1 + A2 + A3 + A4
            Sheets("calculos").Cells(2 + i, 17) = B1 + B2 + B3 + B4
            Sheets("calculos").Cells(2 + i, 18) = C1 + C2 + C3 + C4
            Sheets("calculos").Cells(2 + i, 19) = D1 + D2 + D3 + D4
            Sheets("calculos").Cells(2 + i, 20) = E1 + E2 + E3 + E4
        Case "Tridem"
            Sheets("calculos").Cells(2 + i, 16) = A1 + A2 + A3 + A4 + A5 + A6
            Sheets("calculos").Cells(2 + i, 17) = B1 + B2 + B3 + B4 + D5 + B6
            Sheets("calculos").Cells(2 + i, 18) = C1 + C2 + C3 + C4 + C5 + C6
            Sheets("calculos").Cells(2 + i, 19) = D1 + D2 + D3 + D4 + D5 + D6
            Sheets("calculos").Cells(2 + i, 20) = E1 + E2 + E3 + E4 + E5 + E6
End Select

'si no es la ultima
Sheets("calculos").Cells(2 + i, 21) = Sheets("calculos").Cells(2 + i, 16) * 10 * Abs(((Sheets("calculos").Cells(2 + i - 1, 17) + _
                                  Sheets("calculos").Cells(2 + i - 1, 18)) - (Cells(2 + i, 19) + Cells(2 + i, 20))))
'si es la última
Sheets("calculos").Cells(2 + i, 21) = Sheets("calculos").Cells(2 + i, 16) * 10 * Abs(((Sheets("calculos").Cells(2 + i - 1, 17) + _
                                  Sheets("calculos").Cells(2 + i - 1, 18)) - (Sheets("calculos").Cells(2 + i, 19) + _
                                  Sheets("calculos").Cells(2 + i, 20))))

Next i

'Z1 = Cells(RENGLON, 9)
'Z2 = Cells(RENGLON, 88)
'For RENGLON = 17 To NCAPAS
'If Cells(RENGLON, 87) = 0.001 Then
'Sheets("ANALISIS PAVIMENTO").Cells(RENGlon, 21) = Cells(RENGLON, 16) * 10 * ((Cells(RENGLON, 19) + Cells(RENGLON, 20)))
'Else
'Sheets("ANALISIS PAVIMENTO").Cells(RENGLON, 21) = Cells(RENGLON, 16) * 10 * Abs(((Cells(RENGLON - 1, 17) + Cells(RENGLON - 1, 18)) - (Cells(RENGLON, 19) + Cells(RENGLON, 20))))
'End If
'Next RENGLON

End Sub
