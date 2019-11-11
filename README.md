# MapApp
Приложение, которое состоит из 2-х экранов: авторизация и карта с маршрутом

В интерфейсе с авторизацией пользователь вводит логин и пароль. Если логин или пароль не верный, выводится предупреждение 
об этом, если логин и пароль верный, то происходит открытие карты с отображением маршрута. Присутствуют различные проверки на ввод данных,
состояние сети и др.

Данные для получения маршрута сохраняются в базу данных, поэтому нет необходимости каждый раз авторизовываться.


# Технологии
 - MVVM
 - Room
 - LiveData
 - Dagger2
 - RxJava2
 - Retrofit2
 - Navigation
 - DataBinding
 - Google Map
