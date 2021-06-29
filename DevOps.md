---
layout: default
title: DevOps
nav_order: 7
---
# Perspetiva do desenvolvedor

Na tabela seguinte, estão presentes as componentes que constituem o sistema assim como informações sobre as mesmas

| Componente | Informação | Ambiente de runtime |
| ---------- | ---------- | ------------------- |
| Webapp     | A webapp desenvolvida em React permite ao utilizador verificar um histórico de dados e de alertas, assim como configurar regras para o envio de comandos | A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31004 |
| DataProcessor | Microsserviço desenvolvido em Spring que tem por objetivo processar os dados armazenados no Influx, assim como os alertas, e expor estes em endpoints REST | A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31003 |
| Management | Microsserviço desenvolvido em Spring que tem por objetivo receber regras da Webapp através de endpoints REST, garantir a persistência destas regras e fazer decisões com base nas regras armazenadas e dados dos sensores consumidos, de modo a produzir comandos para um tópico de Kafka | A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31005 |
| Grafana | Interface de visualização de dashboards, utilizada para a visualização dos dados dos sensores em gráficos, assim como para a configuração de alertas | A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31002 |
# Outros detalhes
