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
| <b>Ator Principal<b/> | Habitante                                                                                                        |
| <b>Função<b/>         | O Habitante procura obter uma lista com os valores obtidos pelo sensor de consumo de eletricidade ao longo da última hora   |
| <b>Pré-condições<b/> | <b>1.<b/> Sensor indicado foi adicionado na plataforma <br/><br/> <b>2.<b/> Sensor indicado está ligado à rede interna <br/><br/> <b>3.<b/> Sensor indicado está em funcionamento |
| <b>Fluxo normal<b/>   | <b>1.<b/> O Habitante abre a página principal da aplicação da plataforma <br/><br/> <b>2.<b/> O Habitante seleciona a tab “Historic Values” <br/><br/> <b>3.<b/> O Habitante seleciona na nova página a opção “Last 1 Hour” |
| <b>Prioridade<b/> | Normal |

</div>

## 2.

<div markdown="1">

| ID | Definir um alerta para um determinado sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| <b>Ator Principal<b/> | Habitante                                                                                                        |
| <b>Função<b/>         | O Habitante procura configurar um alerta num sensor de temperatura já existente para ligar o aquecedor central após baixar do limite de 27ºC   |
| <b>Pré-condições<b/> | <b>1.<b/> Sensor indicado foi adicionado na plataforma <br/><br/> <b>2.<b/> Sensor indicado está ligado à rede interna <br/><br/> <b>3.<b/> Sensor indicado está em funcionamento |
| <b>Fluxo normal<b/>   | <b>1.<b/> O Habitante abre a dashboard Grafana <br/><br/> <b>2.<b/> O Habitante edita um dos gráficos existente para lhe atribuir um alerta <br/><br/> <b>a.<b/> nos campos que surgem para editar, são preenchidos os limites |
| <b>Prioridade<b/> | Alta |

</div>

## 3.

<div markdown="1">

| ID | Verificar o valor em real-time de um determinado sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| <b>Ator Principal<b/> | Habitante                                                                                                        |
| <b>Função<b/>         | O Habitante procura obter o valor mais recente do sensor de temperatura  |
| Pré-condições | <b>1.<b/> Sensor indicado foi adicionado na plataforma <br/><br/> <b>2.<b/> Sensor indicado está ligado à rede interna <br/><br/> <b>3.<b/> Sensor indicado está em funcionamento |
| <b>Fluxo normal<b/>   | <b>1.<b/> O Habitante abre a página principal da aplicação da plataforma <br/><br/> <b>2.<b/> O Habitante seleciona a tab “Dashboard” para visualizar os dados nos últimos 5 segundos e procura pelo sensor desejado |
| <b>Prioridade<b/> | Alta |

</div>

## 4.

<div markdown="1">

| ID | Verificar se existe alguma notificação                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| <b>Ator Principal<b/> | Habitante                                                                                                        |
| <b>Função<b/>         | O Habitante procura saber se têm alguma notificação que ainda não tenha lido  |
| <b>Pré-condições<b/> | <b>1.<b/> Pelo menos um Sensor foi adicionado na plataforma <br/><br/> <b>a.<b/> está na rede interna <br/><br/> <b>b.<b/> está em funcionamento <br/><br/> <b>c.<b/> foi lhe associado um alerta |
| <b>Fluxo normal<b/>   | <b>1.<b/> O Habitante abre a página principal da aplicação da plataforma <br/><br/> <b>2.<b/> O Habitante clica no símbolo de um sininho no canto superior direito para surgir uma lista com as notificações |
| <b>Prioridade<b/> | Normal |

</div>

## 5.

<div markdown="1">

| ID | Verificar um gráfico com a evolução diária dos valores de um sensor                                  |
|:---------------|:------------------------------------------------------------------------------------------------------------------|
| <b>Ator Principal<b/> | Habitante                                                                                                        |
| <b>Função<b/>         | O Habitante procura visualizar um gráfico com os valores de temperatura ao longo do dia.  |
| <b>Pré-condições<b/> | <b>1.<b/> Sensor indicado foi adicionado na plataforma <br/><br/> <b>2.<b/> Sensor indicado está ligado à rede interna <br/><br/> <b>3.<b/> Sensor indicado está em funcionamento |
| <b>Fluxo normal<b/>   | <b>1.<b/> O Habitante abre a dashboard Grafana <br/><br/> <b>2.<b/> O Habitante pesquisar pelo gráfico do sensor que deseja visualizar |
| <b>Prioridade<b/> | Baixa |

</div>