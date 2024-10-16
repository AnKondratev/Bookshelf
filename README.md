<div class="markdown-body"><h1 id="bookshelf-restful-api">Bookshelf RESTful API</h1>
<h2 id="описание">Описание</h2>
<p>Данное приложение реализует RESTful API для управления библиотекой книг с использованием Spring MVC и Spring Data JPA. Пользователи могут выполнять операции CRUD (создание, чтение, обновление и удаление) для книг, а также управлять информацией о авторах.</p>
<h2 id="стек-технологий">Стек технологий</h2>
<ul>
<li>Java</li>
<li>Spring Boot</li>
<li>Spring MVC</li>
<li>Spring Data JPA</li>
<li>PostgreSQL </li>
<li>Lombok</li>
</ul>
<h2 id="сущности">Сущности</h2>
<h3 id="класс-book">Класс Book</h3>
<ul>
<li><code>id</code>: Идентификатор книги (Long)</li>
<li><code>author</code>: Автор книги (Author)</li>
<li><code>title</code>: Название книги (String)</li>
<li><code>genre</code>: Жанр книги (String)</li>
</ul>
<h3 id="класс-author">Класс Author</h3>
<ul>
<li><code>id</code>: Идентификатор автора (Long)</li>
<li><code>fullName</code>: Полное имя автора (String)</li>
</ul>
<h2 id="основные-функции">Основные функции</h2>
<h3 id="эндпоинты-api">Эндпоинты API</h3>
<ol>
<li><p><strong>Получение списка книг с пагинацией и сортировкой</strong></p>
<ul>
<li><strong>Метод</strong>: <code>GET</code></li>
<li><strong>URL</strong>: <code>/api/v1/my-books</code></li>
<li><strong>Параметры</strong>: <code>page</code>, <code>size</code>, <code>sort</code> (опционально)</li></ul>
<p>Результат: Возвращает список книг в виде страницы.</p></li>
<li><p><strong>Получение информации о конкретной книге</strong></p>
<ul>
<li><strong>Метод</strong>: <code>GET</code></li>
<li><strong>URL</strong>: <code>/api/v1/{id}</code></li>
<li><strong>Параметры</strong>: <code>id</code> (идентификатор книги)</li></ul>
<p>Результат: Возвращает информацию о книге с указанным идентификатором.</p></li>
<li><p><strong>Добавление новой книги</strong></p>
<ul>
<li><strong>Метод</strong>: <code>POST</code></li>
<li><strong>URL</strong>: <code>/api/v1/new_book</code></li>
<li><strong>Тело запроса</strong>: <code>BookDTO</code></li></ul>
<p>Результат: Возвращает созданную книгу.</p></li>
<li><p><strong>Обновление информации о книге</strong></p>
<ul>
<li><strong>Метод</strong>: <code>PUT</code></li>
<li><strong>URL</strong>: <code>/api/v1/update_book</code></li>
<li><strong>Тело запроса</strong>: <code>Book</code></li></ul>
<p>Результат: Возвращает обновленную книгу.</p></li>
<li><p><strong>Удаление книги</strong></p>
<ul>
<li><strong>Метод</strong>: <code>DELETE</code></li>
<li><strong>URL</strong>: <code>/api/v1/delete_book</code></li>
<li><strong>Параметры</strong>: <code>id</code> (идентификатор книги)</li></ul>
<p>Результат: Возвращает статус 200 OK.</p></li>
</ol>
<h2 id="установка-и-запуск">Установка и запуск</h2>
<ol>
<li>Склонируйте репозиторий:</li>
</ol>
<pre><code class="bash language-bash hljs">   git <span class="hljs-built_in">clone</span> https://github.com/ваш-логин/Bookshelf.git</code><button class="copy-ai-code" onclick="copyAICode(this)"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg> <span class="label-copy-code">Копировать</span></button></pre>
<ol start="2">
<li>Перейдите в директорию проекта:</li>
</ol>
<pre><code class="bash language-bash hljs">   <span class="hljs-built_in">cd</span> Bookshelf</code></pre>
<ol start="3">
<li><p>Откройте проект в вашей IDE (например, IntelliJ IDEA).</p></li>
<li><p>Запустите приложение, используя Spring Boot.</p></li>
<li><p>Откройте ваше приложение в браузере по адресу: <code>http://localhost:8080</code>.</p></li>
</ol>
<h2 id="примеры-запросов">Примеры запросов</h2>
<h3 id="получение-списка-книг">Получение списка книг</h3>
<pre><code class="plaintext language-plaintext hljs">GET /api/v1/my-books?page=0&amp;size=5&amp;sort=title,asc</code></pre>
<h3 id="получение-информации-о-книге">Получение информации о книге</h3>
<pre><code class="plaintext language-plaintext hljs">GET /api/v1/{id}</code></pre>
<h3 id="добавление-новой-книги">Добавление новой книги</h3>
<pre><code class="plaintext language-plaintext hljs">POST /api/v1/new_book
Content-Type: application/json

{
  "author": {
    "fullName": "Автор Книги"
  },
  "title": "Название Книги",
  "genre": "Жанр Книги"
}</code></pre>
<h3 id="обновление-книги">Обновление книги</h3>
<pre><code class="plaintext language-plaintext hljs">PUT /api/v1/update_book
Content-Type: application/json

{
  "id": 1,
  "author": {
    "id": 1
  },
  "title": "Обновленное Название",
  "genre": "Обновленный Жанр"
}</code></pre>
<h3 id="удаление-книги">Удаление книги</h3>
<pre><code class="plaintext language-plaintext hljs">DELETE /api/v1/delete_book?id=1</code></pre>


<hr>
<p>Если у вас есть какие-либо вопросы или предложения, не стесняйтесь обращаться!</p></div>
