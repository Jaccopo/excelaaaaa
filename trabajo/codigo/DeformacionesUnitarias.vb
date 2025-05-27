Sub DeformacionesUnitarias()

Dim i As Integer
Dim NumCapas As Integer
Dim Poisson As Double
Dim Ez, Er, Et As Double
Dim Epsilon_z, Epsilon_r, Epsilon_t As Double
Dim ModElastico() As Double
Dim Ez_damy As Double
Dim Epsilon_z_damy As Double

NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
Poisson = Sheets("calculos").Cells(3, 5)
ReDim ModElastico(NumCapas)

For i = 1 To 2 ' NumCapas - 1

Ez = Sheets("calculos").Cells(i + 2, 10)
Er = Sheets("calculos").Cells(i + 2, 11)
Et = Sheets("calculos").Cells(i + 2, 12)
ModElastico(i) = Sheets("calculos").Cells(i + 2, 3)

Epsilon_z = (1 / ModElastico(i)) * (Ez - (Poisson * (Er + Et)))
Epsilon_r = (1 / ModElastico(i)) * (Er - (Poisson * (Ez + Et)))
Epsilon_t = (1 / ModElastico(i)) * (Et - (Poisson * (Ez + Er)))

'calculo con damy
Ez_damy = Sheets("calculos").Cells(i + 5, 10)
Epsilon_z_damy = (1 / ModElastico(i)) * (Ez_damy - (Poisson * (Er + Et)))
Sheets("calculos").Cells(i + 5, 13) = Epsilon_z_damy


Sheets("calculos").Cells(i + 2, 13) = Epsilon_z
Sheets("calculos").Cells(i + 2, 14) = Epsilon_r
Sheets("calculos").Cells(i + 2, 15) = Epsilon_t

Next i

End Sub
