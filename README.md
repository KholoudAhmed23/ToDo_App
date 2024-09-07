The To-Do App project is a task management application designed for users to efficiently add, update, and delete tasks. It utilizes Room as a local database to store task information, consisting of a subject and task description. The app follows MVVM architecture and leverages LiveData and ViewModel to manage data efficiently, ensuring data persists through configuration changes.

Key features:

- Add, update, and delete tasks with ease through a user-friendly interface.
- Room Database to store tasks locally using the UserData entity.
- RecyclerView for displaying a list of tasks dynamically.
- ViewModel and Repository pattern to separate concerns and handle data transactions with Room.
- FloatingActionButton to trigger task additions through a custom dialog.

 The app integrates coroutines for background database operations, ensuring smooth user experience without blocking the main thread.
  

https://github.com/user-attachments/assets/160a0f94-bcff-468b-a521-07f43404cae7
