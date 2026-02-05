# HtmlAnalyzer

> A Java-based HTML structure analyzer that extracts text at specific depth levels

##  Overview

This Java program analyzes a provided URL and extracts text content located at depth level 3 within the HTML structure.

## Getting Started

### Compilation

To compile the program, run the following command in the directory containing the source file:

```bash
javac HtmlAnalyzer.java
```

### Execution

To run the program, use the following command with the URL as an argument:

```bash
java HtmlAnalyzer <insert-url-here>
```

#### Example

```bash
java HtmlAnalyzer http://hiring.axreng.com/internship/example1.html
```

## Output

The program returns one of the following outputs:

| Output | Description |
|--------|-------------|
| **Text found** | The text at depth level 3 of the HTML structure |
| `malformed HTML` | Invalid or malformed HTML structure detected |
| `URL connection error` | Unable to retrieve HTML content from the URL |

## Validation Rules

The analyzer implements the following validation checks:

- HTML structure divided into individual lines
- Each line contains only one element (opening tag, closing tag, or text)
- Paired tag validation (matching opening and closing tags)
- Tags must not contain attributes
- Malformed HTML detection
- Whitespace and empty lines are ignored

## Usage Examples

### Valid HTML

```html
<html>
    <head>
        <title>This is the title.</title>
    </head>
    <body>
        This is the body.
    </body>
</html>
```

**Output:** `This is the title.`

### Malformed HTML

```html
<html>
    <head>
        <title>Title without closing tag
    </head>
</html>
```

**Output:** `malformed HTML`

### Invalid URL

```bash
java HtmlAnalyzer http://non-existent-url.com
```

**Output:** `URL connection error`

## Code Structure

| Method | Description |
|--------|-------------|
| `main()` | Entry point; validates arguments and coordinates execution |
| `fetchHtmlContent()` | Retrieves HTML content from the URL |
| `analyzeHtml()` | Analyzes HTML and returns the result |
| `parseHtmlToLines()` | Splits HTML into individual lines |
| `isWellFormed()` | Validates whether the HTML is well-formed |
| `findDeepestText()` | Locates text at depth level 3 |

## ⚠️ Important Notes

- The program only considers HTML elements with paired opening and closing tags
- Self-closing tags like `<br/>` are ignored
- Leading whitespace is ignored
- Empty lines are ignored
- Tags with attributes are considered invalid

---
