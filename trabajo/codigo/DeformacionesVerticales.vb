Sub DeformacionesVerticales()

Dim i As Integer
Dim NumCapas As Integer
Dim ModElastico() As Double
Dim EvZ, Enormal_x, Enormal_y As Double
Dim Poisson As Double
Dim DefVertical As Double
Dim EvZ_damy, DefVertical_damy  As Double

NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
Poisson = Sheets("calculos").Cells(3, 5)

ReDim ModElastico(NumCapas)


For i = 1 To 2 'NumCapas - 1
    ModElastico(i) = Sheets("calculos").Cells(i + 2, 3)
    EvZ = Sheets("calculos").Cells(i + 2, 10)
    Enormal_x = Sheets("calculos").Cells(i + 2, 23)
    Enormal_y = Sheets("calculos").Cells(i + 2, 24)
    
    DefVertical = (1 / ModElastico(i)) * (EvZ - ((Enormal_x + Enormal_y) * Poisson))
    Sheets("calculos").Cells(i + 2, 29) = DefVertical

'calculados con damy
EvZ_damy = Sheets("calculos").Cells(i + 5, 10)
DefVertical_damy = (1 / ModElastico(i)) * (EvZ_damy - ((Enormal_x + Enormal_y) * Poisson))
Sheets("calculos").Cells(i + 5, 29) = DefVertical_damy

Next i

End Sub