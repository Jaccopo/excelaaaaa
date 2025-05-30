Sub Coordenadas()

Dim R11, R21, R31, R41 As Double

Dim X As Double
Dim Y As Double
Dim S As Double
Dim D As Double

Dim r_Llanta1 As Double
Dim r_LLanta2 As Double
Dim r_LLanta3 As Double
Dim r_LLanta4 As Double
Dim r_LLanta5 As Double
Dim r_LLanta6 As Double

Dim R_LLanta1_Capa1 As Double
Dim R_LLanta2_Capa1 As Double
Dim R_LLanta3_Capa1 As Double
Dim R_LLanta4_Capa1 As Double
Dim R_LLanta5_Capa1 As Double
Dim R_LLanta6_Capa1 As Double

Dim R_LLanta1_TNatur As Double
Dim R_LLanta2_TNatur As Double
Dim R_LLanta3_TNatur As Double
Dim R_LLanta4_TNatur As Double
Dim R_LLanta5_TNatur As Double
Dim R_LLanta6_TNatur As Double

Dim Angulo_Llanta1 As Double
Dim Angulo_Llanta2 As Double
Dim Angulo_Llanta3 As Double
Dim Angulo_Llanta4 As Double
Dim Angulo_Llanta5 As Double
Dim Angulo_Llanta6 As Double

Dim F_E_Vertical_ra_11 As Double
Dim F_E_Vertical_ra_12 As Double
Dim F_E_Vertical_ra_13 As Double
Dim F_E_Vertical_ra_14 As Double

Dim interfaz() As Double
Dim R_Contacto_Circular As Double  'verificar si puede ser una variable global
Dim NumCapas As Integer            'verificar si puede ser una variable global
Dim i As Integer


X = Sheets("Avanzado").Cells(17, 2)
Y = Sheets("Avanzado").Cells(18, 2)
S = Sheets("Avanzado").Cells(17, 4)
D = Sheets("Avanzado").Cells(18, 4)
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6) 'debe enviarse el numero de capas que ya existe
R_Contacto_Circular = Sheets("calculos").Cells(3, 6)   'debe enviarse el radio de contacto circular


ReDim interfaz(NumCapas)
'escribir un código donde si existen más interfaces, la ultima interfaz la ponga en interfaz 2

For i = 1 To 2 'NumCapas - 1
interfaz(i) = Sheets("calculos").Cells(2 + i, 7)
Next i

r_Llanta1 = (X ^ 2 + Y ^ 2) ^ 0.5
r_LLanta2 = (X ^ 2 + (Y - S) ^ 2) ^ 0.5
r_LLanta3 = ((X - D) ^ 2 + Y ^ 2) ^ 0.5
r_LLanta4 = ((X - D) ^ 2 + (Y - S) ^ 2) ^ 0.5
r_LLanta5 = ((X - 2 * D) ^ 2 + Y ^ 2) ^ 0.5
r_LLanta6 = ((X - 2 * D) ^ 2 + (Y - S) ^ 2) ^ 0.5

R_LLanta1_Capa1 = (r_Llanta1 ^ 2 + interfaz(1) ^ 2) ^ 0.5
R_LLanta2_Capa1 = (r_LLanta2 ^ 2 + interfaz(1) ^ 2) ^ 0.5
R_LLanta3_Capa1 = (r_LLanta3 ^ 2 + interfaz(1) ^ 2) ^ 0.5
R_LLanta4_Capa1 = (r_LLanta4 ^ 2 + interfaz(1) ^ 2) ^ 0.5
R_LLanta5_Capa1 = (r_LLanta5 ^ 2 + interfaz(1) ^ 2) ^ 0.5
R_LLanta6_Capa1 = (r_LLanta6 ^ 2 + interfaz(1) ^ 2) ^ 0.5

R_LLanta1_TNatur = (r_Llanta1 ^ 2 + interfaz(2) ^ 2) ^ 0.5 'aquí en caso de ampliar las interfaces deberá cambiarse
R_LLanta2_TNatur = (r_LLanta2 ^ 2 + interfaz(2) ^ 2) ^ 0.5 'el índice
R_LLanta3_TNatur = (r_LLanta3 ^ 2 + interfaz(2) ^ 2) ^ 0.5
R_LLanta4_TNatur = (r_LLanta4 ^ 2 + interfaz(2) ^ 2) ^ 0.5
R_LLanta5_TNatur = (r_LLanta5 ^ 2 + interfaz(2) ^ 2) ^ 0.5
R_LLanta6_TNatur = (r_LLanta6 ^ 2 + interfaz(2) ^ 2) ^ 0.5

