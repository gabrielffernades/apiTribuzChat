#!/bin/bash

# Script para parar processos Spring Boot na porta 8080

echo "🔍 Procurando processos na porta 8080..."

PID=$(lsof -ti :8080)

if [ -z "$PID" ]; then
    echo "✅ Nenhum processo encontrado na porta 8080"
else
    echo "🛑 Encontrado processo PID: $PID"
    kill $PID
    echo "✅ Processo encerrado!"
fi

