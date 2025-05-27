Sub VerificDatos()

'verificar los datos de entrada, en la siguiente subrutina deberá verificar que el numero de capas corresponda
'esta subrutina debe ser llamada desde el principio del análisis principal
'EL NUM DE CAPAS DEBERÁ CORRESPONDER CON LAS ESCRITAS EN LA HOJA DE INICIO

NumCapas = 0
  i = 3
  While Len(Sheets("SECTIONS").Cells(i, 1)) > 0
    NumSeg = NumSeg + 1
    i = i + 1
  Wend
  
  'Obtiene el número de alternativas
  NumAlt = Sheets("Principal").Cells(2, 3)


End Sub
