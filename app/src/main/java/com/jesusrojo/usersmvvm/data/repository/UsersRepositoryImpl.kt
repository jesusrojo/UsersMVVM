package com.jesusrojo.usersmvvm.data.repository

import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.model.UserRaw
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersCacheDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersLocalDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersRemoteDataSource
import com.jesusrojo.usersmvvm.data.model.mappers.MapperRawToEnty
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import com.jesusrojo.usersmvvm.utils.DebugHelp
import com.jesusrojo.usersmvvm.utils.Resource
import retrofit2.Response
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource,
    private val localDataSource: UsersLocalDataSource,
    private val cacheDataSource: UsersCacheDataSource,
    private val mapper: MapperRawToEnty
) : UsersRepository {

    override suspend fun fetchUsers(): Resource<List<User>> {
        DebugHelp.l("fetchUsers")
        return fetchFromCache()
    }

    private suspend fun fetchFromCache(): Resource<List<User>> {
        var results : List<User>? = null
        try {
            results = cacheDataSource.fetchDatasFromCache()
        } catch (exception: Exception) {
            DebugHelp.le(exception.message.toString())
        }
        return if (isNotNullIsNotEmpty(results)) {
            Resource.Success(results!!)
        } else {
            fetchFromDB()
        }
    }

    private suspend fun fetchFromDB(): Resource<List<User>> {
        DebugHelp.l("fetchFromDB")
        var results: List<User>? = null
        try {
            results = localDataSource.fetchAllInDB()
        } catch (exception: Exception) {
            DebugHelp.l(exception.message.toString())
        }

        return if (isNotNullIsNotEmpty(results)) {
            cacheDataSource.saveDatasToCache(results!!)
            Resource.Success(results)
        } else {
            fetchFromAPI()
        }
    }

    private suspend fun fetchFromAPI(): Resource<List<User>> {
        DebugHelp.l("fetchFromAPI")
        return try {
            val response = remoteDataSource.fetchUsers()
            handleResponse(response)
        } catch (exception: Exception) {
            val message = exception.message.toString()
            DebugHelp.l(message)
            Resource.Error(message)
        }
    }

    private suspend fun handleResponse(response: Response<List<UserRaw>>): Resource<List<User>>{
        var results: List<User>? = null
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val rawDatas: List<UserRaw> = body
                if (rawDatas != null) {
                    results = mapper(rawDatas)
                    localDataSource.saveAllToDB(results)
                    return Resource.Success(results)
                }
            }
        }
        return Resource.Error(response.errorBody().toString())
    }

    override suspend fun deleteAll() {
        DebugHelp.l("deleteAll")
        cacheDataSource.deleteAllInCache()
        localDataSource.deleteAllInDB()
    }

    override suspend fun deleteAllCache() {
        DebugHelp.l("deleteAllCache")
        cacheDataSource.deleteAllInCache()
    }

    private fun isNotNullIsNotEmpty(datas: List<User>?) =
        datas != null && datas.isNotEmpty()

//    override suspend fun fetchUsersFlow(): Flow<Result<List<User>>> =
//        remoteDataSource.fetchUsersFlow().map {
//            if (it.isSuccess) {
//                val datas = this.mapper(it.getOrNull()!!)
//                Result.success(datas)
//            } else
//                Result.failure(it.exceptionOrNull()!!)
//        }


////////////////FLOW ERRORkotlin.Result cannot be cast to java.util.List
//    override suspend fun fetchUsersFlow(): Flow<Result<List<User>>> =
//        remoteDataSource.fetchUsersFlow().map {
//            println(it)
//            //I/System.out: Success(Success(
//            // [UserRaw(userId=1, name=Leanne Graham, userName=Bret, email=Sincere@april.biz, phone=1-770-736-8031 x56442, website=hildegard.org, address=ADDRESS:
///////////////////////////////////
//              if (it.isSuccess) {
//                val datas = mapper(it.getOrNull()!!)
//                Result.success(datas)
////            } else
////                Result.failure(it.exceptionOrNull()!!)
///////////////////////////////////
//    BUIL.GRADLE MODULE PROJECT
//                ERROR WITH   ext.kotlin_version = "1.5.0"
//                OK WITH      ext.kotlin_version = "1.4.32"

//  search kotlin error with version 1.5.0 and not with version 1.4.32 java.lang.ClassCastException: kotlin.Result cannot be cast to java.util.List

//    http://5.9.10.113/67574632/result-nested-twice-when-collecting-a-flow-in-kotlin-1-5-kotlin-result-cannot-b

//        //https://youtrack.jetbrains.com/issue/KT-27105

////////////////////////
//    AndroidRuntime: FATAL EXCEPTION: main
//    Process: com.jesusrojo.usersclient, PID: 13981
//    java.lang.ClassCastException: kotlin.Result cannot be cast to java.util.List
//    at com.xxxxxxxx.usersclient.data.repository.UsersRepositoryImpl$fetchUsersFlow$$inlined$map$1$2.emit(Collect.kt:138)
//    at kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.emit(Collect.kt:136)
//    at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//    at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//    at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:77)
//    at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:59)
//    at com.jesusrojo.usersclient.data.repository.datasourceimpl.UsersRemoteDataSourceImpl$fetchUsersFlow$2.invokeSuspend(UsersRemoteDataSourceImpl.kt:28)
//    at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//    at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
//    at android.os.Handler.handleCallback(Handler.java:883)
//    at android.os.Handler.dispatchMessage(Handler.java:100)
//    at android.os.Looper.loop(Looper.java:237)
//    at android.app.ActivityThread.main(ActivityThread.java:7948)
//    at java.lang.reflect.Method.invoke(Native Method)
//    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
//    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1075)
//    I/Process: Sending signal. PID: 13981 SIG: 9
///////////////////////////////////////
}