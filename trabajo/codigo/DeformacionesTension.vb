Sub DeformacionesTension()

Dim i As Integer
Dim NumCapas As Integer
Dim AX, BX, CX, DX, EX, FX, GX, HX, IX, JX, KX, LX As Double
Dim AY, BY, CY, DY, EY, FY, GY, HY, IY, JY, KY, LY As Double

Dim O, P, Q, R, S, T  As Double
Dim DEFx, DEFy, GAMAxy As Double
Dim Grados As Double

Dim Angulo_Llanta1 As Double
Dim Angulo_Llanta2 As Double
Dim Angulo_Llanta3 As Double
Dim Angulo_Llanta4 As Double
Dim Angulo_Llanta5 As Double
Dim Angulo_Llanta6 As Double

Dim Er1, Er2, Er3, Er4, Er5, Er6, Et1, Et2, Et3, Et4, Et5, Et6 As Double
Dim Enormal_x, Enormal_y, Ecortante_xy As Double
Dim ModElastico() As Double
Dim DefTension As Double
Dim EvZ As Double
Dim tipoeje As String
Dim Poisson As Double
Dim EvZ_damy, DEFx_damy, DEFy_damy, DefTension_damy As Double


Grados = 1.74532925199433E-02
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)


ReDim ModElastico(NumCapas)

Angulo_Llanta1 = Sheets("calculos").Cells(18, 3)
Angulo_Llanta2 = Sheets("calculos").Cells(19, 3)
Angulo_Llanta3 = Sheets("calculos").Cells(20, 3)
Angulo_Llanta4 = Sheets("calculos").Cells(21, 3)
Angulo_Llanta5 = Sheets("calculos").Cells(22, 3)
Angulo_Llanta6 = Sheets("calculos").Cells(23, 3)


For i = 1 To 2 'NumCapas - 1

Er1 = Sheets("calculos").Cells(41, i + 2)
Er2 = Sheets("calculos").Cells(42, i + 2)
Er3 = Sheets("calculos").Cells(43, i + 2)
Er4 = Sheets("calculos").Cells(44, i + 2)
Er5 = Sheets("calculos").Cells(45, i + 2)
Er6 = Sheets("calculos").Cells(46, i + 2)

Et1 = Sheets("calculos").Cells(41, i + 4)
Et2 = Sheets("calculos").Cells(42, i + 4)
Et3 = Sheets("calculos").Cells(43, i + 4)
Et4 = Sheets("calculos").Cells(44, i + 4)
Et5 = Sheets("calculos").Cells(45, i + 4)
Et6 = Sheets("calculos").Cells(46, i + 4)


'Esfuerzo normal x
'COMPONENTE X ESFUERZO RADIAL
AX = Er1 * (Cos(Angulo_Llanta1 * Grados) ^ 2)
CX = Er2 * (Cos(Angulo_Llanta2 * Grados) ^ 2)
EX = Er3 * (Cos(Angulo_Llanta3 * Grados) ^ 2)
GX = Er4 * (Cos(Angulo_Llanta4 * Grados) ^ 2)
IX = Er5 * (Cos(Angulo_Llanta5 * Grados) ^ 2)
KX = Er6 * (Cos(Angulo_Llanta6 * Grados) ^ 2)

'COMPONENTE X ESFUERZO TANGENCIAL
BX = Et1 * (Sin(Angulo_Llanta1 * Grados) ^ 2)
DX = Et2 * (Sin(Angulo_Llanta2 * Grados) ^ 2)
FX = Et3 * (Sin(Angulo_Llanta3 * Grados) ^ 2)
HX = Et4 * (Sin(Angulo_Llanta4 * Grados) ^ 2)
JX = Et5 * (Sin(Angulo_Llanta5 * Grados) ^ 2)
LX = Et6 * (Sin(Angulo_Llanta6 * Grados) ^ 2)

'Esfuerzo normal y
'COMPONENTE Y ESFUERZO RADIAL
AY = Er1 * (Sin(Angulo_Llanta1 * Grados) ^ 2)
CY = Er2 * (Sin(Angulo_Llanta2 * Grados) ^ 2)
EY = Er3 * (Sin(Angulo_Llanta3 * Grados) ^ 2)
GY = Er4 * (Sin(Angulo_Llanta4 * Grados) ^ 2)
IY = Er5 * (Sin(Angulo_Llanta5 * Grados) ^ 2)
KY = Er6 * (Sin(Angulo_Llanta6 * Grados) ^ 2)

