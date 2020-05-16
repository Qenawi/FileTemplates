class ${ViewModelName}ViewModel(app: Application, val useCase: ${UseCaseName}) :
    BaseViewModel< ${UseCaseName}>(app, useCase) {
    private val mLoading = MutableLiveData<Boolean>()
    val mResult = MutableLiveData<${Result}>()
    fun doWork() {
        val userData = getPref<LoginResponse>(ctx().getPrefs(), Keys.LoginModule)
        mLoading.value = true
    }

    private fun handle${ViewModelName}Sucess(data: ${Result}) {
        mLoading.value = false
        mResult.value = data
    }

    private fun handle${ViewModelName}Fail(fail: Failure) {
        mLoading.value = false
        toastMutable.postValue(fail)
    }
}