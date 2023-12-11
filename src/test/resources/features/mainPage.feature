# language: ru
Функция: Тестирование главной страницы сайта


  @MainPageTest @RegionTest
  Сценарий: Успешная смена региона
    Дано перейти по url "https://www.bspb.ru/"
    Когда пользователь меняет select на "Санкт-Петербург"
    Тогда текст под номером телефона меняется на "В Санкт-Петербурге"
    Затем закрыть страницу


  @MainPageTest @BusinessTest
  Сценарий: Проверка перехода на вкладки в header
    Дано перейти по url "https://www.bspb.ru/"
    Когда пользователь нажимает на "headerBusinessLink" в header
    Тогда в "BusinessPage" должны появиться кнопки:
      | tabFirstButton  | Услуги РКО           |
      | tabSecondButton | Бизнес карта         |
      | tabThirdButton  | Торговый эквайринг   |
      | tabFourthButton | Мобильное приложение |
    Затем закрыть страницу


  @MainPageTest @NewsPageTest
  Сценарий: Проверка перехода на страницу Новостей
    Дано перейти по url "https://www.bspb.ru/"
    Когда пользователь в "MainPage" скроллит до элемента "showNewsLink"
    Когда пользователь нажимает на "showNewsLink"
    Тогда в "NewsPage" должны появиться кнопки:
      | newsTitleH2 | Новости |
    Затем закрыть страницу


  @MainPageTest @CurrencyPage
  Сценарий: Проверка перехода на страницу Валют
    Дано перейти по url "https://www.bspb.ru/"
    Когда пользователь в "MainPage" скроллит до элемента "currencyLink"
    Когда пользователь нажимает на "currencyLink"
    Тогда в "CurrencyPage" должны появиться кнопки:
      | tabFirstButton  | В офисе   |
      | tabSecondButton | По карте  |
      | tabThirdButton  | Онлайн    |
      | tabFourthButton | Документы |
      | tabFifthButton  | Вопросы   |
    Затем закрыть страницу
