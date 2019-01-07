package com.neversaydie.andreii.data.net

import com.google.gson.Gson
import com.neversaydie.andreii.data.entity.ErrorResponse
import com.neversaydie.andreii.domain.entity.AppErrorType
import com.neversaydie.andreii.domain.entity.AppException
import io.reactivex.ObservableTransformer
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RestErrorParser(val gson: Gson) {

    fun <T> parseError(): ObservableTransformer<T, T> {
        return ObservableTransformer { obsevable ->
            obsevable
                    .onErrorReturn { throwable ->
                        when (throwable) {
                            is HttpException -> {
                                val errorBody = throwable.response().errorBody()?.string()
                                if (errorBody == null) {
                                    throw AppException(AppErrorType.UNKNOWN)
                                }

                                try {
                                    val errorObject = gson.fromJson<ErrorResponse>(errorBody,
                                            ErrorResponse::class.java)

                                    when (errorObject.errorCode) {
                                        "1000" -> { //например ошибка неверный id
                                            throw AppException(AppErrorType.INCORRECT_ID)
                                        }
                                        "3000" -> {//например ошибка пользователеь заблокирован
                                            throw AppException(AppErrorType.BLOCKED)
                                        }
                                    }
                                } catch (e: Exception) {
                                    //лучше всего отправить разработчикам сообщение
                                }

                                throw AppException(AppErrorType.UNKNOWN)
                            }
                            is SocketTimeoutException -> {
                                throw AppException(AppErrorType.SERVER_IS_NOT_AVAILABLE)
                            }
                            else -> {
                                throw AppException(AppErrorType.UNKNOWN)
                            }
                        }
                    }
        }
    }
}