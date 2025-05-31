Sub Esfuerzos_Damy(tipoeje, NumCapa)

Dim xp As Double
Dim yp As Double
Dim X(1 To 13) As Double
Dim Y(1 To 13) As Double
Dim xprima(1 To 13) As Double
Dim yprima(1 To 13) As Double
Dim Teta(1 To 2, 1 To 12) As Double
Dim C(1 To 2, 1 To 12) As Double
Dim F(1 To 12) As Double
Dim A(1 To 12) As Double
Dim L(1 To 12) As Double
Dim B(1 To 2, 1 To 12) As Double
Dim W(1 To 2, 1 To 12) As Double
Dim j(1 To 2, 1 To 12) As Double
Dim n(1 To 2, 1 To 12) As Double
Dim Pi As Double
Dim Q As Double
Dim i As Integer
Dim z As Double
Dim radio As Double
Dim parcial, suma, sigmaZ As Double
Dim num_de_calculos As Integer
Dim calculos As Integer

Dim posicionx(1 To 6) As Double
Dim posiciony(1 To 6) As Double
Dim llantax(1 To 6) As Double
Dim llantay(1 To 6) As Double
Dim S As Double
Dim D As Double
Dim xq As Double
Dim yq As Double
Dim sumaporllanta As Double


'datos de entrada
Q = 6.895 * Sheets("NuevoFormatoPav").Cells(9, 6)
radio = Sheets("calculos").Cells(3, 6) / 100
xq = Sheets("avanzado").Cells(17, 2) / 100
yq = Sheets("avanzado").Cells(18, 2) / 100
S = Sheets("avanzado").Cells(17, 4) / 100
D = Sheets("avanzado").Cells(18, 4) / 100

llantax(1) = 0
llantax(2) = 0
llantax(3) = D
llantax(4) = D
llantax(5) = 2 * D
llantax(6) = 2 * D
llantay(1) = 0
llantay(2) = S
llantay(3) = 0
llantay(4) = S
llantay(5) = 0
llantay(6) = S

Select Case tipoeje
    Case "Sencillo"
        calculos = 1
    Case "Sencillo Dual"
        calculos = 2
    Case "Tandem"
        calculos = 4
    Case "Tridem"
        calculos = 6
End Select


z = Sheets("calculos").Cells(2 + NumCapa, 7) / 100

Pi = 3.14159265358979
sumaporllanta = 0
    
    For num_de_calculos = 1 To calculos
      
        posicionx(num_de_calculos) = Abs(llantax(num_de_calculos) - xq)
        posiciony(num_de_calculos) = Abs(llantay(num_de_calculos) - yq)
      
      xp = posicionx(num_de_calculos)
      yp = posiciony(num_de_calculos)
      
        For i = 1 To 12
            
            X(i) = radio * Cos((i - 1) * Pi / 6)
            Y(i) = radio * Sin((i - 1) * Pi / 6)
            X(1 + i) = radio * Cos(i * Pi / 6)
            Y(1 + i) = radio * Sin(i * Pi / 6)
            
            xprima(i) = X(i) - xp
            xprima(i + 1) = X(i + 1) - xp
            yprima(i) = Y(i) - yp
            yprima(i + 1) = Y(i + 1) - yp
            
            F(i) = xprima(i) * yprima(i + 1) - xprima(i + 1) * yprima(i)
            L(i) = ((xprima(i + 1) - xprima(i)) ^ 2 + (yprima(i + 1) - yprima(i)) ^ 2) ^ (1 / 2)
            A(i) = Abs(z * L(i) / F(i))
            C(1, i) = (xprima(i) * (xprima(i + 1) - xprima(i)) + yprima(i) * (yprima(i + 1) - yprima(i))) / F(i)
            C(2, i) = (xprima(i + 1) * (xprima(i + 1) - xprima(i)) + yprima(i + 1) * (yprima(i + 1) - yprima(i))) / F(i)
            
            Teta(1, i) = Atn(C(1, i))
            Teta(2, i) = Atn(C(2, i))
            
            B(1, i) = (A(i) * C(1, i)) / ((1 + ((A(i)) ^ 2) + ((C(1, i)) ^ 2)) ^ (1 / 2))
            B(2, i) = (A(i) * C(2, i)) / ((1 + ((A(i)) ^ 2) + ((C(2, i)) ^ 2)) ^ (1 / 2))
            W(1, i) = (1 * A(i) * C(1, i)) / ((1 + 1 ^ 2 * ((A(i)) ^ 2 + (C(1, i)) ^ 2)) ^ (1 / 2))
            W(2, i) = (2 * A(i) * C(2, i)) / ((1 + 1 ^ 2 * ((A(i)) ^ 2 + (C(2, i)) ^ 2)) ^ (1 / 2))
            j(1, i) = C(1, i) / ((1 + (A(i)) ^ 2) ^ (1 / 2))
            j(2, i) = C(2, i) / ((1 + (A(i)) ^ 2) ^ (1 / 2))
            n(1, i) = (A(i) ^ 2 * C(1, i)) / (1 + A(i) ^ 2 + C(1, i) ^ 2)
            n(2, i) = (A(i) ^ 2 * C(2, i)) / (1 + A(i) ^ 2 + C(2, i) ^ 2)
                          
            parcial = Teta(2, i) - Teta(1, i) - Atn(B(2, i)) + Atn(B(1, i)) + (B(2, i) - B(1, i)) / (A(i) ^ 2 + 1)
            suma = suma + parcial
        
        Next i
      sigmaZ = (Q * suma) / (2 * Pi)
      Sheets("calculos").Cells(30 + num_de_calculos, 2 + NumCapa) = sigmaZ ' es el resultado de un calculo o un area y una profundidad
      suma = 0
      sumaporllanta = sumaporllanta + sigmaZ
    Next num_de_calculos
Sheets("calculos").Cells(5 + NumCapa, 10) = sumaporllanta
End Sub

