Sub DeflexionCircular()
Dim PesoEje As Double '
Dim tipoeje As String '
Dim NumLlantas As Integer '
Dim PesoNeum As Double '
Dim RadioContacto As Double '
Dim Pi As Double '
Dim PresionInflado As Double '
Dim NumCapas As Integer '
Dim Espesor() As Double '
Dim ModElastico() As Double '
Dim Poisson As Double '
Dim Fcorreccion() As Double '
Dim i As Integer '
Dim he(), z(), dz() As Double '
Dim A As Double '
Dim PsiAMpa As Double '
Dim DeflexionTotal As Double '

    Pi = 3.14159265358979
    PesoEje = Sheets("NuevoFormatoPav").Cells(7, 6)     'inputs
    tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)     'inputs
    PresionInflado = Sheets("avanzado").Cells(2, 2)     'inputs
    NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)   'inputs

ReDim Espesor(NumCapas)
ReDim ModElastico(NumCapas)
ReDim Fcorreccion(NumCapas)
ReDim he(NumCapas - 1)
ReDim z(NumCapas)
ReDim dz(NumCapas)

Select Case tipoeje
    Case "Sencillo"
        NumLlantas = 1
    Case "Sencillo Dual"
        NumLlantas = 2
    Case "Tandem"
        NumLlantas = 4
    Case "Tridem"
        NumLlantas = 6
    Case "Medio Tridem"
        NumLlantas = 3
End Select

PesoNeum = PesoEje / (2 * NumLlantas)
RadioContacto = (((PesoNeum * 2204.623) / (Pi * PresionInflado)) ^ 0.5) * 2.54
Poisson = Sheets("NuevoFormatoPav").Cells(11, 6)
                                                           'anotar una condicion donde aplique que solamente
                                                           'funciona odemark para 2 o mas capas
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
            Sheets("calculos").Cells(48 + i, 3) = he(i) ' sirve para anotar los espesores equivalentes de las interfaces
Next i

PsiAMpa = 0.00689475719
DeflexionTotal = 0
A = RadioContacto
For i = 1 To NumCapas

If i <> NumCapas Then

dz(i) = (((1 + Poisson) * PresionInflado * PsiAMpa * A) / (ModElastico(i) * 0.001)) * _
    (1 / (1 + (he(i - 1) / A) ^ 2) ^ 0.5 + (1 - 2 * Poisson) * ((1 + (he(i - 1) / A) ^ 2) ^ 0.5 - he(i - 1) / A)) - _
    (((1 + Poisson) * PresionInflado * PsiAMpa * A) / (ModElastico(i) * 0.001)) * _
    (1 / (1 + ((Espesor(i) + he(i - 1)) / A) ^ 2) ^ 0.5 + (1 - 2 * Poisson) * _
    ((1 + ((Espesor(i) + he(i - 1)) / A) ^ 2) ^ 0.5 - (Espesor(i) + he(i - 1)) / A)) ' ojo con el Mod elastico
Else

dz(i) = (((1 + Poisson) * PresionInflado * PsiAMpa * A) / (ModElastico(i) * 0.001)) * _
    (1 / (1 + (he(i - 1) / A) ^ 2) ^ 0.5 + (1 - 2 * Poisson) * ((1 + (he(i - 1) / A) ^ 2) ^ 0.5 - he(i - 1) / A))

End If
        
   DeflexionTotal = DeflexionTotal + dz(i)
        
Next i
Sheets("calculos").Cells(3, 22) = DeflexionTotal * 10 ' para que de en milimetros, ya que las entradas van en cms

End Sub
