---
layout: default
title: Testes
nav_order: 5
---

# Testes

Foram efectuados diferentes testes nos vários módulos do sistema para garantir o seu correcto funcionamento e capacidade de responder às necessidades do utilizador.
Estes testes correspondem a cenários representativos das principais funcionalidades dos módulos

---

## Data Processor

### Utilizadores conseguem aceder aos alertas enviados

**Dado** que o sensor de luminosidade da cozinha está configurado para detectar quando a divisão está mais escura que um dado valor\
**Quando** o alerta é consumido pelo sistema\
**Então**, o alerta deve ficar acessível no endpoint /alerts endpoint com a descrição de que a cozinha está muito escura\

Neste teste, garante-se que quando um dado valor de uma métrica, neste caso a luminosidade, causa o envio de um alerta por parte da API, este alerta deve estar agora acessível pelo endpoint disponibilizado para o efeito.

### Utilizadores conseguem aceder às métricas

**Quando** um utilizador pede os dados sobre as métricas do sistema\
**Então**, deve receber a lista com as métricas temperatura, luminosidade e movimento\

Este teste básico pretende assegurar que o utilizador consegue obter dados básicos sobre as métricas, nomeadamente, quais são estas.

### Utilizadores podem ver os dados relacionados com os sensores

**Dado** que o utilizador desconhece os id do sensor que pretende\
**Quando** o utilizador pede informação sobre todos os sensores existentes\
**Então**, é retornada a lista com leituras e informação de todos os sensores no quarto1, quarto2, cozinha e sala de estar\

**Dado** que o utilizador sabe, agora, o id do sensor que pretende (ex: cozinha)\
**Quando** o utilizador pede informação sobre o sensor da cozinha\
**Então**, a lista das leituras do sensor da cozinha é retornada\

Neste teste, o utilizador tem, primeiro de descobrir o id do sensor que pretende e só depois pode saber as medições deste.

---

## Management

### Se uma medida violar uma configuração definida, enviar um comando ao dispositivo correspondente

**Dado** que existe uma configuração para quando a temperatura no quarto1 excede o valor de 40\
**Quando** a temperatura no quarto1 é maior que 40\
**Então**, deve ser enviado um comando para diminuir a temperatura no quarto1 para 25\

A função deste teste é garantir o correcto funcionamento do sistema de comandos automáticos que devem ser disparados aquando da violação de uma condição previamente configurada.