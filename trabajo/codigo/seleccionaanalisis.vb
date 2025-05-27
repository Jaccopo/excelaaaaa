Sub SeleccionaAnalisis()
Dim TipoAnalisis As String

TipoAnalisis = Sheets("Avanzado").Cells(3, 4)

Select Case TipoAnalisis
          
          Case Is = "Individual"
          
            Sheets("NuevoFormatoPav").Cells(7, 6) = Sheets("Análisis Individual").Cells(7, 6)
           ' Sheets("NuevoFormatoPav").Cells(8, 6) el tipo de eje se actualiza al cambiar el combobox
            Sheets("NuevoFormatoPav").Cells(9, 6) = Sheets("Avanzado").Cells(2, 2)
            Sheets("NuevoFormatoPav").Cells(10, 6) = Sheets("Análisis Individual").Cells(10, 6)
            Sheets("NuevoFormatoPav").Cells(11, 6) = Sheets("Avanzado").Cells(3, 2)
            Sheets("NuevoFormatoPav").Cells(14, 4) = Sheets("Análisis Individual").Cells(14, 4)
            Sheets("NuevoFormatoPav").Cells(15, 4) = Sheets("Análisis Individual").Cells(15, 4)
            Sheets("NuevoFormatoPav").Cells(16, 4) = Sheets("Análisis Individual").Cells(16, 4)
            Sheets("NuevoFormatoPav").Cells(17, 4) = Sheets("Análisis Individual").Cells(17, 4)
            Sheets("NuevoFormatoPav").Cells(18, 4) = Sheets("Análisis Individual").Cells(18, 4)
            Sheets("NuevoFormatoPav").Cells(19, 4) = Sheets("Análisis Individual").Cells(19, 4)
            Sheets("NuevoFormatoPav").Cells(20, 4) = Sheets("Análisis Individual").Cells(20, 4)
            Sheets("NuevoFormatoPav").Cells(21, 4) = Sheets("Análisis Individual").Cells(21, 4)
            Sheets("NuevoFormatoPav").Cells(22, 4) = Sheets("Análisis Individual").Cells(22, 4)
            Sheets("NuevoFormatoPav").Cells(23, 4) = Sheets("Análisis Individual").Cells(23, 4)
            Sheets("NuevoFormatoPav").Cells(14, 5) = Sheets("Análisis Individual").Cells(14, 5)
            Sheets("NuevoFormatoPav").Cells(15, 5) = Sheets("Análisis Individual").Cells(15, 5)
            Sheets("NuevoFormatoPav").Cells(16, 5) = Sheets("Análisis Individual").Cells(16, 5)
            Sheets("NuevoFormatoPav").Cells(17, 5) = Sheets("Análisis Individual").Cells(17, 5)
            Sheets("NuevoFormatoPav").Cells(18, 5) = Sheets("Análisis Individual").Cells(18, 5)
            Sheets("NuevoFormatoPav").Cells(19, 5) = Sheets("Análisis Individual").Cells(19, 5)
            Sheets("NuevoFormatoPav").Cells(20, 5) = Sheets("Análisis Individual").Cells(20, 5)
            Sheets("NuevoFormatoPav").Cells(21, 5) = Sheets("Análisis Individual").Cells(21, 5)
            Sheets("NuevoFormatoPav").Cells(22, 5) = Sheets("Análisis Individual").Cells(22, 5)
            Sheets("NuevoFormatoPav").Cells(23, 5) = Sheets("Análisis Individual").Cells(23, 5)
            Sheets("NuevoFormatoPav").Cells(14, 6) = Sheets("Análisis Individual").Cells(14, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(15, 6) = Sheets("Análisis Individual").Cells(15, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(16, 6) = Sheets("Análisis Individual").Cells(16, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(17, 6) = Sheets("Análisis Individual").Cells(17, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(18, 6) = Sheets("Análisis Individual").Cells(18, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(19, 6) = Sheets("Análisis Individual").Cells(19, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(20, 6) = Sheets("Análisis Individual").Cells(20, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(21, 6) = Sheets("Análisis Individual").Cells(21, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(22, 6) = Sheets("Análisis Individual").Cells(22, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(23, 6) = Sheets("Análisis Individual").Cells(23, 6) * 1000
            Sheets("Avanzado").Cells(17, 2) = Sheets("Análisis Individual").Cells(10, 10)
            Sheets("Avanzado").Cells(18, 2) = Sheets("Análisis Individual").Cells(11, 10)
            Sheets("Avanzado").Cells(17, 4) = Sheets("Análisis Individual").Cells(8, 10)
            Sheets("Avanzado").Cells(18, 4) = Sheets("Análisis Individual").Cells(7, 10)
          
          'estas cuatro lineas siguientes hacen que no se pueda mover la geometría del eje sustitur a su debido tiempo
          
            Sheets("Avanzado").Cells(17, 4) = 36
            Sheets("Avanzado").Cells(18, 4) = 122
            Sheets("Análisis Individual").Cells(7, 10) = 122
            Sheets("Análisis Individual").Cells(8, 10) = 36
          
          
          
          Call DibujaDiagrama
          
          If Sheets("Análisis Individual").Cells(10, 6) <> 1 Then
          Call CalculosDef
          End If
           
          
          Case Is = "Espectral"

            Sheets("NuevoFormatoPav").Cells(9, 6) = Sheets("Avanzado").Cells(2, 2)
            Sheets("NuevoFormatoPav").Cells(10, 6) = Sheets("Análisis Espectral").Cells(7, 6)
            Sheets("NuevoFormatoPav").Cells(11, 6) = Sheets("Avanzado").Cells(3, 2)
            Sheets("NuevoFormatoPav").Cells(14, 4) = Sheets("Análisis Espectral").Cells(13, 4)
            Sheets("NuevoFormatoPav").Cells(15, 4) = Sheets("Análisis Espectral").Cells(14, 4)
            Sheets("NuevoFormatoPav").Cells(16, 4) = Sheets("Análisis Espectral").Cells(15, 4)
            Sheets("NuevoFormatoPav").Cells(17, 4) = Sheets("Análisis Espectral").Cells(16, 4)
            Sheets("NuevoFormatoPav").Cells(18, 4) = Sheets("Análisis Espectral").Cells(17, 4)
            Sheets("NuevoFormatoPav").Cells(19, 4) = Sheets("Análisis Espectral").Cells(18, 4)
            Sheets("NuevoFormatoPav").Cells(20, 4) = Sheets("Análisis Espectral").Cells(19, 4)
            Sheets("NuevoFormatoPav").Cells(21, 4) = Sheets("Análisis Espectral").Cells(20, 4)
            Sheets("NuevoFormatoPav").Cells(22, 4) = Sheets("Análisis Espectral").Cells(21, 4)
            Sheets("NuevoFormatoPav").Cells(23, 4) = Sheets("Análisis Espectral").Cells(22, 4)
            Sheets("NuevoFormatoPav").Cells(14, 5) = Sheets("Análisis Espectral").Cells(13, 5)
            Sheets("NuevoFormatoPav").Cells(15, 5) = Sheets("Análisis Espectral").Cells(14, 5)
            Sheets("NuevoFormatoPav").Cells(16, 5) = Sheets("Análisis Espectral").Cells(15, 5)
            Sheets("NuevoFormatoPav").Cells(17, 5) = Sheets("Análisis Espectral").Cells(16, 5)
            Sheets("NuevoFormatoPav").Cells(18, 5) = Sheets("Análisis Espectral").Cells(17, 5)
            Sheets("NuevoFormatoPav").Cells(19, 5) = Sheets("Análisis Espectral").Cells(18, 5)
            Sheets("NuevoFormatoPav").Cells(20, 5) = Sheets("Análisis Espectral").Cells(19, 5)
            Sheets("NuevoFormatoPav").Cells(21, 5) = Sheets("Análisis Espectral").Cells(20, 5)
            Sheets("NuevoFormatoPav").Cells(22, 5) = Sheets("Análisis Espectral").Cells(21, 5)
            Sheets("NuevoFormatoPav").Cells(23, 5) = Sheets("Análisis Espectral").Cells(22, 5)
            Sheets("NuevoFormatoPav").Cells(14, 6) = Sheets("Análisis Espectral").Cells(13, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(15, 6) = Sheets("Análisis Espectral").Cells(14, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(16, 6) = Sheets("Análisis Espectral").Cells(15, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(17, 6) = Sheets("Análisis Espectral").Cells(16, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(18, 6) = Sheets("Análisis Espectral").Cells(17, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(19, 6) = Sheets("Análisis Espectral").Cells(18, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(20, 6) = Sheets("Análisis Espectral").Cells(19, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(21, 6) = Sheets("Análisis Espectral").Cells(20, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(22, 6) = Sheets("Análisis Espectral").Cells(21, 6) * 1000
            Sheets("NuevoFormatoPav").Cells(23, 6) = Sheets("Análisis Espectral").Cells(22, 6) * 1000

'Coloca las coordenas exactas para el analisis espectral, aun no se separa para el individual, este deberá
'ser en otra parte

            Sheets("Avanzado").Cells(17, 2) = 0
            Sheets("Avanzado").Cells(18, 2) = 18
            Sheets("Avanzado").Cells(17, 4) = 36
            Sheets("Avanzado").Cells(18, 4) = 122


 Call Avance2

'anota las vidas remanentes en la hoja de AnalisisEspectral

End Select
          
End Sub
