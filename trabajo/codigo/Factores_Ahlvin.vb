
Sub Factores_Ahlvin()

Dim n As Integer
Dim x_inter(), y_inter() As Double
ReDim x_inter(18)
ReDim y_inter(23)
Dim r_sobre_a As Double
Dim z_sobre_a As Double
Dim X As Double
Dim Y As Double
Dim buscax As Integer
Dim buscay As Integer

Dim posx1, posx2, posy1, posy2 As Integer

Dim valorA As Double
Dim valref As Double
Dim valorC As Double
Dim valorI As Double
Dim valorK As Double
Dim llantas As Double

Dim res_interpolacion1 As Double
Dim res_interpolacion2 As Double
Dim res_interpolacion3 As Double

Dim RadioContacto As Double
Dim z As Double

x_inter(1) = 0
x_inter(2) = 0.2
x_inter(3) = 0.4
x_inter(4) = 0.6
x_inter(5) = 0.8
x_inter(6) = 1
x_inter(7) = 1.2
x_inter(8) = 1.5
x_inter(9) = 2
x_inter(10) = 3
x_inter(11) = 4
x_inter(12) = 5
x_inter(13) = 6
x_inter(14) = 7
x_inter(15) = 8
x_inter(16) = 10
x_inter(17) = 12
x_inter(18) = 14
    
y_inter(1) = 0
y_inter(2) = 0.1
y_inter(3) = 0.2
y_inter(4) = 0.3
y_inter(5) = 0.4
y_inter(6) = 0.5
y_inter(7) = 0.6
y_inter(8) = 0.7
y_inter(9) = 0.8
y_inter(10) = 0.9
y_inter(11) = 1
y_inter(12) = 1.2
y_inter(13) = 1.5
y_inter(14) = 2
y_inter(15) = 2.5
y_inter(16) = 3
y_inter(17) = 4
y_inter(18) = 5
y_inter(19) = 6
y_inter(20) = 7
y_inter(21) = 8
y_inter(22) = 9
y_inter(23) = 10

'inicia el ciclo para las cuatro llantas
For llantas = 1 To 4

RadioContacto = Sheets("calculos").Cells(3, 6)
z = Sheets("calculos").Cells(3, 7)

r_sobre_a = Sheets("calculos").Cells(25 + llantas, 3)
' r_sobre_a = Sheets("ANALISIS PAVIMENTO").Cells(22 + llantas, 181) ' verificar como es que daba lo mismo si los valores no cambiaban o si?
z_sobre_a = z / RadioContacto


If r_sobre_a > 14 Then
r_sobre_a = 13.999
End If
If z_sobre_a > 10 Then
z_sobre_a = 9.999
End If


X = r_sobre_a
Y = z_sobre_a
    
    For buscax = 1 To 18
       If X >= x_inter(buscax) And X < x_inter(buscax + 1) Then
            posx1 = buscax
            posx2 = buscax + 1
            Exit For
        Else
        
        End If
    Next buscax

    For buscay = 1 To 23
        If Y >= y_inter(buscay) And Y < y_inter(buscay + 1) Then
            posy1 = buscay
            posy2 = buscay + 1
            Exit For
        Else
        
        End If
    Next buscay

'interpolación para la primera tabla

            valorA = y_inter(buscay)
            valorC = y_inter(buscay + 1)
            valorI = Sheets("factor_e_vert").Cells(posy1 + 2, posx1 + 2)
            valorK = Sheets("factor_e_vert").Cells(posy2 + 2, posx1 + 2)
            
            valref = Y
            
            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorI = Sheets("factor_e_vert").Cells(posy1 + 2, posx2 + 2)
            valorK = Sheets("factor_e_vert").Cells(posy2 + 2, posx2 + 2)
             
            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorA = x_inter(buscax)
            valref = X
            valorC = x_inter(buscax + 1)
            
            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2)
              
            Sheets("calculos").Cells(25 + llantas, 4) = res_interpolacion3

'intepolación para la segunda tabla
  
            valorA = y_inter(buscay)
            valorC = y_inter(buscay + 1)
            valorI = Sheets("factor_e_rad").Cells(posy1 + 2, posx1 + 2)
            valorK = Sheets("factor_e_rad").Cells(posy2 + 2, posx1 + 2)
            
            valref = Y
            
            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorI = Sheets("factor_e_rad").Cells(posy1 + 2, posx2 + 2)
            valorK = Sheets("factor_e_rad").Cells(posy2 + 2, posx2 + 2)
             
            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorA = x_inter(buscax)
            valref = X
            valorC = x_inter(buscax + 1)
            
            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2)
              
            Sheets("calculos").Cells(25 + llantas, 5) = res_interpolacion3

  
'interpolación para la tercera tabla
              
            valorA = y_inter(buscay)
            valorC = y_inter(buscay + 1)
            valorI = Sheets("factor_e_tan").Cells(posy1 + 2, posx1 + 2)
            valorK = Sheets("factor_e_tan").Cells(posy2 + 2, posx1 + 2)
            
            valref = Y
            
            res_interpolacion1 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorI = Sheets("factor_e_tan").Cells(posy1 + 2, posx2 + 2)
            valorK = Sheets("factor_e_tan").Cells(posy2 + 2, posx2 + 2)
             
            res_interpolacion2 = interpola(valorA, valref, valorC, valorI, valorK)
            
            valorA = x_inter(buscax)
            valref = X
            valorC = x_inter(buscax + 1)
            
            res_interpolacion3 = interpola(valorA, valref, valorC, res_interpolacion1, res_interpolacion2)
              
            Sheets("calculos").Cells(25 + llantas, 6) = res_interpolacion3


Next llantas
End Sub
