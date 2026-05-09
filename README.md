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

## Comparação com o Boilerplate DIO

O código gerado foi aprimorado em relação ao boilerplate básico:
- **Separação de Camadas**: Modelos, Serviços e UI estão desacoplados.
- **UI Responsiva**: Utiliza layouts flexíveis (`GridBagLayout`) e estilos modernos.
- **Validação em Tempo Real**: Diferente de algumas versões que validam apenas ao final, esta destaca conflitos enquanto você joga.
- **Destaque de Seleção**: Ajuda visual para identificar células relacionadas à seleção atual.
