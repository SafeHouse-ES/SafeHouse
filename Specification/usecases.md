---
layout: default
title: Use Cases
parent: Specification
---

# Use Cases

## 1.

<div markdown="1">

| ID | Verificar os valores lidos por um determinado sensor num intervalo de tempo especificado                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| Ator Principal | Habitante                                                                                                        |
| Função         | O Habitante procura obter uma lista com os valores obtidos pelo sensor de consumo de eletricidade ao longo da última hora   |
| Pré-condições | 1. Sensor indicado foi adicionado na plataforma <br/><br/> 2. Sensor indicado está ligado à rede interna <br/><br/> 3. Sensor indicado está em funcionamento <br/><br/> |
| Fluxo normal   | 1. O Habitante abre a página principal da aplicação da plataforma <br/><br/> 2. O Habitante seleciona a tab “Historic Values” <br/><br/> 3. O Habitante seleciona na nova página a opção “Last 1 Hour” <br/><br/> |
| Prioridade | Normal |

</div>

## 2.

<div markdown="1">

| ID | Definir um alerta para um determinado sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| Ator Principal | Habitante                                                                                                        |
| Função         | O Habitante procura configurar um alerta num sensor de temperatura já existente para ligar o aquecedor central após baixar do limite de 27ºC   |
| Pré-condições | 1. Sensor indicado foi adicionado na plataforma </br> 2. Sensor indicado está ligado à rede interna </br> 3. Sensor indicado está em funcionamento |
| Fluxo normal   | 1. O Habitante abre a dashboard Grafana </br> 2. O Habitante edita um dos gráficos existente para lhe atribuir um alerta </br> a. nos campos que surgem para editar, são preenchidos os limites |
| Prioridade | Alta |

</div>

## 3.

<div markdown="1">

| ID | Verificar o valor em real-time de um determinado sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| Ator Principal | Habitante                                                                                                        |
| Função         | O Habitante procura obter o valor mais recente do sensor de temperatura  |
| Pré-condições | 1. Sensor indicado foi adicionado na plataforma </br> 2. Sensor indicado está ligado à rede interna </br> 3. Sensor indicado está em funcionamento |
| Fluxo normal   | 1. O Habitante abre a página principal da aplicação da plataforma </br> 2. O Habitante seleciona a tab “Dashboard” para visualizar os dados nos últimos 5 segundos e procura pelo sensor desejado |
| Prioridade | Alta |

</div>

## 4.

<div markdown="1">

| ID | Verificar se existe alguma notificação                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| Ator Principal | Habitante                                                                                                        |
| Função         | O Habitante procura saber se têm alguma notificação que ainda não tenha lido  |
| Pré-condições | 1. Pelo menos um Sensor foi adicionado na plataforma </br> a. está na rede interna </br> b. está em funcionamento </br> c. foi lhe associado um alerta |
| Fluxo normal   | 1. O Habitante abre a página principal da aplicação da plataforma </br> 2. O Habitante clica no símbolo de um sininho no canto superior direito para surgir uma lista com as notificações |
| Prioridade | Normal |

</div>

## 5.

<div markdown="1">

| ID | Verificar um gráfico com a evolução diária dos valores de um sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| Ator Principal | Habitante                                                                                                        |
| Função         | O Habitante procura visualizar um gráfico com os valores de temperatura ao longo do dia.  |
| Pré-condições | 1. Sensor indicado foi adicionado na plataforma </br> 2. Sensor indicado está ligado à rede interna </br> 3. Sensor indicado está em funcionamento |
| Fluxo normal   | 1. O Habitante abre a dashboard Grafana </br> 2. O Habitante pesquisar pelo gráfico do sensor que deseja visualizar |
| Prioridade | Baixa |

</div>