'COMPONENTE Y ESFUERZO TANGENCIAL
BY = Et1 * (Cos(Angulo_Llanta1 * Grados) ^ 2)
DY = Et2 * (Cos(Angulo_Llanta2 * Grados) ^ 2)
FY = Et3 * (Cos(Angulo_Llanta3 * Grados) ^ 2)
HY = Et4 * (Cos(Angulo_Llanta4 * Grados) ^ 2)
JY = Et5 * (Cos(Angulo_Llanta5 * Grados) ^ 2)
LY = Et6 * (Cos(Angulo_Llanta6 * Grados) ^ 2)

'ESFUERZO CORTANTE XY
O = (Er1 - Et1) * (Sin(Angulo_Llanta1 * Grados)) * (Cos(Angulo_Llanta1 * Grados))
P = (Er2 - Et2) * (Sin(Angulo_Llanta2 * Grados)) * (Cos(Angulo_Llanta2 * Grados))
Q = (Er3 - Et3) * (Sin(Angulo_Llanta3 * Grados)) * (Cos(Angulo_Llanta3 * Grados))
R = (Er4 - Et4) * (Sin(Angulo_Llanta4 * Grados)) * (Cos(Angulo_Llanta4 * Grados))
S = (Er5 - Et5) * (Sin(Angulo_Llanta5 * Grados)) * (Cos(Angulo_Llanta5 * Grados))
T = (Er6 - Et6) * (Sin(Angulo_Llanta6 * Grados)) * (Cos(Angulo_Llanta6 * Grados))


Select Case tipoeje
    Case "Sencillo"
        Enormal_x = AX + BX
        Enormal_y = AY + BY
        Ecortante_xy = O
    Case "Sencillo Dual"
        Enormal_x = AX + BX + CX + DX
        Enormal_y = AY + BY + CY + DY
        Ecortante_xy = O + P
    Case "Tandem"
        Enormal_x = AX + BX + CX + DX + EX + FX + GX + HX
        Enormal_y = AY + BY + CY + DY + EY + FY + GY + HY
        Ecortante_xy = O + P + Q + R
    Case "Tridem"
        Enormal_x = AX + BX + CX + DX + EX + FX + GX + HX + IX + JX + KX + LX
        Enormal_y = AY + BY + CY + DY + EY + FY + GY + HY + IY + JY + KY + LY
        Ecortante_xy = O + P + Q + R + S + T
    Case "Medio Tridem"
        Enormal_x = AX + BX + EX + FX + IX + JX
        Enormal_y = AY + BY + EY + FY + IY + JY
        Ecortante_xy = O + Q + S
End Select

Sheets("calculos").Cells(2 + i, 23) = Enormal_x
Sheets("calculos").Cells(2 + i, 24) = Enormal_y
Sheets("calculos").Cells(2 + i, 25) = Ecortante_xy

Next i

'DEFORMACIÓN POR TENSIÓN

Poisson = Sheets("calculos").Cells(3, 5)

For i = 1 To 2 'NumCapas - 1
    EvZ = Sheets("calculos").Cells(i + 2, 10)
    ModElastico(i) = Sheets("calculos").Cells(i + 2, 3)
    Enormal_x = Sheets("calculos").Cells(i + 2, 23)
    Enormal_y = Sheets("calculos").Cells(i + 2, 24)
    Ecortante_xy = Sheets("calculos").Cells(i + 2, 25)
           
    DEFx = (1 / ModElastico(i)) * (Enormal_x - (Poisson * (Enormal_y + EvZ)))
    DEFy = (1 / ModElastico(i)) * (Enormal_y - (Poisson * (Enormal_x + EvZ)))
    GAMAxy = (2 * (1 + Poisson) * Ecortante_xy) / ModElastico(i)
    
    DefTension = ((DEFx + DEFy) / 2) - ((((DEFx - DEFy) / 2) ^ 2) + GAMAxy ^ 2) ^ 0.5
    Sheets("calculos").Cells(2 + i, 30) = DefTension

'calculados con damy
EvZ_damy = Sheets("calculos").Cells(i + 5, 10)
DEFx_damy = (1 / ModElastico(i)) * (Enormal_x - (Poisson * (Enormal_y + EvZ_damy)))
DEFy_damy = (1 / ModElastico(i)) * (Enormal_y - (Poisson * (Enormal_x + EvZ_damy)))
DefTension_damy = ((DEFx_damy + DEFy_damy) / 2) - ((((DEFx_damy - DEFy_damy) / 2) ^ 2) + GAMAxy ^ 2) ^ 0.5
Sheets("calculos").Cells(5 + i, 30) = DefTension_damy

Next i


End Sub
