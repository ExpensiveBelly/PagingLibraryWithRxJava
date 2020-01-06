# PagingLibraryWithRxJava
My approach on how to use Google PagingLibrary with RxJava

It allows for:

1. Easy invalidation of the data by changing the JourneyRequest to be loaded: 
`journeyRepository.journey.onNext(JourneyRequest(...)`
2. Retries when connectivity is lost and then it comes back.
3. Easy to integrate with swipe to refresh, by just calling invalidate: 
`journeyPagingDataSourceFactory.journeyDataSource.invalidate()`
