# HtmlAnalyzer - Analisador de HTML

## Descrição
Este programa Java analisa uma URL fornecida e extrai o texto contido no nível 3 de profundidade da estrutura HTML.

## Compilação

Para compilar o programa, execute o seguinte comando no diretório que contém o arquivo fonte:

```bash
javac HtmlAnalyzer.java
```

## Execução

Para executar o programa, use o seguinte comando passando a URL como argumento:

```bash
java HtmlAnalyzer inserir-url-aqui
```

### Exemplo:
```bash
java HtmlAnalyzer http://hiring.axreng.com/internship/example1.html
```

## Saídas Possíveis

O programa retorna uma das seguintes saídas:

1. **Texto encontrado**: O texto no nível 3 de profundidade do HTML
2. **"malformed HTML"**: Caso a estrutura HTML seja inválida ou mal-formada
3. **"URL connection error"**: Caso não seja possível obter o conteúdo HTML da URL


### Validações Implementadas
- Estrutura HTML dividida em linhas
- Cada linha contém apenas um elemento (tag de abertura, fechamento ou texto)
- Validação de tags pareadas (abertura e fechamento)
- Validação de que tags não possuem atributos
- Detecção de HTML mal-formado
- Ignoração de espaços em branco e linhas vazias

## Exemplos de Uso

### HTML Válido:
```html
<html>
    <head>
        <title>Este é o título.</title>
    </head>
    <body>
        Este é o corpo.
    </body>
</html>
```
**Saída**: `Este é o título.`

### HTML Mal-formado:
```html
<html>
    <head>
        <title>Título sem fechamento
    </head>
</html>
```
**Saída**: `malformed HTML`

### URL Inválida:
```bash
java HtmlAnalyzer http://url-inexistente.com
```
**Saída**: `URL connection error`

## Estrutura do Código

- `main()`: Ponto de entrada, valida argumentos e coordena a execução
- `fetchHtmlContent()`: Obtém o conteúdo HTML da URL
- `analyzeHtml()`: Analisa o HTML e retorna o resultado
- `parseHtmlToLines()`: Divide o HTML em linhas
- `isWellFormed()`: Valida se o HTML está bem-formado
- `findDeepestText()`: Encontra o texto no nível 3 de profundidade

## Observações

- O programa considera apenas elementos HTML com tags de abertura e fechamento pareadas
- Tags como `<br/>` são ignoradas
- Espaços em branco iniciais são ignorados
- Linhas em branco são ignoradas
- Tags com atributos são consideradas inválidas