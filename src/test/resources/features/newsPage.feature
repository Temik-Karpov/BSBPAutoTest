# language: ru
Функция: Тестирование новостной страницы сайта

  @NewsTest @YearNews
  Структура сценария: Проверка смены года в фильтре новостей
    Дано перейти по url "https://www.bspb.ru/news?client_type_id=1"
    Когда пользователь меняет select на <год>
    Тогда все новости должны содержать <год> в дате
    Затем закрыть страницу
    Примеры:
      | год    |
      | "2022" |
      | "2021" |

  @NewsTest @FilterNews
  Структура сценария: Проверка смены категории в фильтре новостей
    Дано перейти по url "https://www.bspb.ru/news?client_type_id="
    Когда пользователь выбирает <категорию>
    Тогда все отобразившиеся новости имеют <данную категорию>
    Затем закрыть страницу
    Примеры:
      | категорию          | данную категорию   |
      | "Бизнесу"          | "БИЗНЕСУ"          |
      | "Частным клиентам" | "ЧАСТНЫМ КЛИЕНТАМ" |
