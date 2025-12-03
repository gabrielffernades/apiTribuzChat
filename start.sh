#!/bin/bash

# Script para iniciar o Spring Boot (para processos antigos primeiro)

echo "🛑 Parando processos antigos na porta 8080..."
./stop.sh

echo ""
echo "🚀 Iniciando Spring Boot..."
./mvnw spring-boot:run

