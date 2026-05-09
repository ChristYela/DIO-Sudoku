# Zen Sudoku - DIO Java Challenge

Este projeto é uma implementação do desafio de Sudoku da Digital Innovation One (DIO) utilizando Java e Swing para a interface gráfica.

## Estrutura do Projeto

O código foi organizado seguindo padrões de Orientação a Objetos (POO) e a estrutura base do desafio original:

- `br.com.dio.Main`: Ponto de entrada do aplicativo. Aceita a string de seed como argumento.
- `br.com.dio.model.Board`: Representa o tabuleiro 9x9.
- `br.com.dio.model.Cell`: Representa cada célula do jogo (valor, posição e se é fixa).
- `br.com.dio.service.BoardService`: Contém as regras de negócio (parsing da seed, validação de conflitos e condições de vitória).
- `br.com.dio.ui.SudokuUI`: Interface gráfica construída com Java Swing.

## Como Compilar e Executar

Certifique-se de estar na raiz do projeto (onde a pasta `src` está localizada).

1. **Compilar**:
   ```bash
   javac -d out src/br/com/dio/Main.java src/br/com/dio/model/*.java src/br/com/dio/service/*.java src/br/com/dio/ui/*.java
   ```

2. **Executar**:
   ```bash
   java -cp out br.com.dio.Main
   ```

3. **Executar com Seed Customizada (Exemplo)**:
   ```bash
   java -cp out br.com.dio.Main "0,0;4,false 1,0;7,false..."
   ```

## Manual do Usuário

### Como Jogar
1. **Selecionar Célula**: Clique em qualquer quadrado do tabuleiro. Células relacionadas (mesma linha, coluna ou bloco) serão destacadas em cinza claro para ajudar na visualização.
2. **Inserir Números**: Utilize os botões de 1 a 9 na parte inferior para preencher a célula selecionada.
3. **Apagar**: O botão "X" apaga o valor da célula selecionada (apenas se ela não for uma célula fixa do puzzle original).
4. **Validação**: Se você inserir um número que já existe na mesma linha, coluna ou bloco 3x3, ele ficará vermelho, indicando um erro.
5. **Vitória**: O jogo detecta automaticamente quando todas as células foram preenchidas corretamente e exibirá uma mensagem de vitória.

## Como fazer o Checkout/Walkthrough do código
1. **Ponto de Partida**: Comece examinando o arquivo `br.com.dio.Main`. Ele é o responsável por carregar os dados iniciais (seed) e iniciar a Interface Gráfica.
2. **Lógica de Dados**: Veja `br.com.dio.model.Board` e `Cell`. Eles definem como o Sudoku é representado na memória.
3. **Regras do Jogo**: O arquivo `br.com.dio.service.BoardService` é o "cérebro". Ele contém o método `hasConflict` que implementa as regras clássicas de não repetir números em linhas, colunas ou blocos.
4. **Interface**: `br.com.dio.ui.SudokuUI` gerencia os botões, cores e interações do usuário.

5. 
## Comparação com o Boilerplate DIO


- **Separação de Camadas**: Modelos, Serviços e UI estão desacoplados.
- **UI Responsiva**: Utiliza layouts flexíveis (`GridBagLayout`) e estilos modernos.
- **Validação em Tempo Real**: Diferente de algumas versões que validam apenas ao final, esta destaca conflitos enquanto você joga.
- **Destaque de Seleção**: Ajuda visual para identificar células relacionadas à seleção atual.
-**Boilerplate**: Geralmente foca apenas em classes simples ou jogo via console.
- **Esta Versão**: Oferece uma experiência visual completa (GUI), separação clara de responsabilidades (Service/Model/UI) e validação dinâmica de erros. 
