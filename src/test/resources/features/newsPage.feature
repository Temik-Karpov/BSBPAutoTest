# language: ru
Функция: Тестирование новостной страницы сайта

  Структура сценария: Проверка смены года в фильтре новостей
    Дано открытая новостная страница "https://www.bspb.ru/news?client_type_id=1"
    Когда пользователь меняет год на <нужный>
    Тогда все новости должны содержать <нужный> в дате
    И закрыть новостную страницу

    Примеры:
      | нужный |
      | "2022" |
      | "2021" |

  Сценарий: Проверка смены категории в фильтре новостей
    Дано открытая новостная страница "https://www.bspb.ru/news?client_type_id=1"
    Когда пользователь выбирает категорию Частным клиентам
    Тогда все отобразившиеся новости имеют данную категорию
    И закрыть новостную страницу
