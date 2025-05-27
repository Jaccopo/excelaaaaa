Sub VolumenTransito()

Dim A, B2, B3, C2, C3, T3S2, T3S3, T3S2R4, otros As Double
Dim Tasa, TDPA, años, FDS, FDC As Double
Dim CoefAcumTrans, TTotalAcum, Trans_Prom_anual As Double
Dim Porcentaje_sencillo, Porcentaje_dual, Porcentaje_tandem, Porcentaje_tridem As Double
Dim RepEjes_sencillo, RepEjes_dual, RepEjes_tandem, RepEjes_tridem As Double
Dim NumEjes_Sencillos_año, NumEjes_dual_año, NumEjes_tandem_año, NumEjes_tridem_año As Double
Dim TransProm_anual_carga, Total_ejes As Double
Dim SumaPorcentajes As Double
  
Tasa = CDbl(Sheets("Tránsito").Tasa1.Text)
años = CDbl(Sheets("Tránsito").años1.Text)
TDPA = CDbl(Sheets("Tránsito").TDPA1.Text)
FDS = CDbl(Sheets("Tránsito").FDS1.Text)
FDC = CDbl(Sheets("Tránsito").FDC1.Text)
A = CDbl(Sheets("Tránsito").vehiculoA.Text)
B2 = CDbl(Sheets("Tránsito").VehiculoB2.Text)
B3 = CDbl(Sheets("Tránsito").VehiculoB3.Text)
C2 = CDbl(Sheets("Tránsito").VehiculoC2.Text)
C3 = CDbl(Sheets("Tránsito").vehiculoC3.Text)
T3S2 = CDbl(Sheets("Tránsito").VehiculoT3S2.Text)
T3S3 = CDbl(Sheets("Tránsito").VehiculoT3S3.Text)
T3S2R4 = CDbl(Sheets("Tránsito").VehiculoT3S2R4.Text)
otros = CDbl(Sheets("Tránsito").otros.Text)
SumaPorcentajes = A + B2 + B3 + C2 + C3 + T3S2 + T3S3 + T3S2R4 + otros

'If SumaPorcentajes <> 100 Then
'MsgBox ("La suma de pocentajes debe sumar 100")
'End If
 
Tasa = Tasa / 100

CoefAcumTrans = 365 * (((1 + CDbl(Tasa)) ^ años - 1) / Tasa)
MsgBox (CoefAcumTrans)
TTotalAcum = TDPA * CoefAcumTrans * FDS * FDC
Trans_Prom_anual = TTotalAcum / años
      
Porcentaje_sencillo = (B2 + B3 + C2 + C3 + T3S2 + T3S3 + T3S2R4) / 100
Porcentaje_dual = (B2 + C2) / 100
Porcentaje_tandem = (B3 + C3 + T3S2 * 2 + T3S3 + T3S2R4 * 4) / 100
Porcentaje_tridem = T3S3 / 100

RepEjes_sencillo = TTotalAcum * Porcentaje_sencillo
RepEjes_dual = TTotalAcum * Porcentaje_dual
RepEjes_tandem = TTotalAcum * Porcentaje_tandem
RepEjes_tridem = TTotalAcum * Porcentaje_tridem

NumEjes_Sencillos_año = RepEjes_sencillo / años
NumEjes_dual_año = RepEjes_dual / años
NumEjes_tandem_año = RepEjes_tandem / años
NumEjes_tridem_año = RepEjes_tridem / años

TransProm_anual_carga = RepEjes_sencillo / años
Total_ejes = NumEjes_Sencillos_año + NumEjes_dual_año + NumEjes_tandem_año + NumEjes_tridem_año

'Sheets("Tránsito").Cells(17, 8) = TTotalAcum
'Sheets("Tránsito").Cells(18, 8) = Trans_Prom_anual
'Sheets("Tránsito").Cells(19, 8) = TransProm_anual_carga
Sheets("Tránsito").Cells(20, 7) = NumEjes_Sencillos_año
Sheets("Tránsito").Cells(21, 7) = NumEjes_dual_año
Sheets("Tránsito").Cells(22, 7) = NumEjes_tandem_año
Sheets("Tránsito").Cells(23, 7) = NumEjes_tridem_año
Sheets("Tránsito").Cells(20, 5) = 100 * NumEjes_Sencillos_año / Total_ejes
Sheets("Tránsito").Cells(21, 5) = 100 * NumEjes_dual_año / Total_ejes
Sheets("Tránsito").Cells(22, 5) = 100 * NumEjes_tandem_año / Total_ejes
Sheets("Tránsito").Cells(23, 5) = 100 * NumEjes_tridem_año / Total_ejes
Sheets("Tránsito").Cells(23, 11) = SumaPorcentajes

End Sub
