#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
#end
#parse("File Header.java")
@Module
class ${ModuleName}Module{

    @Named(Dagger${ModuleName} + UseCaseScopeName)
    @Provides
    fun providesCoroutinesScope(): CoroutineScope = object : CoroutineScope
    {
        private val job = Job()
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO
    }
    @Named(Dagger${ModuleName} + DispatcherUseCaseName)
    @Provides
    @Singleton
    fun provideCoroutinesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    @Provides
    fun ${ModuleName}Repo(network: ${ModuleName}Repo.NetWork): ${ModuleName}Repo {return network}
    @Provides
    fun provide${ModuleName}UseCase(repo:LoginRepo,
                          @Named(Dagger${ModuleName} + UseCaseScopeName)
                          scope: CoroutineScope,
                          @Named(Dagger${ModuleName} + DispatcherUseCaseName)
                          dispatcher: CoroutineDispatcher):  ${ModuleName}UseCase
    { return  ${ModuleName}UseCase(dispatcher,scope,repo) }

}