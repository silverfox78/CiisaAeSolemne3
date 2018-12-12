#!/bin/bash
declare -a METODOS=(
    "m01_listarCursos"
	"m02_guardaCurso"
	"m03_listarCursoPorID"
	"m04_actualizaCurso"
    "m05_listarCursoPorID"
    "m06_guardaAlumno"
    "m07_listarAlumnoPorID"
    "m08_actualizaAlumno"
    "m09_listarAlumnoPorIDCurso"
    "m10_eliminaAlumnoPorID"
	"m11_eliminaCurso"
    "m12_listarCursos"
    )

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