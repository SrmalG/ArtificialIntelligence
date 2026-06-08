# Feedforward Neural Network - Java REST API

Implementación de una red neuronal feedforward (FNN) desde cero en Java, expuesta como API REST mediante Spring Boot.

## Requisitos

- Java 11+
- Maven 3.6+

## Instalación y Ejecución

### Compilar el proyecto

```bash
mvn clean install -DskipTests
```

### Ejecutar el servidor

```bash
mvn spring-boot:run
```

El servidor se levanta en `http://localhost:8080`

### Ejecutar con JAR

```bash
mvn clean package -DskipTests
java -jar target/int-0.0.1-SNAPSHOT.jar
```

## Endpoints

### Health Check

```
GET /api/v1/ai/health
```

**Respuesta:**
```json
{
  "status": "UP",
  "uptime": "120s",
  "version": "1.1.0"
}
```

### Entrenar Modelo

```
POST /api/v1/ai/train
Content-Type: application/json
```

**Body:**
```json
{
  "data": [[0.5, 0.3, ...], [0.2, 0.8, ...]],
  "target": [[1], [0]],
  "epochs": 1000,
  "learningRate": 0.1,
  "hiddenLayers": [8, 4],
  "lossesAvailable": true
}
```

| Campo | Tipo | Default | Descripción |
|-------|------|---------|-------------|
| data | double[][] | requerido | Matriz de features [n_samples][n_features] |
| target | double[][] | requerido | Matriz de targets [n_samples][n_outputs] |
| epochs | int | 100 | Número de épocas |
| learningRate | double | 0.05 | Tasa de aprendizaje |
| hiddenLayers | int[] | [4] ó [8,4]| Neuronas por capa oculta |
| lossesAvailable | boolean | false | Incluir array de losses en respuesta |

**Respuesta:**
```json
{
  "success": true,
  "message": "Train completed",
  "losses": [0.25, 0.18, ...],
  "lastLoss": 0.006333,
  "time": "52ms"
}
```

### Predecir (una muestra)

```
POST /api/v1/ai/calculate
Content-Type: application/json
```

**Body:**
```json
{
  "input": [0.5, 0.3, 0.7, ...]
}
```

**Respuesta:**
```json
{
  "success": true,
  "message": "The result is: 0.92",
  "result": "MALIGNANT",
  "input": [0.5, 0.3, 0.7, ...]
}
```

### Predecir (múltiples muestras)

```
POST /api/v1/ai/calculateArray
Content-Type: application/json
```

**Body:**
```json
{
  "inputs": [[0.5, 0.3, ...], [0.2, 0.8, ...]],
  "showInfo": true
}
```

### Calcular Métricas

```
POST /api/v1/ai/metrics
Content-Type: application/json
```

**Body:**
```json
{
  "predictions": [0.9, 0.1, 0.8, 0.3],
  "targets": [1, 0, 1, 0],
  "threshold": 0.5
}
```

**Respuesta:**
```json
{
  "confusionMatrix": [[2, 0], [0, 2]],
  "accuracy": 1.0,
  "f1Score": 1.0,
  "balancedAccuracy": 1.0
}
```

## Ejemplo Completo con cURL

```bash
# 1. Verificar que el servidor está activo
curl http://localhost:8080/api/v1/ai/health

# 2. Entrenar modelo (ejemplo con datos sintéticos)
curl -X POST http://localhost:8080/api/v1/ai/train \
  -H "Content-Type: application/json" \
  -d '{
    "data": [[0.1, 0.2], [0.9, 0.8], [0.2, 0.1], [0.8, 0.9]],
    "target": [[0], [1], [0], [1]],
    "epochs": 500,
    "learningRate": 0.1,
    "hiddenLayers": [4, 2],
    "lossesAvailable": false
  }'

# 3. Realizar predicción
curl -X POST http://localhost:8080/api/v1/ai/calculate \
  -H "Content-Type: application/json" \
  -d '{"input": [0.85, 0.75]}'
```

## Ejecutar Tests

```bash
mvn test
```

## Estructura del Proyecto

```
src/main/java/com/example/demo/
├── entitiesAI/          # Núcleo de la red neuronal
│   ├── Neuron.java
│   ├── NeuronLayer.java
│   └── NeuronalNetwork.java
├── service/             # Lógica de negocio
│   └── impl/FNNArtificialEngineServiceImpl.java
├── web/                 # Controladores REST
│   └── ArtificialController.java
└── dto/                 # Objetos de transferencia
```
