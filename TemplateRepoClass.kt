#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
#end
#parse("File Header.java")
interface ${RepoName} {
    fun doWork(params: ${UseCaseName}.Params): Either<Failure, ${Response}>

    class NetWork @Inject constructor(
        val service: RetrofitService,
        val network: NetworkHandler
    ) : ${RepoName} {
        override fun doWork(params: ${UseCaseName}.Params): Either<Failure, ${Response}> {
        return   when (network.isConnected) {
                false, null -> Either.Left(Failure.NetworkConnection)
                else -> {
                     request(
                        service.__(params.toHashMap()),
                        {j-> j.mMapToObject()},
                        {v-> v.validateType()},
                        _____()
                    )
                }
            } //when
        }
    }
}