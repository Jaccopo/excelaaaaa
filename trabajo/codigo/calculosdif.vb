Sub CalculosDef()

Dim PesoEje As Double
Dim tipoeje As String
Dim NumLlantas As Integer
Dim PesoNeum As Double
Dim RadioContacto As Double
Dim Pi As Double
Dim PresionInflado As Double
Dim NumCapas As Integer
Dim Espesor() As Double
Dim ModElastico() As Double
Dim Poisson As Double
Dim Fcorreccion() As Double
Dim i, u As Integer
Dim he() As Double

  Worksheets("NuevoFormatoPav").Range("I14:N15").ClearContents
  Worksheets("calculos").Range("A3:AD4").ClearContents
  Worksheets("calculos").Range("C18:F23").ClearContents
  Worksheets("calculos").Range("C26:F29").ClearContents
  Worksheets("calculos").Range("C31:D36").ClearContents
  Worksheets("calculos").Range("C41:F46").ClearContents
          
          
    Pi = 3.14159265358979
    PesoEje = Sheets("NuevoFormatoPav").Cells(7, 6)     'inputs
    tipoeje = Sheets("NuevoFormatoPav").Cells(8, 6)     'inputs
    PresionInflado = Sheets("avanzado").Cells(2, 2)     'inputs
    NumCapas = Sheets("NuevoFormatoPav").Cells(10, 6)   'inputs

ReDim Espesor(NumCapas) 'ya
ReDim ModElastico(NumCapas)'ya
ReDim Fcorreccion(NumCapas)'ya
ReDim he(NumCapas - 1)'ya
'porque esta mamada esta asi alv, ya lavi xdxd aaaaaaaaaaaaaaaaaa porque tanto odio pipipi
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

Sheets("calculos").Cells(3, 6) = RadioContacto
Sheets("calculos").Cells(3, 5) = Poisson
Sheets("calculos").Cells(3, 4) = PesoNeum
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

u = 1
For i = 1 To NumCapas - 1
    he(i) = Fcorreccion(i) * (Espesor(i) + he(i - 1)) * (ModElastico(i) / ModElastico(i + 1)) ^ (1 / 3)
        If i = 1 Or i = NumCapas - 1 Then               'controla que solo haya dos interfaces
            Sheets("calculos").Cells(2 + u, 7) = he(i) ' sirve para anotar los espesores equivalentes de las interfaces
            u = u + 1
        End If
Next i

If NumCapas = 2 Then                                    'en caso de haber solo dos capas reescribe el segundo espesor
Sheets("calculos").Cells(4, 7) = Sheets("calculos").Cells(3, 7)'auxiliar 1
End If


Sheets("calculos").Cells(3, 3) = ModElastico(2)  ' anota siempre el segundo módulo
Sheets("calculos").Cells(4, 3) = ModElastico(NumCapas) ' anota el módulo de la capa inferior
 
Call Coordenadas
Call Factores_Ahlvin
Call DeflexionCircular

  Select Case tipoeje
    
    Case "Sencillo"
        
        Call EsfuerzosEjeSencillo
        Call DeformacionesUnitarias
        Call DeformacionesTension
        Call DeformacionesVerticales
        
    Case "Sencillo Dual"
        
        
        If PesoEje <= 2 Then
            Call EsfuerzoVerticalPuntual
            Call EsfuerzoRadialPuntual
            Call EsfuerzoTangencialPuntual
        Else
            Call EsfuerzosEjeSencilloDual
        End If
        
        Call DeformacionesUnitarias
        Call DeformacionesTension
        Call DeformacionesVerticales
                
    Case "Tandem"
        'Call DeflexionPuntual
        
        If PesoEje <= 8 Then
            Call EsfuerzoVerticalPuntual
            Call EsfuerzoRadialPuntual
            Call EsfuerzoTangencialPuntual
        Else
            Call EsfuerzosEjeTandem
        End If
        
        Call DeformacionesUnitarias
        Call DeformacionesTension
        Call DeformacionesVerticales
        
    Case "Tridem"
        
        If PesoEje <= 10 Then
            Call EsfuerzoVerticalPuntual
            Call EsfuerzoRadialPuntual
            Call EsfuerzoTangencialPuntual
        Else
           Call EsfuerzosEjeTridem
        End If
        
        Call DeformacionesUnitarias
        Call DeformacionesTension
        Call DeformacionesVerticales
               
    Case "Medio Tridem"
        
        If PesoEje <= 10 Then
            Call EsfuerzoVerticalPuntual
            Call EsfuerzoRadialPuntual
            Call EsfuerzoTangencialPuntual
        Else
           Call EsfuerzosEjeMedioTridem
        End If
        
        Call DeformacionesUnitarias
        Call DeformacionesTension
        Call DeformacionesVerticales
               
               
    End Select
 
 Call PrintResIndividual
 
 
 End Sub