
# Android Technical Test

We need to show a list of the repositories  using an android application.


## Objective

Display scrollable list of repositories of “square” organisation in GitHub. The app should consist of only one screen (Repositories Screen).
Each list item should have at least the name and description of the repo. 

## Technical Test API

The api is located on https://api.github.com/ so the BASE_URL would be: orgs/square/repos and the Queries: ?page=3&per_page=50 , so that means that
we are going to use pagination with PAGE_SIZE = 50 by default.

## Technologies Used

-Koin -Room -Navigation component -Paging library 3.0 -Retrofit+Okhttp -Coroutines -Flow -viewModel -Mockito-Expresso


## Architecture

This app uses a MVVM architecture with the following components

Model: Is effectively the output from the domain layer
View: The android activity and it's layout responsible for UI and user/system events
ViewModel: A simple datastore that is observed by the view to populate it's UI ( survive to the configuration changes and help us to deal with the memory leaks)

![alt text](https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTDBDE6fEDFGXfXVd8VpnpSK8PMDW7R1njA3A&usqp=CAU)

## Paging library

In order to deal with the problem of the pagination, I have used paging library, so it bring only the necesary items from the model to the view. 
I have use diff_util in the adapter to set the list in a background thread and changing only the items that have been changed.

We can have a overview of the architecture with the next image:

![alt text](https://developer.android.com/topic/libraries/architecture/images/paging3-library-architecture.svg)

We can see that we are not using livedata in this opportunity instead of it we used flow pasing the data to the view and after calling sumitData 
to set the items into the adapter.

## Testing

In order to test the app I have mockito and expresso library, I have used KoinTest Class to override the modules ( viewmodel and adapter) and finally 
I have used Room.inMemoryDatabaseBuilder(...) that allow the creation of a temporally local database so I can do testing whit it.
NOTE: All the test has been implementated using androidInstrumentation test due to we need the emulator to play around with the room stuff.

## Posible updates

Would be intereting if the user can change the user's repository let say using an SearchView component, another improve would be have a bottomNavigation with multiple back stack
so the user would have the home(where the search option would be located),repo(public and private repos),favourites(favourites repositories) option.

Would be interesting if we could see the commits as well like the image below (app created for me):

(supergit.png)








