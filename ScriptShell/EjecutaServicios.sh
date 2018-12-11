#!/bin/bash
declare -a METODOS=(
    "m01_listarCursos"
	"m02_listarCursoPorID"
	"m03_guardaCurso"
	"m04_actualizaCurso"
	"m05_eliminaCurso")

EjecutarServicio(){
    Metodo=$1
    Fecha=$2
    Configuracion="$Metodo.config"
    Salida="Json_$Metodo$Fecha.json"

    NowDate=$(date +"%d/%m/%Y %H:%M:%S")
    echo "Se ejecuta el metodo [$Metodo]." > $Salida
    echo "Fecha: [$NowDate]." >> $Salida
    echo " " >> $Salida
    echo "Respuesta:" >> $Salida
    echo " " >> $Salida
    curl -K $Configuracion >> $Salida
}

Fecha=$(date +"%Y%m%d_%H%M%S")
Contador=0

for Metodo in "${METODOS[@]}"
do
    Contador=$((Contador+1))
    echo "$Contador.- Se ejecuta el metodo [$Metodo]."
    EjecutarServicio "$Metodo" "$Fecha"
done