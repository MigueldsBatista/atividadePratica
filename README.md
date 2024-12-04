# Projeto de Repositório de Entidades

## Descrição
Este projeto é uma implementação de um sistema de transações entre entidades, que usa um repositório genérico para gerenciar as mesmas utilizando serialização de objetos. A arquitetura do projeto é baseada em um repositório geral abstrato que é estendido por repositórios específicos para diferentes tipos de entidades. Além disso, o projeto inclui uma interface gráfica para facilitar a interação do usuário com o sistema.

## Arquitetura do Projeto

### 1. `RepositorioGeral`
- **Descrição**: Classe abstrata que serve como base para todos os repositórios específicos.
- **Atributos**:
  - `dao`: Instância de `DAOSerializadorObjetos` utilizada para realizar operações de inclusão, exclusão, alteração e busca.
- **Métodos**:
  - `RepositorioGeral()`: Construtor que inicializa o atributo `dao` com uma instância de `DAOSerializadorObjetos`, passando o retorno do método abstrato `getClasseEntidade()`.
  - `getClasseEntidade()`: Método abstrato que deve ser implementado pelas subclasses para retornar a classe da entidade específica.

### 2. `DAOSerializadorObjetos`
- **Descrição**: Classe genérica responsável pela serialização e desserialização de objetos.
- **Atributos**:
  - `SEP_ARQUIVO`: Separador de arquivos do sistema.
  - `PONTO`: Ponto utilizado para formar o caminho do diretório.
  - `NOME_DIR`: Nome do diretório onde os arquivos serão armazenados.
  - `tipoEntidade`: Classe da entidade que está sendo gerenciada.
  - `cache`: Cache de objetos para melhorar a performance.
- **Métodos**:
  - `DAOSerializadorObjetos(Class<T> tipoEntidade)`: Construtor que inicializa os atributos e cria o diretório se não existir.
  - `createIfNotExist()`: Método privado que cria o diretório se ele não existir.

### 3. Repositórios Específicos
- **Descrição**: Classes que estendem `RepositorioGeral` e implementam o método `getClasseEntidade()` para retornar a classe da entidade específica.
- **Exemplo**:
  - `RepositorioTransacao`: Repositório específico para a entidade `Transacao`.

### 4. Interface Gráfica
O projeto inclui uma interface gráfica para facilitar a interação do usuário com o sistema. As telas são implementadas utilizando a biblioteca Swing do Java.

#### Classes de Telas
- **`NavegacaoTituloDivida`**: Tela de navegação principal para títulos de dívida.
- **`TelaIncluirTituloDivida`**: Tela para inclusão de novos títulos de dívida.
- **`TelaExcluirTituloDivida`**: Tela para exclusão de títulos de dívida.

### Exemplo de Implementação

#### `RepositorioGeral.java`
```java
package br.com.cesarschool.poo.repositorio;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {
    protected DAOSerializadorObjetos dao;

    public RepositorioGeral() {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    protected abstract Class<?> getClasseEntidade();
}