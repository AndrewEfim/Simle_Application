# Simle_Application
This Mini game about country capital.
Use Clean Architecture for convenient extension in future use patterns: Repository, MVVM , Router
DOMAIN module
Independent layer, it contains only business logic written on java or kotlin - no dependencies from Android SDK.
DATA Module
It contains the functions for obtaining and storing data - network calls, database, files, shared preferences etc. 
This layer can depend on Android SDK and other libs(for exp: Gson, Retrofit).
Presentation module
First of all this layer contains UI. All interactions with the user should be here.
The Presentation layer communicates with the domain layer for receiving and sending data(entity).
