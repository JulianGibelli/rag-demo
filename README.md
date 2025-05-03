# 📄 RAG con Spring Boot: PDF + PGVector + OpenAI

Es una POC en Java con Spring Boot que permite:

- Cargar un PDF local.
- Dividir el contenido en *chunks* usando `PdfDocumentReader`.
- Convertir los chunks a vectores con embeddings.
- Guardarlos en una base de datos vectorial (PostgreSQL + PGVector).
- Recuperar contexto relevante a partir de una consulta.
- Usar OpenAI para generar respuestas con contexto (*Retrieval-Augmented Generation*).
- Tener en cuenta que requiere de API KEY

---

## ⚙️ Tecnologías utilizadas

- **Java 23+**
- **Spring Boot 3+**
- **Spring AI**
- **PGVector** (extensión para PostgreSQL)
- **OpenAI API**
- **Spring AI PDF Reader**
- **Maven**
- **Docker**

---

## 🚀 Ejecución

1. Clonar el repositorio
2. Configurar la variable de entorno para OpenAI api key
3. Ejecutar la aplicacion levanta el compose.yaml (daemon de docker debe estar corriendo)

```
curl --location 'localhost:8080/chat' \
--header 'Content-Type: application/json' \
--data <aca la consulta sobre el archivo>
```

## 🧱 RAG ( Retrieval-Augmented Generation)

La idea es cargar __PDF__ el cual por medio y gracias del PdfDocumentReader va a splittear el contenido en diferentes chunks
o segmentos. Lo cual despues se convierte a un embedding, que es una representacion numerica del texto que nosotros podemos
comprender. Tambien se lo llama **vector**.
Esto es necesario para que el **LLM** pueda procesar y entender el texto. 

![RAG-Model](/RAG-model.png)

Cuando el usuario hace una pregunta (ej: "¿Qué dice el documento sobre seguridad de datos?"):
Esa pregunta también se convierte a embedding.
Se hace una búsqueda por similaridad vectorial en la base de datos (PGVector) comparando el embedding de la pregunta con los embeddings de los chunks guardados.
Se recuperan los n chunks más similares → esto es lo que se llama como el contexto relevante.

Y despues se termina armando un prompt que incluye:

La pregunta del usuario + los chunks recuperados como contexto.

Este prompt se envía al **LLM (Large Language Model - openAI en nuestro caso)** para que genere una respuesta informada basada en:

Lo que sabe de su entrenamiento + lo que vos le pasaste como contexto del documento.

---
## 📄 Example
![postman](/postman.png)

![dbconnection](/img.png)