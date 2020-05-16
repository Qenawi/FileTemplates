#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}

#end
#parse("File Header.java")
class ${UseCaseName}UseCase @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val scope: CoroutineScope,
    val repo: ${RepoName}
) : BaseUseCase<${Response}, ${UseCaseName}UseCase.Params>(scope, dispatcher) {

    fun doWork(params: Params, call: (Either<Failure, ${Response}>) -> Unit = {}) {
        scope.launch {
            val result = repo.login(params)
            withContext(dispatcher) {
                call(result)
            }
        }
    }


    data class Params( ) {
        fun toHashMap(): HashMap<String, String> {
            val ret = HashMap<String, String>()
            return ret
        }
    }
}