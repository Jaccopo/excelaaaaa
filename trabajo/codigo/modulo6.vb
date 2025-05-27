' verificar si hace falta borrar celdas antes de iniciar el ciclo
'__________________________________________________________________________________________
 
'he = EspesorEquivalente(NumCapas, ModElastico(), Espesor(), Poisson())
'Public Function EspesorEquivalente(NumCapas As Integer, ModElastico() As Double, Espesor() As Double, Poisson() As Double) As Variant()
'
' Dim Interfaces()
' Dim EspEquiv() As Double
'
' If NumCapas = 2 Then
' FCorreccion = 0.9
' Else
' FCorreccion = 1
' End If
'
'he() = FCorreccion() * Espesor() * (ModElastico() / ModElastico()) ^ (1 / 3)
'
' End Function
'___________________________________________________________________________________________

'ReDim Entrada(fin - inicio)
'P = percentil(Entrada(), n(), nseg)
'Public Function percentil(Entrada() As Double, n() As Integer, nseg As Integer) As Variant()
'Dim percentiles()
'   percentiles(i) = yk + D * (yk1 - yk)
'   End If
'     If n(i) = 1 Then percentiles(i) = Entrada(h - 1)
'   marca = 0
' Next i
'percentil = percentiles
'End Function
'________________________________________________________________________________________________

Sub GraficaEspectrosCarga()
Call espectros
'aun no se usa esta sub
End Sub
Sub PresentaGrafico1()
    Sheets("Espectro Daño Deformacion").Select
End Sub
Sub PresentaGrafico2()
    Sheets("Espectro Daño Fatiga").Select
End Sub
Sub PresentaGrafico3()
    Sheets("Daño Acumulado Deformacion").Select
End Sub
Sub PresentaGrafico4()
    Sheets("Daño Acumulado Fatiga").Select
End Sub
Sub RegresarDeGrafico()
Sheets("Análisis Espectral").Select
End Sub
    

