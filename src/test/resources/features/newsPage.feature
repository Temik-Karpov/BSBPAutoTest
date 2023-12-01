# language: ru
Функция: Тестирование новостной страницы сайта

  Сценарий: Проверка смены года в фильтре новостей
    Дано открытая новостная страница "https://www.bspb.ru/news?client_type_id=1"
    Когда пользователь меняет год на "2022"
    Тогда все новости должны содержать "2022" в дате
    И закрыть новостную страницу

  Сценарий: Проверка смены категории в фильтре новостей
    Дано открытая новостная страница "https://www.bspb.ru/news?client_type_id=1"
    Когда пользователь выбирает категорию Частным клиентам
    Тогда все отобразившиеся новости имеют данную категорию
    И закрыть новостную страницу