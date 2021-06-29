---
layout: default
title: Modelos de Mensagens
parent: Specification
---

# Estrutura das Mensagens

Tendo em conta que a funcionalidade principal do projeto é a obtenção de dados sensoriais, e consequentemente a sua utilização para o controlo de dispositivos inteligentes, foi necessário definir inicialmente a estrutura das mensagens dos dados sensoriais e dos comandos a serem enviados aos dispositivos. Deste modo, optou-se por mensagens JSON, em que os campos fossem de fácil compreensão.

## Dados dos sensores

```
{
  id: sensor_id,
  temperature: temperature_value,
  luminosity: luminosity_value,
  movement: movement_boolean_value,
  timestamp: timestamp
}

```

## Comandos

```
{
  id: room_id,
  device: device_id,
  value: device_value
}

```