If X = 0 Then
Angulo_Llanta1 = 90
Angulo_Llanta2 = 90
Else
Angulo_Llanta1 = Atn(Abs(Y) / X) / 1.74532925199433E-02
Angulo_Llanta2 = Atn(Abs((Y - S) / X)) / 1.74532925199433E-02
End If

If X = D Then
Angulo_Llanta3 = 90
Angulo_Llanta4 = 90
Else
Angulo_Llanta3 = Atn(Abs(Y / (X - D))) / 1.74532925199433E-02
Angulo_Llanta4 = Atn(Abs((Y - S) / (X - D))) / 1.74532925199433E-02
End If

If X = 2 * D Then
Angulo_Llanta5 = 90
Angulo_Llanta6 = 90
Else
Angulo_Llanta5 = Atn(Abs(Y / (X - 2 * D))) / 1.74532925199433E-02
Angulo_Llanta6 = Atn(Abs((Y - S) / (X - 2 * D))) / 1.74532925199433E-02
End If


'r/a de las cuatro llantas, es probable que esto no se use solo es para ahlvin
R11 = r_Llanta1 / R_Contacto_Circular
R21 = r_LLanta2 / R_Contacto_Circular
R31 = r_LLanta3 / R_Contacto_Circular
R41 = r_LLanta4 / R_Contacto_Circular

If R11 < 14 Then
F_E_Vertical_ra_11 = R11
Else
F_E_Vertical_ra_11 = 13.999
End If
If R21 < 14 Then
F_E_Vertical_ra_12 = R21
Else
F_E_Vertical_ra_12 = 13.999
End If
If R31 < 14 Then
F_E_Vertical_ra_13 = R31
Else
F_E_Vertical_ra_13 = 13.999
End If
If R41 < 14 Then
F_E_Vertical_ra_14 = R41
Else
F_E_Vertical_ra_14 = 13.999
End If

Sheets("calculos").Cells(18, 3) = Angulo_Llanta1
Sheets("calculos").Cells(19, 3) = Angulo_Llanta2
Sheets("calculos").Cells(20, 3) = Angulo_Llanta3
Sheets("calculos").Cells(21, 3) = Angulo_Llanta4
Sheets("calculos").Cells(22, 3) = Angulo_Llanta5
Sheets("calculos").Cells(23, 3) = Angulo_Llanta6
Sheets("calculos").Cells(18, 4) = r_Llanta1
Sheets("calculos").Cells(19, 4) = r_LLanta2
Sheets("calculos").Cells(20, 4) = r_LLanta3
Sheets("calculos").Cells(21, 4) = r_LLanta4
Sheets("calculos").Cells(22, 4) = r_LLanta5
Sheets("calculos").Cells(23, 4) = r_LLanta6
Sheets("calculos").Cells(18, 5) = R_LLanta1_Capa1
Sheets("calculos").Cells(19, 5) = R_LLanta2_Capa1
Sheets("calculos").Cells(20, 5) = R_LLanta3_Capa1
Sheets("calculos").Cells(21, 5) = R_LLanta4_Capa1
Sheets("calculos").Cells(22, 5) = R_LLanta5_Capa1
Sheets("calculos").Cells(23, 5) = R_LLanta6_Capa1
Sheets("calculos").Cells(18, 6) = R_LLanta1_TNatur
Sheets("calculos").Cells(19, 6) = R_LLanta2_TNatur
Sheets("calculos").Cells(20, 6) = R_LLanta3_TNatur
Sheets("calculos").Cells(21, 6) = R_LLanta4_TNatur
Sheets("calculos").Cells(22, 6) = R_LLanta5_TNatur
Sheets("calculos").Cells(23, 6) = R_LLanta6_TNatur


Sheets("calculos").Cells(26, 3) = F_E_Vertical_ra_11 'estas son las r/a
Sheets("calculos").Cells(27, 3) = F_E_Vertical_ra_12 'estas son las r/a
Sheets("calculos").Cells(28, 3) = F_E_Vertical_ra_13 'estas son las r/a
Sheets("calculos").Cells(29, 3) = F_E_Vertical_ra_14 'estas son las r/a

End Sub
