Sub EsfuerzosEjeMedioTridem()

Dim PsiAKpa As Double
Dim Er1, Er2, Er3, Er4, Er5, Er6 As Double
Dim Et1, Et2, Et3, Et4, Et5, Et6 As Double

Dim PresionInflado As Double
Dim FEV1, FEV2, FEV3, FEV4, FER1, FER2, FER3, FER4, FET1, FET2, FET3, FET4 As Double
Dim X, Y, S, D As Double

Dim NumCapas As Integer
Dim NumCapa As Integer
Dim tipoeje As String


PsiAKpa = 6.894757

X = Sheets("Avanzado").Cells(17, 2)
Y = Sheets("Avanzado").Cells(18, 2)
S = Sheets("Avanzado").Cells(17, 4)
D = Sheets("Avanzado").Cells(18, 4)

PresionInflado = Sheets("NuevoFormatoPav").Cells(9, 6)
NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)
tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)

FEV1 = Sheets("calculos").Cells(26, 4)
FEV2 = Sheets("calculos").Cells(27, 4)
FEV3 = Sheets("calculos").Cells(28, 4)
FEV4 = Sheets("calculos").Cells(29, 4)
FER1 = Sheets("calculos").Cells(26, 5)
FER2 = Sheets("calculos").Cells(27, 5)
FER3 = Sheets("calculos").Cells(28, 5)
FER4 = Sheets("calculos").Cells(29, 5)
FET1 = Sheets("calculos").Cells(26, 6)
FET2 = Sheets("calculos").Cells(27, 6)
FET3 = Sheets("calculos").Cells(28, 6)
FET4 = Sheets("calculos").Cells(29, 6)


'ESFUERZO VERTICAL
   
        If X = D Then
        Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (2 * FEV1 + FEV3)
        Else
        Sheets("calculos").Cells(3, 10) = PsiAKpa * PresionInflado * (FEV1 + FEV3)
        End If
   
    Call E_VERTICAL_PTODOS_MA

    For NumCapa = 1 To 2 'NumCapas - 1
        Call Esfuerzos_Damy(tipoeje, NumCapa)
    Next NumCapa

'ESFUERZO RADIAL
    
        If X = D Then
        Sheets("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (2 * FER1 + FER3)

        Er1 = PsiAKpa * PresionInflado * FER1
        Er2 = PsiAKpa * PresionInflado * FER2
        Er3 = PsiAKpa * PresionInflado * FER3
        Er4 = PsiAKpa * PresionInflado * FER4
        Er5 = PsiAKpa * PresionInflado * FER1
        Er6 = PsiAKpa * PresionInflado * FER2
        
        Else
        
        Sheets("calculos").Cells(3, 11) = PsiAKpa * PresionInflado * (FER1 + FER3)
        Er1 = PsiAKpa * PresionInflado * FER1
        Er2 = PsiAKpa * PresionInflado * FER2
        Er3 = PsiAKpa * PresionInflado * FER3
        Er4 = PsiAKpa * PresionInflado * FER4
        Er5 = 0
        Er6 = 0
       
        End If
    
    Call E_RADIAL_PTODOS_MA

'ESFUERZO TANGENCIAL
    
        If X = D Then
        Sheets("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (2 * FET1 + FET3)

        Et1 = PsiAKpa * PresionInflado * FET1
        Et2 = PsiAKpa * PresionInflado * FET2
        Et3 = PsiAKpa * PresionInflado * FET3
        Et4 = PsiAKpa * PresionInflado * FET4
        Et5 = PsiAKpa * PresionInflado * FET1
        Et6 = PsiAKpa * PresionInflado * FET2
        
        Else
        Sheets("calculos").Cells(3, 12) = PsiAKpa * PresionInflado * (FET1 + FET3)

        Et1 = PsiAKpa * PresionInflado * FET1
        Et2 = PsiAKpa * PresionInflado * FET2
        Et3 = PsiAKpa * PresionInflado * FET3
        Et4 = PsiAKpa * PresionInflado * FET4
        Et5 = 0
        Et6 = 0
     
        End If
    
    Call E_TANGENCIAL_PTODOS_MA

    Sheets("calculos").Cells(41, 3) = Er1
    Sheets("calculos").Cells(42, 3) = Er2
    Sheets("calculos").Cells(43, 3) = Er3
    Sheets("calculos").Cells(44, 3) = Er4
    Sheets("calculos").Cells(45, 3) = Er5
    Sheets("calculos").Cells(46, 3) = Er6
    Sheets("calculos").Cells(41, 5) = Et1
    Sheets("calculos").Cells(42, 5) = Et2
    Sheets("calculos").Cells(43, 5) = Et3
    Sheets("calculos").Cells(44, 5) = Et4
    Sheets("calculos").Cells(45, 5) = Et5
    Sheets("calculos").Cells(46, 5) = Et6


End Sub

