---
layout: default
title: DevOps
nav_order: 7
---
# Perspetiva do desenvolvedor

## Implementação
Na tabela seguinte, estão presentes as componentes que constituem o sistema assim como informações sobre as mesmas

<table style="width:90%">
 <tr>
   <th>Componente</th>
   <th>Informação</th>
   <th>Ambiente de runtime</th>
 </tr>
 <tr>
   <td>Webapp</td>
   <td>A webapp desenvolvida em React permite ao utilizador verificar um histórico de dados e de alertas, assim como configurar regras para o envio de comandos</td>
   <td>A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31004</td>
 </tr>
 <tr>
   <td>DataProcessor</td>
   <td>Microsserviço desenvolvido em Spring que tem por objetivo processar os dados armazenados no Influx, assim como os alertas, e expor estes em endpoints REST</td>
   <td>O microsserviço corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31003</td>
 </tr>
 <tr>
   <td>Management</td>
   <td>Microsserviço desenvolvido em Spring que tem por objetivo receber regras da Webapp através de endpoints REST, garantir a persistência destas regras e fazer decisões com base nas regras armazenadas e dados dos sensores consumidos, de modo a produzir comandos para um tópico de Kafka</td>
   <td>O microsserviço corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31005</td>
 </tr>
 <tr>
   <td>Grafana</td>
   <td>Interface de visualização de dashboards, utilizada para a visualização dos dados dos sensores em gráficos, assim como para a configuração de alertas</td>
   <td>A aplicação corre num container de docker, acessível pelo endereço 192.168.160.87 e porto 31002</td>
 </tr>
</table>


# Outros detalhes
