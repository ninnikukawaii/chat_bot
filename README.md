# chat_bot
Формулировка второй задачи
Бот общается с пользователями через Telegram. 
Логика Telegram отделена от логики самого бота.
В чем сложность и интерес:
- поддерка ботом возможностей Telegram 


правки
- интерфейс для commandProcessing и requestProcessing (Processor)
  => Изменения сигнатуры RequestHandler.getAnswerByCommandAndRequest ->
    List<String> getAnswerByCommandAndRequest(Processor processor, User user) +
- заупскать тест для каждого state (theories?) +
- убрать рандом из тестов +
- изменение обработки хелпа в стейт +
